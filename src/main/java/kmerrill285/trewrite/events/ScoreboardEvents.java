package kmerrill285.trewrite.events;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.accessories.Accessory;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SScoreboardObjectivePacket;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.ScoreCriteria.RenderType;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ScoreboardEvents {	
	
	public static String POTION_SICKNESS = "PSN",
			MANA = "MANA", LIFE_CRYSTALS = "LCRYSTALS", MAX_MANA = "MAXMANA", MANA_TIMER = "MANAT",
			COINS = "COINS", DAYTIME = "DAYT", MANA_SICKNESS = "MS", MANA_SICKNESS_EFFECT = "ME",
			BUILDER = "BDR", CALMING = "CLM", IRONSKIN = "ISKN", SWIFTNESS = "SFT", NIGHT_OWL = "NOL",
			GILLS = "GLS", REGENERATION = "REGEN",
			MINING = "MINE", ARCHERY = "ARCH",
			HUNTER = "HUNT", FEATHERFALL = "FTHR",
			FLIPPER = "FLPR", GRAVITATION = "GRAV",
			HEARTREACH = "HRCH", INVISIBILITY = "INVIS",
			THORNS = "THRS", WATER_WALKING = "WATER",
			SHINE = "SHN", BATTLE = "BTL",
			OBSIDIAN_SKIN = "OBSS",
			MAGIC_POWER = "MPWR", MANA_REGENERATION = "MNRG",
			TITAN = "TITAN", WEAK = "WEAK", HORRIFIED = "HORROR", LIFE_FRUIT = "LFRUIT", WELL_FEED = "WELL_FEED";
	
	public static int tickTimer = 0;
	
	@SubscribeEvent
	public static void handleTickEvent(TickEvent event) {
		if (tickTimer < 20) {
			tickTimer++;
		} else {
			tickTimer = 0;
		}
	}
	
	public static int getMana(PlayerEntity player) {
		return Util.renderMana;
	}
	
	public static int getMaxMana(PlayerEntity player) {
		return Util.renderMaxMana;
	}
	
	public static Score getScore(Scoreboard scoreboard, PlayerEntity player, String name) {
		ScoreObjective OBJ = getObjective(name, scoreboard, player);
		return scoreboard.getOrCreateScore(player.getScoreboardName(), OBJ);
	}
	
	public static void handleScoreboard(PlayerEntity player, World world, Scoreboard scoreboard) {
//		if (Minecraft.getInstance() == null) return;
//		if (Minecraft.getInstance().getRenderViewEntity() == null) return;
		int i = 0;
		ScoreObjective COINS = getObjective(ScoreboardEvents.COINS, scoreboard, player, i++);
		i++; //skip over sidebar rendering
		ScoreObjective MANA = getObjective(ScoreboardEvents.MANA, scoreboard, player, i++);
		ScoreObjective MAX_MANA = getObjective(ScoreboardEvents.MAX_MANA, scoreboard, player, i++);
		ScoreObjective MANA_TIMER = getObjective(ScoreboardEvents.MANA_TIMER, scoreboard, player);
		ScoreObjective LIFE_CRYSTALS = getObjective(ScoreboardEvents.LIFE_CRYSTALS, scoreboard, player, i++);
		ScoreObjective LIFE_FRUIT = getObjective(ScoreboardEvents.LIFE_FRUIT, scoreboard, player, i++);
		ScoreObjective POTION_SICKNESS = getObjective(ScoreboardEvents.POTION_SICKNESS, scoreboard, player);
		ScoreObjective DAYTIME = getObjective(ScoreboardEvents.DAYTIME, scoreboard, player, i++);
		ScoreObjective MANA_SICKNESS = getObjective(ScoreboardEvents.MANA_SICKNESS, scoreboard, player);
		ScoreObjective MANA_SICKNESS_EFFECT = getObjective(ScoreboardEvents.MANA_SICKNESS_EFFECT, scoreboard, player);
		ScoreObjective[] potions = {
		getObjective(ScoreboardEvents.BUILDER, scoreboard, player),
		getObjective(ScoreboardEvents.CALMING, scoreboard, player),
		getObjective(ScoreboardEvents.IRONSKIN, scoreboard, player),
		getObjective(ScoreboardEvents.SWIFTNESS, scoreboard, player),
		getObjective(ScoreboardEvents.NIGHT_OWL, scoreboard, player),
		getObjective(ScoreboardEvents.GILLS, scoreboard, player),
		getObjective(ScoreboardEvents.REGENERATION, scoreboard, player),
		getObjective(ScoreboardEvents.MINING, scoreboard, player),
		getObjective(ScoreboardEvents.ARCHERY, scoreboard, player),
		getObjective(ScoreboardEvents.FEATHERFALL, scoreboard, player),
		getObjective(ScoreboardEvents.FLIPPER, scoreboard, player),
		getObjective(ScoreboardEvents.GRAVITATION, scoreboard, player),
		getObjective(ScoreboardEvents.HEARTREACH, scoreboard, player),
		getObjective(ScoreboardEvents.INVISIBILITY, scoreboard, player),
		getObjective(ScoreboardEvents.THORNS, scoreboard, player),
		getObjective(ScoreboardEvents.WATER_WALKING, scoreboard, player),
		getObjective(ScoreboardEvents.SHINE, scoreboard, player),
		getObjective(ScoreboardEvents.BATTLE, scoreboard, player),
		getObjective(ScoreboardEvents.OBSIDIAN_SKIN, scoreboard, player),
		getObjective(ScoreboardEvents.MAGIC_POWER, scoreboard, player),
		getObjective(ScoreboardEvents.MANA_REGENERATION, scoreboard, player),
		getObjective(ScoreboardEvents.TITAN, scoreboard, player),
		getObjective(ScoreboardEvents.HUNTER, scoreboard, player),
		getObjective(ScoreboardEvents.WEAK, scoreboard, player),
		getObjective(ScoreboardEvents.HORRIFIED, scoreboard, player),
		getObjective(ScoreboardEvents.WELL_FEED, scoreboard, player)
		};
		String[] PTNSTR = {
			ScoreboardEvents.BUILDER, ScoreboardEvents.CALMING,
			ScoreboardEvents.IRONSKIN, ScoreboardEvents.SWIFTNESS, ScoreboardEvents.NIGHT_OWL, ScoreboardEvents.GILLS,
			ScoreboardEvents.REGENERATION, ScoreboardEvents.MINING, ScoreboardEvents.ARCHERY, ScoreboardEvents.FEATHERFALL,
			ScoreboardEvents.FLIPPER, ScoreboardEvents.GRAVITATION, ScoreboardEvents.HEARTREACH, ScoreboardEvents.INVISIBILITY,
			ScoreboardEvents.THORNS, ScoreboardEvents.WATER_WALKING, ScoreboardEvents.SHINE, ScoreboardEvents.BATTLE, ScoreboardEvents.OBSIDIAN_SKIN,
			ScoreboardEvents.MAGIC_POWER, ScoreboardEvents.MANA_REGENERATION, ScoreboardEvents.TITAN, ScoreboardEvents.HUNTER, ScoreboardEvents.WEAK,
			ScoreboardEvents.HORRIFIED, ScoreboardEvents.WELL_FEED
		};
		
		Score manaSickness = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_SICKNESS);
		Score manaSicknessEffect = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_SICKNESS_EFFECT);
		
		
		Score mana = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA);
		Score maxMana = scoreboard.getOrCreateScore(player.getScoreboardName(), MAX_MANA);
		Score manaTimer = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_TIMER);
		
		
		Score coins = scoreboard.getOrCreateScore(player.getScoreboardName(), COINS);
		Score potionSickness = scoreboard.getOrCreateScore(player.getScoreboardName(), POTION_SICKNESS);
		Score daytime = scoreboard.getOrCreateScore(player.getScoreboardName(), DAYTIME);

		
		daytime.setScorePoints((int)(world.getDayTime() % 24000));
		
		if (Util.projectileCooldown > 0) {
			Util.projectileCooldown--;
		}
		
		if (potionSickness.getScorePoints() > 0) {
			setDisplay(i++, scoreboard, ScoreboardEvents.POTION_SICKNESS);
			potionSickness.setScorePoints(potionSickness.getScorePoints() - 1);
		}
		
		if (manaSickness.getScorePoints() > 0) {
			setDisplay(i++, scoreboard, ScoreboardEvents.MANA_SICKNESS);
			setDisplay(i++, scoreboard, ScoreboardEvents.MANA_SICKNESS_EFFECT);
			manaSickness.setScorePoints(manaSickness.getScorePoints() - 1);
		} else {
			manaSicknessEffect.setScorePoints(0);
		}
		
		for (int p = 0; p < potions.length; p++) {
			ScoreObjective score = potions[p];
			Score potion = scoreboard.getOrCreateScore(player.getScoreboardName(), score);
			if (potion != null)
			if (potion.getScorePoints() > 0) {
				setDisplay(i++, scoreboard, PTNSTR[p]);
				potion.setScorePoints(potion.getScorePoints() - 1);
			} else {
				potion.setScorePoints(0);
			}
		}
		
		if (mana.getScorePoints() < 0) mana.setScorePoints(0);
		if (maxMana.getScorePoints() < 20) { 
			maxMana.setScorePoints(20); 
			mana.setScorePoints(20);
		}
		boolean playerMoving = false;
		int mana_regen = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MANA_REGENERATION).getScorePoints();
		
		if (Math.abs(player.getMotion().x + player.getMotion().y + player.getMotion().z) > 0.1f) playerMoving = true;
		if (playerMoving == true && mana_regen <= 0) {
			manaTimer.setScorePoints(0);
		}
		
		if (manaTimer.getScorePoints() < maxMana.getScorePoints()) {
			manaTimer.incrementScore();
		}
		
		int extraMana = 0;
		
		InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);
		if (inventory != null)
		for (int a = 0; a < inventory.accessory.length; a++) {
			if (inventory.accessory[a] != null) {
				InventorySlot slot = inventory.accessory[a];
				if (slot.stack != null) {
					if (slot.stack.item instanceof ItemT) {
						ItemT item = (ItemT)slot.stack.item;
						ItemModifier modifier = ItemModifier.getModifier(slot.stack.modifier);
						if (item instanceof Accessory) {
							Accessory accessory = (Accessory)item;
							extraMana += accessory.extraMana;
						}
						if (modifier != null)
						extraMana += modifier.mana;
					}
				}
			}
		}
		
		if (mana.getScorePoints() < maxMana.getScorePoints() + extraMana) {
			mana.setScorePoints((int)(mana.getScorePoints() + (manaTimer.getScorePoints()) * 0.05));
		}
		
		if (mana.getScorePoints() > maxMana.getScorePoints() + extraMana) mana.setScorePoints(maxMana.getScorePoints() + extraMana);
		
		
	}
	
	public static boolean scoreboardHasObjective(Scoreboard scoreboard, String name) {
		return scoreboard.getObjective(name) != null;
	}
	
	public static void setDisplay(int scoreDisplay, Scoreboard scoreboard, String name) {
		if (scoreDisplay != -1)
			if (scoreboard.getObjectiveInDisplaySlot(scoreDisplay) != scoreboard.getObjective(name))
				scoreboard.setObjectiveInDisplaySlot(scoreDisplay, scoreboard.getObjective(name));
	}
	
	public static ScoreObjective getObjective (String name, Scoreboard scoreboard, PlayerEntity player) {
		if (scoreboardHasObjective(scoreboard, name)) {
			
			return scoreboard.getObjective(name);
		}
		return scoreboard.addObjective(name, ScoreCriteria.DUMMY, new StringTextComponent(name), RenderType.INTEGER);
	}
	
	public static ScoreObjective getObjective (String name, Scoreboard scoreboard, PlayerEntity player, int scoreDisplay) {
		if (scoreboardHasObjective(scoreboard, name)) {
			if (scoreDisplay != -1)
			if (scoreboard.getObjectiveInDisplaySlot(scoreDisplay) != scoreboard.getObjective(name)) {
				scoreboard.setObjectiveInDisplaySlot(scoreDisplay, scoreboard.getObjective(name));
				System.out.println(Scoreboard.getObjectiveDisplaySlot(scoreDisplay));
			}
			return scoreboard.getObjective(name);
		}
		return scoreboard.addObjective(name, ScoreCriteria.DUMMY, new StringTextComponent(name), RenderType.INTEGER);
	}
	@OnlyIn(value = Dist.CLIENT)
	public static void handleClientScoreboard(PlayerEntity player, World world, Scoreboard scoreboard) {
		ScoreObjective COINS = getObjectiveClient(ScoreboardEvents.COINS, scoreboard, player);
		ScoreObjective MANA = getObjectiveClient(ScoreboardEvents.MANA, scoreboard, player);
		ScoreObjective MAX_MANA = getObjectiveClient(ScoreboardEvents.MAX_MANA, scoreboard, player);
		ScoreObjective MANA_TIMER = getObjectiveClient(ScoreboardEvents.MANA_TIMER, scoreboard, player);
		ScoreObjective LIFE_CRYSTALS = getObjectiveClient(ScoreboardEvents.LIFE_CRYSTALS, scoreboard, player);
		ScoreObjective LIFE_FRUIT = getObjectiveClient(ScoreboardEvents.LIFE_FRUIT, scoreboard, player);
		ScoreObjective POTION_SICKNESS = getObjectiveClient(ScoreboardEvents.POTION_SICKNESS, scoreboard, player);
		ScoreObjective DAYTIME = getObjectiveClient(ScoreboardEvents.DAYTIME, scoreboard, player);
		ScoreObjective MANA_SICKNESS = getObjectiveClient(ScoreboardEvents.MANA_SICKNESS, scoreboard, player);
		ScoreObjective MANA_SICKNESS_EFFECT = getObjectiveClient(ScoreboardEvents.MANA_SICKNESS_EFFECT, scoreboard, player);
		
		
		ScoreObjective BUILDER = getObjective(ScoreboardEvents.BUILDER, scoreboard, player);
		
		ScoreObjective CALMING = getObjective(ScoreboardEvents.CALMING, scoreboard, player);
		ScoreObjective IRONSKIN = getObjective(ScoreboardEvents.IRONSKIN, scoreboard, player);
		ScoreObjective SWIFTNESS = getObjective(ScoreboardEvents.SWIFTNESS, scoreboard, player);
		ScoreObjective NIGHT_OWL = getObjective(ScoreboardEvents.NIGHT_OWL, scoreboard, player);
		ScoreObjective GILLS = getObjective(ScoreboardEvents.GILLS, scoreboard, player);
		ScoreObjective REGENERATION = getObjective(ScoreboardEvents.REGENERATION, scoreboard, player);
		ScoreObjective MINING = getObjective(ScoreboardEvents.MINING, scoreboard, player);
		ScoreObjective ARCHERY = getObjective(ScoreboardEvents.ARCHERY, scoreboard, player);
		ScoreObjective FEATHERFALL = getObjective(ScoreboardEvents.FEATHERFALL, scoreboard, player);
		ScoreObjective FLIPPER = getObjective(ScoreboardEvents.FLIPPER, scoreboard, player);
		ScoreObjective GRAVITATION = getObjective(ScoreboardEvents.GRAVITATION, scoreboard, player);
		ScoreObjective HEARTREACH = getObjective(ScoreboardEvents.HEARTREACH, scoreboard, player);
		ScoreObjective INVISIBILITY = getObjective(ScoreboardEvents.INVISIBILITY, scoreboard, player);
		ScoreObjective THORNS = getObjective(ScoreboardEvents.THORNS, scoreboard, player);
		ScoreObjective WATER_WALKING = getObjective(ScoreboardEvents.WATER_WALKING, scoreboard, player);
		ScoreObjective SHINE = getObjective(ScoreboardEvents.SHINE, scoreboard, player);
		ScoreObjective BATTLE = getObjective(ScoreboardEvents.BATTLE, scoreboard, player);
		ScoreObjective OBSIDIAN_SKIN = getObjective(ScoreboardEvents.OBSIDIAN_SKIN, scoreboard, player);
		ScoreObjective MAGIC_POWER = getObjective(ScoreboardEvents.MAGIC_POWER, scoreboard, player);
		ScoreObjective MANA_REGENERATION = getObjective(ScoreboardEvents.MANA_REGENERATION, scoreboard, player);
		ScoreObjective TITAN = getObjective(ScoreboardEvents.TITAN, scoreboard, player);
		ScoreObjective HUNTER = getObjective(ScoreboardEvents.HUNTER, scoreboard, player);
		ScoreObjective WEAK = getObjective(ScoreboardEvents.WEAK, scoreboard, player);
		ScoreObjective HORRIFIED = getObjective(ScoreboardEvents.HORRIFIED, scoreboard, player);
		ScoreObjective WELL_FEED = getObjective(ScoreboardEvents.WELL_FEED, scoreboard, player);
		
		Score builder = scoreboard.getOrCreateScore(player.getScoreboardName(), BUILDER);
		Score calming = scoreboard.getOrCreateScore(player.getScoreboardName(), CALMING);
		Score ironskin = scoreboard.getOrCreateScore(player.getScoreboardName(), IRONSKIN);
		Score swiftness = scoreboard.getOrCreateScore(player.getScoreboardName(), SWIFTNESS);
		Score night_owl = scoreboard.getOrCreateScore(player.getScoreboardName(), NIGHT_OWL);
		Score gills = scoreboard.getOrCreateScore(player.getScoreboardName(), GILLS);
		Score regeneration = scoreboard.getOrCreateScore(player.getScoreboardName(), REGENERATION);
		Score mining = scoreboard.getOrCreateScore(player.getScoreboardName(), MINING);
		Score archery = scoreboard.getOrCreateScore(player.getScoreboardName(), ARCHERY);
		Score hunter = scoreboard.getOrCreateScore(player.getScoreboardName(), HUNTER);
		Score featherfall = scoreboard.getOrCreateScore(player.getScoreboardName(), FEATHERFALL);
		Score flipper = scoreboard.getOrCreateScore(player.getScoreboardName(), FLIPPER);
		Score gravitation = scoreboard.getOrCreateScore(player.getScoreboardName(), GRAVITATION);
		Score heartreach = scoreboard.getOrCreateScore(player.getScoreboardName(), HEARTREACH);
		Score invisibility = scoreboard.getOrCreateScore(player.getScoreboardName(), INVISIBILITY);
		Score thorns = scoreboard.getOrCreateScore(player.getScoreboardName(), THORNS);
		Score water_walking = scoreboard.getOrCreateScore(player.getScoreboardName(), WATER_WALKING);
		Score shine = scoreboard.getOrCreateScore(player.getScoreboardName(), SHINE);
		Score battle = scoreboard.getOrCreateScore(player.getScoreboardName(), BATTLE);
		Score obsidian_skin = scoreboard.getOrCreateScore(player.getScoreboardName(), OBSIDIAN_SKIN);
		Score magic_power = scoreboard.getOrCreateScore(player.getScoreboardName(), MAGIC_POWER);
		Score mana_regeneration = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_REGENERATION);
		Score titan = scoreboard.getOrCreateScore(player.getScoreboardName(), TITAN);
		Score weak = scoreboard.getOrCreateScore(player.getScoreboardName(), WEAK);
		Score horrified = scoreboard.getOrCreateScore(player.getScoreboardName(), HORRIFIED);
		Score wellFeed = scoreboard.getOrCreateScore(player.getScoreboardName(), WELL_FEED);
		
		Score manaSickness = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_SICKNESS);
		Score manaSicknessEffect = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_SICKNESS_EFFECT);
		
		
		Score mana = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA);
		Score maxMana = scoreboard.getOrCreateScore(player.getScoreboardName(), MAX_MANA);
		Score manaTimer = scoreboard.getOrCreateScore(player.getScoreboardName(), MANA_TIMER);
		
		
		Score coins = scoreboard.getOrCreateScore(player.getScoreboardName(), COINS);
		Score potionSickness = scoreboard.getOrCreateScore(player.getScoreboardName(), POTION_SICKNESS);
		Score daytime = scoreboard.getOrCreateScore(player.getScoreboardName(), DAYTIME);

		
		
		if (Minecraft.getInstance() != null) {
			if (Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity) {
				PlayerEntity p = (PlayerEntity)Minecraft.getInstance().getRenderViewEntity();
				if (p.getScoreboardName().contentEquals(player.getScoreboardName())) {
					Util.renderCoins = coins.getScorePoints();
					Util.renderMana = mana.getScorePoints();
					Util.renderMaxMana = maxMana.getScorePoints();
					Util.renderPotionSickness = potionSickness.getScorePoints() / 20;
					Util.dayTime = daytime.getScorePoints();
					Util.renderManaSickness = manaSickness.getScorePoints() / 20;
					Util.renderManaSicknessEffect = manaSicknessEffect.getScorePoints();
					Util.renderBuild = builder.getScorePoints() / 20;
					Util.renderCalming = calming.getScorePoints() / 20;
					Util.renderIronskin = ironskin.getScorePoints() / 20;
					Util.renderSwiftness = swiftness.getScorePoints() / 20;
					Util.renderNightOwl = night_owl.getScorePoints() / 20;
					Util.renderGills = gills.getScorePoints() / 20;
					Util.renderRegeneration = regeneration.getScorePoints() / 20;
					Util.renderMining = mining.getScorePoints() / 20;
					Util.renderArchery = archery.getScorePoints() / 20;
					Util.renderHunter = hunter.getScorePoints() / 20;
					Util.renderFeatherfall = featherfall.getScorePoints() / 20;
					Util.renderFlipper = flipper.getScorePoints() / 20;
					Util.renderGravitation = gravitation.getScorePoints() / 20;
					Util.renderHeartreach = heartreach.getScorePoints() / 20;
					Util.renderInvisibility = invisibility.getScorePoints() / 20;
					Util.renderThorns = thorns.getScorePoints() / 20;
					Util.renderWaterWalking = water_walking.getScorePoints() / 20;
					Util.renderShine = shine.getScorePoints() / 20;
					Util.renderBattle = battle.getScorePoints() / 20;
					Util.renderObsidianSkin = obsidian_skin.getScorePoints() / 20;
					Util.renderMagicPower = magic_power.getScorePoints() / 20;
					Util.renderManaRegeneration = mana_regeneration.getScorePoints() / 20;
					Util.renderTitan = titan.getScorePoints() / 20;
					Util.renderWeakDebuff = weak.getScorePoints() / 20;
					Util.renderHorrified = horrified.getScorePoints() / 20;
					Util.renderWellFeed = wellFeed.getScorePoints() / 20;

				}
			}
		}
		
	}
	@OnlyIn(value = Dist.CLIENT)
	public static ScoreObjective getObjectiveClient (String name, Scoreboard scoreboard, PlayerEntity player) {
		if (scoreboard.hasObjective(name)) {
			//scoreboard.broadcastScoreUpdate(player.getScoreboardName(), scoreboard.getObjective(name));
			return scoreboard.getObjective(name);
		}
		return scoreboard.addObjective(name, ScoreCriteria.DUMMY, new StringTextComponent(name), RenderType.INTEGER);
	}
	
}
