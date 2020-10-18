package kmerrill285.trewrite.entities.models;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class RenderEntityItemT extends EntityRenderer<EntityItemT> {

	private ItemRenderer itemrenderer;
	private Random random = new Random();
	
	public RenderEntityItemT(EntityRendererManager p_i46185_1_, ItemRenderer p_i46167_2_) {
		super(p_i46185_1_);
		this.itemrenderer = p_i46167_2_;
	    this.shadowSize = 0.15F;
	    this.shadowOpaque = 0.75F;
	}

	   private int transformModelCount(EntityItemT itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
	      ItemStackT itemstack = itemIn.getItem();
	      Item item = itemstack.item;
	      if (item == null) {
	         return 0;
	      } else {
	         boolean flag = p_177077_9_.isGui3d();
	         int i = this.getModelCount(itemstack);
	         float f = 0.25F;
	         float f1 = true ? MathHelper.sin(((float)itemIn.getAge() + p_177077_8_) / 10.0F + itemIn.hoverStart) * 0.1F + 0.1F : 0;
	         float f2 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.getY();
	         GlStateManager.translatef((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25F * f2, (float)p_177077_6_);
	         if (flag || this.getRenderManager().options != null) {
	            float f3 = (((float)itemIn.getAge() + p_177077_8_) / 20.0F + itemIn.hoverStart) * (180F / (float)Math.PI);
	            GlStateManager.rotatef(f3, 0.0F, 1.0F, 0.0F);
	         }

	         GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	         return i;
	      }
	   }

	   protected int getModelCount(ItemStackT stack) {
	      int i = 1;
//	      if (stack.size > 48) {
//	         i = 5;
//	      } else if (stack.size > 32) {
//	         i = 4;
//	      } else if (stack.size > 16) {
//	         i = 3;
//	      } else if (stack.size > 1) {
//	         i = 2;
//	      }

	      return i;
	   }
	
	
	public void doRender(EntityItemT entity, double x, double y, double z, float entityYaw, float partialTicks) {
	      ItemStackT itemstack = entity.getItem();
	      //System.out.println(itemstack.itemForRender);
	      int i = 0;
	      this.random.setSeed((long)i);
	      boolean flag = false;
	      if (this.bindEntityTexture(entity)) {
	    	  //System.out.println("BIND TEXTURE");
	         this.getRenderManager().textureManager.getTexture(this.getEntityTexture(entity)).setBlurMipmap(false, false);
	         flag = true;
	      }

	      GlStateManager.enableRescaleNormal();
	      GlStateManager.alphaFunc(516, 0.1F);
	      GlStateManager.enableBlend();
	      RenderHelper.enableStandardItemLighting();
	      GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	      GlStateManager.pushMatrix();
	      IBakedModel ibakedmodel = this.itemrenderer.getItemModelWithOverrides(itemstack.itemForRender, entity.world, (LivingEntity)null);
	      int j = this.transformModelCount(entity, x, y, z, partialTicks, ibakedmodel);
	      //System.out.println("TRANSFORM MODEL");
	      boolean flag1 = ibakedmodel.isGui3d();
	      if (!flag1) {
	         float f3 = -0.0F * (float)(j - 1) * 0.5F;
	         float f4 = -0.0F * (float)(j - 1) * 0.5F;
	         float f5 = -0.09375F * (float)(j - 1) * 0.5F;
	         GlStateManager.translatef(f3, f4, f5);
	      }

	      if (this.renderOutlines) {
	    	  GlStateManager.enableColorMaterial();
	          GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
	      }
	      j = 1;
	      for(int k = 0; k < j; ++k) {
	         if (flag1) {
	            GlStateManager.pushMatrix();
	            if (k > 0) {
	               float f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
	               float f9 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
	               float f6 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
	               GlStateManager.translatef(shouldSpreadItems() ? f7 : 0, shouldSpreadItems() ? f9 : 0, f6);
	            }

	            IBakedModel transformedModel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND, false);
	            //System.out.println("RENDER ITEM");
	            this.itemrenderer.renderItem(itemstack.itemForRender, transformedModel);
	            GlStateManager.popMatrix();
	         } else {
	            GlStateManager.pushMatrix();
	            if (k > 0) {
	               float f8 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
	               float f10 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
	               GlStateManager.translatef(f8, f10, 0.0F);
	            }

	            IBakedModel transformedModel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GROUND, false);
	            //System.out.println("RENDER ITEM 2");
	            this.itemrenderer.renderItem(itemstack.itemForRender, transformedModel);
	            GlStateManager.popMatrix();
	            GlStateManager.translatef(0.0F, 0.0F, 0.09375F);
	         }
	      }

	      if (this.renderOutlines) {
//	         GlStateManager.disableOutlineMode();
//	         GlStateManager.disableColorMaterial();
	      }

	      GlStateManager.popMatrix();
	      GlStateManager.disableRescaleNormal();
	      GlStateManager.disableBlend();
	      this.bindEntityTexture(entity);
	      if (flag) {
	         this.getRenderManager().textureManager.getTexture(this.getEntityTexture(entity)).restoreLastBlurMipmap();
	      }
	      //System.out.println("SUPER RENDER");
	      super.doRender(entity, x, y, z, entityYaw, partialTicks);
	   }
	
	private boolean shouldSpreadItems() {
		return true;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityItemT entity) {
	      return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	   }

}
