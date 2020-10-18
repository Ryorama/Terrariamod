package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityBat;
import kmerrill285.trewrite.entities.monsters.EntityPossessedArmor;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPossessedArmor extends MobRenderer<EntityPossessedArmor, ModelPossessedArmor> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/possessed_armor.png");

	   public RenderPossessedArmor(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelPossessedArmor(), 0.25F);
	   }

	   public void doRender(EntityPossessedArmor entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F;
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(EntityPossessedArmor entitylivingbaseIn, float partialTickTime) {
	   }

	   protected ResourceLocation getEntityTexture(EntityPossessedArmor entity) {
	      return texture;
	   }
	}
