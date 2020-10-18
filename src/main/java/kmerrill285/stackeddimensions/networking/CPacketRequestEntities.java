package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;

import kmerrill285.stackeddimensions.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class CPacketRequestEntities {
	private int x, y, z;
	private ResourceLocation dimension;
	public CPacketRequestEntities(double x, double y, double z, ResourceLocation dimension) {
		this.x = (int)x;
		this.y = (int)y;
		this.z = (int)z;
		this.dimension = dimension;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeResourceLocation(dimension);
    }
	
	public CPacketRequestEntities(PacketBuffer buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		dimension = buf.readResourceLocation();
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() -> {
			
			ServerPlayerEntity player = ctx.get().getSender();
			
			DimensionType t = Util.getDimension(dimension);
			
//	 		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), new SPacketRefreshEntities());

			if (t == null) return;
			DimensionManager.keepLoaded(t, true);
			ServerWorld dim = DimensionManager.getWorld(player.getServer(), t, true, true);
			DimensionManager.keepLoaded(t, true);
			java.util.List<Entity> entities = dim.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(x - 15, y - 15, z - 15, x + 15, y + 15, z + 15));
			int i = 0;
			for (Entity entity : entities) {
//				i++;
//				if (i > 15 && !(entity instanceof PlayerEntity)) continue;

				CompoundNBT nbt = new CompoundNBT();
				final float pitch = entity.rotationPitch;
				final float yaw = entity.rotationYaw;
				entity.rotationPitch = 0;
				entity.rotationYaw = 0;
				entity.writeUnlessPassenger(nbt);
				entity.rotationPitch = pitch;
				entity.rotationYaw = yaw;
				String customName = "";
				if (entity.hasCustomName())
					customName = entity.getCustomName().toString();
				///	public SPacketSendEntity(EntityType<?> entity, double x, double y, double z, float rotationPitch, float rotationYaw, float yawHead, CompoundNBT nbt, String name) {

		 		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), new SPacketSendEntity(entity.getType(), entity.posX, entity.posY, entity.posZ, pitch, yaw, entity.getRotationYawHead(), nbt, customName));
			}
			
        });
        ctx.get().setPacketHandled(true);
	}
}