package kmerrill285.trewrite.entities.models.boc;

import javax.annotation.Nullable;

import kmerrill285.trewrite.entities.monsters.bosses.boc.EntityCreeper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderCreeper extends LivingRenderer<EntityCreeper, ModelCreeper>
{   
	
    private ResourceLocation texture = new ResourceLocation("trewrite:textures/entity/creeper.png");
	
    public RenderCreeper(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new ModelCreeper(), 4.0f);
    }

    public void doRender(EntityCreeper entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityCreeper entitylivingbaseIn, float partialTickTime)
    {
       
    }
    
    @Nullable
    protected ResourceLocation getEntityTexture(EntityCreeper entity)
    {
    	return texture;
    }
}
