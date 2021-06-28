package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.items.armor.CopperArmorMaterial;
import com.ryorama.terrariamod.items.armor.GoldTArmorMaterial;
import com.ryorama.terrariamod.items.armor.IronTArmorMaterial;
import com.ryorama.terrariamod.items.arrows.FlamingArrow;
import com.ryorama.terrariamod.items.arrows.WoodenArrow;
import com.ryorama.terrariamod.items.boss_summons.SlimeCrown;
import com.ryorama.terrariamod.items.boss_summons.SuspiciousLookingEye;
import com.ryorama.terrariamod.items.potions.LesserHealingPotion;
import com.ryorama.terrariamod.items.tools.MagicMirror;
import com.ryorama.terrariamod.items.tools.axe.CopperAxe;
import com.ryorama.terrariamod.items.tools.axe.GoldAxe;
import com.ryorama.terrariamod.items.tools.axe.IronAxe;
import com.ryorama.terrariamod.items.tools.bows.CopperBow;
import com.ryorama.terrariamod.items.tools.bows.GoldBow;
import com.ryorama.terrariamod.items.tools.bows.IronBow;
import com.ryorama.terrariamod.items.tools.picks.CopperPickaxe;
import com.ryorama.terrariamod.items.tools.picks.GoldPickaxe;
import com.ryorama.terrariamod.items.tools.picks.IronPickaxe;
import com.ryorama.terrariamod.items.weapons.broadswords.CopperBroadsword;
import com.ryorama.terrariamod.items.weapons.broadswords.GoldBroadsword;
import com.ryorama.terrariamod.items.weapons.broadswords.IronBroadsword;
import com.ryorama.terrariamod.items.weapons.shortswords.CopperShortsword;
import com.ryorama.terrariamod.items.weapons.shortswords.GoldShortsword;
import com.ryorama.terrariamod.items.weapons.shortswords.IronShortsword;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsT {
			
	public static String getStringForItem(Item item) {
		if (item == null) {
			return "null";
		}
		String str = Registry.ITEM.getId(item).getNamespace()+":"+Registry.ITEM.getId(item).getPath();
		if (Registry.ITEM.getId(item).toString().contains(Registry.ITEM.getId(item).getNamespace())) {
			str = Registry.ITEM.getId(item).toString();
		}
		return str;
	}
	
	public static Item getItemFromString(String name) {
		
		try {
			return Registry.ITEM.get(new Identifier(name));
		}catch (Exception e) {
//			e.printStackTrace();
		}
		
		try {
			return Registry.ITEM.get(new Identifier("trewrite:"+name));
		}catch (Exception e) {
//			e.printStackTrace();
		}
		
		return null;
//		return ItemsT.items.get(name);
	}
	
	public static ItemT WOOD = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static ItemT COPPER_BAR = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static ItemT GEL = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static ItemT LENS = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static WoodenArrow WOODEN_ARROW = (WoodenArrow) new WoodenArrow(new FabricItemSettings().group(ItemGroup.COMBAT)).setRarity(2);
	public static FlamingArrow FLAMING_ARROW = (FlamingArrow) new FlamingArrow(new FabricItemSettings().group(ItemGroup.COMBAT)).setRarity(2);

	public static LifeCrystal HEALTH_CRYSTAL = (LifeCrystal) new LifeCrystal(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static MagicMirror MAGIC_MIRROR = (MagicMirror) new MagicMirror(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);

	public static ItemT SLIME_CROWN = new SlimeCrown(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(3);
	public static ItemT SUSPICIOUS_LOOKING_EYE = new SuspiciousLookingEye(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(3);

	public static ItemT GOLD_CROWN = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	
	public static ItemT IRON_BAR = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static ItemT GOLD_BAR = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);

	public static CopperShortsword COPPER_SHORTSWORD = (CopperShortsword) new CopperShortsword().setRarity(3);
	public static CopperPickaxe COPPER_PICKAXE = (CopperPickaxe) new CopperPickaxe().setRarity(3);
	public static CopperAxe COPPER_AXE = (CopperAxe) new CopperAxe().setRarity(3);
	public static CopperBroadsword COPPER_BROADSWORD = (CopperBroadsword) new CopperBroadsword().setRarity(3);
	
	public static IronShortsword IRON_SHORTSWORD = (IronShortsword) new IronShortsword().setRarity(3);
	public static IronPickaxe IRON_PICKAXE = (IronPickaxe) new IronPickaxe().setRarity(3);
	public static IronAxe IRON_AXE = (IronAxe) new IronAxe().setRarity(3);
	public static IronBroadsword IRON_BROADSWORD = (IronBroadsword) new IronBroadsword().setRarity(3);
	
	public static GoldShortsword GOLD_SHORTSWORD = (GoldShortsword) new GoldShortsword().setRarity(3);
	public static GoldPickaxe GOLD_PICKAXE = (GoldPickaxe) new GoldPickaxe().setRarity(3);
	public static GoldAxe GOLD_AXE = (GoldAxe) new GoldAxe().setRarity(3);
	public static GoldBroadsword GOLD_BROADSWORD = (GoldBroadsword) new GoldBroadsword().setRarity(3);
	
	public static CopperArmorMaterial COPPER_MATERIAL = new CopperArmorMaterial();
	public static IronTArmorMaterial IRON_MATERIAL = new IronTArmorMaterial();
	public static GoldTArmorMaterial GOLD_MATERIAL = new GoldTArmorMaterial();
	
	public static Item COPPER_HELMET = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item COPPER_CHESTPLATE = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item COPPER_LEGGINGS = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT));	

	public static Item IRON_HELMET = new ArmorItem(IRON_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item IRON_CHESTPLATE = new ArmorItem(IRON_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item IRON_LEGGINGS = new ArmorItem(IRON_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT));	
	
	public static Item GOLD_HELMET = new ArmorItem(GOLD_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item GOLD_CHESTPLATE = new ArmorItem(GOLD_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item GOLD_LEGGINGS = new ArmorItem(GOLD_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT));	
	
	public static CopperBow COPPER_BOW = new CopperBow(new FabricItemSettings().group(ItemGroup.COMBAT));
	public static IronBow IRON_BOW = new IronBow(new FabricItemSettings().group(ItemGroup.COMBAT));
	public static GoldBow GOLD_BOW = new GoldBow(new FabricItemSettings().group(ItemGroup.COMBAT));
	
	public static LesserHealingPotion LESSER_HEALING_POTION = new LesserHealingPotion(new FabricItemSettings().group(ItemGroup.BREWING));
	
	public static void init() {
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "wood"), WOOD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_bar"), COPPER_BAR);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gel"), GEL);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "lens"), LENS);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "life_crystal"), HEALTH_CRYSTAL);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "wooden_arrow"), WOODEN_ARROW);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "flaming_arrow"), FLAMING_ARROW);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "magic_mirror"), MAGIC_MIRROR);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "lesser_healing_potion"), LESSER_HEALING_POTION);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_bar"), IRON_BAR);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_bar"), GOLD_BAR);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_crown"), GOLD_CROWN);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "slime_crown"), SLIME_CROWN);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "suspicious_looking_eye"), SUSPICIOUS_LOOKING_EYE);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_shortsword"), COPPER_SHORTSWORD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_pickaxe"), COPPER_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_axe"), COPPER_AXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_broadsword"), COPPER_BROADSWORD);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_shortsword"), IRON_SHORTSWORD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_pickaxe"), IRON_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_axe"), IRON_AXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_broadsword"), IRON_BROADSWORD);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_shortsword"), GOLD_SHORTSWORD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_pickaxe"), GOLD_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_axe"), GOLD_AXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_broadsword"), GOLD_BROADSWORD);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_helmet"), COPPER_HELMET);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_chestplate"), COPPER_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_leggings"), COPPER_LEGGINGS);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_helmet"), IRON_HELMET);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_chestplate"), IRON_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_leggings"), IRON_LEGGINGS);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_helmet"), GOLD_HELMET);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_chestplate"), GOLD_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_leggings"), GOLD_LEGGINGS);

		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "grass_block"), new BlockItemT(BlocksT.GRASS_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)).setRarity(2));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "dirt_block"), new BlockItemT(BlocksT.DIRT_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)).setRarity(2));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "stone_block"), new BlockItemT(BlocksT.STONE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "ash"), new BlockItemT(BlocksT.ASH, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "mud"), new BlockItemT(BlocksT.MUD, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "sand"), new BlockItemT(BlocksT.SAND, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "snow"), new BlockItemT(BlocksT.SNOW, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "marble"), new BlockItemT(BlocksT.MARBLE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "granite"), new BlockItemT(BlocksT.GRANITE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "wooden_beam"), new BlockItemT(BlocksT.WOODEN_BEAM, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "ebonstone"), new BlockItemT(BlocksT.EBONSTONE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "empty_bottle"), new BlockItemT(BlocksT.EMPTY_BOTTLE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "mushroom"), new BlockItemT(BlocksT.MUSHROOM, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_ore"), new BlockItemT(BlocksT.COPPER_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_ore"), new BlockItemT(BlocksT.IRON_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_ore"), new BlockItemT(BlocksT.GOLD_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "hellstone_ore"), new BlockItemT(BlocksT.HELLSTONE_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
	
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "ruby_ore"), new BlockItemT(BlocksT.RUBY_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "sapphire_ore"), new BlockItemT(BlocksT.SAPPHIRE_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "green_slime_spawn_egg"), new SpawnEggItem(EntitiesT.GREEN_SLIME, 0x2ec221, 0x5fed53, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "blue_slime_spawn_egg"), new SpawnEggItem(EntitiesT.BLUE_SLIME, 0x2f7dc4, 0x53a3ed, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "demon_eye_slime_spawn_egg"), new SpawnEggItem(EntitiesT.DEMON_EYE, 0xffffff, 0xff0000, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "demon_spawn_egg"), new SpawnEggItem(EntitiesT.DEMON, 0x704b14, 0x703614, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "eater_of_souls_spawn_egg"), new SpawnEggItem(EntitiesT.EATER_OF_SOULS, 0x704b14, 0x7a8737, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "granite_elemental_spawn_egg"), new SpawnEggItem(EntitiesT.GRANITE_ELEMETAL, 0x0c0833, 0x2c2387, new FabricItemSettings().group(ItemGroup.MISC)));

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "copper_bow"), COPPER_BOW);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "iron_bow"), IRON_BOW);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "gold_bow"), GOLD_BOW);

	}
	
	public static ItemStack gel(int amount, String color) {
		ItemStack stack = GEL.stack(amount);
		NbtCompound tag;
		if (!stack.hasTag()) {
			tag = new NbtCompound();
		} else {
			System.out.println("Has tag");
			tag = stack.getTag();
		}
		tag.putString("GelColor", color);
		stack.setTag(tag);
		return stack;
	}
}
