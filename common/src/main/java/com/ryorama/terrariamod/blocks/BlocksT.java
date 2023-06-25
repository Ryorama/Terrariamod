package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.impl.BlockT;
import com.ryorama.terrariamod.blocks.impl.CMBlockT;
import com.ryorama.terrariamod.blocks.impl.PlantT;
import com.ryorama.terrariamod.blocks.impl.TreeSegment;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BlocksT {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.BLOCK);

    //public static Spike SPIKE = (Spike) new Spike(AbstractBlock.Settings.of), 15, 15).setPick(true);
    //public static WaterCandle WATER_CANDLE = (WaterCandle) new WaterCandle(AbstractBlock.Settings.of), 15, 15).setPick(true);
    //public static Pot FOREST_POT = new Pot(AbstractBlock.Settings.of().sounds(BlockSoundGroup.GLASS), 0.1f, 0.1f);

    public static final RegistrySupplier<Block> FOREST_STUMP = register("forest_stump", () -> new TreeSegment(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setAxe(true));
    public static final RegistrySupplier<Block> FOREST_STEM = register("forest_stem", () -> new TreeSegment(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setAxe(true));
    public static final RegistrySupplier<Block> FOREST_TOP = register("forest_top", () -> new TreeSegment(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setAxe(true));

    public static RegistrySupplier<Block> GIANT_GLOWING_MUSHROOM_STEM = register("giant_glowing_mushroom_stem", () -> new TreeSegment(AbstractBlock.Settings.create(), 15, 15).setAxe(true));
    public static RegistrySupplier<Block> GIANT_GLOWING_MUSHROOM_TOP = register("giant_glowing_mushroom_top", () -> new TreeSegment(AbstractBlock.Settings.create().luminance(new ToIntFunction<BlockState>() {
        @Override
        public int applyAsInt(BlockState value) {
            return 20;
        }
    }), 15, 15).setAxe(true));

    //public static GravestoneT TOMBSTONE = (GravestoneT) new GravestoneT(AbstractBlock.Settings.of), 15, 15).setPick(true);
    //public static GravestoneT GOLD_TOMBSTONE = (GravestoneT) new GravestoneT(AbstractBlock.Settings.of), 15, 15).setPick(true);

    //public static final RegistrySupplier<Block> WOOD_CHEST = register("wood_chest", () -> new WoodChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.WOOD_CHEST));
    //public static final RegistrySupplier<Block> GOLD_CHEST = register("gold_chest", () -> new GoldChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.GOLD_CHEST));
    //public static final RegistrySupplier<Block> WATER_CHEST = register("water_chest", () -> new WaterChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.WATER_CHEST));
    //public static final RegistrySupplier<Block> IVY_CHEST = register("ivy_chest",() -> new IvyChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.IVY_CHEST));
    //public static final RegistrySupplier<Block> SKYWARE_CHEST = register("skyware_chest", () -> new SkywareChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.SKYWARE_CHEST);
    //public static final RegistrySupplier<Block> SANDSTONE_CHEST = register("sandstone_chest", () -> new SandstoneChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.SANDSTONE_CHEST));
    //public static final RegistrySupplier<Block> FROZEN_CHEST = register("frozen_chest", () -> new FrozenChest(AbstractBlock.Settings.of().strength(3, 6), EntitiesT.FROZEN_CHEST));

    //public static Larva LARVA = (Larva) new Larva(AbstractBlock.Settings.of(Material.SPONGE), 15, 15).setPick(true).setAxe(true);

    public static final RegistrySupplier<Block> GRASS_BLOCK = register("grass_block", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.GREEN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> CORRUPTED_GRASS_BLOCK = register("corrupted_grass", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.MAGENTA), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> CRIMSON_GRASS_BLOCK = register("crimson_grass", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.RED), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> JUNGLE_GRASS = register("jungle_grass", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.LIME), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> DIRT_BLOCK = register("dirt_block", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> STONE_BLOCK = register("stone_block", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.GRAY), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> BLUE_BRICK = register("blue_brick", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.CYAN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> GREEN_BRICK = register("green_brick", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.GREEN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> PURPLE_BRICK = register("purple_brick", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.PINK), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> EBONSTONE = register("ebonstone", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.PURPLE), 15, 30).setPick(true));
    public static final RegistrySupplier<Block> CRIMSTONE = register("crimstone", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.RED), 15, 30).setPick(true));
    public static final RegistrySupplier<Block> MUD = register("mud", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> SAND = register("sand", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> SANDSTONE = register("sandstone", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> SNOW = register("snow", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.WHITE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> ICE = register("ice", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.LIGHT_BLUE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> ASH = register("ash", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.GRAY), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> MARBLE = register("marble", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.WHITE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> GRANITE = register("granite", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> MUSHROOM_GRASS = register("mushroom_grass", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE), 15, 15, 15).setPick(true));
    public static final RegistrySupplier<Block> WOOD = register("wood", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> WOODEN_BEAM = register("wooden_beam", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> SUNPLATE_BLOCK = register("sunplate_block", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> CLOUD = register("cloud", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.WHITE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> RAIN_CLOUD = register("rain_cloud", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.GRAY), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> COPPER_ORE = register("copper_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> IRON_ORE = register("iron_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.WHITE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> GOLD_ORE = register("gold_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> DEMONITE_ORE = register("demonite_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.PURPLE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> CRIMTANE_ORE = register("crimtane_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.RED), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> HELLSTONE_ORE = register("hellstone_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.ORANGE), 50, 50, 10).setPick(true));
    public static final RegistrySupplier<Block> RUBY_ORE = register("ruby_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.RED), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> HONEY_BLOCK = register("honey_block", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> CRISPY_HONEY_BLOCK = register("crispy_honey_block", () -> new BlockT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 15, 15).setPick(true));

    public static final RegistrySupplier<Block> EMPTY_BOTTLE = register("empty_bottle", () -> new CMBlockT(AbstractBlock.Settings.create().mapColor(DyeColor.WHITE), 15, 15).setPick(true));
    public static final RegistrySupplier<Block> LIFE_CRYSTAL_BLOCK = register("life_crystal_block", () -> new CMBlockT(AbstractBlock.Settings.create().mapColor(DyeColor.PINK), 15, 15, 10).setPick(true));
    public static final RegistrySupplier<Block> VINE = register("vine", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.GREEN), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> MUSHROOM = register("mushroom", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.ORANGE), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> GRASS = register("grass", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.GREEN), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> GLOWING_MUSHROOM = register("glowing_mushroom", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE), 0.1f, 0.1f, 15).setPick(true));
    public static final RegistrySupplier<Block> JUNGLE_SPORES = register("jungle_spores", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.LIME), 0.1f, 0.1f, 10).setPick(true));
    public static final RegistrySupplier<Block> BLINKROOT = register("blinkroot", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.BROWN), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> DAYBLOOM = register("daybloom", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> DEATHWEED = register("deathweed", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.MAGENTA), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> FIREBLOSSOM = register("fireblossom", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.ORANGE), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> MOONGLOW = register("moonglow", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> SHIVERTHORN = register("shiverthorn", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.LIGHT_BLUE), 0.1f, 0.1f).setPick(true));
    public static final RegistrySupplier<Block> WATERLEAF = register("waterleaf", () -> new PlantT(AbstractBlock.Settings.create().mapColor(DyeColor.BLUE), 0.1f, 0.1f).setPick(true));

    public static void init() {
        BLOCKS.register();
    }

    static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
        Identifier id = new Identifier(TerrariaMod.MOD_ID, name);
        return BLOCKS.register(id, block);
    }
}
