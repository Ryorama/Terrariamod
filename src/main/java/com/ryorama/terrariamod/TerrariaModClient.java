package com.ryorama.terrariamod;

import com.ryorama.terrariamod.core.client.CelestialManager;
import com.ryorama.terrariamod.core.client.ParticleRegistry;
import com.ryorama.terrariamod.core.client.TMusicTicker;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
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
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;
import java.util.function.Function;

public class TerrariaModClient implements ClientModInitializer {

    public boolean ironSkinJustActivated = false;

    public static boolean DEBUG = true;

    private static KeyBinding openCraftingScreen;

    public TerrariaModClient() {

    }

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {
        EntitiesT.initClient();
        ParticleRegistry.initClient();
        ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
        setupFluidRendering(TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY, new Identifier(TerrariaMod.MODID, "honey"), 0xFFFFFF);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), TerrariaMod.STILL_HONEY, TerrariaMod.FLOWING_HONEY);
        onTickClient();

        openCraftingScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding("com.ryorama.terrariamod", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.terrariamod.crafting"));

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

    @Environment(EnvType.CLIENT)
    public void onTickClient() {

        ClientTickEvents.START_CLIENT_TICK.register(callbacks -> {

            if (callbacks.world != null) {

                World world = callbacks.world;
                CelestialManager.tick(world);

                if (world.isClient()) {
                    int ticks = 0;

                    ClientPlayerEntity playerClient = null;
                    PlayerEntity player = null;

                    for (int p = 0; p < world.getPlayers().size(); p++) {
                        player = world.getPlayers().get(p);
                        playerClient = (ClientPlayerEntity) world.getPlayers().get(p);
                    }

                    if (playerClient != null) {
                        if (WorldDataT.firstUpdate && !WorldDataT.hasStartingTools) {
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), 20);
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA), 20);

                            if (TerrariaMod.CONFIG.modifyPlayerHealth) {
                                player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
                                player.heal(100);
                            }

                            WorldDataT.hasStartingTools = true;
                            WorldDataT.firstUpdate = false;
                        }

                        if (playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) > 0) {
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN), playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) - 1);

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

                        if (playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) > 0) {
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS), playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) - 1);
                        }

                        if (playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE)) > 0) {
                            if (ticks % 100 == 0) {
                                player.heal(player.getHealth() + 2);
                            }

                            if (ticks % 20 == 0) {
                                playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE), playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE)) - 150);
                            }
                        }

                        if (playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.REGENERATION)) > 0) {
                            if (ticks % 80 == 0) {
                                player.heal(player.getHealth() + 2);
                            }
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.REGENERATION), playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.REGENERATION)) - 1);
                        }

                        if (playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POISONED)) > 0) {
                            if (ticks % 140 == 0) {
                                player.damage(DamageSource.GENERIC, player.getHealth() - 2);
                            }
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.POISONED), playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POISONED)) - 1);
                        }

                        if (playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) < playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA))) {
                            playerClient.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), playerClient.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) + 1);
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
}
