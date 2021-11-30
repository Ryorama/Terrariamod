package com.ryorama.terrariamod.core.network;

import com.ryorama.terrariamod.TerrariaMod;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class NetworkHandler {

    public static Identifier openInventoryPacketId = new Identifier(TerrariaMod.MODID, "openInventory");

    public static void registerPackets() {
        ClientPlayNetworking.registerGlobalReceiver(openInventoryPacketId, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                ClientPlayerEntity sender =  client.player;

                
            });
        });
    }
}
