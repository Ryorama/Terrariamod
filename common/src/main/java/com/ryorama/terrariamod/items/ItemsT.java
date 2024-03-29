package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.items.impl.BlockItemT;
import com.ryorama.terrariamod.items.impl.ItemT;
import com.ryorama.terrariamod.items.terraria.armor.*;
import com.ryorama.terrariamod.items.terraria.axes.*;
import com.ryorama.terrariamod.items.terraria.bows.*;
import com.ryorama.terrariamod.items.terraria.broadswords.*;
import com.ryorama.terrariamod.items.terraria.consumable.ammo.arrows.FlamingArrow;
import com.ryorama.terrariamod.items.terraria.consumable.ammo.arrows.WoodenArrow;
import com.ryorama.terrariamod.items.terraria.consumable.misc.LifeCrystal;
import com.ryorama.terrariamod.items.terraria.consumable.potions.HealingPotion;
import com.ryorama.terrariamod.items.terraria.consumable.potions.LesserHealingPotion;
import com.ryorama.terrariamod.items.terraria.picks.*;
import com.ryorama.terrariamod.items.terraria.shortswords.*;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemsT {
    public static List<Item> fixedRenders = new ArrayList<>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ITEM);
    public static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ITEM_GROUP);

    public static final RegistrySupplier<ItemGroup> TERRARIAMOD_GROUP = registerTab("terrariamod_tab", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup.terrariamod.terrariamod_tab"),
            () -> ItemsT.GRASS_BLOCK.get().getDefaultStack()
    ));


    public static final CopperArmorMaterial COPPER_MATERIAL = new CopperArmorMaterial();
    public static final IronTArmorMaterial IRON_MATERIAL = new IronTArmorMaterial();
    public static final GoldTArmorMaterial GOLD_MATERIAL = new GoldTArmorMaterial();
    public static final NinjaArmorMaterial NINJA_MATERIAL = new NinjaArmorMaterial();
    public static final GoldCrownMaterial GOLD_CROWN_MATERIAL = new GoldCrownMaterial();
    public static final PlatinumCrownMaterial PLATINUM_CROWN_MATERIAL = new PlatinumCrownMaterial();

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
    public static final RegistrySupplier<Item> SUNGLASSES = register("sunglasses", () -> new ArmorItem(new SunglassesMaterial(), ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> ACORN = register("acorn", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> GEL = register("gel", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> LENS = register("lens", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> BLACK_LENS = register("black_lens", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> FALLEN_STAR = register("fallen_star", () -> new ItemT(new Item.Settings().maxCount(9999)).setRarity(2));
    public static final RegistrySupplier<Item> LIFE_CRYSTAL = register("life_crystal", () -> new LifeCrystal(new Item.Settings().maxCount(9999)).setRarity(4));
    public static final RegistrySupplier<Item> GOLD_CROWN = register("gold_crown", () -> new ArmorItem(GOLD_CROWN_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
    public static final RegistrySupplier<Item> PLATINUM_CROWN = register("platinum_crown", () -> new ArmorItem(PLATINUM_CROWN_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().arch$tab(TERRARIAMOD_GROUP).maxCount(1)));
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
    public static final RegistrySupplier<Item> TIN_SHORTSWORD = register("tin_shortsword", () -> new TinShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_SHORTSWORD = register("copper_shortsword", () -> new CopperShortsword().setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> TIN_BROADSWORD = register("tin_broadsword", () -> new TinBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_BROADSWORD = register("copper_broadsword", () -> new CopperBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_PICKAXE = register("copper_pickaxe", () -> new CopperPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TIN_PICKAXE = register("tin_pickaxe", () -> new TinPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_AXE = register("copper_axe", () -> new CopperAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TIN_AXE = register("tin_axe", () -> new TinAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> COPPER_BOW = register("copper_bow", () -> new CopperBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TIN_BOW = register("tin_bow", () -> new TinBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_SHORTSWORD = register("iron_shortsword", () -> new IronShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> LEAD_SHORTSWORD = register("lead_shortsword", () -> new LeadShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_BROADSWORD = register("iron_broadsword", () -> new IronBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> LEAD_BROADSWORD = register("lead_broadsword", () -> new LeadBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_PICKAXE = register("iron_pickaxe", () -> new IronPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> LEAD_PICKAXE = register("lead_pickaxe", () -> new LeadPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_AXE = register("iron_axe", () -> new IronAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> LEAD_AXE = register("lead_axe", () -> new LeadAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> IRON_BOW = register("iron_bow", () -> new IronBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> LEAD_BOW = register("lead_bow", () -> new IronBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_SHORTSWORD = register("gold_shortsword", () -> new GoldShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> PLATINUM_SHORTSWORD = register("platinum_shortsword", () -> new PlatinumShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_BROADSWORD = register("gold_broadsword", () -> new GoldBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> PLATINUM_BROADSWORD = register("platinum_broadsword", () -> new PlatinumBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_PICKAXE = register("gold_pickaxe", () -> new GoldPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> PLATINUM_PICKAXE = register("platinum_pickaxe", () -> new PlatinumPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_AXE = register("gold_axe", () -> new GoldAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> PLATINUM_AXE = register("platinum_axe", () -> new PlatinumAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> GOLD_BOW = register("gold_bow", () -> new GoldBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> PLATINUM_BOW = register("platinum_bow", () -> new PlatinumBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> WOODEN_ARROW = register("wooden_arrow", () -> new WoodenArrow(new Item.Settings()).setMaxStack(9999).setRarity(3));
    public static final RegistrySupplier<Item> FLAMING_ARROW = register("flaming_arrow", () -> new FlamingArrow(new Item.Settings()).setMaxStack(9999).setRarity(3));


    public static final RegistrySupplier<Item> SILVER_PICKAXE = register("silver_pickaxe", () -> new SilverPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> SILVER_AXE = register("silver_axe", () -> new SilverAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> SILVER_BROADSWORD = register("silver_broadsword", () -> new SilverBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> SILVER_SHORTSWORD = register("silver_shortsword", () -> new SilverShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> SILVER_BOW = register("silver_bow", () -> new SilverBow(new Item.Settings()).setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> TUNGSTEN_PICKAXE = register("tungsten_pickaxe", () -> new TungstenPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TUNGSTEN_AXE = register("tungsten_axe", () -> new TungstenAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TUNGSTEN_BROADSWORD = register("tungsten_broadsword", () -> new TungstenBroadsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TUNGSTEN_SHORTSWORD = register("tungsten_shortsword", () -> new TungstenShortsword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> TUNGSTEN_BOW = register("tungsten_bow", () -> new TungstenBow(new Item.Settings()).setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> DEMONITE_PICKAXE = register("demonite_pickaxe", () -> new DemonitePickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> DEMONITE_AXE = register("demonite_axe", () -> new SilverAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> LIGHTS_BANE = register("lights_bane", () -> new LightsBane().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> DEMONITE_BOW = register("demonite_bow", () -> new DemoniteBow(new Item.Settings()).setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> CRIMTANE_PICKAXE = register("crimtane_pickaxe", () -> new DeathbringerPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> CRIMTANE_AXE = register("crimtane_axe", () -> new CrimtaneAxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> BLOOD_BUTCHER = register("blood_butcher", () -> new BloodButcher().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> CRIMTANE_BOW = register("crimtane_bow", () -> new CrimtaneBow(new Item.Settings()).setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> WOODEN_BOW = register("wooden_bow", () -> new WoodenBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> EBONWOOD_BOW = register("ebonwood_bow", () -> new EbonwoodBow(new Item.Settings()).setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> SHADEWOOD_BOW = register("shadewood_bow", () -> new ShadewoodBow(new Item.Settings()).setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> CACTUS_PICKAXE = register("cactus_pickaxe", () -> new CactusPickaxe().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> CACTUS_SWORD = register("cactus_sword", () -> new CactusSword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> WOODEN_SWORD = register("wooden_sword", () -> new WoodenSword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> EBONWOOD_SWORD = register("ebonwood_sword", () -> new EbonwoodSword().setMaxStack(1).setRarity(3));
    public static final RegistrySupplier<Item> SHADEWOOD_SWORD = register("shadewood_sword", () -> new ShadewoodSword().setMaxStack(1).setRarity(3));

    public static final RegistrySupplier<Item> LESSER_HEALING_POTION = register("lesser_healing_potion", () -> new LesserHealingPotion(new Item.Settings()).setRarity(2).isConsumable(true));
    public static final RegistrySupplier<Item> HEALING_POTION = register("healing_potion", () -> new HealingPotion(new Item.Settings()).setRarity(2).isConsumable(true));

    public static final RegistrySupplier<Item> GREEN_SLIME_SPAWN_EGG = register("green_slime_spawn_egg", () -> new SpawnEggItem(EntitiesT.GREEN_SLIME.get(), 0x2ec221, 0x5fed53, new Item.Settings().arch$tab(TERRARIAMOD_GROUP)));
    public static final RegistrySupplier<Item> BLUE_SLIME_SPAWN_EGG = register("blue_slime_spawn_egg", () -> new SpawnEggItem(EntitiesT.BLUE_SLIME.get(), 0x2f7dc4, 0x53a3ed, new Item.Settings().arch$tab(TERRARIAMOD_GROUP)));
    public static final RegistrySupplier<Item> DEMON_EYE_SPAWN_EGG = register("demon_eye_spawn_egg", () -> new SpawnEggItem(EntitiesT.DEMON_EYE.get(), 0xffffff, 0xff0000, new Item.Settings().arch$tab(TERRARIAMOD_GROUP)));


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
    public static final RegistrySupplier<Item> CACTUS = register("cactus", () -> new BlockItemT(BlocksT.CACTUS_BLOCK.get(), new Item.Settings().maxCount(9999)).setRarity(2));

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
    public static final RegistrySupplier<Item> HELLSTONE_ORE = register("hellstone_ore", () -> new BlockItemT(BlocksT.HELLSTONE_ORE.get(), new Item.Settings().fireproof().maxCount(9999)).setRarity(2));
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

        fixedRenders.add(LIGHTS_BANE.get());
        fixedRenders.add(CRIMTANE_AXE.get());
    }

    static RegistrySupplier<ItemGroup> registerTab(String name, Supplier<ItemGroup> group) {
        return ITEM_GROUPS.register(new Identifier(TerrariaMod.MOD_ID, name), group);
    }

    static RegistrySupplier<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(new Identifier(TerrariaMod.MOD_ID, name), item);
    }
}
