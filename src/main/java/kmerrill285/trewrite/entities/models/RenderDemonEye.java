package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderDemonEye extends MobRenderer<EntityDemonEye, ModelDemonEye>
{
    private ResourceLocation[] eyeTexture = {
    		new ResourceLocation("trewrite:textures/entity/eyes/eye.png"),
    		new ResourceLocation("trewrite:textures/entity/eyes/eye_cataract.png"),
    		new ResourceLocation("trewrite:textures/entity/eyes/eye_dilated.png"),
    		new ResourceLocation("trewrite:textures/entity/eyes/eye_green.png"),
    		new ResourceLocation("trewrite:textures/entity/eyes/eye_purple.png"),
    		new ResourceLocation("trewrite:textures/entity/eyes/eye_sleepy.png")
    };
    
    public RenderDemonEye(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new ModelDemonEye(), 0.25f);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityDemonEye entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        this.shadowSize = 0.25F;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityDemonEye entitylivingbaseIn, float partialTickTime)
    {
        float f = 1;
        GlStateManager.rotatef(entitylivingbaseIn.rx, 1, 0, 0);
        GlStateManager.rotatef(entitylivingbaseIn.ry, 0, 1, 0);
        GlStateManager.rotatef(entitylivingbaseIn.rz, 0, 0, 1);
        GlStateManager.scalef(f, f, f);
       
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDemonEye entity)
    {
    	int type = entity.getDataManager().get(EntityDemonEye.type_data).intValue();
    	
        return eyeTexture[type];
    }
}
