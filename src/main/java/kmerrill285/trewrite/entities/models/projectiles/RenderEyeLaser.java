package kmerrill285.trewrite.entities.models.projectiles;

import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEyeLaser extends MobRenderer<EntityEyeLaser, ModelEyeLaser> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/eye_laser.png");

   public RenderEyeLaser(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ModelEyeLaser(), 4.0F);
   }

   public void doRender(EntityEyeLaser entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(EntityEyeLaser entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityEyeLaser entity) {
      return this.texture;
   }
}
