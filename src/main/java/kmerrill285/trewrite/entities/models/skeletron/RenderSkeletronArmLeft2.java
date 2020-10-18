package kmerrill285.trewrite.entities.models.skeletron;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronLeftArm2;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderSkeletronArmLeft2 extends MobRenderer<EntitySkeletronLeftArm2, ModelSkeletronLeftArm2>
{
    private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/skeletron_arm_left_2.png");
    
    public RenderSkeletronArmLeft2(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new ModelSkeletronLeftArm2(), 4.0f);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntitySkeletronLeftArm2 entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;

        super.doRender(entity, x, y, z, 0, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntitySkeletronLeftArm2 entitylivingbaseIn, float partialTickTime)
    {
    	GlStateManager.rotated(0, 0, 0, 90);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySkeletronLeftArm2 entity)
    {
    	return texture;
    }
}
