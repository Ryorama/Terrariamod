package kmerrill285.trewrite.entities.models.summoning;

import kmerrill285.trewrite.entities.summoning.EntitySummoningImp;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderSummoningImp extends MobRenderer<EntitySummoningImp, SummoningImpModel> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/summoning/flying_imp.png");

   public RenderSummoningImp(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new SummoningImpModel(), 4.0F);
   }

   public void doRender(EntitySummoningImp entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y + Math.abs(Math.cos(Math.toRadians((double)System.nanoTime() / 1.0E7D))), z, (float)Math.cos(Math.toRadians((double)System.nanoTime() / 10000.0D)), partialTicks);
   }

   protected void preRenderCallback(EntitySummoningImp entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntitySummoningImp entity) {
      return this.texture;
   }
}
