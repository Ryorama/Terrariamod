package kmerrill285.trewrite.entities.models.layers;

import java.util.Map;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.platform.GlStateManager;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.items.Armor;
import kmerrill285.trewrite.items.Armor.ArmorType;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class TerrariaAccessoryLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends LayerRenderer<T, M> {
   protected static final ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   protected final A modelLeggings;
   protected final A modelArmor;
   private float alpha = 1.0F;
   private float colorR = 1.0F;
   private float colorG = 1.0F;
   private float colorB = 1.0F;
   private boolean skipRenderGlint;
   private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();

   protected TerrariaAccessoryLayer(IEntityRenderer<T, M> p_i50951_1_, A p_i50951_2_, A p_i50951_3_) {
      super(p_i50951_1_);
      this.modelLeggings = p_i50951_2_;
      this.modelArmor = p_i50951_3_;
   }

   public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      this.renderArmorLayer(entityIn, p_212842_2_, p_212842_3_, p_212842_4_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_, Armor.ArmorType.CHEST);
      this.renderArmorLayer(entityIn, p_212842_2_, p_212842_3_, p_212842_4_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_, Armor.ArmorType.LEGS);
      this.renderArmorLayer(entityIn, p_212842_2_, p_212842_3_, p_212842_4_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_, Armor.ArmorType.HEAD);
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   private void renderArmorLayer(T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, Armor.ArmorType slotIn) {
	   if (modelArmor == null || entityLivingBaseIn == null) return;
	   this.modelArmor.isSneak = false;
	   this.modelLeggings.isSneak = false;
	   if (entityLivingBaseIn.isSneaking()) {
		  this.modelArmor.isSneak = true;
		  this.modelLeggings.isSneak = true;
	  }
	   
	   A a = this.func_215337_a(slotIn);
       a = getArmorModelHook(entityLivingBaseIn, new ItemStackT(ItemsT.GOGGLES), slotIn, a);
       ((BipedModel)this.getEntityModel()).func_217148_a(a);
       a.setLivingAnimations(entityLivingBaseIn, limbSwing, limbSwingAmount, partialTicks);
       GL11.glPushMatrix();
       GL11.glTranslatef(0, -1000, 0);
       a.render(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
       GL11.glPopMatrix();
       this.setModelSlotVisible(this.modelArmor, Armor.ArmorType.CHEST, limbSwing, limbSwingAmount, (PlayerEntity)entityLivingBaseIn);
       
   }

   public A func_215337_a(ArmorType slotIn) {
      return (A)(this.isLegSlot(slotIn) ? this.modelLeggings : this.modelArmor);
   }

   private boolean isLegSlot(ArmorType slotIn) {
      return slotIn == ArmorType.LEGS;
   }

   public static <T extends Entity> void func_215338_a(Consumer<ResourceLocation> p_215338_0_, T p_215338_1_, EntityModel<T> p_215338_2_, float p_215338_3_, float p_215338_4_, float p_215338_5_, float p_215338_6_, float p_215338_7_, float p_215338_8_, float p_215338_9_) {
      float f = (float)p_215338_1_.ticksExisted + p_215338_5_;
      p_215338_0_.accept(ENCHANTED_ITEM_GLINT_RES);
      GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
      gamerenderer.setupFogColor(true);
      GlStateManager.enableBlend();
      GlStateManager.depthFunc(514);
      GlStateManager.depthMask(false);
      float f1 = 0.5F;
      GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);

      for(int i = 0; i < 2; ++i) {
         GlStateManager.disableLighting();
         GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
         float f2 = 0.76F;
         GlStateManager.color4f(0.38F, 0.19F, 0.608F, 1.0F);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         float f3 = 0.33333334F;
         GlStateManager.scalef(0.33333334F, 0.33333334F, 0.33333334F);
         GlStateManager.rotatef(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.translatef(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
         GlStateManager.matrixMode(5888);
         p_215338_2_.render(p_215338_1_, p_215338_3_, p_215338_4_, p_215338_6_, p_215338_7_, p_215338_8_, p_215338_9_);
         GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      }

      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.enableLighting();
      GlStateManager.depthMask(true);
      GlStateManager.depthFunc(515);
      GlStateManager.disableBlend();
      gamerenderer.setupFogColor(false);
   }

   @Deprecated //Use the more sensitive version getArmorResource below
   private ResourceLocation getArmorResource(ArmorItem armor, boolean p_177181_2_) {
      return this.getArmorResource(armor, p_177181_2_, (String)null);
   }

   @Deprecated //Use the more sensitive version getArmorResource below
   private ResourceLocation getArmorResource(ArmorItem armor, boolean p_177178_2_, @Nullable String p_177178_3_) {
      String s = "textures/models/armor/" + armor.getArmorMaterial().getName() + "_layer_" + (p_177178_2_ ? 2 : 1) + (p_177178_3_ == null ? "" : "_" + p_177178_3_) + ".png";
      return ARMOR_TEXTURE_RES_MAP.computeIfAbsent(s, ResourceLocation::new);
   }


   /*=================================== FORGE START =========================================*/

   /**
    * Hook to allow item-sensitive armor model. for LayerBipedArmor.
    */
   protected A getArmorModelHook(LivingEntity entity, ItemStackT stack, ArmorType slotIn, A model) {
      return model;
   }

   /**
    * More generic ForgeHook version of the above function, it allows for Items to have more control over what texture they provide.
    *
    * @param entity Entity wearing the armor
    * @param stack ItemStack for the armor
    * @param slotIn Slot ID that the item is in
    * @param type Subtype, can be null or "overlay"
    * @return ResourceLocation pointing at the armor's texture
    */
   public ResourceLocation getArmorResource(net.minecraft.entity.Entity entity, ItemStackT stack, ArmorType slotIn, @javax.annotation.Nullable String type) {
	   Armor item = (Armor)stack.item;
      String texture = item.itemName;
      String domain = "trewrite";
      int idx = texture.indexOf(':');
      if (idx != -1)
      {
         domain = texture.substring(0, idx);
         texture = texture.substring(idx + 1);
      }
      String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, (isLegSlot(slotIn) ? 2 : 1), type == null ? "" : String.format("_%s", type));
      
//      s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, s1, slotIn, type);
      ResourceLocation resourcelocation = (ResourceLocation)ARMOR_TEXTURE_RES_MAP.get(s1);

      if (resourcelocation == null)
      {
         resourcelocation = new ResourceLocation(s1);
         ARMOR_TEXTURE_RES_MAP.put(s1, resourcelocation);
      }

      return resourcelocation;
   }
   /*=================================== FORGE END ===========================================*/
   protected abstract void setModelSlotVisible(A p_188359_1_, ArmorType slotIn, float limbSwing, float limbSwingAmount, PlayerEntity player);

   protected abstract void setModelVisible(A model);
}