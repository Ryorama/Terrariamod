package kmerrill285.trewrite.core.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import kmerrill285.stackeddimensions.networking.SPacketForceMovement;
import kmerrill285.stackeddimensions.networking.SPacketRefreshDimensionRenderer;
import kmerrill285.trewrite.core.network.client.CPacketAddScore;
import kmerrill285.trewrite.core.network.client.CPacketChangeScore;
import kmerrill285.trewrite.core.network.client.CPacketCloseInventoryTerraria;
import kmerrill285.trewrite.core.network.client.CPacketEquipItemTerraria;
import kmerrill285.trewrite.core.network.client.CPacketHeal;
import kmerrill285.trewrite.core.network.client.CPacketNegateFall;
import kmerrill285.trewrite.core.network.client.CPacketOpenChestTerraria;
import kmerrill285.trewrite.core.network.client.CPacketOpenInventoryTerraria;
import kmerrill285.trewrite.core.network.client.CPacketOpenInventoryVanilla;
import kmerrill285.trewrite.core.network.client.CPacketRemoveSummons;
import kmerrill285.trewrite.core.network.client.CPacketRequestInventoryChest;
import kmerrill285.trewrite.core.network.client.CPacketRequestInventoryTerraria;
import kmerrill285.trewrite.core.network.client.CPacketSaveChestTerraria;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryChest;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryTerraria;
import kmerrill285.trewrite.core.network.client.CPacketThrowItemTerraria;
import kmerrill285.trewrite.core.network.server.SPacketSendAccessories;
import kmerrill285.trewrite.core.network.server.SPacketSendInventoryTerraria;
import kmerrill285.trewrite.core.network.server.SPacketSyncInventoryChest;
import kmerrill285.trewrite.core.network.server.SPacketSyncInventoryTerraria;
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
        registerMessage(CPacketOpenInventoryTerraria.class, CPacketOpenInventoryTerraria::encode, CPacketOpenInventoryTerraria::new, CPacketOpenInventoryTerraria::handle);
        registerMessage(CPacketOpenInventoryVanilla.class, CPacketOpenInventoryVanilla::encode, CPacketOpenInventoryVanilla::new, CPacketOpenInventoryVanilla::handle);
        registerMessage(CPacketSyncInventoryTerraria.class, CPacketSyncInventoryTerraria::encode, CPacketSyncInventoryTerraria::new, CPacketSyncInventoryTerraria::handle);
        registerMessage(CPacketRequestInventoryTerraria.class, CPacketRequestInventoryTerraria::encode, CPacketRequestInventoryTerraria::decode, CPacketRequestInventoryTerraria::handle);
        registerMessage(CPacketEquipItemTerraria.class, CPacketEquipItemTerraria::encode, CPacketEquipItemTerraria::new, CPacketEquipItemTerraria::handle);
        registerMessage(CPacketCloseInventoryTerraria.class, CPacketCloseInventoryTerraria::encode, CPacketCloseInventoryTerraria::new, CPacketCloseInventoryTerraria::handle);
        registerMessage(CPacketThrowItemTerraria.class, CPacketThrowItemTerraria::encode, CPacketThrowItemTerraria::new, CPacketThrowItemTerraria::handle);
        registerMessage(CPacketSyncInventoryChest.class, CPacketSyncInventoryChest::encode, CPacketSyncInventoryChest::new, CPacketSyncInventoryChest::handle);
        registerMessage(CPacketRequestInventoryChest.class, CPacketRequestInventoryChest::encode, CPacketRequestInventoryChest::new, CPacketRequestInventoryChest::handle);
        registerMessage(CPacketOpenChestTerraria.class, CPacketOpenChestTerraria::encode, CPacketOpenChestTerraria::new, CPacketOpenChestTerraria::handle);
        registerMessage(CPacketSaveChestTerraria.class, CPacketSaveChestTerraria::encode, CPacketSaveChestTerraria::new, CPacketSaveChestTerraria::handle);
        registerMessage(CPacketNegateFall.class, CPacketNegateFall::encode, CPacketNegateFall::new, CPacketNegateFall::handle);
        registerMessage(CPacketAddScore.class, CPacketAddScore::encode, CPacketAddScore::new, CPacketAddScore::handle);
        registerMessage(CPacketChangeScore.class, CPacketChangeScore::encode, CPacketChangeScore::new, CPacketChangeScore::handle);
        registerMessage(CPacketHeal.class, CPacketHeal::encode, CPacketHeal::new, CPacketHeal::handle);
        registerMessage(CPacketRemoveSummons.class, CPacketRemoveSummons::encode, CPacketRemoveSummons::new, CPacketRemoveSummons::handle);

        registerMessage(SPacketSendInventoryTerraria.class, SPacketSendInventoryTerraria::encode, SPacketSendInventoryTerraria::new, SPacketSendInventoryTerraria::handle);
        registerMessage(SPacketSyncInventoryTerraria.class, SPacketSyncInventoryTerraria::encode, SPacketSyncInventoryTerraria::decode, SPacketSyncInventoryTerraria::handle);
//        registerMessage(SPacketSpawnItemTerraria.class, SPacketSpawnItemTerraria::encode, SPacketSpawnItemTerraria::decode, SPacketSpawnItemTerraria::handle);
        registerMessage(SPacketSyncInventoryChest.class, SPacketSyncInventoryChest::encode, SPacketSyncInventoryChest::new, SPacketSyncInventoryChest::handle);
        registerMessage(SPacketSendAccessories.class, SPacketSendAccessories::encode, SPacketSendAccessories::new, SPacketSendAccessories::handle);
        registerMessage(SPacketForceMovement.class, SPacketForceMovement::encode, SPacketForceMovement::new, SPacketForceMovement::handle);
        registerMessage(SPacketRefreshDimensionRenderer.class, SPacketRefreshDimensionRenderer::encode, SPacketRefreshDimensionRenderer::new, SPacketRefreshDimensionRenderer::handle);


	}
	
	 private static <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
		 INSTANCE.registerMessage(id++, messageType, encoder, decoder, messageConsumer);
	}
}
