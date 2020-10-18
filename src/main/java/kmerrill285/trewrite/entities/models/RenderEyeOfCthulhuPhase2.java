package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEyeOfCthulhuPhase2 extends MobRenderer<EntityEyeOfCthulhu, ModelEyeOfCthulhuPhase2> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/eyes/eye_of_cthulhu_phase2.png");

	   public RenderEyeOfCthulhuPhase2(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelEyeOfCthulhuPhase2(), 0.25F);
	   }

	   public void doRender(EntityEyeOfCthulhu entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(EntityEyeOfCthulhu entitylivingbaseIn, float partialTickTime) {
	      float f = 1.0F;
	      GlStateManager.rotatef(entitylivingbaseIn.rx, 1.0F, 0.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.ry, 0.0F, 1.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.rz, 0.0F, 0.0F, 1.0F);
	      GlStateManager.scalef(f, f, f);
	   }

	   protected ResourceLocation getEntityTexture(EntityEyeOfCthulhu entity) {
	      return texture;
	   }
	}
