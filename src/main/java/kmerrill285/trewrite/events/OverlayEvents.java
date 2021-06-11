package kmerrill285.trewrite.events;

import java.util.ArrayList;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.core.client.TerrariaUIManager;
import kmerrill285.trewrite.core.client.UIRenderer;
import org.lwjgl.opengl.GL11;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.core.inventory.container.GuiContainerTerrariaInventory;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.models.ModelRegistry;
import kmerrill285.trewrite.entities.models.layers.TerrariaBipedAccessoryLayer;
import kmerrill285.trewrite.entities.models.layers.TerrariaBipedArmorLayer;
import kmerrill285.trewrite.entities.monsters.EntityZombieT;
import kmerrill285.trewrite.items.Armor;
import kmerrill285.trewrite.items.Arrow;
import kmerrill285.trewrite.items.Bow;
import kmerrill285.trewrite.items.Bullet;
import kmerrill285.trewrite.items.Gun;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.accessories.Accessory;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Conversions;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.TRenderInfo;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
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

	public static ResourceLocation slot_texture = new ResourceLocation(Trewrite.modid, "textures/gui/slot.png");
	public static ResourceLocation slot_texture_selected = new ResourceLocation(Trewrite.modid, "textures/gui/slot_selected.png");

	public static boolean debug = false;
	
	public static boolean b = false;
		
	public static int frameCount = 0;
	
	public static EntityZombieT camera;
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
	public static void handlePrePlayerRenderEvent(RenderPlayerEvent.Pre event) {
		//      this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
		if (!addedLayer) {
			event.getRenderer().addLayer(new TerrariaBipedArmorLayer<>(event.getRenderer(), new BipedModel(0.5f), new BipedModel(1.0f)));
			event.getRenderer().addLayer(new TerrariaBipedAccessoryLayer<>(event.getRenderer(), new BipedModel(0.5f), new BipedModel(1.0f)));
			addedLayer = true;
		}
		
	}
	
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
		TerrariaUIManager.renderTerrariaMana();
		TerrariaUIManager.renderTerrariaHealth();

		InventoryTerraria inventory = ContainerTerrariaInventory.inventory;

		if (!inventory.open) {
			int i2 = 17;
			int j2 = 10;

			for (int m = 0; m < InventoryTerraria.HOTBAR_SLOTS; m++) {
				if (inventory.hotbarSelected == m) {
					UIRenderer.instance.renderOverlay(slot_texture_selected, 0.75f, 16, 16, i2, j2, -90);
				} else {
					UIRenderer.instance.renderOverlay(slot_texture, 0.75f, 16, 16, i2, j2, -90);
				}
				if (inventory.hotbar[m].stack != null) {
					GuiContainerTerrariaInventory.renderItemIntoGUI(inventory.hotbar[m].stack, i2, j2);
				}

				i2 += 17;
			}
		}
	}
}