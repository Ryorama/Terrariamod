package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityToxicSludge;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;

public class RenderToxicSludge extends MobRenderer<EntityToxicSludge, ModelToxicSludge> {

	  private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/toxic_sludge.png");
	   
	   public RenderToxicSludge(EntityRendererManager renderManagerIn) {
	      super(renderManagerIn, new ModelToxicSludge(), 0.25F);
	   }

	   /**
	    * Renders the desired {@code T} type Entity.
	    */
	   public void doRender(EntityToxicSludge entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      this.shadowSize = 0.25F * (float)entity.getSlimeSize();
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }

	   /**
	    * Allows the render to do state modifications necessary before the model is rendered.
	    */
	   protected void preRenderCallback(EntityToxicSludge entitylivingbaseIn, float partialTickTime) {	   
	   }

	   /**
	    * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	    */
	   protected ResourceLocation getEntityTexture(EntityToxicSludge entity) {
	      return texture;
	   }
	}