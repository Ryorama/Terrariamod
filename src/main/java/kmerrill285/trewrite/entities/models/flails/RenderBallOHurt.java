package kmerrill285.trewrite.entities.models.flails;

import kmerrill285.trewrite.entities.projectiles.flails.EntityBallOHurt;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderBallOHurt extends MobRenderer<EntityBallOHurt, ModelBallOHurt> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/flail/ball_of_hurt.png");

   public RenderBallOHurt(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ModelBallOHurt(), 4.0F);
   }

   public void doRender(EntityBallOHurt entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      entity.rotationYaw = 0.0F;
      entity.prevRotationYaw = 0.0F;
      entity.rotationYawHead = 0.0F;
      entity.prevRotationYawHead = 0.0F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(EntityBallOHurt entity, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityBallOHurt entity) {
      return this.texture;
   }
}
