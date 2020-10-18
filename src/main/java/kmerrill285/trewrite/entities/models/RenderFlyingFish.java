package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityBat;
import kmerrill285.trewrite.entities.monsters.EntityFlyingFish;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderFlyingFish extends MobRenderer<EntityFlyingFish, ModelFlyingFish> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/flying_fish.png");

	   public RenderFlyingFish(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelFlyingFish(), 0.25F);
	   }

	   public void doRender(EntityFlyingFish entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(EntityFlyingFish entitylivingbaseIn, float partialTickTime) {
	      float f = 1.0F;
	      GlStateManager.rotatef(entitylivingbaseIn.rx, 1.0F, 0.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.ry, 0.0F, 1.0F, 0.0F);
	      GlStateManager.rotatef(entitylivingbaseIn.rz, 0.0F, 0.0F, 1.0F);
	      GlStateManager.scalef(f, f, f);
	   }

	   protected ResourceLocation getEntityTexture(EntityFlyingFish entity) {
	      return texture;
	   }
	}

