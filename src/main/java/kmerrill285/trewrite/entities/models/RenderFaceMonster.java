package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityFaceMonster;
import kmerrill285.trewrite.entities.monsters.EntityPossessedArmor;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderFaceMonster extends MobRenderer<EntityFaceMonster, ModelFaceMonster> {
	   private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/face_monster.png");

	   public RenderFaceMonster(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelFaceMonster(), 0.25F);
	   }

	   public void doRender(EntityFaceMonster entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      float size = 2;
		  this.shadowSize = 0.25F;
		  GlStateManager.scalef(size, size, size);
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   protected void preRenderCallback(EntityFaceMonster entitylivingbaseIn, float partialTickTime) {
	   }

	   protected ResourceLocation getEntityTexture(EntityFaceMonster entity) {
	      return texture;
	   }
	}
