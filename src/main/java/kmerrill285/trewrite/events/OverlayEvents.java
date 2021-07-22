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

	public static ResourceLocation potion_sickness_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/potion_sickness.png");
	public static ResourceLocation iron_skin_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/ironskin.png");
	public static ResourceLocation shine_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/shine.png");
	public static ResourceLocation swiftness_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/swiftness.png");
	public static ResourceLocation waterwalking_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/water_walking.png");
	public static ResourceLocation regenration_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/regeneration.png");
	public static ResourceLocation build_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/builder.png");
	public static ResourceLocation mining_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/mining.png");
	public static ResourceLocation gravitation_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/gravitation.png");
	public static ResourceLocation gills_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/gills.png");
	public static ResourceLocation magicpower_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/magic_power.png");
	public static ResourceLocation heartreach_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/heartreach.png");
	public static ResourceLocation mana_sickness_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/mana_sickness.png");
	public static ResourceLocation weak_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/weak.png");
	public static ResourceLocation horified_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/horrified.png");
	public static ResourceLocation calming_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/calm.png");
	public static ResourceLocation night_owl_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/night_owl.png");
	public static ResourceLocation archery_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/archery.png");
	public static ResourceLocation battle_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/battle.png");
	public static ResourceLocation hunter_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/hunter.png");
	public static ResourceLocation featherfall_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/featherfall.png");
	public static ResourceLocation flipper_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/flipper.png");
	public static ResourceLocation thorns_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/thorns.png");
	public static ResourceLocation manaregenertation_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/manaregeneration.png");
	public static ResourceLocation invisibility_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/invisibility.png");
	public static ResourceLocation obsidianskin_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/obsidianskin.png");
	public static ResourceLocation titan_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/titan.png");
	public static ResourceLocation wellfeef_buff = new ResourceLocation(Trewrite.modid, "textures/gui/buffs/well_fed.png");

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

		int i2 = 17;
		int j2 = 10;

		if (!inventory.open) {
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

				int i3 = 17;
				int j3 = 27;

				//RENDER BUFFS / DEBUFFS
				int effectCounter = 0;
				if (Util.renderPotionSickness > 0) {
					UIRenderer.instance.renderOverlay(potion_sickness_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderManaSickness > 0) {
					UIRenderer.instance.renderOverlay(mana_sickness_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderWeakDebuff > 0) {
					UIRenderer.instance.renderOverlay(weak_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderHorrified > 0) {
					UIRenderer.instance.renderOverlay(horified_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderBuild > 0) {
					UIRenderer.instance.renderOverlay(build_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderCalming > 0) {
					UIRenderer.instance.renderOverlay(calming_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderIronskin > 0) {
					UIRenderer.instance.renderOverlay(iron_skin_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderNightOwl > 0) {
					UIRenderer.instance.renderOverlay(night_owl_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderShine > 0) {
					UIRenderer.instance.renderOverlay(shine_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderSwiftness > 0) {
					UIRenderer.instance.renderOverlay(swiftness_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderMining > 0) {
					UIRenderer.instance.renderOverlay(mining_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderArchery > 0) {
					UIRenderer.instance.renderOverlay(archery_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderHunter > 0) {
					UIRenderer.instance.renderOverlay(hunter_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderGills > 0) {
					UIRenderer.instance.renderOverlay(gills_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderRegeneration > 0) {
					UIRenderer.instance.renderOverlay(regenration_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderFeatherfall > 0) {
					UIRenderer.instance.renderOverlay(featherfall_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderFlipper > 0) {
					UIRenderer.instance.renderOverlay(flipper_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderGravitation > 0) {
					UIRenderer.instance.renderOverlay(gravitation_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderHeartreach > 0) {
					UIRenderer.instance.renderOverlay(heartreach_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderInvisibility > 0) {
					UIRenderer.instance.renderOverlay(invisibility_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderThorns > 0) {
					UIRenderer.instance.renderOverlay(thorns_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderMagicPower > 0) {
					UIRenderer.instance.renderOverlay(magicpower_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderManaRegeneration > 0) {
					UIRenderer.instance.renderOverlay(manaregenertation_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderObsidianSkin > 0) {
					UIRenderer.instance.renderOverlay(obsidianskin_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderTitan > 0) {
					UIRenderer.instance.renderOverlay(titan_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderWaterWalking > 0) {
					UIRenderer.instance.renderOverlay(waterwalking_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderBattle > 0) {
					UIRenderer.instance.renderOverlay(battle_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

				if (Util.renderWellFeed > 0) {
					UIRenderer.instance.renderOverlay(wellfeef_buff, 1f, 8, 8, i3 + effectCounter * 10f, j3, -90);
					effectCounter++;
				}

			}
		}

		if (event.getType() == ElementType.FOOD) {
			event.setCanceled(true);
		}
		if (event.getType() == ElementType.HEALTH) {
			event.setCanceled(true);
		}
		if (event.getType() == ElementType.EXPERIENCE) {
			event.setCanceled(true);
		}
		if (event.getType() == ElementType.HOTBAR) {
			event.setCanceled(true);
		}
	}
}