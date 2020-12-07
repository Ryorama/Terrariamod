package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityCrimera;
import kmerrill285.trewrite.entities.monsters.EntityEaterOfSouls;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderCrimera extends MobRenderer<EntityCrimera, ModelCrimera> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/crimera.png");

   public RenderCrimera(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ModelCrimera(), 0.25F);
   }

   public void doRender(EntityCrimera entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(EntityCrimera entitylivingbaseIn, float partialTickTime) {
      float f = 1.0F;
      GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 0.0F);
      GlStateManager.scalef(f, f, f);
   }

   protected ResourceLocation getEntityTexture(EntityCrimera entity) {
      return this.texture;
   }
}
