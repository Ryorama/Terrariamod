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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.core.client.KeyRegistry;
import kmerrill285.trewrite.core.client.TerrariaUIManager;
import kmerrill285.trewrite.core.client.UIRenderer;
import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventorySlot.ItemType;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryTerraria;
import kmerrill285.trewrite.core.network.client.CPacketThrowItemTerraria;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.*;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.items.terraria.clickable.Clickable;
import kmerrill285.trewrite.items.terraria.loot_bags.LootBag;
import kmerrill285.trewrite.items.terraria.loot_bags.LootStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.NoteBlockEvent;
import org.lwjgl.system.CallbackI;

@OnlyIn(Dist.CLIENT)
public class GuiContainerTerrariaInventory extends ContainerScreen<ContainerTerrariaInventory> {

	public ResourceLocation slot_texture = new ResourceLocation(Trewrite.modid, "textures/gui/slot.png");
	public ResourceLocation slot_texture2 = new ResourceLocation(Trewrite.modid, "textures/gui/slot2.png");
	public ResourceLocation slot_texture3 = new ResourceLocation(Trewrite.modid, "textures/gui/slot3.png");
	public ResourceLocation slot_texture4 = new ResourceLocation(Trewrite.modid, "textures/gui/slot4.png");
	public ResourceLocation slot_selected = new ResourceLocation(Trewrite.modid, "textures/gui/slot_selected.png");
	public ResourceLocation trash_slot = new ResourceLocation(Trewrite.modid, "textures/gui/trash_slot.png");
	public ResourceLocation trash_icon = new ResourceLocation(Trewrite.modid, "textures/gui/trash.png");
	public ResourceLocation fav = new ResourceLocation(Trewrite.modid, "textures/gui/fav.png");

	public ResourceLocation helmet_icon = new ResourceLocation(Trewrite.modid, "textures/gui/helmet.png");
	public ResourceLocation chestplate_icon = new ResourceLocation(Trewrite.modid, "textures/gui/chestplate.png");
	public ResourceLocation boots_icon = new ResourceLocation(Trewrite.modid, "textures/gui/boots.png");
	public ResourceLocation hat_icon = new ResourceLocation(Trewrite.modid, "textures/gui/hat.png");
	public ResourceLocation shirt_icon = new ResourceLocation(Trewrite.modid, "textures/gui/shirt.png");
	public ResourceLocation leggings_icon = new ResourceLocation(Trewrite.modid, "textures/gui/leggings.png");
	public ResourceLocation accessory_icon = new ResourceLocation(Trewrite.modid, "textures/gui/artifact.png");
	public ResourceLocation dye_icon = new ResourceLocation(Trewrite.modid, "textures/gui/dye.png");
	public ResourceLocation vanity_icon = new ResourceLocation(Trewrite.modid, "textures/gui/artifact_social.png");

	public float oldMouseX;
    public float oldMouseY;
    
    private boolean buttonClicked;
    
    public InventorySlot selectedSlot = null;
    public InventorySlot trashSlot = null;

    public int WIDTH = 256, HEIGHT = 164;
    
    private int[] TOP_ARMOR = {8, 8}, TOP_ARMOR_VANITY = {80, 8}, TRASHCAN = {8, 66}, INVENTORY_TOPLEFT = {8, 84}, HOTBAR_LEFT = {8, 142}, 
    		ACCESSORY_TOPLEFT = {197, 71}, CRAFTING_TOPLEFT = {120, 8}, ICONS = {69, 168};
    
    private int accessory = 0, trash = 1, dye = 2, vanity = 3, helmet = 4, chestplate = 5, leggings = 6, hat = 7, shirt = 8, pants = 9;

    public  ArrayList<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();
    public  ArrayList<CraftingRecipe> availableRecipes = new ArrayList<CraftingRecipe>();
    public CraftingRecipe selectedRecipe = null;
    public int page = 0;
    public boolean upSelected = false, downSelected = false;
    
