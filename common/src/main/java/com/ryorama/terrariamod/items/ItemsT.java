package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.items.impl.BlockItemT;
import com.ryorama.terrariamod.items.impl.ItemT;
import com.ryorama.terrariamod.items.terraria.armor.*;
import com.ryorama.terrariamod.items.terraria.axes.CopperAxe;
import com.ryorama.terrariamod.items.terraria.axes.GoldAxe;
import com.ryorama.terrariamod.items.terraria.axes.IronAxe;
import com.ryorama.terrariamod.items.terraria.bows.CopperBow;
import com.ryorama.terrariamod.items.terraria.bows.GoldBow;
import com.ryorama.terrariamod.items.terraria.bows.IronBow;
import com.ryorama.terrariamod.items.terraria.broadswords.CopperBroadsword;
import com.ryorama.terrariamod.items.terraria.broadswords.GoldBroadsword;
import com.ryorama.terrariamod.items.terraria.broadswords.IronBroadsword;
import com.ryorama.terrariamod.items.terraria.consumable.ammo.arrows.FlamingArrow;
import com.ryorama.terrariamod.items.terraria.consumable.ammo.arrows.WoodenArrow;
import com.ryorama.terrariamod.items.terraria.consumable.misc.LifeCrystal;
import com.ryorama.terrariamod.items.terraria.consumable.potions.LesserHealingPotion;
import com.ryorama.terrariamod.items.terraria.picks.CopperPickaxe;
import com.ryorama.terrariamod.items.terraria.picks.GoldPickaxe;
import com.ryorama.terrariamod.items.terraria.picks.IronPickaxe;
import com.ryorama.terrariamod.items.terraria.shortswords.CopperShortsword;
import com.ryorama.terrariamod.items.terraria.shortswords.GoldShortsword;
import com.ryorama.terrariamod.items.terraria.shortswords.IronShortsword;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ItemsT {
    //public static WoodenArrow WOODEN_ARROW = (WoodenArrow) new WoodenArrow(new Item.Settings().maxCount(999)).setRarity(2);
    //public static FlamingArrow FLAMING_ARROW = (FlamingArrow) new FlamingArrow(new Item.Settings().maxCount(999)).setRarity(2);

    //public static LifeCrystal HEALTH_CRYSTAL = (LifeCrystal) new LifeCrystal(new Item.Settings().maxCount(99)).setRarity(2).isConsumable(true);
    //public static MagicMirror MAGIC_MIRROR = (MagicMirror) new MagicMirror(new Item.Settings().maxCount(1)).setRarity(2);

    //public static ItemT SLIME_CROWN = new SlimeCrown(new Item.Settings().maxCount(30)).setRarity(3).isConsumable(true);
    //public static ItemT SUSPICIOUS_LOOKING_EYE = new SuspiciousLookingEye(new Item.Settings().maxCount(30)).setRarity(3).isConsumable(true);

    //public static CopperArmorMaterial COPPER_MATERIAL = new CopperArmorMaterial();
    //public static IronTArmorMaterial IRON_MATERIAL = new IronTArmorMaterial();
    //public static GoldTArmorMaterial GOLD_MATERIAL = new GoldTArmorMaterial();
    //public static NinjaArmorMaterial NINJA_MATERIAL = new NinjaArmorMaterial();

    //public static Item COPPER_HELMET = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());
    //public static Item COPPER_CHESTPLATE = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
    //public static Item COPPER_LEGGINGS = new ArmorItem(COPPER_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());

    //public static Item IRON_HELMET = new ArmorItem(IRON_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());
    //public static Item IRON_CHESTPLATE = new ArmorItem(IRON_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
    //public static Item IRON_LEGGINGS = new ArmorItem(IRON_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());

    //public static Item GOLD_HELMET = new ArmorItem(GOLD_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());
    //public static Item GOLD_CHESTPLATE = new ArmorItem(GOLD_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
    //public static Item GOLD_LEGGINGS = new ArmorItem(GOLD_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());

    //public static Item NINJA_HELMET = new ArmorItem(NINJA_MATERIAL, EquipmentSlot.HEAD, new Item.Settings());
    //public static Item NINJA_CHESTPLATE = new ArmorItem(NINJA_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
    //public static Item NINJA_LEGGINGS = new ArmorItem(NINJA_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());

    //public static WaterBottle WATER_BOTTLE = (WaterBottle) new WaterBottle(new Item.Settings().arch$tab(ItemGroups.FOOD_AND_DRINK)).isConsumable(true);
    //public static LesserHealingPotion LESSER_HEALING_POTION = (LesserHealingPotion) new LesserHealingPotion(new Item.Settings().arch$tab(ItemGroups.FOOD_AND_DRINK)).isConsumable(true);
    //public static HealingPotion HEALING_POTION = (HealingPotion) new HealingPotion(new Item.Settings().arch$tab(ItemGroups.FOOD_AND_DRINK)).isConsumable(true);
    //public static HoneyBottle HONEY_BOTTLE = (HoneyBottle) new HoneyBottle(new Item.Settings().arch$tab(ItemGroups.FOOD_AND_DRINK)).isConsumable(true);
    //public static IronSkinPotion IRONSKIN_POTION = (IronSkinPotion) new IronSkinPotion(new Item.Settings().arch$tab(ItemGroups.FOOD_AND_DRINK)).isConsumable(true);
    //public static RegenerationPotion REGENERATION_POTION = (RegenerationPotion) new RegenerationPotion(new Item.Settings().arch$tab(ItemGroups.FOOD_AND_DRINK)).isConsumable(true);

    //public static KingSlimeTresureBag KING_SLIME_BAG = (KingSlimeTresureBag) new KingSlimeTresureBag(new Item.Settings().maxCount(30)).isConsumable(true);
    //public static EyeOfCthulhuTresureBag EOC_BAG = (EyeOfCthulhuTresureBag) new EyeOfCthulhuTresureBag(new Item.Settings().maxCount(30)).isConsumable(true);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ITEM);
    public static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ITEM_GROUP);

    public static final RegistrySupplier<ItemGroup> TERRARIAMOD_GROUP = registerTab("terrariamod_tab", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup.terrariamod.terrariamod_tab"),
            () -> ItemsT.GRASS_BLOCK.get().getDefaultStack()
    ));

    public static Item BAND_OF_REGENERATION;
    public static Item BAND_OF_STARPOWER;
    public static Item AGLET;
    public static Item MAGMA_STONE;
    public static Item SHACKLE;
    public static Item ROYAL_GEL;
    public static Item SHIELD_OF_CTHULHU;
    public static Item FLEDGLING_WINGS;

    public static Item SHINY_RED_BALLOON;

    public static Item TERRARIA_DAYONE_MUSIC;
    public static Item TERRARIA_NIGHT_MUSIC;
    public static Item TERRARIA_BOSSONE_MUSIC;
    public static Item TERRARIA_CAVE_MUSIC;
    public static Item TERRARIA_UNDERWORLD_MUSIC;
    public static Item TERRARIA_TITLE_MUSIC;

    public static final CopperArmorMaterial COPPER_MATERIAL = new CopperArmorMaterial();
    public static final IronTArmorMaterial IRON_MATERIAL = new IronTArmorMaterial();
    public static final GoldTArmorMaterial GOLD_MATERIAL = new GoldTArmorMaterial();
    public static final NinjaArmorMaterial NINJA_MATERIAL = new NinjaArmorMaterial();
    public static final CrownMaterial CROWN_MATERIAL = new CrownMaterial();

    public static final RegistrySupplier<Item> COPPER_HELMET = register("copper_helmet", () -> new ArmorItem(COPPER_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> COPPER_CHESTPLATE = register("copper_chestplate", () -> new ArmorItem(COPPER_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> COPPER_LEGGINGS = register("copper_leggings", () -> new ArmorItem(COPPER_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));

    public static final RegistrySupplier<Item> IRON_HELMET = register("iron_helmet", () -> new ArmorItem(IRON_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> IRON_CHESTPLATE = register("iron_chestplate", () -> new ArmorItem(IRON_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> IRON_LEGGINGS = register("iron_leggings", () -> new ArmorItem(IRON_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));

    public static final RegistrySupplier<Item> GOLD_HELMET = register("gold_helmet", () -> new ArmorItem(GOLD_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> GOLD_CHESTPLATE = register("gold_chestplate", () -> new ArmorItem(GOLD_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> GOLD_LEGGINGS = register("gold_leggings", () -> new ArmorItem(GOLD_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));

    public static final RegistrySupplier<Item> NINJA_HELMET = register("ninja_helmet", () -> new ArmorItem(NINJA_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> NINJA_CHESTPLATE = register("ninja_chestplate", () -> new ArmorItem(NINJA_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> NINJA_LEGGINGS = register("ninja_leggings", () -> new ArmorItem(NINJA_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));

    public static final RegistrySupplier<Item> ACORN = register("acorn", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> GEL = register("gel", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> LENS = register("lens", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> FALLEN_STAR = register("fallen_star", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> LIFE_CRYSTAL = register("life_crystal", () -> new LifeCrystal(new Item.Settings().maxCount(9999)).setRarity(4));
    public static final RegistrySupplier<Item> GOLD_CROWN = register("gold_crown", () -> new ArmorItem(CROWN_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> COPPER_BAR = register("copper_bar", () ->  new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> IRON_BAR = register("iron_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> GOLD_BAR = register("gold_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> DEMONITE_BAR = register("demonite_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CRIMTANE_BAR = register("crimtane_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> HELLSTONE_BAR = register("hellstone_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(4));
    public static final RegistrySupplier<Item> TIN_BAR = register("tin_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> LEAD_BAR = register("lead_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> PLATINUM_BAR = register("platinum_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> TUNGSTEN_BAR = register("tungsten_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SILVER_BAR = register("silver_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> METEORITE_BAR = register("meteorite_bar", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(4));
    public static final RegistrySupplier<Item> COPPER_SHORTSWORD = register("copper_shortsword", () -> new CopperShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_BROADSWORD = register("copper_broadsword", () -> new CopperBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_PICKAXE = register("copper_pickaxe", () -> new CopperPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_AXE = register("copper_axe", () -> new CopperAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_BOW = register("copper_bow", () -> new CopperBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_SHORTSWORD = register("iron_shortsword", () -> new IronShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_BROADSWORD = register("iron_broadsword", () -> new IronBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_PICKAXE = register("iron_pickaxe", () -> new IronPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_AXE = register("iron_axe", () -> new IronAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_BOW = register("iron_bow", () -> new IronBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_SHORTSWORD = register("gold_shortsword", () -> new GoldShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_BROADSWORD = register("gold_broadsword", () -> new GoldBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_PICKAXE = register("gold_pickaxe", () -> new GoldPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_AXE = register("gold_axe", () -> new GoldAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_BOW = register("gold_bow", () -> new GoldBow(new Item.Settings()).setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> WOODEN_ARROW = register("wooden_arrow", () -> new WoodenArrow(new Item.Settings()).setMaxStack(9999).setRarity(3));
    public static final RegistrySupplier<Item> FLAMING_ARROW = register("flaming_arrow", () -> new FlamingArrow(new Item.Settings()).setMaxStack(9999).setRarity(3));

    public static final RegistrySupplier<Item> LESSER_HEALING_POTION = register("lesser_healing_potion", () -> new LesserHealingPotion(new Item.Settings()).setMaxStack(9999).setRarity(2).isConsumable(true));

    public static final RegistrySupplier<Item> GREEN_SLIME_SPAWN_EGG = register("green_slime_spawn_egg", () -> new SpawnEggItem(EntitiesT.GREEN_SLIME.get(), 0x2ec221, 0x5fed53, new Item.Settings().arch$tab(TERRARIAMOD_GROUP)));
    public static final RegistrySupplier<Item> BLUE_SLIME_SPAWN_EGG = register("blue_slime_spawn_egg", () -> new SpawnEggItem(EntitiesT.BLUE_SLIME.get(), 0x2f7dc4, 0x53a3ed, new Item.Settings().arch$tab(TERRARIAMOD_GROUP)));


    public static final RegistrySupplier<Item> GRASS_BLOCK = register("grass_block", () -> new BlockItemT(BlocksT.GRASS_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CORRUPTED_GRASS_BLOCK = register("corrupted_grass", () -> new BlockItemT(BlocksT.CORRUPTED_GRASS_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CRIMSON_GRASS_BLOCK = register("crimson_grass", () -> new BlockItemT(BlocksT.CRIMSON_GRASS_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> JUNGLE_GRASS_BLOCK = register("jungle_grass", () -> new BlockItemT(BlocksT.JUNGLE_GRASS.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> ASH_GRASS_BLOCK = register("ash_grass_block", () -> new BlockItemT(BlocksT.ASH_GRASS_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> DIRT_BLOCK = register("dirt_block", () -> new BlockItemT(BlocksT.DIRT_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> STONE_BLOCK = register("stone_block", () -> new BlockItemT(BlocksT.STONE_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> BLUE_BRICK = register("blue_brick", () -> new BlockItemT(BlocksT.BLUE_BRICK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> GREEN_BRICK = register("green_brick", () -> new BlockItemT(BlocksT.GREEN_BRICK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> PURPLE_BRICK = register("purple_brick", () -> new BlockItemT(BlocksT.PURPLE_BRICK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> ASH_BLOCK = register("ash", () -> new BlockItemT(BlocksT.ASH.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> MUD_BLOCK = register("mud", () -> new BlockItemT(BlocksT.MUD.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SAND_BLOCK = register("sand", () -> new BlockItemT(BlocksT.SAND.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SANDSTONE_BLOCK = register("sandstone", () -> new BlockItemT(BlocksT.SANDSTONE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SNOW_BLOCK = register("snow", () -> new BlockItemT(BlocksT.SNOW.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> ICE_BLOCK = register("ice", () -> new BlockItemT(BlocksT.ICE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> MARBLE = register("marble", () -> new BlockItemT(BlocksT.MARBLE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> GRANITE = register("granite", () -> new BlockItemT(BlocksT.GRANITE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> WOOD = register("wood", () -> new BlockItemT(BlocksT.WOOD.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CORRUPTED_WOOD = register("corrupted_wood", () -> new BlockItemT(BlocksT.CORRUPTED_WOOD.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CRIMSON_WOOD = register("crimson_wood", () -> new BlockItemT(BlocksT.CRIMSON_WOOD.get(), new Item.Settings().maxCount(9999)).setRarity(2));

    public static final RegistrySupplier<Item> WOODEN_BEAM = register("wooden_beam", () -> new BlockItemT(BlocksT.WOODEN_BEAM.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> EBONSTONE = register("ebonstone", () -> new BlockItemT(BlocksT.EBONSTONE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CRIMSTONE = register("crimstone", () -> new BlockItemT(BlocksT.CRIMSTONE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> MUSHROOM_GRASS_BLOCK = register("mushroom_grass", () -> new BlockItemT(BlocksT.MUSHROOM_GRASS.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> HONEY_BLOCK = register("honey_block", () -> new BlockItemT(BlocksT.HONEY_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CRISPY_HONEY_BLOCK = register("crispy_honey_block", () -> new BlockItemT(BlocksT.CRISPY_HONEY_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SUNPLATE_BLOCK = register("sunplate_block", () -> new BlockItemT(BlocksT.SUNPLATE_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CLOUD = register("cloud", () -> new BlockItemT(BlocksT.CLOUD.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> RAIN_CLOUD = register("rain_cloud", () -> new BlockItemT(BlocksT.RAIN_CLOUD.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> COPPER_ORE = register("copper_ore", () -> new BlockItemT(BlocksT.COPPER_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> IRON_ORE = register("iron_ore", () -> new BlockItemT(BlocksT.IRON_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> GOLD_ORE = register("gold_ore", () -> new BlockItemT(BlocksT.GOLD_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> DEMONITE_ORE = register("demonite_ore", () -> new BlockItemT(BlocksT.DEMONITE_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> CRIMTANE_ORE = register("crimtane_ore", () -> new BlockItemT(BlocksT.CRIMTANE_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> HELLSTONE_ORE = register("hellstone_ore", () -> new BlockItemT(BlocksT.HELLSTONE_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> TIN_ORE = register("tin_ore", () -> new BlockItemT(BlocksT.TIN_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> LEAD_ORE = register("lead_ore", () -> new BlockItemT(BlocksT.LEAD_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> PLATINUM_ORE = register("platinum_ore", () -> new BlockItemT(BlocksT.PLATINUM_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SILVER_ORE = register("silver_ore", () -> new BlockItemT(BlocksT.SILVER_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> TUNGSTEN_ORE = register("tungsten_ore", () -> new BlockItemT(BlocksT.TUNGSTEN_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> METEORITE_ORE = register("meteorite_ore", () -> new BlockItemT(BlocksT.METEORITE_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> OBSIDIAN = register("obsidian", () -> new BlockItemT(BlocksT.OBSIDIAN.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> RUBY_ORE = register("ruby_ore", () -> new BlockItemT(BlocksT.RUBY_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> SAPPHIRE_ORE = register("sapphire_ore", () -> new BlockItemT(BlocksT.SAPPHIRE_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> EMERALD_ORE = register("emerald_ore", () -> new BlockItemT(BlocksT.EMERALD_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> DIAMOND_ORE = register("diamond_ore", () -> new BlockItemT(BlocksT.DIAMOND_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> TOPAZ_ORE = register("topaz_ore", () -> new BlockItemT(BlocksT.TOPAZ_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> AMETHYST_ORE = register("amethyst_ore", () -> new BlockItemT(BlocksT.AMETHYST_ORE.get(), new Item.Settings().maxCount(9999)).setRarity(2));

    public static final RegistrySupplier<Item> EMPTY_BOTTLE = register("empty_bottle", () -> new BlockItemT(BlocksT.EMPTY_BOTTLE.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> VINE = register("vine", () -> new BlockItemT(BlocksT.VINE.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> MUSHROOM = register("mushroom", () -> new BlockItemT(BlocksT.MUSHROOM.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> VILE_MUSHROOM = register("vile_mushroom", () -> new BlockItemT(BlocksT.VILE_MUSHROOM.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> VICIOUS_MUSHROOM = register("vicious_mushroom", () -> new BlockItemT(BlocksT.VICIOUS_MUSHROOM.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> GLOWING_MUSHROOM = register("glowing_mushroom", () -> new BlockItemT(BlocksT.GLOWING_MUSHROOM.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> JUNGLE_SPORES = register("jungle_spores", () -> new BlockItemT(BlocksT.JUNGLE_SPORES.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> BLINKROOT = register("blinkroot", () -> new BlockItemT(BlocksT.BLINKROOT.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> DAYBLOOM = register("daybloom", () -> new BlockItemT(BlocksT.DAYBLOOM.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> DEATHWEED = register("deathweed", () -> new BlockItemT(BlocksT.DEATHWEED.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> FIREBLOSSOM = register("fireblossom", () -> new BlockItemT(BlocksT.FIREBLOSSOM.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> MOONGLOW = register("moonglow", () -> new BlockItemT(BlocksT.MOONGLOW.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> SHIVERTHORN = register("shiverthorn", () -> new BlockItemT(BlocksT.SHIVERTHORN.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> WATERLEAF = register("waterleaf", () -> new BlockItemT(BlocksT.WATERLEAF.get(), new Item.Settings().maxCount(9999)));

    public static final RegistrySupplier<Item> WOOD_CHEST = register("wood_chest", () -> new BlockItemT(BlocksT.WOOD_CHEST.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> GOLD_CHEST = register("gold_chest", () -> new BlockItemT(BlocksT.GOLD_CHEST.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> IVY_CHEST = register("ivy_chest", () -> new BlockItemT(BlocksT.IVY_CHEST.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> FROZEN_CHEST = register("frozen_chest", () -> new BlockItemT(BlocksT.FROZEN_CHEST.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> SANDSTONE_CHEST = register("sandstone_chest", () -> new BlockItemT(BlocksT.SANDSTONE_CHEST.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> SKYWARE_CHEST = register("skyware_chest", () -> new BlockItemT(BlocksT.SKYWARE_CHEST.get(), new Item.Settings().maxCount(9999)));
    public static final RegistrySupplier<Item> WATER_CHEST = register("water_chest", () -> new BlockItemT(BlocksT.WATER_CHEST.get(), new Item.Settings().maxCount(9999)));

    public static void init() {
        ITEMS.register();
        ITEM_GROUPS.register();
    }

    static RegistrySupplier<ItemGroup> registerTab(String name, Supplier<ItemGroup> group) {
        return ITEM_GROUPS.register(new Identifier(TerrariaMod.MOD_ID, name), group);
    }

    static RegistrySupplier<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(new Identifier(TerrariaMod.MOD_ID, name), item);
    }
}
