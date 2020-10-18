package kmerrill285.trewrite.entities.models.wall_of_flesh;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshEye;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelWallOfFleshEye extends EntityModel<EntityWallOfFleshEye> {
   private final RendererModel bb_main;

   public ModelWallOfFleshEye() {
      this.textureWidth = 3510;
      this.textureHeight = 1756;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -440.0F, -440.0F, -440.0F, 880, 880, 880, 0.0F, false));
   }

   public void render(EntityWallOfFleshEye entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GlStateManager.pushMatrix();
      double pitch = (double)entity.getPitch(f5);
      GlStateManager.translated(0.0D, -25.0D, 0.0D);
      GlStateManager.rotated(pitch, 1.0D, 0.0D, 0.0D);
      GlStateManager.scalef(0.75F, 0.75F, 0.75F);
      this.bb_main.render(f5);
      GlStateManager.popMatrix();
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
