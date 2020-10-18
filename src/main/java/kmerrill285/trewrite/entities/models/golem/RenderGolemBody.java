package kmerrill285.trewrite.entities.models.golem;

import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemBody;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGolemBody extends MobRenderer<GolemBody, ModelGolemBody> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/golem_body.png");

	   public RenderGolemBody(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelGolemBody(), 0.25F);
	   }

	   public void doRender(GolemBody entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(GolemBody entitylivingbaseIn, float partialTickTime) {

	   }

	   protected ResourceLocation getEntityTexture(GolemBody entity) {
	      return texture;
	   }
	}
