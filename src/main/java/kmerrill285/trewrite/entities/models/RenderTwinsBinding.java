package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityHellbat;
import kmerrill285.trewrite.entities.monsters.bosses.twins.TwinsBinding;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTwinsBinding extends MobRenderer<TwinsBinding, ModelTwinsCord> {
	   
	private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/twins_binding.png");

	   public RenderTwinsBinding(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelTwinsCord(), 0.25F);
	   }

	   public void doRender(TwinsBinding entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(TwinsBinding entitylivingbaseIn, float partialTickTime) {
	      float f = 1.0F;
	      GlStateManager.scalef(1, 3, 1);
	   }

	   protected ResourceLocation getEntityTexture(TwinsBinding entity) {
	      return texture;
	   }
	}
