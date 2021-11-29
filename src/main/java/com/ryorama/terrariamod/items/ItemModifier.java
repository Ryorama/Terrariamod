package com.ryorama.terrariamod.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.ryorama.terrariamod.items.api.ItemT;
import com.ryorama.terrariamod.items.modifiers.AccessoryModifier;
import com.ryorama.terrariamod.items.modifiers.CommonModifier;
import com.ryorama.terrariamod.items.modifiers.MagicModifier;
import com.ryorama.terrariamod.items.modifiers.MeleeModifier;
import com.ryorama.terrariamod.items.modifiers.RangedModifier;
import com.ryorama.terrariamod.items.modifiers.UniversalModifer;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ItemModifier {
	
public static HashMap<String, ItemModifier> modifiers = new HashMap<String, ItemModifier>();
	
	public EnumModifierType modifierType = EnumModifierType.NONE;
	
	public double tier, value, damage, speed, crit, manaCost, size, velocity, knockback;
	public double defense, movementSpeed, meleeSpeed, mana, health;

	public String name;
	
	public static ItemModifier HARD = registerModifier(new AccessoryModifier("Hard", 0, 10.25).defense(1));
	public static ItemModifier GUARDING = registerModifier(new AccessoryModifier("Guarding", 1, 21.00).defense(2));
	public static ItemModifier ARMORED = registerModifier(new AccessoryModifier("Armored", 1, 32.25).defense(3));
	public static ItemModifier WARDING = registerModifier(new AccessoryModifier("Warding", 2, 44.00).defense(4));
	public static ItemModifier PRECISE = registerModifier(new AccessoryModifier("Precise", 1, 21.00).crit(2));
	public static ItemModifier LUCKY = registerModifier(new AccessoryModifier("Lucky", 2, 44.00).crit(4));
	public static ItemModifier JAGGED = registerModifier(new AccessoryModifier("Jagged", 0, 10.25).damage(1));
	public static ItemModifier SPIKED = registerModifier(new AccessoryModifier("Spiked", 1, 21.00).damage(2));
	public static ItemModifier ANGRY = registerModifier(new AccessoryModifier("Angry", 1, 32.25).damage(3));
	public static ItemModifier MENACING = registerModifier(new AccessoryModifier("Menacing", 2, 44.00).damage(4));
	public static ItemModifier BRISK = registerModifier(new AccessoryModifier("Brisk", 0, 10.25).movementSpeed(1));
	public static ItemModifier FLEETING = registerModifier(new AccessoryModifier("Fleeting", 1, 21.00).movementSpeed(2));
	public static ItemModifier HASTY = registerModifier(new AccessoryModifier("Hasty", 1, 32.25).movementSpeed(3));
	public static ItemModifier QUICK = registerModifier(new AccessoryModifier("Quick", 2, 44.00).movementSpeed(4));
	public static ItemModifier WILD = registerModifier(new AccessoryModifier("Wild", 0, 10.25).meleeSpeed(1));
	public static ItemModifier RASH = registerModifier(new AccessoryModifier("Rash", 1, 21.00).meleeSpeed(2));
	public static ItemModifier INTREPID = registerModifier(new AccessoryModifier("Intrepid", 1, 32.25).meleeSpeed(3));
	public static ItemModifier VIOLENT = registerModifier(new AccessoryModifier("Violent", 2, 44.00).meleeSpeed(4));
	public static ItemModifier ARCANE = registerModifier(new AccessoryModifier("Arcane", 1, 32.25).mana(20));
	
	
	public static ItemModifier KEEN = registerModifier(new UniversalModifer("Keen", 0, 3, 0, 1, 12.36));
	public static ItemModifier SUPERIOR = registerModifier(new UniversalModifer("Superior", 10, 3, 10, 2, 64.51));
	public static ItemModifier FORCEFUL = registerModifier(new UniversalModifer("Forceful", 0, 0, 15, 1, 32.25));
	public static ItemModifier BROKEN = registerModifier(new UniversalModifer("Broken", -30, 0, -20, -2, -68.64));
	public static ItemModifier DAMAGED = registerModifier(new UniversalModifer("Damaged", -15, 0, 0, -1, -27.75));
	public static ItemModifier SHODDY = registerModifier(new UniversalModifer("Shoddy", -10, 0, -15, -2, -41.48));
	public static ItemModifier HURTFUL = registerModifier(new UniversalModifer("Hurtful", 10, 0, 0, 1, 21.00));
	public static ItemModifier STRONG = registerModifier(new UniversalModifer("Strong", 0, 0, 15, 1, 32.25));
	public static ItemModifier UNPLEASANT = registerModifier(new UniversalModifer("Unpleasant", 5, 0, 15, 2, 45.81));
	public static ItemModifier WEAK = registerModifier(new UniversalModifer("Weak", 0, 0, -20, -1, -36.00));
	public static ItemModifier RUTHLESS = registerModifier(new UniversalModifer("Ruthless", 18, 0, -10, 1, 12.78));
	public static ItemModifier GODLY = registerModifier(new UniversalModifer("Godly", 15, 5, 15, 2, 111.63));
	public static ItemModifier DEMONIC = registerModifier(new UniversalModifer("Demonic", 15, 5, 0, 2, 60.02));
	public static ItemModifier ZEALOUS = registerModifier(new UniversalModifer("Zealous", 0, 5, 0, 1, 21.00));

	public static ItemModifier QUICK_COMMON = registerModifier("QuickCommon", new CommonModifier("Quick", 0, 10, 0, 0, 1, 21.00));
	public static ItemModifier DEADLY = registerModifier(new CommonModifier("Deadly", 10, 10, 0, 0, 2, 46.41));
	public static ItemModifier AGILE = registerModifier(new CommonModifier("Agile", 0, 10, 3, 0, 1, 35.96));
	public static ItemModifier NIMBLE = registerModifier(new CommonModifier("Nimble", 0, 5, 0, 0, 1, 10.25));
	public static ItemModifier MURDEROUS = registerModifier(new CommonModifier("Murderous", 7, 6, 3, 0, 2, 44.54));
	public static ItemModifier SLOW = registerModifier(new CommonModifier("Slow", 0, -15, 0, 0, -1, -27.75));
	public static ItemModifier SLUGGISH = registerModifier(new CommonModifier("Sluggish", 0, -20, 0, 0, -2, -36.00));
	public static ItemModifier LAZY = registerModifier(new CommonModifier("Lazy", 0, -8, 0, 0, -1, -15.36));
	public static ItemModifier ANNOYING = registerModifier(new CommonModifier("Annoying", -20, -15, 0, 0, -2, -53.76));
	public static ItemModifier NASTY = registerModifier(new CommonModifier("Nasty", 5, 10, 2, -10, 1, 16.87));
	
	public static ItemModifier LARGE = registerModifier(new MeleeModifier("Large", 0, 0, 0, 12, 0, 1, 25.44));
	public static ItemModifier MASSIVE = registerModifier(new MeleeModifier("Massive", 0, 0, 0, 18, 0, 1, 39.24));
	public static ItemModifier DANGEROUS = registerModifier(new MeleeModifier("Dangerous", 5, 0, 2, 5, 0, 1, 31.47));
	public static ItemModifier SAVAGE = registerModifier(new MeleeModifier("Savage", 10, 0, 0, 10, 10, 2, 77.16));
	public static ItemModifier SHARP = registerModifier(new MeleeModifier("Sharp", 15, 0, 0, 0, 0, 1, 32.25));
	public static ItemModifier POINTY = registerModifier(new MeleeModifier("Pointy", 10, 0, 0, 0, 0, 1, 21.00));
	public static ItemModifier TINY = registerModifier(new MeleeModifier("Tiny", 0, 0, 0, -18, 0, -1, -32.76));
	public static ItemModifier TERRIBLE = registerModifier(new MeleeModifier("Terrible", -15, 0, 0, -13, -15, -2, -60.49));
	public static ItemModifier SMALL = registerModifier(new MeleeModifier("Small", 0, 0, 0, -10, 0, -1, -19.00));
	public static ItemModifier DULL = registerModifier(new MeleeModifier("Dull", -15, 0, 0, 0, 0, -1, -27.75));
	public static ItemModifier UNHAPPY = registerModifier(new MeleeModifier("Unhappy", 0, -10, 0, -10, -10, -2, -46.86));
	public static ItemModifier BULKY = registerModifier(new MeleeModifier("Bulky", 5, -15, 0, 10, 10, 1, 16.62));
	public static ItemModifier SHAMEFUL = registerModifier(new MeleeModifier("Shameful", -10, 0, 0, 10, -20, -2, -37.27));
	public static ItemModifier HEAVY = registerModifier(new MeleeModifier("Heavy", 0, -10, 0, 0, 15, 0, 7.12));
	public static ItemModifier LIGHT = registerModifier(new MeleeModifier("Light", 0, 15, 0, 0, -10, 0, 7.12));
	public static ItemModifier LEGENDARY = registerModifier(new MeleeModifier("Legendary", 15, 10, 5, 10, 15, 2, 209.85));

	public static ItemModifier SIGHTED = registerModifier(new RangedModifier("Sighted", 10, 0, 3, 0, 0, 1, 35.96));
	public static ItemModifier RAPID = registerModifier(new RangedModifier("Rapid", 0, 15, 0, 10, 0, 2, 60.02));
	public static ItemModifier HASTY_RANGED = registerModifier("HastyRanged",new RangedModifier("Hasty", 0, 10, 0, 15, 0, 2, 60.02));
	public static ItemModifier INTIMIDATING = registerModifier(new RangedModifier("Intimidating", 0, 0, 0, 5, 15, 2, 45.81));
	public static ItemModifier DEADLY_RANGED = registerModifier("DeadlyRanged",new RangedModifier("Deadly", 10, 5, 2, 5, 5, 2, 75.38));
	public static ItemModifier STAUNCH = registerModifier(new RangedModifier("Staunch", 10, 0, 0, 0, 15, 2, 60.02));
	public static ItemModifier AWFUL = registerModifier(new RangedModifier("Awful", -15, 0, 0, -10, -10, -2, -52.60));
	public static ItemModifier LETHARGIC = registerModifier(new RangedModifier("Lethargic", 0, -15, 0, -10, 0, -2, -41.48));
	public static ItemModifier AWKWARD = registerModifier(new RangedModifier("Awkward", 0, 10, 0, 0, -20, -2, -48.16));
	public static ItemModifier POWERFUL = registerModifier(new RangedModifier("Powerful", 15, -10, 1, 0, 0, 1, 11.45));
	public static ItemModifier FRENZYING = registerModifier(new RangedModifier("Frenzying", -15, 15, 0, 0, 0, 0, -4.45));
	public static ItemModifier UNREAL = registerModifier(new RangedModifier("Unreal", 15, 10, 5, 10, 15, 2, 209.85));
	
	public static ItemModifier MYSTIC = registerModifier(new MagicModifier("Mystic", 10, 0, 0, -15, 0, 2, 60.02));
	public static ItemModifier ADEPT = registerModifier(new MagicModifier("Adept", 0, 0, 0, -15, 0, 1, 32.25));
	public static ItemModifier MASTERFUL = registerModifier(new MagicModifier("Masterful", 15, 0, 0, -20, 5, 2, 92.83));
	public static ItemModifier INEPT = registerModifier(new MagicModifier("Inept", 0, 0, 0, 10, 0, -1, -19.00));
	public static ItemModifier IGNORANT = registerModifier(new MagicModifier("Ignorant", -10, 0, 0, 20, 0, -2, -48.16));
	public static ItemModifier DERANGED = registerModifier(new MagicModifier("Deranged", -10, 0, 0, 0, -10, -1, -34.39));
	public static ItemModifier INTENSE = registerModifier(new MagicModifier("Intense", 10, 0, 0, 15, 0, -1, -12.58));
	public static ItemModifier TABOO = registerModifier(new MagicModifier("Taboo", 0, 10, 0, 10, 10, 1, 18.59));
	public static ItemModifier CELESTIAL = registerModifier(new MagicModifier("Celestial", 10, -10, 0, -10, 10, 1, 43.50));
	public static ItemModifier FURIOUS = registerModifier(new MagicModifier("Furious", 15, 0, 0, 20, 15, 1, 11.94));
	public static ItemModifier MANIC = registerModifier(new MagicModifier("Manic", -10, 10, 0, -10, 0, 1, 18.59));
	public static ItemModifier MYTHICAL = registerModifier(new MagicModifier("Mythical", 15, 10, 5, -10, 15, 2, 209.85));
	
	
	
	public ItemModifier(EnumModifierType modifierType, String name, double tier, double value) {
		this.modifierType = modifierType;
		this.name = name;
		this.tier = tier;
		this.value = value;
	}
	
	public ItemModifier() {
		
	}
	
	public boolean doesModifierEffect(ItemT item) {
		return item.MODIFIER_TYPE.isCompatible(this.modifierType);
	}
	
	public static ItemModifier registerModifier(String id, ItemModifier modifier) {
		ItemModifier.modifiers.put(id, modifier);
		return modifier;
	}
	
	public static ItemModifier registerModifier(ItemModifier modifier) {
		ItemModifier.modifiers.put(modifier.name, modifier);
		return modifier;
	}
	
	public static ItemModifier getRandomModifier(Item i) {
		
		if (i instanceof ItemT) {
			ItemT item = (ItemT)i;
			
			if (item.MODIFIER_TYPE == EnumModifierType.NONE) return null;
			ItemModifier modifier = null;
			ArrayList<ItemModifier> modifiersForItem = new ArrayList<ItemModifier>();
			for (String str : ItemModifier.modifiers.keySet()) {
				ItemModifier mod = ItemModifier.modifiers.get(str);
				if (mod.doesModifierEffect(item)) {
					modifiersForItem.add(mod);
				}
			}
			return modifiersForItem.get(new Random().nextInt(modifiersForItem.size()));
		}
		return null;
	}
	
	public static int getIdForModifier(ItemModifier modifier) {
		if (modifier == null) return -1;
		int i = 0;
		boolean hasMod = false;
		for (String str : ItemModifier.modifiers.keySet()) {
			ItemModifier mod = ItemModifier.modifiers.get(str);
			if (mod == modifier) {
				hasMod = true;
				break;
			}
			i++;
		}
		return (hasMod ? i : -1);
	}

	public static ItemModifier getModifier(int modifier) {
		if (modifier == -1) return null;
		int a = 0;
		for (String str : ItemModifier.modifiers.keySet()) {
			if (a == modifier) {
				return ItemModifier.modifiers.get(str);
			}
			a++;
		}
		return null;
	}

//	public double tier, value, damage, speed, crit, manaCost, size, velocity, knockback;
//	public double defense, movementSpeed, meleeSpeed, mana, health;
	
	public void addTooltips(List<Text> tooltips) {
		addLine(damage, "damage", true, tooltips);
		addLine(speed, "use speed", true, tooltips);
		addLine(crit, "critical strike chance", true, tooltips);
		addLine(manaCost, "mana cost", true, tooltips);
		addLine(velocity, "velocity", true, tooltips);
		addLine(knockback, "knockback", true, tooltips);
		addLine(defense, "defense", false, tooltips);
		addLine(movementSpeed, "movement speed", true, tooltips);
		addLine(meleeSpeed, "melee speed", true, tooltips);
		addLine(mana, "max mana", false, tooltips);
		addLine(health, "max health", false, tooltips);
		addLine(size, "size", true, tooltips);
	}
	public void addLine(double a, String description, boolean percent, List<Text> tooltips) {
		String str = "";
		if (a == 0) return;
		str += getValue(a) + (percent ? "%" : "") + " " + description;
		tooltips.add(new TranslatableText(str));
	}
	public String getValue(double a) {
		if (a < 0) return ""+a;
		return "+"+a;
	}

}
