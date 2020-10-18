package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.util.Util;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeModelT extends SlimeModel {
   private final RendererModel bow = new RendererModel(this, 32, 0);

   public SlimeModelT(int slimeBodyTexOffY) {
      super(slimeBodyTexOffY);
      this.bow.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
   }

   public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      if (Util.isChristmas()) {
         GlStateManager.pushMatrix();
         GlStateManager.disableTexture();
         GlStateManager.translatef(-0.025F, 0.0F, 0.15F);
         GlStateManager.pushMatrix();
         GlStateManager.scalef(4.0F, 1.0F, 1.0F);
         GlStateManager.translatef(0.15F, 0.25F, 0.0F);
         this.bow.render(scale);
         GlStateManager.translatef(0.0F, -0.45F, 0.0F);
         this.bow.render(scale);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translatef(-0.05F, -3.5F, 0.0F);
         GlStateManager.scalef(1.0F, 4.0F, 1.0F);
         this.bow.render(scale);
         GlStateManager.translatef(0.45F, 0.0F, 0.0F);
         this.bow.render(scale);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translatef(0.9F, 0.1F, 0.0F);
         GlStateManager.rotatef(45.0F, 0.0F, 0.0F, 1.0F);
         this.bow.render(scale);
         GlStateManager.translatef(0.1F, -0.1F, 0.0F);
         this.bow.render(scale);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.translatef(0.17F, -0.25F, 0.1F);
         GlStateManager.scalef(1.0F, 1.0F, 1.6F);
         this.bow.render(scale);
         GlStateManager.popMatrix();
         GlStateManager.enableTexture();
         GlStateManager.popMatrix();
      }
   }
}
