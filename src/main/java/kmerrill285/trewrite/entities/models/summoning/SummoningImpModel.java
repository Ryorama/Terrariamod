package kmerrill285.trewrite.entities.models.summoning;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.summoning.EntitySummoningImp;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class SummoningImpModel extends EntityModel<EntitySummoningImp> {
   private final RendererModel bone;
   private final RendererModel bone4;
   private final RendererModel bone6;
   private final RendererModel wingl;
   private final RendererModel wingr;
   private final RendererModel bone5;

   public SummoningImpModel() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.bone = new RendererModel(this);
      this.bone.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bone.cubeList.add(new ModelBox(this.bone, 12, 25, -2.0F, -7.0F, -5.0F, 5, 2, 1, 0.0F, false));
      this.bone.cubeList.add(new ModelBox(this.bone, 13, 22, -1.0F, -8.0F, -5.0F, 3, 1, 1, 0.0F, false));
      this.bone.cubeList.add(new ModelBox(this.bone, 7, 4, -4.0F, -13.0F, -4.0F, 9, 8, 7, 0.0F, false));
      this.bone4 = new RendererModel(this);
      this.bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.setRotationAngle(this.bone4, 0.0F, -1.5708F, -0.2618F);
      this.bone.addChild(this.bone4);
      this.bone4.cubeList.add(new ModelBox(this.bone4, 25, 20, -3.0F, -9.6F, -8.0F, 4, 5, 1, 0.0F, false));
      this.bone6 = new RendererModel(this);
      this.bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.setRotationAngle(this.bone6, 0.0F, 1.5708F, 0.2618F);
      this.bone.addChild(this.bone6);
      this.bone6.cubeList.add(new ModelBox(this.bone6, 25, 20, -1.0F, -9.6F, -7.0F, 4, 5, 1, 0.0F, false));
      this.wingl = new RendererModel(this);
      this.wingl.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.setRotationAngle(this.wingl, -1.5708F, 0.0F, 1.5708F);
      this.wingl.cubeList.add(new ModelBox(this.wingl, 42, 14, -7.5F, -6.0F, -3.0F, 3, 4, 1, 0.0F, false));
      this.wingr = new RendererModel(this);
      this.wingr.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.setRotationAngle(this.wingr, -1.5708F, 0.0F, 1.5708F);
      this.wingr.cubeList.add(new ModelBox(this.wingr, 42, 14, -7.5F, -6.0F, 1.0F, 3, 4, 1, 0.0F, false));
      this.bone5 = new RendererModel(this);
      this.bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.setRotationAngle(this.bone5, 0.7854F, 0.0F, 0.0F);
      this.bone5.cubeList.add(new ModelBox(this.bone5, 31, 30, 1.0F, 0.4645F, -0.5355F, 3, 2, 2, 0.0F, false));
      this.bone5.cubeList.add(new ModelBox(this.bone5, 31, 30, 2.0F, -4.5355F, 0.4645F, 3, 2, 2, 0.0F, false));
      this.bone5.cubeList.add(new ModelBox(this.bone5, 31, 30, -4.0F, -4.5355F, 0.4645F, 3, 2, 2, 0.0F, false));
      this.bone5.cubeList.add(new ModelBox(this.bone5, 31, 30, -3.0F, 0.4645F, -0.5355F, 3, 2, 2, 0.0F, false));
      this.bone5.cubeList.add(new ModelBox(this.bone5, 6, 29, -3.0F, -5.5355F, 1.4645F, 7, 7, 5, 0.0F, false));
   }

   public void render(EntitySummoningImp entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GlStateManager.rotated(-90.0D, 0.0D, 1.0D, 0.0D);
      this.bone.render(f5);
      this.bone5.render(f5);
      double wingMove = 15.0D * Math.cos(Math.toRadians((double)System.nanoTime()));
      GlStateManager.pushMatrix();
      GlStateManager.rotated(wingMove, 0.0D, 1.0D, 0.0D);
      this.wingl.render(f5);
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.rotated(-wingMove, 0.0D, 1.0D, 0.0D);
      this.wingr.render(f5);
      GlStateManager.popMatrix();
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
