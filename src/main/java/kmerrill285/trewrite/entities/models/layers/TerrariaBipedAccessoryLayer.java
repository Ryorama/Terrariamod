package kmerrill285.trewrite.entities.models.layers;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import kmerrill285.trewrite.core.client.ClientProxy;
import kmerrill285.trewrite.items.Armor.ArmorType;
import kmerrill285.trewrite.items.accessories.Accessory;
import kmerrill285.trewrite.items.accessories.Accessory.WearSlot;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TerrariaBipedAccessoryLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends TerrariaAccessoryLayer<T, M, A> {
   public TerrariaBipedAccessoryLayer(IEntityRenderer<T, M> p_i50936_1_, A p_i50936_2_, A p_i50936_3_) {
      super(p_i50936_1_, p_i50936_2_, p_i50936_3_);
   }

   protected void setModelSlotVisible(A p_188359_1_, ArmorType slotIn, float limbSwing, float limbSwingAmount, PlayerEntity player) {
      this.setModelVisible(p_188359_1_);
    
     p_188359_1_.bipedHead.showModel = true;
     p_188359_1_.bipedHeadwear.showModel = true;
 
     p_188359_1_.bipedBody.showModel = true;
     p_188359_1_.bipedRightArm.showModel = true;
     p_188359_1_.bipedLeftArm.showModel = true;
   
     p_188359_1_.bipedBody.showModel = true;
     p_188359_1_.bipedRightLeg.showModel = true;
     p_188359_1_.bipedLeftLeg.showModel = true;
       
	  
      A bipedModel = p_188359_1_;
      
	  
      
      ArrayList<ItemStack> items = ClientProxy.playerAccessories.get(player.getScoreboardName());
      if (items == null) {
    	  return;
      }
      if (items.isEmpty()) {
    	  return;
      }
      
      String head = null, chest = null, left_leg = null, right_leg = null, right_arm = null, left_arm = null, wings = null, fins = null, gloves = null, boots = null;
      
      for (ItemStack stack : items) {
    	  if (stack.getItem() instanceof Accessory) {
    		  Accessory a = (Accessory)stack.getItem();
    		  if (a.wearSlot == WearSlot.HEAD) {
    			  head = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.CHEST) {
    			  chest = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.SINGLE_LEG) {
    			  if (left_leg == null) {
    				  left_leg = a.wearTexture;
    			  } else {
    				  if (right_leg == null) {
    					  right_leg = a.wearTexture;
    				  }
    			  }
    		  }
    		  if (a.wearSlot == WearSlot.BOTH_LEGS) {
    			  left_leg = a.wearTexture;
    			  right_leg = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.BOTH_ARMS) {
    			  left_arm = a.wearTexture;
    			  right_arm = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.DIVING_FINS) {
    			  fins = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.WINGS) {
    			  wings = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.GLOVES) {
    			  gloves = a.wearTexture;
    		  }
    		  if (a.wearSlot == WearSlot.FEET) {
    			  boots = a.wearTexture;
    		  }
    	  }
      }
      
      
      
      if (chest != null) {
    	  this.bindTexture(new ResourceLocation(chest));
    	  renderBody(bipedModel);
      }
      
      if (head != null) {
    	  this.bindTexture(new ResourceLocation(head));
    	  renderHead(bipedModel);
      }
      
      if (left_arm != null) {
    	  this.bindTexture(new ResourceLocation(left_arm));
    	  renderLeftArm(bipedModel);
      }
      
      if (right_arm != null) {
    	  this.bindTexture(new ResourceLocation(right_arm));
    	  renderRightArm(bipedModel);
      }
      
      if (left_leg != null) {
    	  this.bindTexture(new ResourceLocation(left_leg));
    	  renderLeftLeg(bipedModel);
      }
      
      if (right_leg != null) {
    	  this.bindTexture(new ResourceLocation(right_leg));
    	  renderRightLeg(bipedModel);
      }
      
      if (fins != null) {
    	  this.bindTexture(new ResourceLocation(fins));
    	  renderDivingFins(bipedModel);
      }
	  
      if (wings != null) {
    	  this.bindTexture(new ResourceLocation(wings));
    	  renderWings(bipedModel);
      }
      
      if (gloves != null) {
    	  this.bindTexture(new ResourceLocation(gloves));
    	  renderLeftArm(bipedModel);
    	  renderRightArm(bipedModel);
      }
      
      if (boots != null) {
    	  this.bindTexture(new ResourceLocation(boots));
    	  renderLeftLeg(bipedModel);
    	  renderRightLeg(bipedModel);
      }
   }
   
   public void renderBody(A bipedModel) {
	   if (bipedModel.isSneak) {
	          bipedModel.bipedBody.showModel = true;
	          GL11.glPushMatrix();
//	          GL11.glRotatef(24, 1, 0, 0);
	          GL11.glTranslatef(0, 0.22f, -0.04f);
	          GL11.glScalef(0.93f, 0.93f, 0.93f);
	          bipedModel.bipedBody.render(0.075f);
	          GL11.glPopMatrix();
	      } else {
	          bipedModel.bipedBody.showModel = true;
	          GL11.glPushMatrix();
	          
//	          GL11.glTranslatef(0, 0.05f, 0.0f);
	          GL11.glScalef(0.91f, 0.91f, 0.91f);
	          bipedModel.bipedBody.render(0.075f);
	          GL11.glPopMatrix();
	      }
   }
   
   public void renderRightArm(A bipedModel) {
	   if (bipedModel.isSneak) {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.9f, 0.9f, 0.9f);
	       GL11.glTranslatef(0, 0.24f, -0.03f);
//		   GL11.glTranslatef(-4.65f, -1.85f, 0.0f);
		   bipedModel.bipedRightArm.showModel = true;
		   bipedModel.bipedRightArm.render(0.075f);
		   GL11.glPopMatrix();
	   } else {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.9f, 0.9f, 0.9f);
//		   GL11.glTranslatef(-4.65f, -1.85f, 0.0f);
		   bipedModel.bipedRightArm.showModel = true;
		   bipedModel.bipedRightArm.render(0.075f);
		   GL11.glPopMatrix();
	   }
   }
   
   public void renderLeftArm(A bipedModel) {
	   if (bipedModel.isSneak) {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.9f, 0.9f, 0.9f);
	       GL11.glTranslatef(0, 0.24f, -0.03f);
//		   GL11.glTranslatef(-4.65f, -1.85f, 0.0f);
		   bipedModel.bipedLeftArm.showModel = true;
		   bipedModel.bipedLeftArm.render(0.075f);
		   GL11.glPopMatrix();
	   } else {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.9f, 0.9f, 0.9f);
