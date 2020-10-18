package kmerrill285.stackeddimensions.render;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.util.math.Vec3d;

public class StackedRenderInfo extends ActiveRenderInfo {
   public void setPosition(Vec3d position) {
      super.setPostion(position);
   }

   public void setDirection(float pitch, float yaw) {
      super.setDirection(pitch, yaw);
   }
}
