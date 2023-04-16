package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.impl.BlockT;
import com.ryorama.terrariamod.blocks.impl.LightBlockT;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class BlocksT {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.BLOCK);

    //public static Spike SPIKE = (Spike) new Spike(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true);
    //public static WaterCandle WATER_CANDLE = (WaterCandle) new WaterCandle(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true);

    //public static PlantT LIFE_CRYSTAL_BLOCK = (PlantT) new PlantT(AbstractBlock.Settings.copy(Blocks.STONE).luminance(10), 15, 15).setPick(true);
    //public static PlantT VINE = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.STONE), 0.1f, 0.1f).setPick(true).setAxe(true);
    //public static Pot FOREST_POT = new Pot(AbstractBlock.Settings.of(Material.GLASS).sounds(BlockSoundGroup.GLASS), 0.1f, 0.1f);

    //public static PlantT MUSHROOM = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static LightPlantT GLOWING_MUSHROOM = (LightPlantT) new LightPlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f, 15).setPick(true);
    //public static LightPlantT JUNGLE_SPORES = (LightPlantT) new LightPlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f, 10).setPick(true);

    //public static PlantT BLINKROOT = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static PlantT DAYBLOOM = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static PlantT DEATHWEED = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static PlantT FIREBLOSSOM = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static PlantT MOONGLOW = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static PlantT SHIVERTHORN = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
    //public static PlantT WATERLEAF = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);

    //public static PlantT EMPTY_BOTTLE = (PlantT) new PlantT(AbstractBlock.Settings.of(Material.GLASS), 0.5f, 0.5f).setPick(true);

    //public static TreeSegment FOREST_STUMP = (TreeSegment) new TreeSegment(AbstractBlock.Settings.of(Material.WOOD), 15, 15).setAxe(true);
    //public static TreeSegment FOREST_STEM = (TreeSegment) new TreeSegment(AbstractBlock.Settings.of(Material.WOOD), 15, 15).setAxe(true);
    //public static TreeSegment FOREST_TOP = (TreeSegment) new TreeSegment(AbstractBlock.Settings.of(Material.WOOD), 15, 15).setAxe(true);

    //public static TreeSegment GIANT_GLOWING_MUSHROOM_STEM = (TreeSegment) new TreeSegment(AbstractBlock.Settings.of(Material.WOOD), 15, 15).setAxe(true);
    //public static TreeSegment GIANT_GLOWING_MUSHROOM_TOP = (TreeSegment) new TreeSegment(AbstractBlock.Settings.of(Material.WOOD).luminance(20), 15, 15).setAxe(true);

    //public static GravestoneT TOMBSTONE = (GravestoneT) new GravestoneT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true);
    //public static GravestoneT GOLD_TOMBSTONE = (GravestoneT) new GravestoneT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true);

    //public static Block WOOD_CHEST = new WoodChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.WOOD_CHEST);
    //public static Block GOLD_CHEST = new GoldChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.GOLD_CHEST);
    //public static Block WATER_CHEST = new WaterChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.WATER_CHEST);
    //public static Block IVY_CHEST = new IvyChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.IVY_CHEST);
    //public static Block SKYWARE_CHEST = new SkywareChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.SKYWARE_CHEST);
    //public static Block SANDSTONE_CHEST = new SandstoneChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.SANDSTONE_CHEST);
    //public static Block FROZEN_CHEST = new FrozenChest(AbstractBlock.Settings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.FROZEN_CHEST);

    //public static Larva LARVA = (Larva) new Larva(AbstractBlock.Settings.of(Material.SPONGE), 15, 15).setPick(true).setAxe(true);

    public static final RegistrySupplier<BlockT> GRASS_BLOCK = register("grass_block", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> CORRUPTED_GRASS_BLOCK = register("corrupted_grass", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> CRIMSON_GRASS_BLOCK = register("crimson_grass", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> JUNGLE_GRASS = register("jungle_grass", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> DIRT_BLOCK = register("dirt_block", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> STONE_BLOCK = register("stone_block", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> BLUE_BRICK = register("blue_brick", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> GREEN_BRICK = register("green_brick", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> PURPLE_BRICK = register("purple_brick", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> EBONSTONE = register("ebonstone", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 30).setPick(true));
    public static final RegistrySupplier<BlockT> CRIMSTONE = register("crimstone", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 30).setPick(true));
    public static final RegistrySupplier<BlockT> MUD = register("mud", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> SAND = register("sand", () -> new BlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> SANDSTONE = register("sandstone", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> SNOW = register("snow", () -> new BlockT(AbstractBlock.Settings.of(Material.SNOW_BLOCK), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> ICE = register("ice", () -> new BlockT(AbstractBlock.Settings.of(Material.ICE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> ASH = register("ash", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> MARBLE = register("marble", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> GRANITE = register("granite", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> MUSHROOM_GRASS = register("mushroom_grass", () -> new LightBlockT(AbstractBlock.Settings.of(Material.SOIL), 15, 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> WOOD = register("wood", () -> new BlockT(AbstractBlock.Settings.of(Material.WOOD), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> WOODEN_BEAM = register("wooden_beam", () -> new BlockT(AbstractBlock.Settings.of(Material.WOOD), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> SUNPLATE_BLOCK = register("sunplate_block", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> CLOUD = register("cloud", () -> new BlockT(AbstractBlock.Settings.of(Material.WOOL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> RAIN_CLOUD = register("rain_cloud", () -> new BlockT(AbstractBlock.Settings.of(Material.WOOL), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> COPPER_ORE = register("copper_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> IRON_ORE = register("iron_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> GOLD_ORE = register("gold_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> DEMONITE_ORE = register("demonite_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> CRIMTANE_ORE = register("crimtane_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> HELLSTONE_ORE = register("hellstone_ore", () -> new LightBlockT(AbstractBlock.Settings.of(Material.STONE), 50, 50, 10).setPick(true));
    public static final RegistrySupplier<BlockT> RUBY_ORE = register("ruby_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> SAPPHIRE_ORE = register("sapphire_ore", () -> new BlockT(AbstractBlock.Settings.of(Material.STONE), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> HONEY_BLOCK = register("honey_block", () -> new BlockT(AbstractBlock.Settings.of(Material.ORGANIC_PRODUCT), 15, 15).setPick(true));
    public static final RegistrySupplier<BlockT> CRISPY_HONEY_BLOCK = register("crispy_honey_block", () -> new BlockT(AbstractBlock.Settings.of(Material.ORGANIC_PRODUCT), 15, 15).setPick(true));

    public static void init() {
        BLOCKS.register();
    }

    static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
        Identifier id = new Identifier(TerrariaMod.MOD_ID, name);
        return BLOCKS.register(id, block);
    }
}
