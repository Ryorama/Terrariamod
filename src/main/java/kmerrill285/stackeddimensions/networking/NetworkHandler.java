package kmerrill285.stackeddimensions.networking;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
	private static final String PTC_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation("stackeddimenisons", "main"))
            .networkProtocolVersion(() -> PTC_VERSION)
            .clientAcceptedVersions(PTC_VERSION::equals)
            .serverAcceptedVersions(PTC_VERSION::equals)
            .simpleChannel();
	
	private static int id = 0;

	public static void register() {
        registerMessage(CPacketSendBlockClick.class, CPacketSendBlockClick::encode, CPacketSendBlockClick::new, CPacketSendBlockClick::handle);
        registerMessage(CPacketRequestChunks.class, CPacketRequestChunks::encode, CPacketRequestChunks::new, CPacketRequestChunks::handle);
        registerMessage(SPacketForceMovement.class, SPacketForceMovement::encode, SPacketForceMovement::new, SPacketForceMovement::handle);
        registerMessage(SPacketRefreshDimensionRenderer.class, SPacketRefreshDimensionRenderer::encode, SPacketRefreshDimensionRenderer::new, SPacketRefreshDimensionRenderer::handle);
        registerMessage(SPacketSendChunk.class, SPacketSendChunk::encode, SPacketSendChunk::new, SPacketSendChunk::handle);
        registerMessage(CPacketRequestEntities.class, CPacketRequestEntities::encode, CPacketRequestEntities::new, CPacketRequestEntities::handle);
        registerMessage(SPacketSendEntity.class, SPacketSendEntity::encode, SPacketSendEntity::new, SPacketSendEntity::handle);
        registerMessage(SPacketRefreshEntities.class, SPacketRefreshEntities::encode, SPacketRefreshEntities::new, SPacketRefreshEntities::handle);

	}

   private static <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
		 INSTANCE.registerMessage(id++, messageType, encoder, decoder, messageConsumer);
	}
}
