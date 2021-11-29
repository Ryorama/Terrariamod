package com.ryorama.terrariamod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.OptionalLong;
import java.util.function.Function;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlockT;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.blocks.TreeSegment;
import com.ryorama.terrariamod.client.CelestialManager;
import com.ryorama.terrariamod.client.TMusicTicker;
import com.ryorama.terrariamod.client.fx.ShadersManager;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;
import com.ryorama.terrariamod.fluid.HoneyFluid;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.api.ItemT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.TerrariaUIRenderer;
import com.ryorama.terrariamod.weather.WeatherBase;
import com.ryorama.terrariamod.world.EntitySpawner;

import com.ryorama.terrariamod.world.WorldDataT;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
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
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer, ClientModInitializer {

	public static final String MODID = "terrariamod";
	
	private static final DimensionType MODIFIED_OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -256, 256, 256, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(), DimensionType.OVERWORLD_ID, 0.0F);

	public static FlowableFluid STILL_HONEY;
	public static FlowableFluid FLOWING_HONEY;
	public static Item HONEY_BUCKET;
	public static Block HONEY;

	public static final Identifier MANA = new Identifier(MODID, "mana");
	public static final Identifier MAX_MANA = new Identifier(MODID, "max_mana");

	public static boolean spawningEye = false;
	public static boolean spawningDestroyer = false;
	public static boolean oncePerDay = false;
	public static boolean meteoriteAttempt = false;

	@Override
	public void onInitialize() {
		BiomeRegistry.RegisterBiomes();
		BlocksT.init();
		ItemsT.init();
		
		ModifyWorldHeight();
		GeckoLib.initialize();

		Registry.register(Registry.CUSTOM_STAT, "mana", MANA);
		Stats.CUSTOM.getOrCreateStat(MANA, StatFormatter.DEFAULT);

		Registry.register(Registry.CUSTOM_STAT, "max_mana", MAX_MANA);
		Stats.CUSTOM.getOrCreateStat(MAX_MANA, StatFormatter.DEFAULT);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		EntitiesT.init();
		ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
		onTick();
		addCutouts();

		STILL_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "still_honey"), new HoneyFluid.Still());
		FLOWING_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "flowing_honey"), new HoneyFluid.Flowing());
		HONEY_BUCKET = Registry.register(Registry.ITEM, new Identifier(MODID, "honey_bucket"),
				new BucketItem(STILL_HONEY, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		HONEY = Registry.register(Registry.BLOCK, new Identifier(MODID, "honey"), new FluidBlock(STILL_HONEY, FabricBlockSettings.copy(Blocks.WATER)){});

		setupFluidRendering(TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY, new Identifier(MODID, "honey"), 0xFFFFFF);
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY);

		ShadersManager.registerShaders();

	}

	@Environment(EnvType.CLIENT)
	public static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
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
		//BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderLayer.getCutout());
		//BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VINE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LIFE_CRYSTAL_BLOCK, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE, RenderLayer.getCutout());
	}
	
 
	public void onTick() {
		
		TerrariaUIRenderer.renderTerrariaHealth();
		TerrariaUIRenderer.renderTerrariaDefense();
		TerrariaUIRenderer.renderTerrariaMana();

		TMusicTicker.musicChanged = true;
		ClientTickEvents.START_CLIENT_TICK.register(client -> {TMusicTicker.onTickUpdate();});

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if (state.getBlock() instanceof TreeSegment) {
				for (int i = 0; i <= 10; i++) {
					if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())).getBlock() instanceof TreeSegment) {
						world.setBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), Blocks.AIR.getDefaultState());
					}
				}
			}
		});

		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			if (!player.isCreative()) {
				if (world.getBlockState(pos).getBlock() instanceof BlockT) {
					BlockT block = (BlockT)world.getBlockState(pos).getBlock();
									
					if (player.getInventory().getMainHandStack().getItem() instanceof ItemT) {
						ItemT item = (ItemT)player.getInventory().getMainHandStack().getItem();
						if (block.difficulty > 0) {
							if (block.pick && item.pick >= block.difficulty) {
				                return ActionResult.PASS;
							}
							if (block.axe && item.axe >= block.difficulty) {
				                return ActionResult.PASS;
							}
							if (block.hammer && item.hammer >= block.difficulty) {
				                return ActionResult.PASS;
							}
							return ActionResult.FAIL;
						}
						return ActionResult.FAIL;
					}
					return ActionResult.FAIL;
				}
				return ActionResult.FAIL;
			} else {
				return ActionResult.PASS;
			}
		});

		WorldTickCallback.EVENT.register(world -> {
			
			WeatherBase.tickWeather();
			CelestialManager.handleMoon(world);
			CelestialManager.handleSolarEvents(world);

			for (int i = 0; i < world.getPlayers().size(); i++) {
				if (world.getPlayers().get(i).getMaxHealth() >= 200 && world.getPlayers().get(i).getAttributeBaseValue(EntityAttributes.GENERIC_ARMOR) >= 2) {
					if (!world.isClient) {
						if (world.getTimeOfDay() % 24000 >= 11000) {
							if (oncePerDay == false) {
								oncePerDay = true;
								if (world.getRandom().nextInt(10) == 0) {
									spawningEye = true;
									world.getServer().getPlayerManager().getPlayerList().get(i).sendMessage(new TranslatableText("You feel an evil presence watching you.").formatted(Formatting.BOLD).formatted(Formatting.GREEN), false);
								}
							}
						}
					}
				}
			}

			if (!world.isClient) {
				if (world.getTimeOfDay() % 24000 <= 1000) {
					oncePerDay = false;
				}
				if (spawningEye == true && world.getTimeOfDay() % 24000 > 17500) {
					if (world.getPlayers().size() > 0) {
						PlayerEntity player = world.getPlayers().get(world.getRandom().nextInt(world.getPlayers().size()));
						float posX = 0, posY = world.getRandom().nextInt(20) - 10, posZ = 0;
						float rad = 20;

						float rotation = world.getRandom().nextInt(360);
						posX = (float) (Math.cos(Math.toDegrees(rotation)) * rad);
						posZ = (float) (Math.sin(Math.toDegrees(rotation)) * rad);

						EntityEyeOfCthulhu eye = EntitiesT.EOC.create(world);
						eye.setPosition(player.getPos().getX() + posX, player.getPos().getY() + posY, player.getPos().getZ() + posZ);
						world.spawnEntity(eye);
						spawningEye = false;
					}
				}
			}


			if (world.getRandom().nextInt(700) <= 10)
			if (world.getPlayers().size() > 0) {
				PlayerEntity player = world.getPlayers().get(world.random.nextInt(world.getPlayers().size()));
				double x = player.getPos().x + world.random.nextInt(80) - 40, y = player.getPos().y + world.random.nextInt(80) - 40, z = player.getPos().z + world.random.nextInt(80) - 40;

				for (PlayerEntity p2 : world.getPlayers()) {
						if (p2.getPos().distanceTo(new Vec3d(x, y, z)) >= 5) {
							new Thread () {
								public void run() {
									EntitySpawner.spawnEntities(player, x, y, z);
								}
							}.start();
							
							break;
						}
					}
				}
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
		//OVERWORLD: 14
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
		
		/*
		 * OVERWORLD = create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -64,
				384, 384, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(),
				OVERWORLD_ID, 0.0F);
		 */
	}

	public static EntityAttribute registerNewAttribute(String id, EntityAttribute attribute) {
		return Registry.register(Registry.ATTRIBUTE, id, attribute);
	}
}
