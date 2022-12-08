package com.ryorama.terrariamod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.OptionalLong;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.client.ParticleRegistry;
import com.ryorama.terrariamod.crafting.Recipes;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.fluid.HoneyFluid;
import com.ryorama.terrariamod.items.ItemsT;

import com.ryorama.terrariamod.ui.TerrariaUIRenderer;
import com.ryorama.terrariamod.world.EntitySpawner;
import com.ryorama.terrariamod.world.WorldDataT;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.IntProviderType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer {

	public static final String MODID = "terrariamod";
	
	private static final DimensionType MODIFIED_OVERWORLD = new DimensionType(OptionalLong.empty(), true, false, false, true, 1.0D, true, false,-256, 512, 512, BlockTags.INFINIBURN_OVERWORLD, DimensionTypes.OVERWORLD_ID, 0.0f, new DimensionType.MonsterSettings(false, true, UniformIntProvider.create(0, 7),0));

	public static FlowableFluid STILL_HONEY;
	public static FlowableFluid FLOWING_HONEY;
	public static Block HONEY;

	public static Item HONEY_BUCKET;

	public static boolean DEBUG = false;
	public static final Identifier MANA = new Identifier(MODID, "mana");
	public static final Identifier MAX_MANA = new Identifier(MODID, "max_mana");
	public static final Identifier ROYAL_GEL_EQ = new Identifier(MODID, "royal_gel_eq");

	//Buffs & Debuffs
	public static final Identifier IRON_SKIN = new Identifier(MODID, "iron_skin");
	public static final Identifier POTION_SICKNESS = new Identifier(MODID, "potion_sickness");
	public static final Identifier HAPPY = new Identifier(MODID, "happy");
	public static final Identifier COZY_FIRE = new Identifier(MODID, "cozy_fire");
	public static final Identifier REGENERATION = new Identifier(MODID, "regeneration");
	public static final Identifier POISONED = new Identifier(MODID, "poisoned");

	public static final Identifier BLEEDING = new Identifier(MODID, "bleeding");
	public static final Identifier WATER_CANDLE = new Identifier(MODID, "water_candle");

	public static TerrariaModConfig CONFIG = new TerrariaModConfig();

	public static ScreenHandlerType CRAFTING_TYPE;

	public static ServerWorld serverWorld;

	@Override
	public void onInitialize() {
		AutoConfig.register(TerrariaModConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(TerrariaModConfig.class).getConfig();
		ParticleRegistry.init();
		BlocksT.init();
		ItemsT.init();
		onTick();
		EntitiesT.init();
		Recipes.addAllRecipes();
		ModifyWorldHeight();
		GeckoLib.initialize();
		TAudio.registerAudio();
		TAudio.registerMusic();
		addCutouts();
		WorldDataT.setupWorldData();

		STILL_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "still_honey"), new HoneyFluid.Still());
		FLOWING_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "flowing_honey"), new HoneyFluid.Flowing());
		HONEY = Registry.register(Registry.BLOCK, new Identifier(MODID, "honey"), new FluidBlock(STILL_HONEY, FabricBlockSettings.copy(Blocks.WATER)){});

		HONEY_BUCKET = Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "honey_bucket"),
				new BucketItem(TerrariaMod.STILL_HONEY, new FabricItemSettings().group(ItemGroup.MISC).recipeRemainder(Items.BUCKET).maxCount(1)));


		Registry.register(Registry.CUSTOM_STAT, "mana", MANA);
		Stats.CUSTOM.getOrCreateStat(MANA, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "max_mana", MAX_MANA);
		Stats.CUSTOM.getOrCreateStat(MAX_MANA, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "royal_gel_eq", ROYAL_GEL_EQ);
		Stats.CUSTOM.getOrCreateStat(ROYAL_GEL_EQ, StatFormatter.DEFAULT);

		//Buffs
		Registry.register(Registry.CUSTOM_STAT, "iron_skin", IRON_SKIN);
		Stats.CUSTOM.getOrCreateStat(IRON_SKIN, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "happy", HAPPY);
		Stats.CUSTOM.getOrCreateStat(HAPPY, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "cozy_fire", COZY_FIRE);
		Stats.CUSTOM.getOrCreateStat(COZY_FIRE, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "regeneration", REGENERATION);
		Stats.CUSTOM.getOrCreateStat(REGENERATION, StatFormatter.DEFAULT);

		//DeBuffs
		Registry.register(Registry.CUSTOM_STAT, "potion_sickness", POTION_SICKNESS);
		Stats.CUSTOM.getOrCreateStat(POTION_SICKNESS, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "poisoned", POISONED);
		Stats.CUSTOM.getOrCreateStat(POISONED, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "bleeding", BLEEDING);
		Stats.CUSTOM.getOrCreateStat(BLEEDING, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "water_candle", WATER_CANDLE);
		Stats.CUSTOM.getOrCreateStat(WATER_CANDLE, StatFormatter.DEFAULT);
	}

	private static void ModifyWorldHeight() {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SPIKE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SPIKE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATER_CANDLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATER_CANDLE, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LARVA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LARVA, RenderLayer.getTranslucent());

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

	public void onTick() {

		TerrariaUIRenderer.renderTerrariaHealth();
		TerrariaUIRenderer.renderTerrariaDefense();
		TerrariaUIRenderer.renderTerrariaMana();
		TerrariaUIRenderer.renderTerrariaEffects();

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
							return ActionResult.PASS;
						}
						return ActionResult.PASS;
					}
					return ActionResult.PASS;
				}
				return ActionResult.PASS;
			} else {
				return ActionResult.SUCCESS;
			}
		});
         */

		ServerTickEvents.START_SERVER_TICK.register(callbacks -> {

			if (callbacks.getOverworld() != null) {

				World world = callbacks.getOverworld();

				serverWorld = callbacks.getOverworld();

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

				ServerPlayerEntity player = null;

				for (int p = 0; p < world.getPlayers().size(); p++) {
					player = (ServerPlayerEntity) world.getPlayers().get(p);
				}

				if (world.getPlayers().size() > 0) {
					PlayerEntity player2 = world.getPlayers().get(world.random.nextInt(world.getPlayers().size()));
					ServerPlayerEntity serverPlayerEntity = ((ServerPlayerEntity) player2);
					if (serverPlayerEntity.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.WATER_CANDLE)) <= 0) {
						if (world.getRandom().nextInt(700) <= 10) {
							double x = player2.getPos().x + world.random.nextInt(80) - 40, y = player2.getPos().y + world.random.nextInt(80) - 40, z = player2.getPos().z + world.random.nextInt(80) - 40;

							for (PlayerEntity p2 : world.getPlayers()) {
								if (p2.getPos().distanceTo(new Vec3d(x, y, z)) >= 5) {
									new Thread() {
										public void run() {
											EntitySpawner.spawnEntities(player2, x, y, z);
										}
									}.start();
									break;
								}
							}
						}
					} else {
						if (world.getRandom().nextInt(300) <= 10) {
							double x = player2.getPos().x + world.random.nextInt(80) - 40, y = player2.getPos().y + world.random.nextInt(80) - 40, z = player2.getPos().z + world.random.nextInt(80) - 40;

							for (PlayerEntity p2 : world.getPlayers()) {
								if (p2.getPos().distanceTo(new Vec3d(x, y, z)) >= 5) {
									new Thread() {
										public void run() {
											EntitySpawner.spawnEntities(player2, x, y, z);
										}
									}.start();
									break;
								}
							}
						}
					}
				}

                /*
                if (world.isNight()) {
                    if (world.getRandom().nextInt(100) <= Util.starChance) {
                        if (world.getPlayers().size() > 0) {
                            System.out.println("Fallen Star");
                            PlayerEntity player = world.getPlayers().get(world.getRandom().nextInt(world.getPlayers().size()));
                            double x = player.getX() + world.getRandom().nextInt(80) - 40, y = 255, z = player.getZ() + world.getRandom().nextInt(80) - 40;
                            ItemEntity item = new ItemEntity(world, x, y, z, ItemsT.FALLEN_STAR.getDefaultStack());
                            world.spawnEntity(item);

                            while (item.isAlive() && !item.isOnGround()) {
                                world.playSound(item.getX(), item.getY(), item.getZ(), TAudio.STAR_FALL, SoundCategory.NEUTRAL, 100f, 1f, false);
                            }
                        }
                    }
                }
                 */
			}
		});

		ServerWorldEvents.LOAD.register(((server, world) -> {
			try {
				WorldDataT.setupWorldData();
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
}
