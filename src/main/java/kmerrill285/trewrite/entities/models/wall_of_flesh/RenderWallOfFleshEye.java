package kmerrill285.trewrite.entities.models.wall_of_flesh;

import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshEye;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWallOfFleshEye extends MobRenderer<EntityWallOfFleshEye, ModelWallOfFleshEye> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/wall_of_flesh_eye.png");

   public RenderWallOfFleshEye(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ModelWallOfFleshEye(), 4.0F);
   }

   public void doRender(EntityWallOfFleshEye entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(EntityWallOfFleshEye entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityWallOfFleshEye entity) {
      return this.texture;
   }
}
