package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ModelDemonEye extends EntityModel<EntityDemonEye> {
   public RendererModel shape1;
   public RendererModel shape2;
   public RendererModel shape2_1;
   public RendererModel shape2_2;
   public RendererModel shape2_3;
   public RendererModel shape2_4;

   public ModelDemonEye() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.shape2 = new RendererModel(this, 32, 0);
      this.shape2.setRotationPoint(-3.0F, 14.0F, 4.0F);
      this.shape2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
      this.shape2_2 = new RendererModel(this, 32, 0);
      this.shape2_2.setRotationPoint(-0.5F, 11.5F, 4.0F);
      this.shape2_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
      this.shape1 = new RendererModel(this, 0, 0);
      this.shape1.setRotationPoint(-4.0F, 8.0F, -4.0F);
      this.shape1.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
      this.shape2_3 = new RendererModel(this, 32, 0);
      this.shape2_3.setRotationPoint(-3.0F, 9.0F, 4.0F);
      this.shape2_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
      this.shape2_4 = new RendererModel(this, 32, 0);
      this.shape2_4.setRotationPoint(2.0F, 9.0F, 4.0F);
      this.shape2_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
      this.shape2_1 = new RendererModel(this, 32, 0);
      this.shape2_1.setRotationPoint(2.0F, 14.0F, 4.0F);
      this.shape2_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
   }

   public void render(EntityDemonEye entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.shape2.render(f5);
      this.shape2_2.render(f5);
      this.shape1.render(f5);
      this.shape2_3.render(f5);
      this.shape2_4.render(f5);
      this.shape2_1.render(f5);
   }

   public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
