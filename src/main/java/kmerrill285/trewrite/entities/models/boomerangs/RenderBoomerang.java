package kmerrill285.trewrite.entities.models.boomerangs;

import kmerrill285.trewrite.entities.BoomerangEntity;
import kmerrill285.trewrite.entities.projectiles.boomerangs.EntityEnchantedBoomerang;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderBoomerang extends MobRenderer<EntityEnchantedBoomerang, BoomerangModel> {
   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/boomerang/enchanted_boomerang.png");

   public RenderBoomerang(EntityRendererManager renderManagerIn, String entity) {
      super(renderManagerIn, new BoomerangModel(), 4.0F);
      this.texture = new ResourceLocation("trewrite:textures/entity/boomerang/" + entity + ".png");
   }

   public void doRender(EntityEnchantedBoomerang entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.shadowSize = 0.25F;
      super.doRender(entity, x, y, z, 0.0F, partialTicks);
   }

   protected void preRenderCallback(EntityEnchantedBoomerang entitylivingbaseIn, float partialTickTime) {
   }

   protected ResourceLocation getEntityTexture(EntityEnchantedBoomerang entity) {
      return this.texture;
   }
}
