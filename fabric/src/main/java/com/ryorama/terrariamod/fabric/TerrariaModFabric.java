package com.ryorama.terrariamod.fabric;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.stats.StatsT;
import com.ryorama.terrariamod.utils.WorldDataT;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import java.io.IOException;

public class TerrariaModFabric implements ModInitializer {

    public static boolean firstUpdate = false;

    @Override
    public void onInitialize() {
        TerrariaMod.init();
        StatsT.init();
        onTick();
        WorldDataT.setupWorldData();
        handleSaveData();
    }

    public void onTick() {
        ServerTickEvents.START_SERVER_TICK.register(world -> {
                PlayerEntity player = null;

                for (int p = 0; p < world.getPlayerManager().getPlayerList().size(); p++) {
                    player = world.getPlayerManager().getPlayerList().get(p);
                }

                if (player != null) {

                    if (!firstUpdate && !WorldDataT.hasStartingTools) {
                        if (TerrariaMod.CONFIG.modifyPlayerHealth) {
                            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
                            player.setHealth(100);
                        }

                        WorldDataT.hasStartingTools = true;
                        firstUpdate = true;
                    }

                    if (TerrariaMod.CONFIG.disableHunger) {
                        player.getHungerManager().setFoodLevel(20);
                    }
                }
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
