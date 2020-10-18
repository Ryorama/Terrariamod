package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.EntityCoinPortal;
import kmerrill285.trewrite.entities.EntityRope;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderRope extends MobRenderer<EntityRope, RopeModel>
{
    private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/rope.png");

    public RenderRope(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new RopeModel(), 4.0f);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityRope entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;
        super.doRender(entity, x, y, z, 0, partialTicks);
        for (int i = 0; i < entity.lastPos.size(); i++) {
        	Vec3d p = entity.lastPos.get(i);
        	System.out.println(x + ", " + y + ", " + z);
            super.doRender(entity, x + (p.x - entity.posX), y + (p.y - entity.posY), z + (p.z - entity.posZ), 0, partialTicks+i);

        }
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityRope entitylivingbaseIn, float partialTickTime)
    {
    	
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityRope entity)
    {
    	return texture;
    }
}