//		   GL11.glTranslatef(-4.65f, -1.85f, 0.0f);
		   bipedModel.bipedLeftArm.showModel = true;
		   bipedModel.bipedLeftArm.render(0.075f);
		   GL11.glPopMatrix();
	   }
	   
   }
   
   public void renderRightLeg(A bipedModel) {
	   if (bipedModel.isSneak) {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.93f, 0.93f, 0.93f);
	       GL11.glTranslatef(0, 0.24f, -0.03f);

		   bipedModel.bipedRightLeg.showModel = true;
		   bipedModel.bipedRightLeg.render(0.07f);
		   GL11.glPopMatrix();
	   } else {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.93f, 0.93f, 0.93f);

		   bipedModel.bipedRightLeg.showModel = true;
		   bipedModel.bipedRightLeg.render(0.07f);
		   GL11.glPopMatrix();
	   }
   }
   
   public void renderLeftLeg(A bipedModel) {
	   if (bipedModel.isSneak) {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.93f, 0.93f, 0.93f);
	       GL11.glTranslatef(0, 0.24f, -0.03f);

		   bipedModel.bipedLeftLeg.showModel = true;
		   bipedModel.bipedLeftLeg.render(0.07f);
		   GL11.glPopMatrix();
	   } else {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.93f, 0.93f, 0.93f);

		   bipedModel.bipedLeftLeg.showModel = true;
		   bipedModel.bipedLeftLeg.render(0.07f);
		   GL11.glPopMatrix();
	   }
	   
   }
   
   public void renderHead(A bipedModel) {
	   if (bipedModel.isSneak) {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.92f, 0.92f, 0.92f);
	       GL11.glTranslatef(0, 0.24f, -0.03f);
//		   GL11.glTranslatef(-4.65f, -1.85f, 0.0f);
		   bipedModel.bipedHead.showModel = true;
		   bipedModel.bipedHead.render(0.072f);
		   GL11.glPopMatrix();
	   } else {
		   GL11.glPushMatrix();
	       GL11.glScalef(0.9f, 0.9f, 0.9f);
//		   GL11.glTranslatef(-4.65f, -1.85f, 0.0f);
		   bipedModel.bipedHead.showModel = true;
		   bipedModel.bipedHead.render(0.075f);
		   GL11.glPopMatrix();
	   }
   }
   
   public void renderWings(A bipedModel) {
	   
   }
   
   public void renderDivingFins(A bipedModel) {
	   
   }

   protected void setModelVisible(A model) {
      model.setVisible(false);
   }
   
}
