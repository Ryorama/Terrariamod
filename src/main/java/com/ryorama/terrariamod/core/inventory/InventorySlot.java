package com.ryorama.terrariamod.core.inventory;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class InventorySlot {
	
	public static final ResourceLocation SLOT_TEXTURE = new ResourceLocation("terrariamod", "textures/gui/slot.png");
	  
	  public int originalX;
	  
	  public int originalY;
	  
	  IInventory inventory;
	  
	  public int id;
	  
	  public int x;
	  
	  public int y;
	  
	  public int iwidth;
	  
	  public int iheight;
	  
	  public int swidth;
	  
	  public int sheight;
	  	  
	  int slotNumber;
	  
	  InventoryContainer listener;
	  
	  public InventorySlot(IInventory inventory, int id, int x, int y, int itemWidth, int itemHeight, int slotWidth, int slotHeight) {
		    this.inventory = inventory;
		    this.id = id;
		    this.x = this.originalX = x;
		    this.y = this.originalY = y;
		    this.iwidth = itemWidth;
		    this.iheight = itemHeight;
		    this.swidth = slotWidth;
		    this.sheight = slotHeight;
	  }
	  

	  public void onPostSetStack() {}
	  
	  public boolean isStackValid(ItemStack stack) {
	    return this.inventory.isItemValidForSlot(getId(), stack);
	  }
  
	  public InventoryContainer getListener() {
	    return this.listener;
	  }
	  
	  public int getId() {
		    return this.id;
		  }
		  
		  public int getX() {
		    return this.x;
		  }
		  
		  public int getY() {
		    return this.y;
		  }
		  
		  public float getItemX() {
		    return this.x + (this.swidth - this.iwidth) / 2.0F;
		  }
		  
		  public float getItemY() {
		    return this.y + (this.sheight - this.iheight) / 2.0F;
		  }
		  
		  public int getItemHeight() {
		    return this.iheight;
		  }
		  
		  public int getItemWidth() {
		    return this.iwidth;
		  }
		  
		  public int getSlotHeight() {
		    return this.sheight;
		  }
		  
		  public int getSlotWidth() {
		    return this.swidth;
		  }
		  
		  public int getSlotNumber() {
		    return this.slotNumber;
		  }
		  
		  public ItemStack getStack() {
		    return this.inventory.getStackInSlot(this.id);
		  }
		  
		  public ItemStack getCachedStack() {
		    return (ItemStack)this.listener.getInventory().get(this.id);
		  }
		  
		  public void setStack(ItemStack stack) {
			 this.inventory.setInventorySlotContents(this.id, stack);
			 this.inventory.markDirty();
			 this.listener.detectAndSendChanges();
		  }
			  
		  public IInventory getInventory() {
			  return this.inventory;
		  }
	
		  
		    @OnlyIn(Dist.CLIENT)
		    public void render(InventorySlot slot) {
		      if (slot.getStack().isEmpty()) {
		        GlStateManager.pushMatrix();
		        GlStateManager.translatef(slot.getItemX(), slot.getItemY(), 0.0F);
		        GlStateManager.scalef(slot.getItemWidth() / this.getSlotWidth() * 256.0F, slot.getItemHeight() / this.getSlotHeight() * 256.0F, 0.0F);
		        GlStateManager.popMatrix();
		      } 
		    }
		  
}
