package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.armor.CopperArmorMaterial;
import com.ryorama.terrariamod.items.boss_summons.SlimeCrown;
import com.ryorama.terrariamod.items.tools.axe.CopperAxe;
import com.ryorama.terrariamod.items.tools.axe.GoldAxe;
import com.ryorama.terrariamod.items.tools.axe.IronAxe;
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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsT {
			
	public static ItemT WOOD = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static ItemT COPPER_BAR = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	public static ItemT GEL = new ItemT(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(2);
	
	public static ItemT SLIME_CROWN = new SlimeCrown(new FabricItemSettings().group(ItemGroup.MISC)).setRarity(3);
	
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
	
	public static Item COPPER_HELMET = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item COPPER_CHESTPLATE = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT));
	public static Item COPPER_LEGGINGS = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT));	

	public static void init() {
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "wood"), WOOD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_bar"), COPPER_BAR);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "gel"), GEL);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "slime_crown"), SLIME_CROWN);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_shortsword"), COPPER_SHORTSWORD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_pickaxe"), COPPER_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_axe"), COPPER_AXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_broadsword"), COPPER_BROADSWORD);

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "iron_shortsword"), IRON_SHORTSWORD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "iron_pickaxe"), IRON_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "iron_axe"), IRON_AXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "iron_broadsword"), IRON_BROADSWORD);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "gold_shortsword"), GOLD_SHORTSWORD);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "gold_pickaxe"), GOLD_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "gold_axe"), GOLD_AXE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "gold_broadsword"), GOLD_BROADSWORD);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_helmet"), COPPER_HELMET);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_chestplate"), COPPER_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_leggings"), COPPER_LEGGINGS);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "grass_block"), new BlockItemT(BlocksT.GRASS_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)).setRarity(2));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "dirt_block"), new BlockItemT(BlocksT.DIRT_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)).setRarity(2));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "stone_block"), new BlockItemT(BlocksT.STONE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "ash"), new BlockItemT(BlocksT.ASH, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_ore"), new BlockItemT(BlocksT.COPPER_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "iron_ore"), new BlockItemT(BlocksT.IRON_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "gold_ore"), new BlockItemT(BlocksT.GOLD_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "hellstone_ore"), new BlockItemT(BlocksT.HELLSTONE_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));		
	}
	
	public static ItemStack gel(int amount, String color) {
		ItemStack stack = GEL.stack(amount);
		CompoundTag tag;
		if (!stack.hasTag()) {
			tag = new CompoundTag();
		} else {
			System.out.println("Has tag");
			tag = stack.getTag();
		}
		tag.putString("GelColor", color);
		stack.setTag(tag);
		return stack;
	}
}
