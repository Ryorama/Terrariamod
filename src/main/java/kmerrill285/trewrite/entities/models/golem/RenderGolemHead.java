package kmerrill285.trewrite.entities.models.golem;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemHead;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGolemHead extends MobRenderer<GolemHead, ModelGolemHead> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/golem_head.png");

	   public RenderGolemHead(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelGolemHead(), 0.25F);
	   }

	   public void doRender(GolemHead entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(GolemHead entitylivingbaseIn, float partialTickTime) {
	   }

	   protected ResourceLocation getEntityTexture(GolemHead entity) {
	      return texture;
	   }
	}
