package kmerrill285.trewrite.entities.models.projectiles;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelEyeLaser extends EntityModel<EntityEyeLaser>{
   private final RendererModel bb_main;

   public ModelEyeLaser() {
      this.textureWidth = 1;
      this.textureHeight = 1;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -1.0F, -17.0F, 1, 1, 33, 0.0F, false));
   }

   public void render(EntityEyeLaser entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GlStateManager.pushMatrix();
      GlStateManager.rotated((double)entity.getPitch(f5), 1.0D, 0.0D, 0.0D);
      this.bb_main.render(f5);
      GlStateManager.popMatrix();
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
