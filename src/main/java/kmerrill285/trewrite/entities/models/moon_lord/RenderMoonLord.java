package kmerrill285.trewrite.entities.models.moon_lord;

import kmerrill285.trewrite.entities.monsters.bosses.moon_lord.MoonLord;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderMoonLord extends MobRenderer<MoonLord, ModelMoonLord> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/moon_lord.png");

	   public RenderMoonLord(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelMoonLord(), 0.25F);
	   }

	   public void doRender(MoonLord entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(MoonLord entitylivingbaseIn, float partialTickTime) {
	   }

	   protected ResourceLocation getEntityTexture(MoonLord entity) {
	      return texture;
	   }
	}
