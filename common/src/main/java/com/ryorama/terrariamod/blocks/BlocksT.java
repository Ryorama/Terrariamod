package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.impl.*;
import com.ryorama.terrariamod.blocks.terraria.chests.*;
import com.ryorama.terrariamod.blocks.terraria.world.*;
import com.ryorama.terrariamod.blocks.terraria.world.ores.HellstoneOre;
import com.ryorama.terrariamod.client.TAudio;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BlocksT {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(TerrariaMod.MOD_ID, Registries.BLOCK);

    public static float mul = 1.5f;
    public static float GROUND_HARDNESS = 30.0f * mul, STONE_HARDNESS = 45.0f * mul, ORE_HARDNESS = 50.0f * mul, DUNGEON_HARDNESS = 60.0f * mul;

    //public static Spike SPIKE = (Spike) new Spike(BlockBehaviour.Properties.of), 15, 15).setPick(true);
    //public static WaterCandle WATER_CANDLE = (WaterCandle) new WaterCandle(BlockBehaviour.Properties.of), 15, 15).setPick(true);
    //public static Pot FOREST_POT = new Pot(BlockBehaviour.Properties.of().sound(BlockSoundGroup.GLASS), 0.1f, 0.1f);

    public static final RegistrySupplier<Block> FOREST_STUMP = register("forest_stump", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> FOREST_STEM = register("forest_stem", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> FOREST_TOP = register("forest_top", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));

    public static final RegistrySupplier<Block> CORRUPTED_STUMP = register("corrupted_stump", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> CORRUPTED_STEM = register("corrupted_stem", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> CORRUPTED_TOP = register("corrupted_top", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));

    public static final RegistrySupplier<Block> CRIMSON_STUMP = register("crimson_stump", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> CRIMSON_STEM = register("crimson_stem", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> CRIMSON_TOP = register("crimson_top", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 15).setAxe(true));
    public static final RegistrySupplier<Block> CACTUS = register("cactus", () -> new TreeSegment(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN), ORE_HARDNESS * 3, 15).setAxe(true));

    public static RegistrySupplier<Block> GIANT_GLOWING_MUSHROOM_STEM = register("giant_glowing_mushroom_stem", () -> new TreeSegment(BlockBehaviour.Properties.of(), ORE_HARDNESS * 3, 15).setAxe(true));
    public static RegistrySupplier<Block> GIANT_GLOWING_MUSHROOM_TOP = register("giant_glowing_mushroom_top", () -> new TreeSegment(BlockBehaviour.Properties.of().lightLevel(new ToIntFunction<BlockState>() {
        @Override
        public int applyAsInt(BlockState value) {
            return 20;
        }
    }), ORE_HARDNESS * 3, 15).setAxe(true));

    //public static GravestoneT TOMBSTONE = (GravestoneT) new GravestoneT(BlockBehaviour.Properties.of), 15, 15).setPick(true);
    //public static GravestoneT GOLD_TOMBSTONE = (GravestoneT) new GravestoneT(BlockBehaviour.Properties.of), 15, 15).setPick(true);

    public static final RegistrySupplier<Block> WOOD_CHEST = register("wood_chest", () -> new WoodChest(BlockBehaviour.Properties.of().strength(GROUND_HARDNESS, 6)));
    public static final RegistrySupplier<Block> GOLD_CHEST = register("gold_chest", () -> new GoldChest(BlockBehaviour.Properties.of().strength(3, 6)));
    public static final RegistrySupplier<Block> WATER_CHEST = register("water_chest", () -> new WaterChest(BlockBehaviour.Properties.of().strength(3, 6)));
    public static final RegistrySupplier<Block> IVY_CHEST = register("ivy_chest",() -> new IvyChest(BlockBehaviour.Properties.of().strength(3, 6)));
    public static final RegistrySupplier<Block> SKYWARE_CHEST = register("skyware_chest", () -> new SkywareChest(BlockBehaviour.Properties.of().strength(3, 6)));
    public static final RegistrySupplier<Block> SANDSTONE_CHEST = register("sandstone_chest", () -> new SandstoneChest(BlockBehaviour.Properties.of().strength(3, 6)));
    public static final RegistrySupplier<Block> FROZEN_CHEST = register("frozen_chest", () -> new FrozenChest(BlockBehaviour.Properties.of().strength(3, 6)));

    //public static Larva LARVA = (Larva) new Larva(BlockBehaviour.Properties.of(Material.SPONGE), 15, 15).setPick(true).setAxe(true);

    public static final RegistrySupplier<Block> GRASS_BLOCK = register("grass_block", () -> new GrassBlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN).sound(TAudio.DIRT)).setPick(true));
    public static final RegistrySupplier<Block> CORRUPTED_GRASS_BLOCK = register("corrupted_grass", () -> new CorruptionGrass(BlockBehaviour.Properties.of().mapColor(DyeColor.MAGENTA).sound(TAudio.DIRT)).setPick(true));
    public static final RegistrySupplier<Block> CRIMSON_GRASS_BLOCK = register("crimson_grass", () -> new CrimsonGrass(BlockBehaviour.Properties.of().mapColor(DyeColor.RED).sound(TAudio.DIRT)).setPick(true));
    public static final RegistrySupplier<Block> MUSHROOM_GRASS = register("mushroom_grass", () -> new MushroomGrass(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.DIRT)).setPick(true));
    public static final RegistrySupplier<Block> JUNGLE_GRASS = register("jungle_grass", () -> new JungleGrass(BlockBehaviour.Properties.of().mapColor(DyeColor.LIME).sound(TAudio.DIRT)).setPick(true));
    public static final RegistrySupplier<Block> DIRT_BLOCK = register("dirt_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> STONE_BLOCK = register("stone_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.GRAY).sound(TAudio.STONE), STONE_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> BLUE_BRICK = register("blue_brick", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.CYAN).sound(TAudio.STONE), ORE_HARDNESS, 100).setPick(true));
    public static final RegistrySupplier<Block> GREEN_BRICK = register("green_brick", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN).sound(TAudio.STONE), ORE_HARDNESS, 100).setPick(true));
    public static final RegistrySupplier<Block> PURPLE_BRICK = register("purple_brick", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.PINK).sound(TAudio.STONE), ORE_HARDNESS, 100).setPick(true));
    public static final RegistrySupplier<Block> EBONSTONE = register("ebonstone", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.PURPLE).sound(TAudio.STONE), STONE_HARDNESS, 65).setPick(true));
    public static final RegistrySupplier<Block> CRIMSTONE = register("crimstone", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.RED).sound(TAudio.STONE), STONE_HARDNESS, 65).setPick(true));
    public static final RegistrySupplier<Block> MUD = register("mud", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> SAND = register("sand", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> SANDSTONE = register("sandstone", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.DIRT), STONE_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> SNOW = register("snow", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).sound(TAudio.SNOW), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> ICE = register("ice", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.LIGHT_BLUE).sound(TAudio.ICE), STONE_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> ASH = register("ash", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.GRAY).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> MARBLE = register("marble", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).sound(TAudio.STONE), STONE_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> GRANITE = register("granite", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), STONE_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> WOOD = register("wood", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.DIRT), ORE_HARDNESS * 3, 10).setPick(true));
    public static final RegistrySupplier<Block> CORRUPTED_WOOD = register("corrupted_wood", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.DIRT), ORE_HARDNESS * 3, 10).setPick(true));
    public static final RegistrySupplier<Block> CRIMSON_WOOD = register("crimson_wood", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.DIRT), ORE_HARDNESS * 3, 10).setPick(true));
    public static final RegistrySupplier<Block> CACTUS_BLOCK = register("cactus_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN).sound(TAudio.DIRT), ORE_HARDNESS * 3, 10).setPick(true));

    public static final RegistrySupplier<Block> WOODEN_BEAM = register("wooden_beam", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), ORE_HARDNESS * 3, 10).setPick(true));
    public static final RegistrySupplier<Block> SUNPLATE_BLOCK = register("sunplate_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.STONE), STONE_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> CLOUD = register("cloud", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> RAIN_CLOUD = register("rain_cloud", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.GRAY).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));
    public static final RegistrySupplier<Block> COPPER_ORE = register("copper_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> TIN_ORE = register("tin_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> IRON_ORE = register("iron_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> LEAD_ORE = register("lead_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));

    public static final RegistrySupplier<Block> GOLD_ORE = register("gold_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> PLATINUM_ORE = register("platinum_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> SILVER_ORE = register("silver_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> TUNGSTEN_ORE = register("tungsten_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> METEORITE_ORE = register("meteorite_ore", () -> new HotBlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.STONE), ORE_HARDNESS, 55).setPick(true));
    public static final RegistrySupplier<Block> DEMONITE_ORE = register("demonite_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.PURPLE).sound(TAudio.STONE), ORE_HARDNESS, 55).setPick(true));
    public static final RegistrySupplier<Block> CRIMTANE_ORE = register("crimtane_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.RED).sound(TAudio.STONE), ORE_HARDNESS, 55).setPick(true));
    public static final RegistrySupplier<Block> HELLSTONE_ORE = register("hellstone_ore", () -> new HellstoneOre(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE).sound(TAudio.STONE), ORE_HARDNESS, 60).setPick(true));
    public static final RegistrySupplier<Block> RUBY_ORE = register("ruby_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.RED).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> DIAMOND_ORE = register("diamond_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> EMERALD_ORE = register("emerald_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> TOPAZ_ORE = register("topaz_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> AMETHYST_ORE = register("amethyst_ore", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), ORE_HARDNESS, 20).setPick(true));
    public static final RegistrySupplier<Block> OBSIDIAN = register("obsidian", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE).sound(TAudio.STONE), ORE_HARDNESS, 55).setPick(true));
    public static final RegistrySupplier<Block> HONEY_BLOCK = register("honey_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.DIRT), GROUND_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> CRISPY_HONEY_BLOCK = register("crispy_honey_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW).sound(TAudio.DIRT), GROUND_HARDNESS, 15).setPick(true));
    public static final RegistrySupplier<Block> ASH_GRASS_BLOCK = register("ash_grass_block", () -> new BlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE).sound(TAudio.DIRT), GROUND_HARDNESS, 10).setPick(true));

    public static final RegistrySupplier<Block> EMPTY_BOTTLE = register("empty_bottle", () -> new CMBlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE), 0.1f, 1).setPick(true));
    public static final RegistrySupplier<Block> LIFE_CRYSTAL_BLOCK = register("life_crystal_block", () -> new CMBlockT(BlockBehaviour.Properties.of().mapColor(DyeColor.PINK).sound(TAudio.LIFE_CRYSTAL_GRP), STONE_HARDNESS, 20, 10).setPick(true));
    public static final RegistrySupplier<Block> VINE = register("vine", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> MUSHROOM = register("mushroom", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> VILE_MUSHROOM = register("vile_mushroom", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> VICIOUS_MUSHROOM = register("vicious_mushroom", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> SAPLING = register("sapling", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN), GROUND_HARDNESS, 10).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> CORRUPTED_SAPLING = register("corrupted_sapling", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN), GROUND_HARDNESS, 10).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> CRIMSON_SAPLING = register("crimson_sapling", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN), GROUND_HARDNESS, 10).setPick(true).setAxe(true));

    public static final RegistrySupplier<Block> GRASS = register("grass", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> DEAD_GRASS = register("dead_grass", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.PURPLE), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> BLOODY_GRASS = register("bloody_grass", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.RED), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> GLOWING_MUSHROOM = register("glowing_mushroom", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE), 0.1f, 0.1f, 15).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> JUNGLE_SPORES = register("jungle_spores", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.LIME), 0.1f, 0.1f, 10).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> BLINKROOT = register("blinkroot", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN), 0.1f, 0.1f, 3).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> DAYBLOOM = register("daybloom", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> DEATHWEED = register("deathweed", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.MAGENTA), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> FIREBLOSSOM = register("fireblossom", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE), 0.1f, 0.1f).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> MOONGLOW = register("moonglow", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE), 0.1f, 0.1f, 5).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> SHIVERTHORN = register("shiverthorn", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.LIGHT_BLUE), 0.1f, 0.1f, 5).setPick(true).setAxe(true));
    public static final RegistrySupplier<Block> WATERLEAF = register("waterleaf", () -> new PlantT(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE), 0.1f, 0.1f).setPick(true).setAxe(true));

    public static void init() {
        BLOCKS.register();
    }

    static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
        ResourceLocation id = new ResourceLocation(TerrariaMod.MOD_ID, name);
        return BLOCKS.register(id, block);
    }
}
