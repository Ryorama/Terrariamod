package kmerrill285.trewrite.entities.models.plantera;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.models.EyeOfCthulhuPhaseModel;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.Plantera;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPlantera extends LivingRenderer<Plantera, ModelPlantera>
{   
    public RenderPlantera(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new ModelPlantera(), 4.0f);
    }

    public void doRender(Plantera entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;

        super.doRender(entity, x, y-4, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(Plantera entitylivingbaseIn, float partialTickTime)
    {
        float f = 8;
        
        double radp = Math.toRadians(entitylivingbaseIn.rotationPitch);
        GlStateManager.translated(0, -Math.sin(radp)*2, Math.sin(radp)*5.5);
        GlStateManager.rotated(entitylivingbaseIn.rotationPitch, 1, 0, 0);
        GlStateManager.translated(0, Math.sin(radp)*2-1, Math.sin(radp)*3);
    }
    
    @Nullable
    protected ResourceLocation getEntityTexture(Plantera entity)
    {
    	return (Plantera.phase == 1) ? new ResourceLocation("trewrite:textures/entity/platntera_phase1.png") : new ResourceLocation("trewrite:textures/entity/plantera_phase2.png");
    }
}
