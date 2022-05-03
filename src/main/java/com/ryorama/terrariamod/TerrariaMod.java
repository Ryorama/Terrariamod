package com.ryorama.terrariamod;

import java.io.IOException;
import java.util.function.Function;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.client.ParticleRegistry;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.fluid.HoneyFluid;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.TerrariaUIRenderer;

import com.ryorama.terrariamod.world.WorldDataT;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer, ClientModInitializer {

	public static final String MODID = "terrariamod";
	
	//private static final DimensionType MODIFIED_OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -256, 512, 512, BlockTags.INFINIBURN_OVERWORLD, DimensionType.OVERWORLD_ID, 0.0F);

	public static FlowableFluid STILL_HONEY;
	public static FlowableFluid FLOWING_HONEY;
	public static Block HONEY;

	public static boolean DEBUG = false;

	public boolean firstUpdate = false;
	public boolean ironSkinJustActivated = false;
	public int tmpMana = 20;
	public int tmpMaxMana = 20;


	public static final Identifier MANA = new Identifier(MODID, "mana");
	public static final Identifier MAX_MANA = new Identifier(MODID, "max_mana");
	public static final Identifier IRON_SKIN = new Identifier(MODID, "iron_skin");
	public static final Identifier POTION_SICKNESS = new Identifier(MODID, "potion_sickness");
	public static final Identifier HAPPY = new Identifier(MODID, "happy");
	public static final Identifier COZY_FIRE = new Identifier(MODID, "cozy_fire");

	@Override
	public void onInitialize() {
		BiomeRegistry.RegisterBiomes();
		ParticleRegistry.init();
		BlocksT.init();
		ItemsT.init();
		
		ModifyWorldHeight();
		GeckoLib.initialize();
		TAudio.registerAudio();

		STILL_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "still_honey"), new HoneyFluid.Still());
		FLOWING_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "flowing_honey"), new HoneyFluid.Flowing());
		HONEY = Registry.register(Registry.BLOCK, new Identifier(MODID, "honey"), new FluidBlock(STILL_HONEY, FabricBlockSettings.copy(Blocks.WATER)){});

		Registry.register(Registry.CUSTOM_STAT, "mana", MANA);
		Stats.CUSTOM.getOrCreateStat(MANA, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "max_mana", MAX_MANA);
		Stats.CUSTOM.getOrCreateStat(MAX_MANA, StatFormatter.DEFAULT);

		//Buffs
		Registry.register(Registry.CUSTOM_STAT, "iron_skin", IRON_SKIN);
		Stats.CUSTOM.getOrCreateStat(IRON_SKIN, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "happy", HAPPY);
		Stats.CUSTOM.getOrCreateStat(HAPPY, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "cozy_fire", COZY_FIRE);
		Stats.CUSTOM.getOrCreateStat(COZY_FIRE, StatFormatter.DEFAULT);

		//DeBuffs
		Registry.register(Registry.CUSTOM_STAT, "potion_sickness", POTION_SICKNESS);
		Stats.CUSTOM.getOrCreateStat(POTION_SICKNESS, StatFormatter.DEFAULT);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		EntitiesT.init();
		ParticleRegistry.initClient();
		ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
		onTick();
		addCutouts();
		setupFluidRendering(TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY, new Identifier(MODID, "honey"), 0xFFFFFF);
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY);
		onTickClient();

		/*
		try {
			Field f = Resources.findField(MinecraftClient.class, "musicTracker", "field_1714");
			f.set(MinecraftClient.getInstance(), new TMusicTicker(MinecraftClient.getInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

	public static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color)	 {
		final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
		final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");

		// If they're not already present, add the sprites to the block atlas
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(stillSpriteId);
			registry.register(flowingSpriteId);
		});

		final Identifier fluidId = Registry.FLUID.getId(still);
		final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");

		final Sprite[] fluidSprites = { null, null };

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return listenerId;
			}

			/**
			 * Get the sprites from the block atlas when resources are reloaded
			 */
			@Override
			public void reload(ResourceManager resourceManager) {
				final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
				fluidSprites[0] = atlas.apply(stillSpriteId);
				fluidSprites[1] = atlas.apply(flowingSpriteId);
			}
		});

		// The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
		final FluidRenderHandler renderHandler = new FluidRenderHandler()
		{
			@Override
			public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
				return fluidSprites;
			}

			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return color;
			}
		};

		FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
	}

	public void addCutouts() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_POT, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_POT, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLINKROOT, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DAYBLOOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEATHWEED, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FIREBLOSSOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MOONGLOW, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SHIVERTHORN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATERLEAF, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GLOWING_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VINE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LIFE_CRYSTAL_BLOCK, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE, RenderLayer.getCutout());
	}

	@Environment(EnvType.CLIENT)
	public void onTickClient() {
		WorldTickCallback.EVENT.register(world -> {

			int ticks = 0;

			if (MinecraftClient.getInstance().player != null) {

				if (!firstUpdate && WorldDataT.hasStartingTools) {
					MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), tmpMana);
					MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA), tmpMaxMana);
					firstUpdate = true;
				}

				if (MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) > 0) {
					MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN), MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) -1);

					if (!ironSkinJustActivated) {
						MinecraftClient.getInstance().player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(MinecraftClient.getInstance().player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).getValue() + 8);
						ironSkinJustActivated = true;
					}
				} else {
					if (ironSkinJustActivated) {
						MinecraftClient.getInstance().player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(MinecraftClient.getInstance().player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).getValue() - 8);
						ironSkinJustActivated = false;
					}
				}

				if (MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) > 0) {
					MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS), MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) -1);
				}

				if (MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE)) > 0) {
					if (ticks % 100 == 0) {
						MinecraftClient.getInstance().player.setHealth(MinecraftClient.getInstance().player.getHealth() + 2);
					}
				}

				if (MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) < MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA))) {
					MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) + 1);
				}

				//MinecraftClient.getInstance().player.getHungerManager().setFoodLevel(10);
			}

			ticks++;

		});
	}
 
	public void onTick() {
		
		//TerrariaUIRenderer.renderTerrariaHealth();
		//TerrariaUIRenderer.renderTerrariaDefense();
		//TerrariaUIRenderer.renderTerrariaMana();
		TerrariaUIRenderer.renderTerrariaEffects();


		//TMusicTicker.musicChanged = true;
		//ClientTickEvents.START_CLIENT_TICK.register(client -> {TMusicTicker.onTickUpdate();});

		/*
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			if (!player.isCreative()) {
				if (world.getBlockState(pos).getBlock() instanceof BlockT) {
					BlockT block = (BlockT)world.getBlockState(pos).getBlock();
									
					if (player.getInventory().getMainHandStack().getItem() instanceof ItemT) {
						ItemT item = (ItemT)player.getInventory().getMainHandStack().getItem();
						if (block.difficulty > 0) {
							if (block.pick && item.pick >= block.difficulty) {
				                return ActionResult.SUCCESS;
							}
							if (block.axe && item.axe >= block.difficulty) {
				                return ActionResult.SUCCESS;
							}
							if (block.hammer && item.hammer >= block.difficulty) {
				                return ActionResult.SUCCESS;
							}
							return ActionResult.FAIL;
						}
						return ActionResult.FAIL;
					}
					return ActionResult.FAIL;
				}
				return ActionResult.FAIL;
			} else {
				return ActionResult.SUCCESS;
			}
		});
		*/
		
		WorldTickCallback.EVENT.register(world -> {

			//WeatherBase.tickWeather();
			//CelestialManager.handleMoon(world);
			//CelestialManager.handleSolarEvents(world);

			/*
			if (world.isClient()) {
				if (WorldDataT.bloodMoon) {
					ModifyWorldColor.changeWorldColor("FF0000", 1, "FF0000", 0.4f); //Fix to be (double) 0.4
				} else {
					ModifyWorldColor.resetToDefaultColor();
				}
			}
			*/

			/*
			if (world.getRandom().nextInt(700) <= 10) {
				if (world.getPlayers().size() > 0) {
					PlayerEntity player = world.getPlayers().get(world.random.nextInt(world.getPlayers().size()));
					double x = player.getPos().x + world.random.nextInt(80) - 40, y = player.getPos().y + world.random.nextInt(80) - 40, z = player.getPos().z + world.random.nextInt(80) - 40;

					for (PlayerEntity p2 : world.getPlayers()) {
						if (p2.getPos().distanceTo(new Vec3d(x, y, z)) >= 5) {
							new Thread() {
								public void run() {
									EntitySpawner.spawnEntities(player, x, y, z);
								}
							}.start();
							break;
						}
					}
				}
			}

			if (world.isNight()) {
				if (world.getRandom().nextDouble(100) <= Util.starChance) {
					if (world.getPlayers().size() > 0) {
						System.out.println("Fallen Star");
						PlayerEntity player = world.getPlayers().get(world.getRandom().nextInt(world.getPlayers().size()));
						double x = player.getX() + world.getRandom().nextInt(80) - 40, y = 255, z = player.getZ() + world.getRandom().nextInt(80) - 40;
						ItemEntity item = new ItemEntity(world, x, y, z, ItemsT.FALLEN_STAR.getDefaultStack());
						world.spawnEntity(item);

						while (item.isAlive() && !item.isOnGround()) {
							world.playSound(item.getX(), item.getY(), item.getZ(), TAudio.STAR_FALL, SoundCategory.AMBIENT, 100f, 1f, false);
						}
					}
				}
			}
			*/
		});

		ServerWorldEvents.LOAD.register(((server, world) -> {
			try {
				WorldDataT.loadData(world);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));

		ServerWorldEvents.UNLOAD.register(((server, world) -> {
			try {
				WorldDataT.saveData(world);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));
	}
	
	private static void ModifyWorldHeight() {
		/*
		Field[] dimension_fields = DimensionType.class.getDeclaredFields();
		for (int i = 0; i < dimension_fields.length; i++) {
			try {
				Resources.makeFieldAccessible(dimension_fields[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(dimension_fields[i].getName() + ", " + i);
		}
		int overworld_num = 14;
		Field overworld_field = dimension_fields[overworld_num];
		
		try {
			Resources.makeFieldAccessible(overworld_field);
			overworld_field.set(null, MODIFIED_OVERWORLD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}	
}
