package com.ryorama.terrariamod.core.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.ryorama.terrariamod.core.network.client.CPacketAddScore;
import com.ryorama.terrariamod.core.network.client.CPacketChangeScore;
import com.ryorama.terrariamod.core.network.client.CPacketCloseInventoryTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketEquipItemTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketOpenInventoryTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketRequestInventoryTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketSyncInventoryTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketThrowItemTerraria;
import com.ryorama.terrariamod.core.network.server.SPacketSyncInventoryTerraria;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
	
	private static final String PTC_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation("trewrite", "main"))
            .networkProtocolVersion(() -> PTC_VERSION)
            .clientAcceptedVersions(PTC_VERSION::equals)
            .serverAcceptedVersions(PTC_VERSION::equals)
            .simpleChannel();
	
	private static int id = 0;
	
	public static void register() {
		
		registerMessage(CPacketSyncInventoryTerraria.class, CPacketSyncInventoryTerraria::encode, CPacketSyncInventoryTerraria::new, CPacketSyncInventoryTerraria::handle);
		registerMessage(CPacketThrowItemTerraria.class, CPacketThrowItemTerraria::encode, CPacketThrowItemTerraria::new, CPacketThrowItemTerraria::handle);
		registerMessage(CPacketOpenInventoryTerraria.class, CPacketOpenInventoryTerraria::encode, CPacketOpenInventoryTerraria::new, CPacketOpenInventoryTerraria::handle);
		registerMessage(CPacketAddScore.class, CPacketAddScore::encode, CPacketAddScore::new, CPacketAddScore::handle);
        registerMessage(CPacketChangeScore.class, CPacketChangeScore::encode, CPacketChangeScore::new, CPacketChangeScore::handle);  
        registerMessage(CPacketCloseInventoryTerraria.class, CPacketCloseInventoryTerraria::encode, CPacketCloseInventoryTerraria::new, CPacketCloseInventoryTerraria::handle);
        registerMessage(CPacketEquipItemTerraria.class, CPacketEquipItemTerraria::encode, CPacketEquipItemTerraria::new, CPacketEquipItemTerraria::handle);
        registerMessage(CPacketRequestInventoryTerraria.class, CPacketRequestInventoryTerraria::encode, CPacketRequestInventoryTerraria::decode, CPacketRequestInventoryTerraria::handle);
        
		registerMessage(SPacketSyncInventoryTerraria.class, SPacketSyncInventoryTerraria::encode, SPacketSyncInventoryTerraria::decode, SPacketSyncInventoryTerraria::handle);
		
	}
	
	private static <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
		 INSTANCE.registerMessage(id++, messageType, encoder, decoder, messageConsumer);
	}

}
