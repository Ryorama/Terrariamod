package kmerrill285.trewrite.entities.models.wall_of_flesh;

import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFlesh;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWallOfFlesh extends MobRenderer<EntityWallOfFlesh, ModelWallOfFlesh> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/wall_of_flesh.png");

   public RenderWallOfFlesh(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ModelWallOfFlesh(), 4.0F);
   }

   public void doRender(EntityWallOfFlesh entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(EntityWallOfFlesh entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityWallOfFlesh entity) {
      return this.texture;
   }
}
