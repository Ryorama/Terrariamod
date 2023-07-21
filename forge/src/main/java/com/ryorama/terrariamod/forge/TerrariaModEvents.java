package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.forge.network.GameRulesT;
import com.ryorama.terrariamod.forge.network.client.ui.TerraruaUIRenderer;
import com.ryorama.terrariamod.utils.WorldDataT;
import com.ryorama.terrariamod.world.EntitySpawner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;

public class TerrariaModEvents {
    public int tmpMana = 20;
    public int tmpMaxMana = 20;

    @SubscribeEvent
    public static void WorldTickEvent(TickEvent.LevelTickEvent event) {
        PlayerEntity player = null;
        World world = event.level;

        for (int p = 0; p < world.getPlayers().size(); p++) {
            player = world.getPlayers().get(p);
        }

        if (player != null) {
            if (WorldDataT.firstUpdate && !WorldDataT.hasStartingTools) {
                if (TerrariaMod.CONFIG.modifyPlayerHealth) {
                    player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
                    player.setHealth(20);
                }

                WorldDataT.hasStartingTools = true;
                WorldDataT.firstUpdate = false;
            }

            if (TerrariaMod.CONFIG.disableHunger) {
                player.getHungerManager().setFoodLevel(20);
            }
        }

        if (world.getPlayers().size() > 0) {
            PlayerEntity player2 = world.getPlayers().get(world.random.nextInt(world.getPlayers().size()));
            ServerPlayerEntity serverPlayerEntity = ((ServerPlayerEntity) player2);
            if (world.getRandom().nextInt(700) <= 10) {
                int x = (int) (player2.getPos().x + world.random.nextInt(80) - 40), y = (int) (player2.getPos().y + world.random.nextInt(80) - 40), z = (int) (player2.getPos().z + world.random.nextInt(80) - 40);

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

    @SubscribeEvent
    public static void RegisterEntityAttributesEvent(EntityAttributeCreationEvent event) {
        event.put(EntitiesT.GREEN_SLIME.get(), MobEntity.createMobAttributes().build());
        event.put(EntitiesT.BLUE_SLIME.get(), MobEntity.createMobAttributes().build());
    }

    @SubscribeEvent
    public static void WorldLoadEvent(LevelEvent.Load event) {
        if (event.getLevel().getServer() != null) {
            try {
                WorldDataT.setupWorldData();
                WorldDataT.loadData(event.getLevel());
            } catch (IOException e) {
                e.printStackTrace();
            }

            WorldDataT.setupWorldData();
        }
    }
    @SubscribeEvent
    public static void WorldUnloadEvent(LevelEvent.Save event) {
        try {
            WorldDataT.saveData(event.getLevel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    @OnlyIn(value= Dist.CLIENT)
    public static void renderGuiOverlayEvent(RenderGuiOverlayEvent.Pre event) {
        TerraruaUIRenderer.renderTerrariaHealth();
        TerraruaUIRenderer.renderTerrariaEffects();
        TerraruaUIRenderer.renderTerrariaMana();
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public static void ClientWorldTickEvent(TickEvent.LevelTickEvent event) {
        World world = event.level;
        if (world.isClient) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            if (player != null) {
                if (WorldDataT.firstUpdate) {
                    GameRulesT.MAX_MANA.set(100);
                    GameRulesT.MANA.set(GameRulesT.MAX_MANA.get());
                }

                if (GameRulesT.POTION_SICKNESS.get() > 0) {
                    GameRulesT.POTION_SICKNESS.set(GameRulesT.POTION_SICKNESS.get() - 1);
                }

                if (GameRulesT.MANA.get() < GameRulesT.MAX_MANA.get()) {
                    GameRulesT.MANA.set(GameRulesT.MANA.get() + 1);
                }
            }
        }
    }
}