    public GuiContainerTerrariaInventory(ContainerTerrariaInventory container, PlayerInventory player, ITextComponent text) {
    	super(container, player, text);
    	init(container, player.player, 0, 0);
    }
    
    public void init(ContainerTerrariaInventory container, PlayerEntity player, float oldMouseX, float oldMouseY) {
        this.passEvents = true;
        this.oldMouseX = oldMouseX;
        this.oldMouseY = oldMouseY;
        InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
        
        inventory.holdingSlot = new InventorySlot(ItemType.ANY, 0, 0, 0, 0);
        trashSlot = inventory.trash;
        this.resetRecipes();
    }
    
    
    public  void resetRecipes() {
    	
    	ArrayList<BlockT> blocks = new ArrayList<BlockT>();
    	
    	PlayerEntity player = Minecraft.getInstance().player;
    	if (player != null) {
    		if (player.world != null) {
    			BlockPos playerpos = player.getPosition();
    			for (int x = -2; x < 3; x++) {
    				for (int y = -2; y < 3; y++) {
    					for (int z = -2; z < 3; z++) {
    						BlockPos pos2 = new BlockPos(playerpos.getX() + x, playerpos.getY() + y, playerpos.getZ() + z);
    						if (player.world.isBlockLoaded(pos2)) {
    							if (player.world.getBlockState(pos2).getBlock() instanceof BlockT) {
    								BlockT block = (BlockT)player.world.getBlockState(pos2).getBlock();
    								if (Recipes.recipes.containsKey(block)) {
    									//System.out.println("HAS BLOCK");
    									blocks.add(block);
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	availableRecipes.clear();
    	recipes.clear();
        recipes.addAll(Recipes.getRecipesForBlock(null)); //load all crafting recipes that don't require a crafting station
        for (BlockT block : blocks) {
        	recipes.addAll(Recipes.getRecipesForBlock(block));
        	//System.out.println("Found recipes for station: " + block.name);
        }
        //System.out.println("recipe size: " + recipes.size());
        
        for (CraftingRecipe recipe : recipes) {
        	boolean add = true;
        	//System.out.println("checking items for recipe");
        	for (ItemStackT stack : recipe.input) {
        		if (hasItems(stack) == false) {
        			add = false;
        			//System.out.println("you don't have " + stack.item.itemName + " for " + recipe.output.item.itemName);
        			break;
        		}
        	}
        	if (add == true) {
        		//System.out.println("recipe added for " + recipe.output.item.itemName);
        		boolean ad = true;
        		for (CraftingRecipe r : availableRecipes) {
        			if (r.output == recipe.output) {
        				ad = false;
        			}
        		}
        		if (ad == true)
        		availableRecipes.add(recipe);
        		}
        }
    	
    }
    
    public boolean hasItems(ItemStackT item) {
    	//System.out.println("test has item: " + item.item.itemName);
    	
    	
    	boolean hasItem = false;
    	int stack = 0;
    	InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
    	if (inventory == null) return false;
    	for (int x = 0; x < 30; x++) {
    		if (x < 10)
    		if (inventory.hotbar[x].stack != null) {
    			if (ItemsT.getStringForItem(inventory.hotbar[x].stack.item).contentEquals(ItemsT.getStringForItem(item.item))) {
    				//System.out.println("has item");
    				hasItem = true;
    				stack += inventory.hotbar[x].stack.size;
    				}
    		}
    		
    		if (inventory.main[x].stack != null) {
    			if (ItemsT.getStringForItem(inventory.main[x].stack.item).contentEquals(ItemsT.getStringForItem(item.item))) {
    				//System.out.println("has item");
    				hasItem = true;
    				stack += inventory.main[x].stack.size;
    				}
    		}
    		//System.out.acprintln("stack: " + stack);
    		if (hasItem == true && stack >= item.size) {
    			//System.out.println("WE HAVE THE ITEM");
    			return true;
    			}
    	}
    	return false;
    }
    
    @Override
    public void init() {
        super.init();
        this.guiLeft = (int)((this.width - this.xSize) * 0.35);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        oldMouseX = (float)mouseX;
        oldMouseY = (float)mouseY;
    }
    
    public void renderHoveredToolTip(int p_191948_1_, int p_191948_2_) {
    	InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
        if (inventory.holdingSlot.stack == null && this.selectedSlot != null && this.selectedSlot.stack != null) {
           this.renderTooltip(this.selectedSlot.stack, p_191948_1_, p_191948_2_);
        }
        
        if (this.selectedRecipe != null) {
        	if (this.selectedRecipe.output != null)
        	this.renderTooltip(this.selectedRecipe.output, p_191948_1_, p_191948_2_);
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
            Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.MENU_CLOSE, SoundCategory.PLAYERS, 100, 1, false);
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

    //public static ResourceLocation INVENTORY_BACKGROUND = new ResourceLocation("trewrite", "textures/gui/terraria_inventory.png");

	/**
     * Draws the background layer of this container (behind the item).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		InventoryTerraria inventory = ContainerTerrariaInventory.inventory;

		float scaledWidth = Minecraft.getInstance().mainWindow.getScaledWidth();

		selectedSlot = null;

		int i = 17;
		int j = 27;

		minecraft.fontRenderer.drawString("Inventory", 17, 1, 0xffffff);
		for (int m = 0; m < InventoryTerraria.MAIN_SLOTS; m++) {
			UIRenderer.instance.renderOverlay(slot_texture, 0.75f, 16, 16, i, j, -90);
			if (inventory.main[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.main[m].stack, i, j);
			}
			if (inventory.main[m].isFavorite) {
				UIRenderer.instance.renderOverlay(fav, 100f, 16, 16, i, j, -90);
			}
			if(mouseInRect(i, j, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.main[m];
				if (KeyRegistry.favorite_item.isPressed()) {
					if (!inventory.main[m].isFavorite) {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.TICK, SoundCategory.PLAYERS, 100, 1, false);
						inventory.main[m].isFavorite = true;
					} else {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.TICK, SoundCategory.PLAYERS, 100, 1, false);
						inventory.main[m].isFavorite = false;
					}
				}
			}

			i += 17;
			if (i % 170 - 17 == 0 && i != 17) {
				i = 17;
				j += 17;
			}
		}

		int i2 = 17;
		int j2 = 10;

		for (int m = 0; m < InventoryTerraria.HOTBAR_SLOTS; m++) {
			UIRenderer.instance.renderOverlay(slot_texture, 0.75f, 16, 16, i2, j2, -90);
			if (inventory.hotbar[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.hotbar[m].stack, i2, j2);
			}
			if (inventory.hotbar[m].isFavorite) {
				UIRenderer.instance.renderOverlay(fav, 100f, 16, 16, i2, j2, -90);
			}
			if(mouseInRect(i2, j2, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.hotbar[m];
				if (KeyRegistry.favorite_item.isPressed()) {
					if (!inventory.hotbar[m].isFavorite) {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.TICK, SoundCategory.PLAYERS, 100, 1, false);
						inventory.hotbar[m].isFavorite = true;
					} else {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.TICK, SoundCategory.PLAYERS, 100, 1, false);
						inventory.hotbar[m].isFavorite = false;
					}
				}
			}

			i2 += 17;
		}

		int i3 = (int)(scaledWidth - 17);
		int j3 = 120;

		for (int m = 0; m < InventoryTerraria.ARMOR_SLOTS; m++) {
			UIRenderer.instance.renderOverlay(slot_texture2, 0.75f, 16, 16, i3, j3, -90);
			if (m == 0) {
				UIRenderer.instance.renderOverlay(helmet_icon, 0.75f, 16, 16, i3, j3, -90);
			} else if (m == 1) {
				UIRenderer.instance.renderOverlay(chestplate_icon, 0.75f, 16, 16, i3, j3, -90);
			} else {
				UIRenderer.instance.renderOverlay(boots_icon, 0.75f, 16, 16, i3, j3, -90);
			}

			if (inventory.armor[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.armor[m].stack, i3, j3);
			}
			if(mouseInRect(i3, j3, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.armor[m];
			}

			j3 += 17;
		}

		int i4 = (int)(scaledWidth - 17);
		int j4 = 171;

		for (int m = 0; m < InventoryTerraria.ACCESSORY_SLOTS; m++) {
			UIRenderer.instance.renderOverlay(slot_texture2, 0.75f, 16, 16, i4, j4, -90);
			UIRenderer.instance.renderOverlay(accessory_icon, 0.75f, 16, 16, i4, j4, -90);

			if (inventory.accessory[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.accessory[m].stack, i4, j4);
			}
			if(mouseInRect(i4, j4, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.accessory[m];
			}

			j4 += 17;
		}

		int i5 = (int)(scaledWidth - 34);
		int j5 = 120;

		for (int m = 0; m < InventoryTerraria.VANITY_ARMOR; m++) {
			UIRenderer.instance.renderOverlay(slot_texture3, 0.75f, 16, 16, i5, j5, -90);
			if (m == 0) {
				UIRenderer.instance.renderOverlay(hat_icon, 0.75f, 16, 16, i5, j5, -90);
			} else if (m == 1) {
				UIRenderer.instance.renderOverlay(shirt_icon, 0.75f, 16, 16, i5, j5, -90);
			} else {
				UIRenderer.instance.renderOverlay(leggings_icon, 0.75f, 16, 16, i5, j5, -90);
			}

			if (inventory.armorVanity[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.armorVanity[m].stack, i5, j5);
			}
			if(mouseInRect(i5, j5, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.armorVanity[m];
			}

			j5 += 17;
		}

		int i6 = (int)(scaledWidth - 34);
		int j6 = 171;

		for (int m = 0; m < InventoryTerraria.VANITY_ACCESSORY; m++) {
			UIRenderer.instance.renderOverlay(slot_texture3, 0.75f, 16, 16, i6, j6, -90);
			UIRenderer.instance.renderOverlay(vanity_icon, 0.75f, 16, 16, i6, j6, -90);

			if (inventory.accessoryVanity[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.accessoryVanity[m].stack, i6, j6);
			}
			if(mouseInRect(i6, j6, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.accessoryVanity[m];
			}

			j6 += 17;
		}

		int i7 = (int)(scaledWidth - 51);
		int j7 = 120;

		for (int m = 0; m < InventoryTerraria.ARMOR_DYE; m++) {
			UIRenderer.instance.renderOverlay(slot_texture4, 0.75f, 16, 16, i7, j7, -90);
			UIRenderer.instance.renderOverlay(dye_icon, 0.75f, 16, 16, i7, j7, -90);

			if (inventory.armorDyes[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.armorDyes[m].stack, i7, j7);
			}
			if(mouseInRect(i7, j7, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.armorDyes[m];
			}

			j7 += 17;
		}

		int i8 = (int)(scaledWidth - 51);
		int j8 = 171;

		for (int m = 0; m < InventoryTerraria.ACCESSORY_DYE; m++) {
			UIRenderer.instance.renderOverlay(slot_texture4, 0.75f, 16, 16, i8, j8, -90);
			UIRenderer.instance.renderOverlay(dye_icon, 0.75f, 16, 16, i8, j8, -90);

			if (inventory.accessoryDyes[m].stack != null) {
				GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.accessoryDyes[m].stack, i8, j8);
			}
			if(mouseInRect(i8, j8, 16, 16, mouseX, mouseY)) {
				selectedSlot = inventory.accessoryDyes[m];
			}

			j8 += 17;
		}

		int i9 = 170;
		int j9 = 99;

		UIRenderer.instance.renderOverlay(trash_slot, 0.75f, 16, 16, i9, j9, -90);
		if (inventory.trash.stack == null) {
			UIRenderer.instance.renderOverlay(trash_icon, 0.75f, 16, 16, i9, j9, -90);
		}
		if (inventory.trash.stack != null) {
			GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.trash.stack, i9, j9);
		}
		if (mouseInRect(i9, j9, 16, 16, mouseX, mouseY)) {
			selectedSlot = inventory.trash;
		}

		TerrariaUIManager.renderTerrariaDefense();

		if (inventory.holdingSlot.stack != null) {
			renderItemIntoGUI(inventory.holdingSlot.stack, mouseX - 8, mouseY - 8);
		}

		if (rightDown == true && System.currentTimeMillis() > rightClicked + 500L) {
			if (selectedSlot != null && selectedSlot.stack != null)
			{
				Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
				if (inventory.holdingSlot.stack == null) {
					inventory.holdingSlot.stack = new ItemStackT(selectedSlot.stack.item, ItemModifier.getModifier(selectedSlot.stack.modifier));
					selectedSlot.decrementStack(1);
				} else {
					int maxStack = 0;
					if (inventory.holdingSlot.stack.item instanceof ItemT) {
						maxStack = ((ItemT)inventory.holdingSlot.stack.item).maxStack;
					} else {
						maxStack = inventory.holdingSlot.stack.itemForRender.getMaxStackSize();
					}
					if (inventory.holdingSlot.stack.size + 1 < maxStack) {
						inventory.holdingSlot.stack.size++;
						selectedSlot.decrementStack(1);
					}
				}
			}
		}

		selectedRecipe = null;

		int i10 = 17;
		int j10 = 99;

		selectedRecipe = null;

		int maxRecipesShown = 5;

		for (int r = 0; r <= availableRecipes.size() - 1; r++) {
			if (availableRecipes.size() > 0) {

				if (selectedRecipe == availableRecipes.get(r)) {
					UIRenderer.instance.renderOverlay(slot_selected, 0.75f, 16, 16, i10, j10, -90);
					renderItemIntoGUI(availableRecipes.get(r).output, i10, j10);
				} else if (selectedRecipe != availableRecipes.get(r)) {
					UIRenderer.instance.renderOverlay(slot_texture, 0.75f, 16, 16, i10, j10, -90);
					renderItemIntoGUI(availableRecipes.get(r).output, i10, j10);
				}

				if (mouseInRect(i10, j10, 16, 16, mouseX, mouseY)) {
					selectedRecipe = availableRecipes.get(r);
				}

				j10 += 17;
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
        
        GlStateManager.disableDepthTest();
        GlStateManager.disableBlend();
        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(item.itemForRender, x, y);
        GlStateManager.enableBlend();
        GlStateManager.enableDepthTest();
        
    	RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        
    	if (item.size > 1) {
            GlStateManager.disableDepthTest();
            GlStateManager.disableBlend();
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(""+item.size, x + 16 - (""+item.size).length()*5.5f, y+8, 0xFFFFFF);
            GlStateManager.enableBlend();
            GlStateManager.enableDepthTest();
            // Fixes opaque cooldown overlay a bit lower
            // TODO: check if enabled blending still screws things up down the line.
            GlStateManager.enableBlend();
    	}

    	if (item.renderStack > 0) {
            GlStateManager.disableDepthTest();
            GlStateManager.disableBlend();
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(""+item.renderStack, x + 16 - (""+item.renderStack).length()*5.5f, y+8, 0xFFFFFF);
            GlStateManager.enableBlend();
            GlStateManager.enableDepthTest();
            // Fixes opaque cooldown overlay a bit lower
            // TODO: check if enabled blending still screws things up down the line.
            GlStateManager.enableBlend();
    	}
    }

	public static void renderItemIntoGUI(ItemStackT item, int x, int y, int width, int height) {
		//drawString(instance.fontRenderer, ""+Util.watchTime, 5, 10 + i * textSize, 0xFFFFFF);
		GlStateManager.popMatrix();
		GlStateManager.scalef(width, height, 0);
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

		if (item.renderStack > 0) {
			GlStateManager.disableLighting();
			GlStateManager.disableDepthTest();
			GlStateManager.disableBlend();
			Minecraft.getInstance().fontRenderer.drawStringWithShadow("" + item.renderStack, x + 16 - ("" + item.renderStack).length() * 5.5f, y + 8, 0xFFFFFF);
			GlStateManager.enableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableDepthTest();
			// Fixes opaque cooldown overlay a bit lower
			// TODO: check if enabled blending still screws things up down the line.
			GlStateManager.enableBlend();
		}
		GlStateManager.pushMatrix();
	}
    
    public static boolean mouseInRect(int x, int y, int width, int height, int mouseX, int mouseY) {
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
    
    public void removeItemCrafting(ItemStackT item) {
    	int stack = item.size + 0;
    	InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
    	for (int x = 0; x < 30; x++) {
    		if (x < 10) {
    			if (inventory.hotbar[x] != null) {
    				if (inventory.hotbar[x].stack != null) {
    					if (inventory.hotbar[x].stack.item == item.item) {
    						
    						if (inventory.hotbar[x].stack.size > stack) {
    							inventory.hotbar[x].stack.size -= stack;
    							NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.hotbar[x].area, inventory.hotbar[x].id, inventory.hotbar[x].stack));
    							return;
    						} else {
    							stack -= inventory.hotbar[x].stack.size;
    							inventory.hotbar[x].stack = null;
    							
    							NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.hotbar[x].area, inventory.hotbar[x].id, inventory.hotbar[x].stack));
    							if (stack <= 0) return;
    						}
    						
    					}
    				}
    			}
    		}
    		if (inventory.main[x] != null) {
				if (inventory.main[x].stack != null) {
					if (inventory.main[x].stack.item == item.item) {
						
						if (inventory.main[x].stack.size > stack) {
							inventory.main[x].stack.size -= stack;
							NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.main[x].area, inventory.main[x].id, inventory.main[x].stack));
							return;
						} else {
							stack -= inventory.main[x].stack.size;
							inventory.main[x].stack = null;
							
							NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.main[x].area, inventory.main[x].id, inventory.main[x].stack));
							if (stack <= 0) return;
						}
						
					}
				}
			}
    	}
    }
    
    
    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
    	
    	this.resetRecipes();
    	
    	InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
    	if (inventory.holdingSlot != null && inventory.holdingSlot.stack != null)
        	if (mouseX < 0  || mouseY < 0 || mouseX > 0 + this.WIDTH || mouseY > 0 + this.HEIGHT) {
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
    	
    	if (upSelected == true && mouseButton == 0) {
    		page--;
    	}
    	
    	if (downSelected == true && mouseButton == 0) {
    		page++;
    	}

    	if (this.selectedRecipe != null) {
    		//System.out.println("selected recipe");
    		if (inventory.holdingSlot == null || inventory.holdingSlot.stack == null) {
    			//System.out.println("no holding slot");
        		if (mouseButton == 0 && !KeyRegistry.autoEquip.isKeyDown()) {
					Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
					inventory.holdingSlot.stack = new ItemStackT(selectedRecipe.output.item, selectedRecipe.output.size, ItemModifier.getRandomModifier(selectedRecipe.output.item));
        			for (ItemStackT stack : selectedRecipe.input) {
        				this.removeItemCrafting(stack);
        			}
        			this.resetRecipes();
        			rightDown = true;
        			this.rightClicked = System.currentTimeMillis();
        		}
        	} else {
        		if (inventory.holdingSlot.stack != null) {
        			if (mouseButton == 0) {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
						if (inventory.holdingSlot.stack.item == selectedRecipe.output.item) {
        					int maxStack = 0;
            				if (inventory.holdingSlot.stack.item instanceof ItemT) {
            					maxStack = ((ItemT)inventory.holdingSlot.stack.item).maxStack;
            				} else {
            					maxStack = inventory.holdingSlot.stack.itemForRender.getMaxStackSize();
            				}
        					if (inventory.holdingSlot.stack.size + selectedRecipe.output.size < maxStack) {
        						inventory.holdingSlot.stack.size += selectedRecipe.output.size;
                    			for (ItemStackT stack : selectedRecipe.input) {
                    				this.removeItemCrafting(stack);
                    			}
                    			this.resetRecipes();
        					}
        				}
            			
            		}
        			if (mouseButton == 1) {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
						if (inventory.holdingSlot.stack.item == selectedRecipe.output.item) {
        					int maxStack = 0;
            				if (inventory.holdingSlot.stack.item instanceof ItemT) {
            					maxStack = ((ItemT)inventory.holdingSlot.stack.item).maxStack;
            				} else {
            					maxStack = inventory.holdingSlot.stack.itemForRender.getMaxStackSize();
            				}
        					if (inventory.holdingSlot.stack.size + selectedRecipe.output.size < maxStack) {
        						inventory.holdingSlot.stack.size += selectedRecipe.output.size;
                    			for (ItemStackT stack : selectedRecipe.input) {
                    				this.removeItemCrafting(stack);
                    			}
                    			this.resetRecipes();
                    			this.rightDown = true;
                    			this.rightClicked = System.currentTimeMillis();
        					}
        				}
        			}
        		}
        	}
    	}
    	
    	
    	
    	if (selectedSlot == null) return true;
    	
    	
    	
    	rightDown = false;
    	
    	if (mouseButton == 1) {
    		if (selectedSlot.stack != null && inventory.holdingSlot.stack == null) {
    			ItemStackT stack = selectedSlot.stack;
    			
    			if (stack.item instanceof Clickable) {
    				((Clickable)stack.item).use(Minecraft.getInstance().player, selectedSlot);
    			}
    			
    			if (stack.item instanceof LootBag) {
    				
    				LootBag bag = (LootBag)stack.item;
    				LootStack[] stacks = bag.stacks;
    				
    				Random rand = new Random();
    				
    				ItemStackT s = new ItemStackT(stack.item, stack.size - 1);
    				
    				for (LootStack loot : stacks) {
    					int amt = loot.getAmount(rand);
    					ItemStackT stack1 = new ItemStackT(loot.loot, amt);
    					
    					selectedSlot.stack = stack1;
    					NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
            			NetworkHandler.INSTANCE.sendToServer(new CPacketThrowItemTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack, 0));
            			
            			if (loot.secondLoot != null) {
            				ItemStackT stack2 = new ItemStackT(loot.secondLoot, loot.secondValue);
        					
        					selectedSlot.stack = stack2;
        					NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
                			NetworkHandler.INSTANCE.sendToServer(new CPacketThrowItemTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack, 0));
                			
            			}
            			selectedSlot.stack = s;
            			if(s.size <= 0) {
            				selectedSlot.stack = null;
            			}
    				}
    				
        			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
        			
        			return true;
    			}
    		}
    	}
    	
    	if (KeyRegistry.trash.isKeyDown()) {
	        	if (mouseButton == 0) {
	        		if (selectedSlot.stack != null && !selectedSlot.isFavorite) {
	        			ItemStackT stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
	        			trashSlot.stack = stack;
	        			selectedSlot.stack = null;
	        			
	        			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
	        			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, trashSlot.area, trashSlot.id, trashSlot.stack));
	                	
	        		}
	        		return true;
	        	}
    	}

		if (KeyRegistry.autoEquip.isKeyDown()) {
			if (mouseButton == 0) {
				if (selectedSlot.stack != null && selectedSlot.stack.item instanceof Armor) {
					if (((Armor) selectedSlot.stack.item).type == Armor.ArmorType.HEAD) {
						ItemStackT stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
						inventory.armor[0].stack = stack;
						selectedSlot.stack = null;

						NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
						NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.armor[0].area,inventory.armor[0].id, inventory.armor[0].stack));
					} else if (((Armor) selectedSlot.stack.item).type == Armor.ArmorType.CHEST) {
						ItemStackT stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
						inventory.armor[1].stack = stack;
						selectedSlot.stack = null;

						NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
						NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.armor[1].area,inventory.armor[1].id, inventory.armor[1].stack));
					} else if (((Armor) selectedSlot.stack.item).type == Armor.ArmorType.LEGS) {
						ItemStackT stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
						inventory.armor[2].stack = stack;
						selectedSlot.stack = null;

						NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
						NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, inventory.armor[2].area,inventory.armor[2].id, inventory.armor[2].stack));
					}
				}
				return true;
			}
		}
    	
    	if (mouseButton == 0 && !KeyRegistry.autoEquip.isKeyDown()) {
			if (inventory.holdingSlot.stack != null && selectedSlot.itemType == ItemType.ACCESSORY) {
				Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
				boolean a = false;
    			if (selectedSlot.stack != null) {
    				if (selectedSlot.stack.item == inventory.holdingSlot.stack.item) {

    					a = true;
    				}
    			}
    			if (a == false)
    			for (int i = 0; i < inventory.accessory.length; i++) {
    				if (inventory.accessory[i].stack != null) {
    					if (inventory.accessory[i].stack.item == inventory.holdingSlot.stack.item) {
    						return false;
    					}
    				}
    			}
    		}
    		if (inventory.holdingSlot.stack != null && selectedSlot.isTrashSlot == true) {
				Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
				ItemStackT stack = new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier));
    			selectedSlot.stack = stack;
    			inventory.holdingSlot.stack = null;
    		} else {
				Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
				if (inventory.holdingSlot.stack == null) {
        			if (selectedSlot.stack != null) {
        				inventory.holdingSlot.stack = new ItemStackT(selectedSlot.stack.item, selectedSlot.stack.size, ItemModifier.getModifier(selectedSlot.stack.modifier));
        				selectedSlot.stack = null;
        			}
        		} else {
        			if (selectedSlot.stack == null && selectedSlot.canInteractWith(inventory.holdingSlot)) {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
						selectedSlot.stack = new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier));
        				inventory.holdingSlot.stack = null;
        			} else {
						Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
						selectedSlot.takeFromStack(inventory.holdingSlot);
        			}
        		}
    		}
    		
    	}
    	if (mouseButton == 1) {
			Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.GRAB, SoundCategory.PLAYERS, 100, 1, false);
			rightDown = true;
    		rightClicked = System.currentTimeMillis();
    		if (selectedSlot.stack != null) 
    		{
    			if (inventory.holdingSlot.stack == null) {
    				inventory.holdingSlot.stack = new ItemStackT(selectedSlot.stack.item, ItemModifier.getModifier(selectedSlot.stack.modifier));
    				selectedSlot.decrementStack(1);
    			} else {
    				int maxStack = 0;
    				if (inventory.holdingSlot.stack.item instanceof ItemT) {
    					maxStack = ((ItemT)inventory.holdingSlot.stack.item).maxStack;
    				} else {
    					maxStack = inventory.holdingSlot.stack.itemForRender.getMaxStackSize();
    				}
    				if (inventory.holdingSlot.stack.size + 1 < maxStack) {
    					inventory.holdingSlot.stack.size++;
    					selectedSlot.decrementStack(1);
    				}
    			}
    		}
    	}
		NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
    	this.resetRecipes();

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double mouseReleased1, double mouseReleased3, int mouseReleased5) {
    	if (selectedSlot != null)
    		NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, selectedSlot.area, selectedSlot.id, selectedSlot.stack));
    	rightDown = false;
        if (this.buttonClicked) {
            this.buttonClicked = false;
            return true;
        } else {
            return super.mouseReleased(mouseReleased1, mouseReleased3, mouseReleased5);
        }
    }
}
