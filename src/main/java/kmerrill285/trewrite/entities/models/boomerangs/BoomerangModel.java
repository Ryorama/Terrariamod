package kmerrill285.trewrite.entities.models.boomerangs;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.BoomerangEntity;
import kmerrill285.trewrite.entities.projectiles.boomerangs.EntityEnchantedBoomerang;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class BoomerangModel extends EntityModel<EntityEnchantedBoomerang> {
   public RendererModel shape1;
   public RendererModel shape1_1;
   public RendererModel shape1_2;

   public BoomerangModel() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.shape1_2 = new RendererModel(this, 0, 0);
      this.shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 9, 1, 3, 0.0F);
      this.shape1_1 = new RendererModel(this, 0, 0);
      this.shape1_1.setRotationPoint(-3.0F, 0.0F, 0.0F);
      this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 9, 1, 3, 0.0F);
      this.setRotateAngle(this.shape1_1, 0.0F, 1.5707964F, 0.0F);
      this.shape1 = new RendererModel(this, 0, 4);
      this.shape1.setRotationPoint(-3.0F, 0.0F, 0.0F);
      this.shape1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
   }

   public void render(EntityEnchantedBoomerang entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GlStateManager.rotated((double)((float)((double)System.nanoTime() / 1000000.0D)), 0.0D, 1.0D, 0.0D);
      GlStateManager.translated(0.0D, 1.25D, 0.0D);
      this.shape1_2.render(f5);
      this.shape1_1.render(f5);
      this.shape1.render(f5);
   }

   public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
