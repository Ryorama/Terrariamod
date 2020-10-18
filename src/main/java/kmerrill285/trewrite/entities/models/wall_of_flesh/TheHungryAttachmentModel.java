package kmerrill285.trewrite.entities.models.wall_of_flesh;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.MobEntity;

public class TheHungryAttachmentModel extends EntityModel {
   private final RendererModel bb_main;

   public TheHungryAttachmentModel() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 32, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false));
   }

   public void render(MobEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.bb_main.render(f5);
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
