package kmerrill285.trewrite.entities.models.wall_of_flesh;

import kmerrill285.trewrite.entities.monsters.bosses.wof.TheHungryEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTheHungry extends MobRenderer<TheHungryEntity, TheHungryModel> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/the_hungry.png");

   public RenderTheHungry(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new TheHungryModel(), 4.0F);
   }

   public void doRender(TheHungryEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(TheHungryEntity entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(TheHungryEntity entity) {
      return this.texture;
   }
}
