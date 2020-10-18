package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketSendEntity {
   public EntityType entity;
   public double x;
   public double y;
   public double z;
   public CompoundNBT nbt;
   public String name;
   public float rotationPitch;
   public float rotationYaw;
   public float yawHead;

   public SPacketSendEntity(EntityType entity, double x, double y, double z, float rotationPitch, float rotationYaw, float yawHead, CompoundNBT nbt, String name) {
      this.entity = entity;
      this.x = x;
      this.y = y;
      this.z = z;
      this.nbt = nbt;
      this.name = name;
      this.rotationPitch = rotationPitch;
      this.rotationYaw = rotationYaw;
      this.yawHead = yawHead;
   }

   public void encode(PacketBuffer buf) {
      buf.writeResourceLocation(this.entity.getRegistryName());
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeCompoundTag(this.nbt);
      buf.writeString(this.name);
      buf.writeFloat(this.rotationPitch);
      buf.writeFloat(this.rotationYaw);
      buf.writeFloat(this.yawHead);
   }

   public SPacketSendEntity(PacketBuffer buf) {
      this.entity = Util.getEntity(buf.readResourceLocation());
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.nbt = buf.readCompoundTag();
      this.name = buf.readString(100).trim();
      this.rotationPitch = buf.readFloat();
      this.rotationYaw = buf.readFloat();
      this.yawHead = buf.readFloat();
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         if (StackedDimensions.renderWorld != null) {
            World world = StackedDimensions.renderWorld;
            Entity e = this.entity.create(world);
            e.read(this.nbt);
            e.rotationPitch = this.rotationPitch;
            e.rotationYaw = this.rotationYaw;
            StackedDimensions.renderEntities.add(e);
            e.rotationPitch = this.rotationPitch;
            e.prevRotationPitch = this.rotationPitch;
            e.rotationYaw = this.rotationYaw;
            e.prevRotationYaw = this.rotationYaw;
            if (e instanceof LivingEntity) {
               LivingEntity living = (LivingEntity)e;
               living.renderYawOffset = this.rotationYaw;
               living.prevRenderYawOffset = this.rotationYaw;
               living.rotationYawHead = this.yawHead;
               living.prevRotationYawHead = this.yawHead;
            }
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
