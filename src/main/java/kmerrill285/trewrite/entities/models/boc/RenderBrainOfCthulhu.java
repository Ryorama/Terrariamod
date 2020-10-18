package kmerrill285.trewrite.entities.models.boc;

import javax.annotation.Nullable;

import kmerrill285.trewrite.entities.monsters.bosses.boc.EntityBrainOfCthulhu;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderBrainOfCthulhu extends LivingRenderer<EntityBrainOfCthulhu, ModelBrainOfCthulhu>
{   
    public RenderBrainOfCthulhu(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new ModelBrainOfCthulhu(), 4.0f);
    }

    public void doRender(EntityBrainOfCthulhu entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityBrainOfCthulhu entitylivingbaseIn, float partialTickTime)
    {
       
    }
    
    @Nullable
    protected ResourceLocation getEntityTexture(EntityBrainOfCthulhu entity)
    {
    	if(EntityBrainOfCthulhu.phase == 1) {
    		return new ResourceLocation("trewrite:textures/entity/brain_of_cthulhu.png");
    	}
    	else {
    		 return new ResourceLocation("trewrite:textures/entity/brain_of_cthulhu_phase2.png");
    	}
    }
}
