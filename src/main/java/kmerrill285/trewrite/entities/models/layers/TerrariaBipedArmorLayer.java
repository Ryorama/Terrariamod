package kmerrill285.trewrite.entities.models.layers;

import org.lwjgl.opengl.GL11;

import kmerrill285.trewrite.items.Armor.ArmorType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TerrariaBipedArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends TerrariaArmorLayer<T, M, A> {
   public TerrariaBipedArmorLayer(IEntityRenderer<T, M> p_i50936_1_, A p_i50936_2_, A p_i50936_3_) {
      super(p_i50936_1_, p_i50936_2_, p_i50936_3_);
   }

   protected void setModelSlotVisible(A p_188359_1_, ArmorType slotIn) {
      this.setModelVisible(p_188359_1_);
      switch(slotIn) {
      case HEAD:
         p_188359_1_.bipedHead.showModel = true;
         p_188359_1_.bipedHeadwear.showModel = true;
         break;
      case CHEST:
         p_188359_1_.bipedBody.showModel = true;
         p_188359_1_.bipedRightArm.showModel = true;
         p_188359_1_.bipedLeftArm.showModel = true;
         break;
      case LEGS:
         p_188359_1_.bipedBody.showModel = true;
         p_188359_1_.bipedRightLeg.showModel = true;
         p_188359_1_.bipedLeftLeg.showModel = true;
         break;
      }
      
	  this.bindTexture(new ResourceLocation("trewrite:textures/block/clay_block.png"));
	  
      
   }
   

   protected void setModelVisible(A model) {
      model.setVisible(false);
   }
   
}
