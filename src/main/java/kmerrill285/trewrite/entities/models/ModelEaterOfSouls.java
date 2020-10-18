package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityEaterOfSouls;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelEaterOfSouls extends EntityModel<EntityEaterOfSouls> {
   private final RendererModel bb_main;
   private final RendererModel bone2;
   private final RendererModel bone;

   public ModelEaterOfSouls() {
      this.textureWidth = 16;
      this.textureHeight = 16;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -10.0F, -4.0F, 8, 6, 10, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -2.0F, -8.0F, -6.0F, 2, 2, 2, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, 2.0F, -8.0F, -6.0F, 2, 2, 2, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -2.0F, -11.0F, -3.0F, 6, 1, 8, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -2.0F, -4.0F, -3.0F, 6, 1, 8, 0.0F, false));
      this.bone2 = new RendererModel(this);
      this.bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.setRotationAngle(this.bone2, 0.0F, 0.2618F, 0.0F);
      this.bone2.cubeList.add(new ModelBox(this.bone2, 0, 0, 3.8978F, -8.0F, -6.7765F, 1, 2, 2, 0.0F, false));
      this.bone = new RendererModel(this);
      this.bone.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.setRotationAngle(this.bone, 0.0F, -0.2618F, 0.0F);
      this.bone.cubeList.add(new ModelBox(this.bone, 0, 0, -2.9659F, -8.0F, -6.7412F, 1, 2, 2, 0.0F, false));
   }

   public void render(EntityEaterOfSouls entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.bb_main.render(f5);
      this.bone2.render(f5);
      this.bone.render(f5);
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
