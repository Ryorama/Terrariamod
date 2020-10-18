package kmerrill285.trewrite.entities.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class SquareModel extends EntityModel {
   private final RendererModel bb_main;

   public SquareModel() {
      this.textureWidth = 16;
      this.textureHeight = 16;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, 0.0F, 16, 16, 0, 0.0F, false));
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.bb_main.rotateAngleY = (float)((double)System.nanoTime() / 100.0D);
      this.bb_main.render(f5);
   }

   public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
      modelRenderer.rotateAngleX = x;
      modelRenderer.rotateAngleY = y;
      modelRenderer.rotateAngleZ = z;
   }
}
