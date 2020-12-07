package kmerrill285.trewrite.entities.models.worms;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.bosses.EntityEowTail;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class RenderEowTail extends MobRenderer<MobEntity, ModelEowTail>
{
    private ResourceLocation wormTexture = new ResourceLocation("trewrite:textures/entity/worm/eater_tail.png");

    public RenderEowTail(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new ModelEowTail(), 4.0f);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(MobEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(MobEntity entitylivingbaseIn, float partialTickTime)
    {
        float f = 8;
        if (entitylivingbaseIn instanceof EntityEowTail) {
        	 GlStateManager.rotatef(((EntityEowTail)entitylivingbaseIn).rx, 1, 0, 0);
             GlStateManager.rotatef(((EntityEowTail)entitylivingbaseIn).ry, 0, 1, 0);
             GlStateManager.rotatef(((EntityEowTail)entitylivingbaseIn).rz, 0, 0, 1);
             GlStateManager.translatef(0, 2, 0);
             GlStateManager.scalef(f, f, f);
        }
       
       
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(MobEntity entity)
    {
        return wormTexture;
    }
}
