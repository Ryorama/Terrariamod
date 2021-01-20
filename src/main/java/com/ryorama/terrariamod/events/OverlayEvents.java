package com.ryorama.terrariamod.events;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.ryorama.terrariamod.core.inventory.InventorySlot;
import com.ryorama.terrariamod.core.inventory.InventoryTerraria;
import com.ryorama.terrariamod.core.inventory.container.ContainerTerrariaInventory;
import com.ryorama.terrariamod.core.inventory.container.GuiContainerTerrariaInventory;
import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.core.items.modifiers.ItemModifier;
import com.ryorama.terrariamod.items.Accessory;
import com.ryorama.terrariamod.items.Armor;
import com.ryorama.terrariamod.items.ItemT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.util.Conversions;
import com.ryorama.terrariamod.util.Util;
import com.ryorama.terrariamod.world.TRenderInfo;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.sound.PlayStreamingSourceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OverlayEvents {

	public static boolean debug = false;
	
	public static boolean b = false;
		
	public static int frameCount = 0;
	
	public static GameRenderer gameRenderer;
	
	public static TRenderInfo tRenderInfo;
	
	public static WorldRenderer worldRenderer;
	
	public static boolean setupWorld = false;
	public static boolean finishSetup = false;
	public static boolean startSetup = false;
	public static boolean reloadOverworld = false;

	public static World renderWorld;
	
	public static boolean loadingChunks = false;
	
	public static int ticks = 0;
	
	public static float blockMiningProgress = 0.0f;
	
	@SubscribeEvent
	public static void streamMusicEvent(PlayStreamingSourceEvent event) {
		if (event.getSound() == SoundEvents.MUSIC_MENU) {
			System.out.println("bruh music");
		}
	}
	
	@SubscribeEvent
	public static void handleGuiEvents(GuiScreenEvent event) {
//		Sounds.playMusic(Sounds.MUSIC_TITLE);
	}
	
	
	@SubscribeEvent
	public static void handleLivingRender(RenderLivingEvent<?, ?> event) {
		
	}
	
	@SubscribeEvent
	@OnlyIn(value=Dist.CLIENT)
	public static void handlePlayerRenderEvent(RenderPlayerEvent event) {

	}
	
	
	static boolean addedLayer = false;

	public static boolean loadRenderers = false;

	public static RayTraceResult blockHit;
	@SubscribeEvent
	@OnlyIn(value=Dist.CLIENT)
	public static void handlePostPlayerRenderEvent(RenderPlayerEvent.Post event) {
//		AnimatedModel model = Models.GUIDE.get("Idle");
//		if (model != null) {
//			model.render(System.nanoTime() / 1000000000.0);
//		}
		}
	
	@SubscribeEvent
	@OnlyIn(value=Dist.CLIENT)
	public static void handleOverlayEvent(RenderGameOverlayEvent event) {
		
		boolean copper_watch = false;
		boolean silver_watch = false;
		boolean gold_watch = false;
		boolean depth_meter = false;		
		
		if (ContainerTerrariaInventory.inventory != null) {
			InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
			

	        ArrayList<InventorySlot> guns = new ArrayList<InventorySlot>();
	        ArrayList<InventorySlot> bows = new ArrayList<InventorySlot>();

	        int bullets = 0;
	        int arrows = 0;
	        int stars = 0;
	      
	        for (int m = 0; m < InventoryTerraria.MAIN_SLOTS; m++) {
	        	if (inventory.main[m].stack != null) {
	    		  if (inventory.main[m].stack.item == ItemsT.FALLEN_STAR) {
	    			  stars+=inventory.main[m].stack.size;
	    		  }
	        	}
	        }
	        for (int m = 0; m < inventory.hotbar.length; m++) {
	        	if (inventory.hotbar[m].stack != null) {
	    		  if (inventory.hotbar[m].stack.item == ItemsT.FALLEN_STAR) {
	    			  stars+=inventory.hotbar[m].stack.size;
	    		  }
	        	}
	        }
	        for (InventorySlot slot : guns) {
	        	slot.stack.renderStack = bullets;
	        }
	        for (InventorySlot slot : bows) {
	        	slot.stack.renderStack = arrows;
	        }
			
			for (InventorySlot slot : inventory.accessory) {
				ItemStackT stack = slot.stack;
				if (stack == null) {
					continue;
				}
				if (stack.item == null) {
					continue;
				}
			}
		}
		
		long dayTime = Minecraft.getInstance().getRenderViewEntity().getEntityWorld().getDayTime() + 6000;
		if (Minecraft.getInstance().player != null) {
			Minecraft.getInstance().player.getFoodStats().setFoodSaturationLevel(5.0f);
		}
		
		if (gold_watch != false) {
			long fullday = (dayTime % 24000);
			long day = (fullday / 1000) % 12;
			long hour = dayTime % 1000;
			
			String t = ":00 ";
			
			long minute = (int)Math.round(hour / 16.67f);
			
			if (minute >= 60) {
				minute -= 60;
				day++;
				day %= 12;
			}
			
			if (minute < 10) t = ":0"+minute+" ";
			else
				t = ":"+minute+" ";
			
    		String AMPM = "AM";
    		if (fullday >= 12000)
    			AMPM = "PM";
    		if (day == 0) day = 12;
    		Util.watchTime = day + t + AMPM;
		}else
		if (silver_watch != false) {
			long fullday = (dayTime % 24000);
			long day = (fullday / 1000) % 12;
			long hour = dayTime % 1000;
			
			String t = ":00 ";
			if (hour > 500) t = ":30 ";
			
    		String AMPM = "AM";
    		if (fullday >= 12000)
    			AMPM = "PM";
    		if (day == 0) day = 12;
    		Util.watchTime = day + t + AMPM;
		} else {
			if (copper_watch != false) {
				
				long fullday = (dayTime % 24000);
				long day = (fullday / 1000) % 12;
	    		String AMPM = "AM";
	    		if (fullday >= 12000)
	    			AMPM = "PM";
	    		if (day == 0) day = 12;
	    		Util.watchTime = day + ":00 " + AMPM;
			}
		}
		int accessory = 0;
		int textSize = 10;

		if (event.getType() == ElementType.HOTBAR) {
			if (Util.terrariaInventory == true) {
				Minecraft instance = Minecraft.getInstance();
		        instance.getTextureManager().bindTexture(GuiContainerTerrariaInventory.INVENTORY_BACKGROUND);
		        //23, 168 = hotbar deselected
		        //46, 168 = hotbar selected
		        
		        int scaledWidth, scaledHeight;
				scaledWidth = instance.getMainWindow().getScaledWidth();
			    scaledHeight = instance.getMainWindow().getScaledHeight();
				
			    int xx = scaledWidth / 2 - 91 - (20);
			    int yy = scaledHeight - 39 + 16;
			    InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
			    for (int i = 0; i < 10; i++) {
			    	if (inventory.hotbarSelected == i) {
			    		instance.getTextureManager().bindTexture(GuiContainerTerrariaInventory.INVENTORY_BACKGROUND);
			    		GuiContainerTerrariaInventory.drawTexturedRectangle(xx + i * 22, yy, 46, 168, 22, 22);
			    	} else {
			    		instance.getTextureManager().bindTexture(GuiContainerTerrariaInventory.INVENTORY_BACKGROUND);
			    		GuiContainerTerrariaInventory.drawTexturedRectangle(xx + i * 22, yy, 23, 168, 22, 22);
			    	}
			    	if (inventory.hotbar[i].stack != null)
			    		GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.hotbar[i].stack, xx + i * 22 + 2, yy + 3);
			    	
			    }
//		        if (Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity) {
//		        	PlayerEntity player = (PlayerEntity)Minecraft.getInstance().getRenderViewEntity();
////		        		player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.AIR));
//		        	if (inventory.hotbar[inventory.hotbarSelected].stack != null)
//			        	player.inventory.mainInventory.set(player.inventory.currentItem, new ItemStack(inventory.hotbar[inventory.hotbarSelected].stack.itemForRender.getItem(), 1));
//		        	else
//			        	player.inventory.mainInventory.set(player.inventory.currentItem, new ItemStack(Items.AIR, 1));
//		        }
				event.setCanceled(true);
			}
		}
		
//		instance.renderEngine.bindTexture(new ResourceLocation("snapshot", "textures/gui/lunchbox.png"));
//		drawTexturedModalRect(xPos, yPos, 0, 0, 32, 32);
		if (event.getType() == ElementType.EXPERIENCE) {
			int i = 0;
			Minecraft instance = Minecraft.getInstance();
			if (Util.watchTime != "") {
				instance.ingameGUI.drawString(instance.fontRenderer, ""+Util.watchTime, 5, 10 + i * textSize, 0xFFFFFF);
				i++;
			}
			if (depth_meter) {
				instance.ingameGUI.drawString(instance.fontRenderer, "Level "+(int)Minecraft.getInstance().getRenderViewEntity().getPosY() + "." + (int)((Minecraft.getInstance().getRenderViewEntity().getPosY() * 10) % 10), 5, 10 + i * textSize, 0xFFFFFF);
				i++;
			}
			accessory = i;
		}
		Util.watchTime = "";
		if (event.getType() == ElementType.EXPERIENCE) {
			Minecraft instance = Minecraft.getInstance();
			
			int scaledWidth, scaledHeight;
			scaledWidth = instance.getMainWindow().getScaledWidth();
		    scaledHeight = instance.getMainWindow().getScaledHeight();
			
		    int xx = scaledWidth - 10;
		    int yy = 10;
		    
		    		    
		    if (instance.getRenderViewEntity() instanceof PlayerEntity) {
		    	GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glDisable(GL11.GL_LIGHTING);
				
				GL11.glEnable (GL11.GL_BLEND); 
				GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
				
				instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
				
				instance.ingameGUI.blit(Conversions.toScreenX(xx), Conversions.toScreenY(yy), 9 * 1, 9 * 2, 9, 9);
				
				instance.ingameGUI.blit(Conversions.toScreenX(xx), Conversions.toScreenY(yy + 3 * 5), 9 * 2, 9 * 2, 9, 9);
				
				instance.ingameGUI.blit(Conversions.toScreenX(xx), Conversions.toScreenY(yy + 3 * 10), 9 * 3, 9 * 2, 9, 9);

				instance.ingameGUI.blit(Conversions.toScreenX(xx), Conversions.toScreenY(yy + 3 * 15), 9 * 4, 9 * 2, 9, 9);

				
				PlayerEntity player = (PlayerEntity)instance.getRenderViewEntity();
				int coins = (int) Util.renderCoins;
				
				int copper = coins % 100;
				int silver = (coins / 100) % 100;
				int gold = (coins / 10000) % 100;
				int platinum = (coins / 1000000) % 100;
				
				instance.ingameGUI.drawString(instance.fontRenderer, ""+copper, xx + ((1-(""+copper).length()))*5 - (int)5.5, yy+ 1, 0xFFFFFF);
				instance.ingameGUI.drawString(instance.fontRenderer, ""+silver, xx + ((1-(""+silver).length()))*5 - (int)5.5, yy + 3 * 5+ 1, 0xFFFFFF);
				instance.ingameGUI.drawString(instance.fontRenderer, ""+gold, xx + ((1-(""+gold).length()))*5 - (int)5.5, yy + 3 * 10+ 1, 0xFFFFFF);
				instance.ingameGUI.drawString(instance.fontRenderer, ""+platinum, xx + ((1-(""+platinum).length()))*5 - (int)5.5, yy + 3 * 15+ 1, 0xFFFFFF);
				
					
				//RENDER BUFFS / DEBUFFS
				int effectCounter = 0;
				if (Util.renderPotionSickness > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderPotionSickness)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderManaSickness > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "x"+Util.renderManaSicknessEffect+" ["+Util.getTimerString(Util.renderManaSickness)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderWeakDebuff > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 2, 27 + 12 * 3, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderWeakDebuff)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderHorrified > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 3, 27 + 12 * 3, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderHorrified)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderBuild > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 2, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderBuild)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderCalming > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 3, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderCalming)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderIronskin > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 4, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderIronskin)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderNightOwl > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 5, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderNightOwl)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderShine > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 3, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderShine)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderSwiftness > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 7, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderSwiftness)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderMining > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 8, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderMining)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderArchery > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 9, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderArchery)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderHunter > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 10, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderHunter)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderGills > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 11, 27 + 12 * 1, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderGills)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderRegeneration > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderRegeneration)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderFeatherfall > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderFeatherfall)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderFlipper > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 2, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderFlipper)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderGravitation > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 3, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderGravitation)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderHeartreach > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 4, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderHeartreach)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderInvisibility > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 5, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderInvisibility)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderThorns > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 6, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderThorns)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderMagicPower > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 7, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderMagicPower)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderManaRegeneration > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 8, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderManaRegeneration)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderObsidianSkin > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 9, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderObsidianSkin)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderTitan > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 10, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderTitan)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderWaterWalking > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 11, 27 + 12 * 2, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderWaterWalking)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				if (Util.renderBattle > 0) {
					instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 1, 27 + 12 * 0, 12, 12); //debuff background
					instance.ingameGUI.blit(Conversions.toScreenX(5), Conversions.toScreenY(12 * 2 + 12 * effectCounter) + accessory * textSize, 12 * 0, 27 + 12 * 3, 12, 12); //debuff image
					instance.ingameGUI.drawString(instance.fontRenderer, "["+Util.getTimerString(Util.renderBattle)+"]", 5 + 12 + 3, 12 * 2 + 12 * effectCounter + 3 + accessory * textSize, 0xFFFFFF);
					effectCounter++;
				}
				
				GL11.glColor4f(1F, 1F, 1F, 1F);
				instance.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
				
				
		    }
			//event.setCanceled(true);
		}
		
		if (event.getType() == ElementType.FOOD) {
			event.setCanceled(true);
			Minecraft instance = Minecraft.getInstance();
			
			int scaledWidth, scaledHeight;
			scaledWidth = instance.getMainWindow().getScaledWidth();
		    scaledHeight = instance.getMainWindow().getScaledHeight();
			
		    int xx = scaledWidth / 2 - 91 + 10 * 10;
		    int yy = scaledHeight - 39 - 10;
		    
		    if (instance.getRenderViewEntity() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)instance.getRenderViewEntity();
				int xPos = 0;
				int yPos = 0;
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glDisable(GL11.GL_LIGHTING);
				
				GL11.glEnable (GL11.GL_BLEND); 
				GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
				
				instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
				//healthbar
				
				int mana = ScoreboardEvents.getMana(player);
				int extraMana = 0;
				InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
				if (inventory != null)
				for (int a = 0; a < inventory.accessory.length; a++) {
					if (inventory.accessory[a] != null) {
						InventorySlot slot = inventory.accessory[a];
						if (slot.stack != null) {
							if (slot.stack.item instanceof ItemT) {
								ItemT item = (ItemT)slot.stack.item;
								ItemModifier modifier = ItemModifier.getModifier(slot.stack.modifier);
								if (item instanceof Accessory) {
									Accessory acc = (Accessory)item;
									extraMana += acc.extraMana;
								}
								if (modifier != null)
								extraMana += modifier.mana;
							}
						}
					}
				}
				
				int max_mana = ScoreboardEvents.getMaxMana(player) + extraMana;
				
				int len = (int)(mana / 20);
				
				int len2 = (int)(max_mana / 20);
				
				boolean half = (int)mana % 20 <= 19 && (int)mana % 20 > 0;
				
				boolean shake = false;
				
				int X = xx;
				int Y = yy+10;
				
				if (player.getAir() < player.getMaxAir() || player.world.getBlockState(player.getPosition().up(1)).getBlock() == Blocks.WATER)
				Y = yy;
				
				for (int i = 0; i < len2; i++) {
					if (i <= 10) {
						instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y), 9 * 0, 9 * 1, 9, 9);
					} else {
						instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10), 9 * 0, 9 * 1, 9, 9);
					}
				}
				
				
				for (int i = 0; i < len; i++) {
					if (i <= 10) {
						instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y + addShake(shake)), 9 * 4, 9 * 1, 9, 9);
					} else {
						instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10 + addShake(shake)), 9 * 4, 9 * 1, 9, 9);
					}
				}
				if (half == true) {
					GL11.glColor4f(1F, 1F, 1F, ((int)mana % 20) / 20.0f);
					int i = len;
					if (i <= 10) {
						instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y + addShake(shake)), 9 * 4, 9 * 1, 9, 9);
					} else {
						instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10 + addShake(shake)), 9 * 4, 9 * 1, 9, 9);
					}
				}
				GL11.glColor4f(1F, 1F, 1F, 1F);
				instance.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
			}
		    
		}
		
		if (event.getType() == ElementType.ARMOR) {
			Minecraft instance = Minecraft.getInstance();
			
			int scaledWidth, scaledHeight;
			scaledWidth = instance.getMainWindow().getScaledWidth();
		    scaledHeight = instance.getMainWindow().getScaledHeight();
			
		    int xx = scaledWidth / 2 - 91 + 10 * 18 + 9;
		    int yy = scaledHeight - 39 + 25;
		    if (Util.terrariaInventory) xx += (22 / 2); 
		    GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glDisable(GL11.GL_LIGHTING);
			
			GL11.glEnable (GL11.GL_BLEND); 
			GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
			
			instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
		    
			//draw armor sprite
			instance.ingameGUI.blit(Conversions.toScreenX(xx), Conversions.toScreenY(yy), 9 * 0, 9 * 2, 9, 9);
			
			if (instance.getRenderViewEntity() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)instance.getRenderViewEntity();
				int armor = player.getTotalArmorValue();
				
				InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
				if (inventory != null) {

					for (int i = 0; i < 3; i++) {
						if (inventory.armor[i].stack != null) {
							if (inventory.armor[i].stack.item instanceof Armor) {
								Armor a = (Armor)inventory.armor[i].stack.item;
								armor += a.getDefense(inventory.armor);
							}
						}
					}
				}
				
				int ironskin = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.IRONSKIN).getScorePoints();
				if (ironskin > 0) {
					armor += 8;
				}
				
				int weak = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.WEAK).getScorePoints();
				if (weak > 0) {
					armor -= 8;
				}
				
				
				instance.ingameGUI.drawString(instance.fontRenderer, ""+armor, xx + 10, yy, 0xFFFFFF);
			}
			
			GL11.glColor4f(1F, 1F, 1F, 1F);
			instance.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
			event.setCanceled(true);
		}
		
		if (event.getType() == ElementType.HEALTH) {
			
			Minecraft instance = Minecraft.getInstance();
			
			int scaledWidth, scaledHeight;
			scaledWidth = instance.getMainWindow().getScaledWidth();
		    scaledHeight = instance.getMainWindow().getScaledHeight();
			
		    int xx = scaledWidth / 2 - 91;
		    int yy = scaledHeight - 39;
		    
			if (instance.getRenderViewEntity() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)instance.getRenderViewEntity();
				int xPos = 0;
				int yPos = 0;
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glDisable(GL11.GL_LIGHTING);
				
				GL11.glEnable (GL11.GL_BLEND); 
				GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
				
				instance.getTextureManager().bindTexture(new ResourceLocation("terrariamod", "textures/gui/icons.png"));
				//healthbar
				
				
				
				int len = (int)(player.getHealth() / 20);
				
				int len2 = (int)(player.getMaxHealth() / 20);
				
				boolean half = (int)player.getHealth() % 20 <= 19 && (int)player.getHealth() % 20 > 0;
				
				boolean shake = player.getHealth() <= 40;
				
				int X = xx;
				int Y = yy;
				
				boolean healthPhase2 = false;
				
				if (len2 >= 20) {
					healthPhase2 = true;
				}
				
				if (!healthPhase2) {
					for (int i = 0; i < len2; i++) {
						
						if (i < 10) {
							instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y), 9 * 0, 9 * 0, 9, 9);
						} else {
							instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10), 9 * 0, 9 * 0, 9, 9);
						}
					}
					
					
					for (int i = 0; i < len; i++) {
						if (i < 10) {
							instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						} else {
							instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10 + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						}
					}
					if (half == true) {
						GL11.glColor4f(1F, 1F, 1F, ((int)player.getHealth() % 20) / 20.0f);
						int i = len;
						if (i < 10) {
							instance.ingameGUI.blit((int)Conversions.toScreenX(X + i * 10), (int)Conversions.toScreenY(Y + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						} else {
							instance.ingameGUI.blit((int)Conversions.toScreenX(X + (i - 10) * 10), (int)Conversions.toScreenY(Y - 10 + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						}
					}
					GL11.glColor4f(1F, 1F, 1F, 1F);
					instance.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
				}
				else {
					for (int i = 0; i < len2; i++) {
						
						if (i < 10) {
							instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y), 9 * 0, 9 * 0, 9, 9);
						} else {
							instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10), 9 * 0, 9 * 0, 9, 9);
						}
					}
					
					
					for (int i = 0; i < len; i++) {
						if (i < 10) {
							instance.ingameGUI.blit(Conversions.toScreenX(X + i * 10), Conversions.toScreenY(Y + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						} else {
							instance.ingameGUI.blit(Conversions.toScreenX(X + (i - 10) * 10), Conversions.toScreenY(Y - 10 + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						}
					}
					if (half == true) {
						GL11.glColor4f(1F, 1F, 1F, ((int)player.getHealth() % 20) / 20.0f);
						int i = len;
						if (i < 10) {
							instance.ingameGUI.blit((int)Conversions.toScreenX(X + i * 10), (int)Conversions.toScreenY(Y + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						} else {
							instance.ingameGUI.blit((int)Conversions.toScreenX(X + (i - 10) * 10), (int)Conversions.toScreenY(Y - 10 + addShake(shake)), 9 * 4, 9 * 0, 9, 9);
						}
					}
					GL11.glColor4f(1F, 1F, 1F, 1F);
					instance.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
				}
			}
			
			
			event.setCanceled(true);
		}
		
	}
	
	private static int addShake (boolean shake) {
		return 0;
	}
	
	
	
	
}