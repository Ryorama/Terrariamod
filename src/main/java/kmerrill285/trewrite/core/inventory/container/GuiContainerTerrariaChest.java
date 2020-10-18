/*
 * Copyright (C) 2018-2019  C4
 *
 * This file is part of Curios, a mod made for Minecraft.
 *
 * Curios is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Curios is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Curios.  If not, see <https://www.gnu.org/licenses/>.
 */

package kmerrill285.trewrite.core.inventory.container;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.core.client.KeyRegistry;
import kmerrill285.trewrite.core.inventory.InventoryChestTerraria;
import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventorySlot.ItemType;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryChest;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryTerraria;
import kmerrill285.trewrite.core.network.client.CPacketThrowItemTerraria;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiContainerTerrariaChest extends ContainerScreen<ContainerTerrariaChest> {
	
    public float oldMouseX;
    public float oldMouseY;
    
    private boolean buttonClicked;
    
    private int accessory = 0, trash = 1, dye = 2, vanity = 3, helmet = 4, chestplate = 5, leggings = 6, hat = 7, shirt = 8, pants = 9;
    private int[] TOP_ARMOR = {8, 8}, TOP_ARMOR_VANITY = {80, 8}, TRASHCAN = {8, 66}, INVENTORY_TOPLEFT = {8, 84}, HOTBAR_LEFT = {8, 142}, 
    		ACCESSORY_TOPLEFT = {197, 71}, CRAFTING_TOPLEFT = {120, 8}, ICONS = {69, 168};
    
    public InventorySlot selectedSlot = null;
    public InventorySlot trashSlot = null;

    public int WIDTH = 195, HEIGHT = 165;
    
    public GuiContainerTerrariaChest(ContainerTerrariaChest container, PlayerInventory player, ITextComponent text) {
    	super(container, player, text);
    	init(container, player.player, 0, 0);
    }
    
    public void init(ContainerTerrariaChest container, PlayerEntity player, float oldMouseX, float oldMouseY) {
        this.passEvents = true;
        this.oldMouseX = oldMouseX;
        this.oldMouseY = oldMouseY;
        InventoryChestTerraria inventory = ContainerTerrariaChest.inventory;
        
        inventory.holdingSlot = new InventorySlot(ItemType.ANY, 0, 0, 0, 0);
        trashSlot = inventory.trash;
    }

    
    
    @Override
    public void init() {
        super.init();
        this.guiLeft = (int)((this.width - this.xSize) * 0.35);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        oldMouseX = (float)mouseX;
        oldMouseY = (float)mouseY;
    }
    
    public void renderHoveredToolTip(int p_191948_1_, int p_191948_2_) {
    	InventoryChestTerraria inventory = ContainerTerrariaChest.inventory;
        if (inventory.holdingSlot.stack == null && this.selectedSlot != null && this.selectedSlot.stack != null) {
           this.renderTooltip(this.selectedSlot.stack, p_191948_1_, p_191948_2_);
        }
     }
    
    protected void renderTooltip(ItemStackT p_renderTooltip_1_, int p_renderTooltip_2_, int p_renderTooltip_3_) {
        FontRenderer font = p_renderTooltip_1_.itemForRender.getItem().getFontRenderer(p_renderTooltip_1_.itemForRender);
        net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(p_renderTooltip_1_.itemForRender);
        this.renderTooltip(this.getTooltipFromItem(p_renderTooltip_1_), p_renderTooltip_2_, p_renderTooltip_3_, (font == null ? this.font : font));
        net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
     }

     public List<String> getTooltipFromItem(ItemStackT p_getTooltipFromItem_1_) {
        List<ITextComponent> list = p_getTooltipFromItem_1_.getTooltip(this.minecraft.player, this.minecraft.gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);
        List<String> list1 = Lists.newArrayList();

        for(ITextComponent itextcomponent : list) {
           list1.add(itextcomponent.getFormattedText());
        }

        return list1;
     }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {

        if (KeyRegistry.openInventory.isActiveAndMatches(InputMappings.getInputByCode(p_keyPressed_1_, p_keyPressed_2_))) {
            this.minecraft.player.closeScreen();
            return true;
        } else {
            return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //this.fontRenderer.drawString(I18n.format("container.crafting"), 97, 8, 4210752);
    	
        
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static ResourceLocation CHEST_BACKGROUND = new ResourceLocation("trewrite", "textures/gui/chest.png");
    /**
     * Draws the background layer of this container (behind the item).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	
    	
    	selectedSlot = null;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.WIDTH, this.HEIGHT);
        
//        InventoryScreen.drawEntityOnScreen(i + 51, j + 75, 30, (float)(i + 51) - mouseX,
//                (float)(j + 75 - 50) - mouseY, this.minecraft.player);
        
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
        
        this.blit(i + TRASHCAN[0], j + TRASHCAN[1], ICONS[0] + trash * 17, ICONS[1], 16, 16);
        //23, 191
        InventoryChestTerraria inventory = ContainerTerrariaChest.inventory;
        for (int m = 0; m < InventoryChestTerraria.MAIN_SLOTS; m++) {
        	if (inventory.main[m].stack != null) {
        		GuiContainerTerrariaChest.renderItemIntoGUI(inventory.main[m].stack, i + inventory.main[m].x, j + inventory.main[m].y);
    		}
        	if (mouseInRect(inventory.main[m].x + i, inventory.main[m].y + j, 16, 16, mouseX, mouseY)) {
        		selectedSlot = inventory.main[m];
        		this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
        		this.drawTexturedRectangle(inventory.main[m].x + i, inventory.main[m].y + j, 23, 191, 16, 16);
        	}
        }
        
        for (int m = 0; m < InventoryChestTerraria.MAIN_SLOTS; m++) {
        	if (inventory.chest[m].stack != null) {
        		GuiContainerTerrariaChest.renderItemIntoGUI(inventory.chest[m].stack, i + inventory.chest[m].x, j + inventory.chest[m].y);
    		}
        	if (mouseInRect(inventory.chest[m].x + i, inventory.chest[m].y + j, 16, 16, mouseX, mouseY)) {
        		selectedSlot = inventory.chest[m];
        		this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
        		this.drawTexturedRectangle(inventory.chest[m].x + i, inventory.chest[m].y + j, 23, 191, 16, 16);
        	}
        }
        
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        for (int m = 0; m < InventoryChestTerraria.HOTBAR_SLOTS; m++) {
        	if (inventory.hotbar[m].stack != null) {
        		GuiContainerTerrariaChest.renderItemIntoGUI(inventory.hotbar[m].stack, i + inventory.hotbar[m].x, j + inventory.hotbar[m].y);
    		}
        	if (mouseInRect(inventory.hotbar[m].x + i, inventory.hotbar[m].y + j, 16, 16, mouseX, mouseY)) {
        		selectedSlot = inventory.hotbar[m];
        		this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
        		this.drawTexturedRectangle(inventory.hotbar[m].x + i, inventory.hotbar[m].y + j, 23, 191, 16, 16);
        	}
        }
        
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (inventory.trash.stack != null) {
			GuiContainerTerrariaChest.renderItemIntoGUI(inventory.trash.stack, i + inventory.trash.x, j + inventory.trash.y);
		}
        if (mouseInRect(inventory.trash.x + i, inventory.trash.y + j, 16, 16, mouseX, mouseY)) {
        	selectedSlot = inventory.trash;
        	this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
    		this.drawTexturedRectangle(inventory.trash.x + i, inventory.trash.y + j, 23, 191, 16, 16);
    	}
        //inventory start: 7, 83.  Slot size: 18 x 18, Recipe start: 128, 15.  Gap: 1px, Max rows: 2
        //up arrow: 40, 191
        //down arrow: 40, 196
        //arrow size: 7x4
        
        //top arrow location in gui: 241, 9
        //bottom arrow location in gui: 241, 54
        
        {
        	int rx = i + 128;
        	int ry = j + 15;
        	int width = 18;
        	int height = 18;
        	int gap = 1;
        	int row = 6;
        	this.minecraft.getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        	
        
        if (inventory.holdingSlot.stack != null) {
        	renderItemIntoGUI(inventory.holdingSlot.stack, mouseX - 8, mouseY - 8);
        } else {
        	
        }
        }
       
        
    }
    
    public static void drawTexturedRectangle(int x, int y, int u, int v, int w, int h) {
    	GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();
//        GlStateManager.disableBlend();
    	Minecraft.getInstance().ingameGUI.blit(x, y, u, v, w,h);
//    	GlStateManager.enableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableDepthTest();
    }
    
    public static void renderItemIntoGUI(ItemStackT item, int x, int y) {
    	//drawString(instance.fontRenderer, ""+Util.watchTime, 5, 10 + i * textSize, 0xFFFFFF);
    	GlStateManager.enableRescaleNormal();
        RenderHelper.enableGUIStandardItemLighting();
        
    	GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();
        GlStateManager.disableBlend();
        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(item.itemForRender, x, y);
        GlStateManager.enableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableDepthTest();
        
    	RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        
    	if (item.size > 1) {
    		GlStateManager.disableLighting();
            GlStateManager.disableDepthTest();
            GlStateManager.disableBlend();
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(""+item.size, x + 16 - (""+item.size).length()*5.5f, y+8, 0xFFFFFF);
            GlStateManager.enableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableDepthTest();
            // Fixes opaque cooldown overlay a bit lower
            // TODO: check if enabled blending still screws things up down the line.
            GlStateManager.enableBlend();
    	}
    	Minecraft.getInstance().getTextureManager().bindTexture(GuiContainerTerrariaChest.CHEST_BACKGROUND);
    	
    }
    
    private boolean mouseInRect(int x, int y, int width, int height, int mouseX, int mouseY) {
    	return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    /**
     * Test if the 2D point is in a rectangle (relative to the GUI). Args : rectX, rectY, rectWidth, rectHeight, pointX,
     * pointY
     */
    @Override
    protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, double pointX, double pointY) {
        return true;
    }
    
    public boolean rightDown = false;
    public long rightClicked = 0;
    
   
    
    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
    	
    	
    	InventoryChestTerraria inventory = ContainerTerrariaChest.inventory;
    	if (inventory.holdingSlot != null && inventory.holdingSlot.stack != null)
        	if (mouseX < this.guiLeft || mouseY < this.guiTop || mouseX > this.guiLeft + this.WIDTH || mouseY > this.guiTop + this.HEIGHT) {
        		if (mouseButton == 0) {
//        			EntityItemT item = (EntityItemT) EntitiesT.ITEM.spawn(minecraft.player.world, null, null, minecraft.player.getPosition().up(), SpawnReason.EVENT, false, false);
        			EntityItemT.spawnItem(minecraft.player.world, minecraft.player.getPosition().up(), new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier)), 20 * 4);
//            		item.item = inventory.holdingSlot.stack.item.itemName;
//        			item.stack = inventory.holdingSlot.stack.size;
//        			item.pickupDelay = 20 * 4;
            		NetworkHandler.INSTANCE.sendToServer(new CPacketThrowItemTerraria(0, -1, 0, new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier))));
            		inventory.holdingSlot.stack = null;
        		}
            	
        	}
    	
    	
    	
    	
    	
    	if (selectedSlot == null) return true;
    	
    	
    	
    	rightDown = false;
    	
    	
    	
    	if (KeyRegistry.trash.isKeyDown()) {
	        	if (mouseButton == 0) {
	        		if (selectedSlot.stack != null) {
	        			ItemStackT stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
	        			trashSlot.stack = stack;
	        			selectedSlot.stack = null;
	        			
	        			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryChest(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack, ContainerTerrariaChest.position));
	        			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryChest(0, trashSlot.area, trashSlot.id, trashSlot.stack, ContainerTerrariaChest.position));
	                	ContainerTerrariaInventory.inventory.trash.stack = trashSlot.stack;
	        			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, ContainerTerrariaInventory.inventory.trash.area, ContainerTerrariaInventory.inventory.trash.id, ContainerTerrariaInventory.inventory.trash.stack));

	        		}
	        		return true;
	        	}
	        }
    	
    	if (mouseButton == 0) {
    		if (inventory.holdingSlot.stack != null && selectedSlot.isTrashSlot == true) {
    			ItemStackT stack = new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier));
    			selectedSlot.stack = stack;
    			inventory.holdingSlot.stack = null;
    		} else {
    			if (inventory.holdingSlot.stack == null) {
        			if (selectedSlot.stack != null) {
        				inventory.holdingSlot.stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
        				selectedSlot.stack = null;
        			}
        		} else {
        			if (selectedSlot.stack == null && selectedSlot.canInteractWith(inventory.holdingSlot)) {
        				selectedSlot.stack = new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier));
        				inventory.holdingSlot.stack = null;
        			} else {
        				selectedSlot.takeFromStack(inventory.holdingSlot);
        			}
        		}
    		}
    		
    	}
    	if (mouseButton == 1) {
    		rightDown = true;
    		rightClicked = System.currentTimeMillis();
    		if (selectedSlot.stack != null) 
    		{
    			if (inventory.holdingSlot.stack == null) {
    				inventory.holdingSlot.stack = new ItemStackT(selectedSlot.stack.item, ItemModifier.getModifier(selectedSlot.stack.modifier));
    				selectedSlot.decrementStack(1);
    			} else {
    				if (inventory.holdingSlot.stack.item instanceof ItemT) {
	    				if (inventory.holdingSlot.stack.size + 1 < ((ItemT)inventory.holdingSlot.stack.item).maxStack) {
	    					inventory.holdingSlot.stack.size++;
	    					selectedSlot.decrementStack(1);
	    				}
    				} else {
    					if (inventory.holdingSlot.stack.size + 1 < inventory.holdingSlot.stack.itemForRender.getMaxStackSize()) {
    						inventory.holdingSlot.stack.size++;
    						selectedSlot.decrementStack(1);
    					}
    				}
    			}
    		}
    	}
    	
    	int area = selectedSlot.area;
    	InventoryTerraria inv = ContainerTerrariaInventory.inventory;
    	if (area == 0) {
    		inv.main[selectedSlot.id].stack = selectedSlot.stack;
    	}
    	if (area == 1) {
    		inv.hotbar[selectedSlot.id].stack = selectedSlot.stack;
    	}
    	
    	inv.trash.stack = trashSlot.stack;
    	
    	
		NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryChest(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack, ContainerTerrariaChest.position));

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double mouseReleased1, double mouseReleased3, int mouseReleased5) {
    	if (selectedSlot != null)
    		NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryChest(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack, ContainerTerrariaChest.position));
    	rightDown = false;
        if (this.buttonClicked) {
            this.buttonClicked = false;
            return true;
        } else {
            return super.mouseReleased(mouseReleased1, mouseReleased3, mouseReleased5);
        }
    }
}
