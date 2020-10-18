package kmerrill285.trewrite.entities.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.MobEntity;

public class ShadowOrbModel extends EntityModel<MobEntity> {
   private final RendererModel bone;

   public ShadowOrbModel() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.bone = new RendererModel(this);
      this.bone.setRotationPoint(0.0F, 16.0F, 0.0F);
      this.bone.cubeList.add(new ModelBox(this.bone, 0, 0, -8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F, false));
   }

   public void render(MobEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.bone.rotateAngleY = (float)((double)System.nanoTime() / 1.0E9D);
      this.bone.render(f5);
   }

   public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
      modelRenderer.rotateAngleX = x;
      modelRenderer.rotateAngleY = y;
      modelRenderer.rotateAngleZ = z;
   }
}
