package kmerrill285.trewrite.entities.models;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.entities.EntityCoin;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderCoin extends MobRenderer<EntityCoin, CoinModel>
{
    private ResourceLocation copper = new ResourceLocation("trewrite:textures/item/coins/copper.png");
    private ResourceLocation silver = new ResourceLocation("trewrite:textures/item/coins/silver.png");
    private ResourceLocation gold = new ResourceLocation("trewrite:textures/item/coins/gold.png");
    private ResourceLocation platinum = new ResourceLocation("trewrite:textures/item/coins/platinum.png");

    public RenderCoin(EntityRendererManager renderManagerIn)
    {
    	
        super(renderManagerIn, new CoinModel(), 4.0f);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityCoin entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
//    	BossStatus.setBossStatus(entity, true);
        this.shadowSize = 0.25F;
        super.doRender(entity, x, y + Math.abs(Math.cos(Math.toRadians(System.nanoTime() / 10000000.0))) / 2.0, z, 0, partialTicks);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityCoin entitylivingbaseIn, float partialTickTime)
    {
    	
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCoin entity)
    {
    	if (entity.coin == EntityCoin.COPPER) return copper;
    	if (entity.coin == EntityCoin.SILVER) return silver;
    	if (entity.coin == EntityCoin.GOLD) return gold;
    	if (entity.coin == EntityCoin.PLATINUM) return platinum;
    	return copper;
    }
}
