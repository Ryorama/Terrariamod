package com.ryorama.terrariamod;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.client.CelestialManager;
import com.ryorama.terrariamod.core.client.ParticleRegistry;
import com.ryorama.terrariamod.core.client.TMusicTicker;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.gui.crafting.CraftingGuiDescription;
import com.ryorama.terrariamod.gui.crafting.CraftingGuiScreen;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.TerrariaUIRenderer;
import com.ryorama.terrariamod.utils.Util;
import com.ryorama.terrariamod.weather.WeatherBase;
import com.ryorama.terrariamod.world.EntitySpawner;
import com.ryorama.terrariamod.world.WorldDataT;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.function.Function;

public class TerrariaModClient implements ClientModInitializer {

    public boolean firstUpdate = false;
    public boolean ironSkinJustActivated = false;

    public int tmpMana = 20;
    public int tmpMaxMana = 20;

    public static boolean DEBUG = true;

    private static KeyBinding openCraftingScreen;

    public TerrariaModClient() {

    }

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {
        EntitiesT.initClient();
        ParticleRegistry.initClient();
        HandledScreens.<CraftingGuiDescription, CraftingGuiScreen>register(TerrariaMod.CRAFTING_TYPE, (gui, inventory, title) -> new CraftingGuiScreen(gui, inventory.player, title));
        ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
        onTick();
        addCutouts();
        setupFluidRendering(TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY, new Identifier(TerrariaMod.MODID, "honey"), 0xFFFFFF);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY);
        onTickClient();

        openCraftingScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding("com.ryorama.terrariamod", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.terrariamod.crafting"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openCraftingScreen.wasPressed()) {
                //client.player.openHandledScreen()
            }
        });

        try {
            Field musicTicker = MinecraftClient.class.getDeclaredField(DEBUG ? "musicTracker" : "field_1714");
            musicTicker.setAccessible(true);
            musicTicker.set(MinecraftClient.getInstance(), new TMusicTicker(MinecraftClient.getInstance()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
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
        ClientTickEvents.START_CLIENT_TICK.register(callbacks -> {

            if (callbacks.world != null) {

                World world = callbacks.world;

                if (world.isClient()) {
                    int ticks = 0;

                    ClientPlayerEntity player = null;

                    for (int p = 0; p < world.getPlayers().size(); p++) {
                        player = (ClientPlayerEntity) world.getPlayers().get(p);
                    }

                    if (player != null) {

                        if (!firstUpdate && !WorldDataT.hasStartingTools) {
                            if (TerrariaMod.CONFIG.modifyPlayerHealth) {
                                player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
                                player.setHealth(100);
                            }

                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), tmpMana);
                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA), tmpMaxMana);
                            WorldDataT.hasStartingTools = true;
                            firstUpdate = true;
                        }

                        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) > 0) {
                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) - 1);

                            if (!ironSkinJustActivated) {
                                player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).getValue() + 8);
                                ironSkinJustActivated = true;
                            }
                        } else {
                            if (ironSkinJustActivated) {
                                player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).getValue() - 8);
                                ironSkinJustActivated = false;
                            }
                        }

                        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) > 0) {
                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) - 1);
                        }

                        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE)) > 0) {
                            if (ticks % 100 == 0) {
                                player.setHealth(player.getHealth() + 2);
                            }

                            if (ticks % 20 == 0) {
                                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE)) - 150);
                            }
                        }

                        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.REGENERATION)) > 0) {
                            if (ticks % 80 == 0) {
                                player.setHealth(player.getHealth() + 2);
                            }
                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.REGENERATION), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.REGENERATION)) - 1);
                        }

                        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POISONED)) > 0) {
                            if (ticks % 140 == 0) {
                                player.setHealth(player.getHealth() - 2);
                            }
                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.POISONED), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POISONED)) - 1);
                        }

                        if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) < player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA))) {
                            player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) + 1);
                        }

                        if (TerrariaMod.CONFIG.disableHunger) {
                            player.getHungerManager().setFoodLevel(20);
                        }
                    }

                    ticks++;
                }
            }
        });
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

                WeatherBase.tickWeather();
                CelestialManager.handleMoon(world);
                CelestialManager.handleSolarEvents(world);

            /*
			if (world.isClient()) {
				if (WorldDataT.bloodMoon) {
					ModifyWorldColor.changeWorldColor("FF0000", 1, "FF0000", 0.4f); //Fix to be (double) 0.4
				} else {
					ModifyWorldColor.resetToDefaultColor();
				}
			}
			*/


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
}
