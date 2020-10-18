package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.projectiles.magic_projectiles.VilethornProjectile;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelVilethorn extends EntityModel<VilethornProjectile> {
   private final RendererModel bone2;
   private final RendererModel bone3;

   public ModelVilethorn() {
      this.textureWidth = 16;
      this.textureHeight = 16;
      this.bone2 = new RendererModel(this);
      this.bone2.setRotationPoint(0.0F, 16.0F, 0.0F);
      this.setRotationAngle(this.bone2, 0.0F, 0.0F, 0.7854F);
      this.bone2.cubeList.add(new ModelBox(this.bone2, 0, 1, -16.0F, -15.0F, 0.0F, 16, 15, 0, 0.0F, false));
      this.bone3 = new RendererModel(this);
      this.bone3.setRotationPoint(0.0F, 16.0F, 0.0F);
      this.setRotationAngle(this.bone3, 1.5708F, -0.7854F, 0.0F);
      this.bone3.cubeList.add(new ModelBox(this.bone3, 0, 1, -8.0F, -7.0F, 11.4F, 16, 15, 0, 0.0F, false));
   }

   public void render(VilethornProjectile entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GlStateManager.translated(0.0D, 1.0D, 0.0D);
      this.bone2.render(f5);
      this.bone3.render(f5);
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
