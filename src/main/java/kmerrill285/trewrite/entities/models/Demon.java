package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityDemon;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class Demon extends EntityModel<EntityDemon> {
   private final RendererModel bb_main;

   public Demon() {
      this.textureWidth = 32;
      this.textureHeight = 32;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 4, 16, -1.0F, -8.0F, 0.0F, 1, 3, 1, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 16, 1.0F, -8.0F, 0.0F, 1, 3, 1, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 9, -1.0F, -12.0F, -1.0F, 3, 4, 3, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 9, 7, 1.0F, -11.0F, 2.0F, 0, 2, 2, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, 0.0F, -11.0F, 2.0F, 0, 2, 2, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -2.0F, -16.0F, -2.0F, 5, 4, 5, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 8, 16, -2.0F, -18.0F, 0.0F, 1, 2, 1, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 12, 16, -1.0F, -19.0F, 0.0F, 1, 1, 1, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, 1.0F, -19.0F, 0.0F, 1, 1, 1, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 13, 9, 2.0F, -18.0F, 0.0F, 1, 2, 1, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 15, 0, -2.0F, -11.0F, -2.0F, 1, 1, 3, 0.0F, false));
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 12, 12, 2.0F, -11.0F, -2.0F, 1, 1, 3, 0.0F, false));
   }

   public void render(EntityDemon entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.bb_main.render(f5);
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
