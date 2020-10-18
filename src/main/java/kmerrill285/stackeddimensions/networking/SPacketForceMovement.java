package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MoverType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketForceMovement {
   double x;
   double y;
   double z;
   boolean onGround;

   public SPacketForceMovement(double x, double y, double z, boolean onGround) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.onGround = onGround;
   }

   public void encode(PacketBuffer buf) {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeBoolean(this.onGround);
   }

   public SPacketForceMovement(PacketBuffer buf) {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.onGround = buf.readBoolean();
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         Minecraft.getInstance().player.move(MoverType.PISTON, new Vec3d(this.x, this.y, this.z));
         double var10001 = this.x != 0.0D ? 0.0D : Minecraft.getInstance().player.getMotion().x;
         double var10002 = this.y != 0.0D ? 0.0D : Minecraft.getInstance().player.getMotion().y;
         Minecraft.getInstance().player.setMotion(var10001, var10002, this.z != 0.0D ? 0.0D : Minecraft.getInstance().player.getMotion().z);
         Minecraft.getInstance().player.onGround = this.onGround;
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
