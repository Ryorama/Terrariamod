package kmerrill285.trewrite.entities.models.wyvern;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.wyvern.EntityWyvernTail;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWyvernTail extends MobRenderer<EntityWyvernTail, ModelWyvernTail> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/wyvern_tail.png");

	   public RenderWyvernTail(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelWyvernTail(), 0.25F);
	   }

	   public void doRender(EntityWyvernTail entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(EntityWyvernTail entitylivingbaseIn, float partialTickTime) {
	      float f = 1.0F;
	      GlStateManager.rotatef(entitylivingbaseIn.rx, 1.0F, 0.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.ry, 0.0F, 1.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.rz, 0.0F, 0.0F, 1.0F);
	      GlStateManager.scalef(f, f, f);
	   }

	   protected ResourceLocation getEntityTexture(EntityWyvernTail entity) {
	      return texture;
	   }
	}
