package com.ryorama.terrariamod.fabric;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.stats.StatsT;
import com.ryorama.terrariamod.utils.WorldDataT;
import com.ryorama.terrariamod.world.EntitySpawner;
import com.ryorama.terrariamod.world.WorldEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.io.IOException;
import java.util.Random;

public class TerrariaModFabric implements ModInitializer {

    public Random rand = new Random();

    @Override
    public void onInitialize() {
        TerrariaMod.init();
        StatsT.init();
        onTick();
        handleSaveData();
        registerEntityAttributes();
        WorldDataT.setupWorldData();
    }

    public void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EntitiesT.GREEN_SLIME.get(), MobEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(EntitiesT.BLUE_SLIME.get(), MobEntity.createMobAttributes());
    }

    public void onTick() {
        ServerTickEvents.START_SERVER_TICK.register(world -> {
            PlayerEntity player = null;

            for (int p = 0; p < world.getPlayerManager().getPlayerList().size(); p++) {
                player = world.getPlayerManager().getPlayerList().get(p);
            }

            if (player != null) {
                if (WorldDataT.firstUpdate && !WorldDataT.hasStartingTools) {
                    if (TerrariaMod.CONFIG.modifyPlayerHealth) {
                        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
                        player.setHealth(100);
                    }

                    WorldDataT.hasStartingTools = true;
                    WorldDataT.firstUpdate = false;
                }

                if (TerrariaMod.CONFIG.disableHunger) {
                    player.getHungerManager().setFoodLevel(20);
                }
            }

           /* if (world.getOverworld().isNight()) {
                if (rand.nextInt(1000) == 0) {
                    WorldEvents.spawnFallenStar(world.getOverworld(), player);
                }
            }

            if (world.getOverworld().getPlayers().size() > 0) {
                PlayerEntity player2 = world.getOverworld().getPlayers().get(world.getOverworld().getRandom().nextInt(world.getOverworld().getPlayers().size()));
                ServerPlayerEntity serverPlayerEntity = ((ServerPlayerEntity) player2);
                if (world.getOverworld().getRandom().nextInt(700) <= 10) {
                    int x = (int) (player2.getPos().x + world.getOverworld().getRandom().nextInt(80) - 40), y = (int) (player2.getPos().y + world.getOverworld().getRandom().nextInt(80) - 40), z = (int) (player2.getPos().z + world.getOverworld().getRandom().nextInt(80) - 40);

                    for (PlayerEntity p2 : world.getOverworld().getPlayers()) {
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
            }*/
        });
    }

    public void handleSaveData() {
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
