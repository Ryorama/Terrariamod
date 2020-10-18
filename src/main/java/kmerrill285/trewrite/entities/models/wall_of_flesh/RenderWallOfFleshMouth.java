package kmerrill285.trewrite.entities.models.wall_of_flesh;

import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshMouth;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWallOfFleshMouth extends MobRenderer<EntityWallOfFleshMouth, ModelWallOfFleshMouth> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/wall_of_flesh_mouth.png");

   public RenderWallOfFleshMouth(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ModelWallOfFleshMouth(), 4.0F);
   }

   public void doRender(EntityWallOfFleshMouth entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(EntityWallOfFleshMouth entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityWallOfFleshMouth entity) {
      return this.texture;
   }
}
