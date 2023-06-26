package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.utils.StructurePlacerAPI;
import com.ryorama.terrariamod.utils.WorldDataT;
import com.ryorama.terrariamod.utils.math.noise.FastNoise;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.commons.lang3.mutable.Mutable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.BitSet;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    public DoublePerlinNoiseSampler terrainNoise;

    public FastNoise noise;
    public Random random;

    private boolean evil = false;
    private boolean corruption = true;
    private boolean right_jungle = true;

    private float GetTerrainNoise(int x, int y, int z) {
        return noise.GetSimplexFractal(x * 1.25f, y * 2.0f, z * 1.25f) * 5;
    }

    @Inject(at = @At("HEAD"), method = "generateFeatures", cancellable = true)
    public void generateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor, CallbackInfo info) {

        if (noise == null) {
            noise = new FastNoise((int)world.toServerWorld().getSeed());
            random = new CheckedRandom(world.toServerWorld().getSeed());
            corruption = random.nextInt(10) >= 4;
            right_jungle = random.nextBoolean();
        }

        int base_height = 75;
        float threshold = -0.8f;

        int stone_height = 20;
        int underworld_height = -190;
        int underworld_base = -256 + 25;

        int purity_radius = 350;

        int world_radius = 4800;

        int biome_width = 900;
        int biome_length = 1200;

        int snow_position = right_jungle ? -2400 : 2400;
        int evil_position = right_jungle ? -1200 : 1200;

        if (terrainNoise == null) {
            terrainNoise = DoublePerlinNoiseSampler.create(new CheckedRandom(world.getRandom().nextLong()), -8,
                    new double[]{1.0D});
        }

        ChunkPos chunkPos = chunk.getPos();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        if (TerrariaMod.CONFIG.customWorldGen) {
            for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); x++) {
                for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); z++) {
                    float flatness = noise.GetSimplex(x, z);
                    int height_offset = 0;

                    double world_distance = Math.sqrt(x * x + z * z);

                    boolean beach = world_distance >= 4800;
                    boolean jungle = world_distance > 3200 && (right_jungle ? (x > 0) : (x < 0)) && !beach;

                    boolean snow = ((x + snow_position) * (x + snow_position)) / (float)(biome_width * biome_width) + (z * z) / (float)(biome_length * biome_length) <= 1;
                    boolean desert = ((x - snow_position) * (x - snow_position)) / (float)(biome_width * biome_width) + (z * z) / (float)(biome_length * biome_length) <= 1;
                    boolean isEvil = ((x + evil_position) * (x + evil_position)) / (float)(biome_width * biome_width) + (z * z) / (float)(biome_length * biome_length) <= 1;
                    evil = isEvil;

                    if (world_distance >= 4800) {
                        height_offset = (int)(60.0f * (world_distance - 4800) / 300.0f);
                        if (height_offset < 0) height_offset = 0;
                        if (height_offset > 60) height_offset = 60;
                    }

                    if (flatness < 0.05f) flatness = 0.05f;

                    float sine = (float)Math.sin(Math.PI * (world_distance - 2200) / 2400);

                    flatness = MathHelper.lerp(sine > 0 ? sine : 0, flatness, 1);
                    for (int y = world.getBottomY(); y <= world.getTopY(); y++) {
                        pos.set(x, y, z);

                        if (world.isChunkLoaded(pos)) {
                            if (world.getBlockState(pos) != Blocks.WATER.getDefaultState() && world.getBlockState(pos) != Blocks.LAVA.getDefaultState()) {
                                pos.set(x, y, z);
                                BlockState state = Blocks.AIR.getDefaultState();;

                                float stone_density = (y - stone_height + height_offset) / 15.0f;

                                float density = (y - base_height + height_offset) / 15.0f;
                                float n = GetTerrainNoise(x, y, z);
                                float combined = n * flatness + density;

                                float cave_density = y / 184.0f;
                                if (cave_density > 0) cave_density = 0;
                                cave_density = -cave_density;

                                float underworld_density = (y - underworld_height) / 5.0f;

                                float underworld_base_density = (y - underworld_base) / 15.0f;

                                float combined_stone = n + stone_density;
                                float combined_cave = (n * n) * cave_density;
                                float jungle_cave = jungle ? (n * (cave_density + 0.1f)) : 0;

                                float combined_underworld = n + underworld_density;
                                float combined_underworld_base = n + underworld_base_density;

                                boolean pure = Math.sqrt(x * x + y * y + z * z) <= purity_radius;

                                if (combined < threshold) {
                                    if (jungle) {
                                        state = BlocksT.MUD.get().getDefaultState();
                                    } else {
                                        if (snow) {
                                            state = BlocksT.SNOW.get().getDefaultState();
                                        } else {
                                            if (beach || desert) {
                                                state = BlocksT.SAND.get().getDefaultState();
                                            } else {
                                                state = BlocksT.DIRT_BLOCK.get().getDefaultState();
                                            }
                                        }
                                    }
                                }

                                if (combined_stone < threshold) {
                                    if (jungle) {
                                        state = BlocksT.MUD.get().getDefaultState();
                                    } else {
                                        if (snow && !pure) {
                                            state = BlocksT.SNOW.get().getDefaultState();
                                        } else {
                                            if (desert) {
                                                state = BlocksT.SANDSTONE.get().getDefaultState();
                                            } else {
                                                state = BlocksT.STONE_BLOCK.get().getDefaultState();
                                            }
                                        }
                                    }

                                }

                                if (combined_cave >= 0.8f || jungle_cave <= -0.8f) {
                                    if (combined >= threshold) {
                                        state = Blocks.AIR.getDefaultState();
                                    } else {
                                        state = Blocks.CAVE_AIR.getDefaultState();
                                    }
                                }

                                if (combined_underworld < threshold) {
                                    state = Blocks.CAVE_AIR.getDefaultState();

                                }

                                if (y < underworld_base) {
                                    state = Blocks.LAVA.getDefaultState();
                                }

                                if (combined_underworld_base < threshold) {
                                    state = BlocksT.ASH.get().getDefaultState();
                                }

                                if (y <= -253) {
                                    state = Blocks.BEDROCK.getDefaultState();
                                }

                                if (state != null) {
                                    world.setBlockState(pos, state, 0);
                                }
                            }
                        }
                    }
                }
            }

            for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); x++) {
                for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); z++) {
                    for (int y = world.getBottomY(); y <= world.getTopY(); y++) {
                        if (y >= 252) continue;
                        int cave_biome = (int)(noise.GetCellular(x * 0.5f, y, z * 0.5f) * 10);

                        pos.set(x, y, z);
                        if (world.isChunkLoaded(pos)) {
                            pos.set(x, y + 1, z);
                            if (world.isChunkLoaded(pos)) {
                                if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                                    pos.set(x, y, z);
                                    if (world.getBlockState(pos) == BlocksT.DIRT_BLOCK.get().getDefaultState())
                                        world.setBlockState(pos, BlocksT.GRASS_BLOCK.get().getDefaultState(), 0);

                                    if (world.getBlockState(pos) == BlocksT.MUD.get().getDefaultState())
                                        world.setBlockState(pos, BlocksT.JUNGLE_GRASS.get().getDefaultState(), 0);
                                }
                                if (world.getBlockState(pos) == BlocksT.SAND.get().getDefaultState()) {
                                    pos.set(x, y, z);
                                    if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                                        world.setBlockState(pos, BlocksT.SAND.get().getDefaultState(), 0);
                                    }
                                    //world.setBlockState(pos, pure ? Blocks.SANDSTONE.get().getDefaultState() : sandstone, 0);
                                }
                                if (random.nextInt(100) == 0 && cave_biome != 1 && cave_biome != 2 && cave_biome != 3) {
                                    if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() ||
                                            world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                            world.getBlockState(pos) == BlocksT.DIRT_BLOCK.get().getDefaultState()) {
                                        if (random.nextInt(5) >= 2) {
                                            int height = random.nextInt(4) + 1;
                                        }
                                    }
                                }
                            }


                            if (world.getRandom().nextInt(4000) == 0) {
                                if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                    placeStuff(world, BlocksT.WOOD_CHEST.get().getDefaultState(), world.getRandom(), pos);
                                    LootableContainerBlockEntity.setLootTable(world, world.getRandom(), pos, new Identifier(TerrariaMod.MOD_ID, "chests/surface_chest"));
                                }
                            }

                            if (!evil) {
                                //Foliage
                                if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                    if (world.getRandom().nextInt(500) == 0) {
                                        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.MUSHROOM.get().getDefaultState(), 0);
                                    }
                                }

                                if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                    if (world.getRandom().nextInt(5) == 0) {
                                        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.GRASS.get().getDefaultState(), 0);
                                    }
                                }

                                GeneratePurityTrees(world, x, y, z, pos);

                                if (world.getRandom().nextInt(100) == 0 && y > 60 && y < 80) {
                                    PlaceVine(world, pos);
                                }

                                //Misc
                                if (y <= -5) {
                                    if (world.getRandom().nextInt(3500) == 0) {
                                        if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                                placeStuff(world, BlocksT.LIFE_CRYSTAL_BLOCK.get().getDefaultState(), world.getRandom(), pos);
                                            }
                                        }
                                    }
                                }
                            }

                            //Ores
                            if (y <= 80) {
                                if (world.getRandom().nextInt(1200) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.GRASS_BLOCK.get().getDefaultState(), BlocksT.COPPER_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.get().getDefaultState(), BlocksT.COPPER_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.GRASS_BLOCK.getDefaultState(), BlocksT.COPPER_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.STONE.getDefaultState(), BlocksT.COPPER_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 60) {
                                if (world.getRandom().nextInt(1240) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.GRASS_BLOCK.get().getDefaultState(), BlocksT.IRON_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.get().getDefaultState(), BlocksT.IRON_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.GRASS_BLOCK.getDefaultState(), BlocksT.IRON_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.STONE.getDefaultState(), BlocksT.IRON_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 10) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.get().getDefaultState(), BlocksT.GOLD_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.STONE.getDefaultState(), BlocksT.GOLD_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= -150) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.ASH.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.ASH.get().getDefaultState(), BlocksT.HELLSTONE_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 60) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.get().getDefaultState(), BlocksT.RUBY_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 60) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.get().getDefaultState(), BlocksT.SAPPHIRE_ORE.get().getDefaultState());
                                    }
                                }
                            }

                            //Underground Objects
                            if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState() || world.getBlockState(pos) == BlocksT.DIRT_BLOCK.get().getDefaultState()) {
                                if (y <= 50) {
                                    if (world.getRandom().nextInt(100) == 0) {
                                        if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) == Blocks.AIR.getDefaultState()) {
                                            //world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.FOREST_POT.get().getDefaultState(), 0);
                                        }
                                    }
                                }
                            }

                            //Structures
                            if (!world.isClient()) {
                                if (y <= -10 && y >= -150) {
                                    if (world.getRandom().nextInt(20000) == 0) {
                                        StructurePlacerAPI placerAPI = new StructurePlacerAPI(world, new Identifier(TerrariaMod.MOD_ID, "underground_house"), pos);
                                        placerAPI.loadStructure();
                                    }
                                }
                            }

                            if (!world.isClient()) {
                                if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                    if (world.isChunkLoaded(pos)) {
                                        if (world.getRandom().nextInt(30000) == 0) {
                                            int height = 40 + world.getRandom().nextInt(10);
                                            StructurePlacerAPI placerAPI = new StructurePlacerAPI(world, new Identifier(TerrariaMod.MOD_ID, "cloud"), new BlockPos(pos.getX(), pos.getY() + height, pos.getZ()));
                                            placerAPI.loadStructure();
                                        }
                                    }
                                }
                            }

                            //Ice generation
                            if (world.getBlockState(pos) == BlocksT.SNOW.get().getDefaultState()) {
                                if (random.nextInt(1000) == 0) {
                                    placeOre(world, random, pos, 30, BlocksT.SNOW.get().getDefaultState(), BlocksT.ICE.get().getDefaultState());
                                }
                            }

                            if (evil && WorldDataT.worldEvil == 0) {
                                if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                    world.setBlockState(pos, BlocksT.CORRUPTED_GRASS_BLOCK.get().getDefaultState(), 0);
                                }
                                if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                    world.setBlockState(pos, BlocksT.EBONSTONE.get().getDefaultState(), 0);
                                }

                                if (world.getBlockState(pos) == BlocksT.CORRUPTED_GRASS_BLOCK.get().getDefaultState()) {
                                    int depth = 40;
                                    int depth2 = 50;
                                    BlockPos.Mutable pos2 = new BlockPos.Mutable();

                                    for (int i = 0; i < depth2 + 10; i++) {
                                        pos2.set(pos.getX(), pos.getY() - i, pos.getZ());
                                        if (i >= depth - 3 && i <= depth || i >= depth2) {
                                            if (world.getRandom().nextInt(100) <= 95)
                                                world.setBlockState(pos2, BlocksT.EBONSTONE.get().getDefaultState(), 0);
                                        }
                                        if (i > depth && i < depth2) {
                                            world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 0);
                                        }
                                    }
                                    if (world.getRandom().nextInt(10) <= 1)
                                        if (x == 8 && z == 8) {
                                            int pitDepth = world.getRandom().nextInt(40) + 40;
                                            double dirX = world.getRandom().nextDouble() - 0.5 * 4.0;
                                            double dirZ = world.getRandom().nextDouble() - 0.5 * 4.0;
                                            double mul = world.getRandom().nextDouble() * 3;
                                            int size = world.getRandom().nextInt(3) + 3;
                                            for (int xx = 0; xx < 16; xx++) {
                                                for (int zz = 0; zz < 16; zz++) {
                                                    double dist = Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2));
                                                    if (dist <= size) {
                                                        for (int i = 0; i < pitDepth; i++) {
                                                            pos2.set(xx + (int)(Math.cos(xx) * dirX * mul), pos.getY() - i, zz + (int)(Math.sin(zz) * dirZ * mul));
                                                            if (dist <= size - ((world.getRandom().nextInt(10) <= 7) ? 1 : 0)) {
                                                                world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 0);
                                                            } else {
                                                                if (world.getBlockState(pos2).getBlock() != Blocks.AIR &&
                                                                        world.getBlockState(pos2).getBlock() != Blocks.AIR) {
                                                                    world.setBlockState(pos2, BlocksT.EBONSTONE.get().getDefaultState(), 0);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                }
                            } else if (evil && WorldDataT.worldEvil == 1) {
                                if (world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState()) {
                                    world.setBlockState(pos, BlocksT.CRIMSON_GRASS_BLOCK.get().getDefaultState(), 0);
                                }
                                if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState()) {
                                    world.setBlockState(pos, BlocksT.CRIMSTONE.get().getDefaultState(), 0);
                                }
                            }

                            if (y <= 20) {
                                if (cave_biome == 0) {
                                    pos.set(x, y, z);
                                    if (world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState() ||
                                            world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState()) {
                                        world.setBlockState(pos, BlocksT.MUD.get().getDefaultState(), 0);
                                        pos.set(x, y + 1, z);
                                        if (world.isChunkLoaded(pos))
                                            if (world.getBlockState(pos) == Blocks.AIR.getDefaultState() ||
                                                    world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState()) {


                                                if (random.nextInt(100) == 0) {
                                                    if (random.nextBoolean()) {

                                                        int height = random.nextInt(5) + 7;
                                                        for (int j = y; j < y + height; j++) {
                                                            pos.set(x, j, z);
                                                        }
                                                        for (int i = - 2; i <= + 2; i++) {
                                                            for (int j = - 2; j <= + 2; j++) {
                                                                pos.set(x + i, y + height, z + j);

                                                                if (Math.abs(i) == 2 && Math.abs(j) == 2) {
                                                                    if (random.nextInt(4) == 0) continue;
                                                                }

                                                                if (Math.abs(i) == 1 && j == 0 || Math.abs(j) == 1 && i == 0 || i == 0 && j == 0) {
                                                                    for (int k = 0; k < 2; k++) {
                                                                        if (i != 0 || j != 0) {
                                                                            if (k == 1) {
                                                                                if (random.nextInt(4) == 0) continue;
                                                                            }
                                                                        }
                                                                        pos.set(x + i, y + height + 1 + k, z + j);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                pos.set(x, y, z);
                                                world.setBlockState(pos, BlocksT.MUSHROOM_GRASS.get().getDefaultState(), 0);
                                                if (world.getRandom().nextInt(20) == 0) {
                                                    GenerateGiantMushroom(world, x, y, z, pos);
                                                }

                                                if (world.getBlockState(pos) == BlocksT.MUSHROOM_GRASS.get().getDefaultState()) {
                                                    if (world.getRandom().nextInt(70) == 0) {
                                                        if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) == Blocks.AIR.getDefaultState()) {
                                                            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.GLOWING_MUSHROOM.get().getDefaultState(), 0);
                                                        }
                                                    }
                                                }
                                            }
                                    }
                                } else {
                                    if (cave_biome == 1) {
                                        if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState())
                                            world.setBlockState(pos, BlocksT.MARBLE.get().getDefaultState(), 0);
                                    } else {
                                        if (cave_biome == 2) {
                                            if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                    world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState())
                                                world.setBlockState(pos, BlocksT.GRANITE.get().getDefaultState(), 0);
                                        } else {
                                            if (cave_biome == 3) {
                                                pos.set(x, y, z);

                                                if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                        world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState())
                                                {
                                                    //Stuff
                                                }

                                            } else {
                                                if (cave_biome == 4) {
                                                    if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                            world.getBlockState(pos) == BlocksT.STONE_BLOCK.get().getDefaultState() ||
                                                            world.getBlockState(pos) == Blocks.COBWEB.getDefaultState())
                                                    {
                                                        pos.set(x, y + 1, z);
                                                        if (world.getBlockState(pos) == Blocks.AIR.getDefaultState() ||
                                                                world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState())
                                                        {
                                                            if (random.nextInt(10) <= 4) {
                                                                world.setBlockState(pos, Blocks.COBWEB.getDefaultState(), 0);
                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (TerrariaMod.CONFIG.generateExtrasInVanilla) {
                for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); x++) {
                    for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); z++) {
                        float flatness = noise.GetSimplex(x, z);
                        int height_offset = 0;

                        double world_distance = Math.sqrt(x * x + z * z);

                        boolean beach = world_distance >= 2400;
                        boolean jungle = world_distance > 1900 && (right_jungle ? (x > 0) : (x < 0)) && !beach;

                        boolean snow = ((x + snow_position) * (x + snow_position)) / (float) (biome_width * biome_width) + (z * z) / (float) (biome_length * biome_length) <= 1;
                        boolean desert = ((x - snow_position) * (x - snow_position)) / (float) (biome_width * biome_width) + (z * z) / (float) (biome_length * biome_length) <= 1;

                        if (world_distance >= 2400) {
                            height_offset = (int) (60.0f * (world_distance - 2400) / 300.0f);
                            if (height_offset < 0) height_offset = 0;
                            if (height_offset > 60) height_offset = 60;
                        }

                        if (flatness < 0.05f) flatness = 0.05f;

                        float sine = (float) Math.sin(Math.PI * (world_distance - 2200) / 2400);

                        flatness = MathHelper.lerp(sine > 0 ? sine : 0, flatness, 1);
                        for (int y = world.getBottomY(); y <= world.getTopY(); y++) {
                            pos.set(x, y, z);

                            if (world.isChunkLoaded(pos)) {
                                if (world.getBlockState(pos) != Blocks.WATER.getDefaultState() && world.getBlockState(pos) != Blocks.LAVA.getDefaultState()) {
                                    pos.set(x, y, z);
                                    BlockState state = null;

                                    float stone_density = (y - stone_height + height_offset) / 15.0f;

                                    float density = (y - base_height + height_offset) / 15.0f;
                                    float n = GetTerrainNoise(x, y, z);
                                    float combined = n * flatness + density;

                                    float cave_density = y / 184.0f;
                                    if (cave_density > 0) cave_density = 0;
                                    cave_density = -cave_density;

                                    float underworld_density = (y - underworld_height) / 5.0f;

                                    float underworld_base_density = (y - underworld_base) / 15.0f;

                                    float combined_stone = n + stone_density;
                                    float combined_cave = (n * n) * cave_density;
                                    float jungle_cave = jungle ? (n * (cave_density + 0.1f)) : 0;

                                    float combined_underworld = n + underworld_density;
                                    float combined_underworld_base = n + underworld_base_density;

                                    boolean pure = Math.sqrt(x * x + y * y + z * z) <= purity_radius;

                                    if (combined_cave >= 0.8f || jungle_cave <= -0.8f) {
                                        if (combined >= threshold) {
                                            state = Blocks.AIR.getDefaultState();
                                        } else {
                                            state = Blocks.CAVE_AIR.getDefaultState();
                                        }
                                    }

                                    if (combined_underworld < threshold) {
                                        state = Blocks.CAVE_AIR.getDefaultState();

                                    }

                                    if (y > underworld_base && y <= 200) {
                                        if (state == Blocks.LAVA.getDefaultState() || state == Blocks.WATER.getDefaultState()) {
                                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
                                        }
                                    }

                                    if (y < underworld_base) {
                                        state = Blocks.LAVA.getDefaultState();
                                    }

                                    if (combined_underworld_base < threshold) {
                                        state = BlocksT.ASH.get().getDefaultState();
                                    }

                                    if (y <= -253) {
                                        state = Blocks.BEDROCK.getDefaultState();
                                    }

                                    if (state != null) {
                                        world.setBlockState(pos, state, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); x++) {
                for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); z++) {
                    for (int y = world.getBottomY(); y <= world.getTopY(); y++) {
                        if (y >= 252) continue;
                        int cave_biome = (int) (noise.GetCellular(x * 0.5f, y, z * 0.5f) * 10);

                        pos.set(x, y, z);
                        pos.set(x, y, z);
                        if (world.isChunkLoaded(pos)) {
                            pos.set(x, y + 1, z);

                            if (world.getRandom().nextInt(4000) == 0) {
                                if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == Blocks.GRASS_BLOCK.getDefaultState()) {
                                    placeStuff(world, BlocksT.WOOD_CHEST.get().getDefaultState(), world.getRandom(), pos);
                                    LootableContainerBlockEntity.setLootTable(world, world.getRandom(), pos, new Identifier(TerrariaMod.MOD_ID, "chests/surface_chest"));
                                }
                            }

                            if (y <= -5) {
                                if (world.getRandom().nextInt(3500) == 0) {
                                    if (world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                                        if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == Blocks.STONE.getDefaultState() || world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == Blocks.DEEPSLATE.getDefaultState()) {
                                            placeStuff(world, BlocksT.LIFE_CRYSTAL_BLOCK.get().getDefaultState(), world.getRandom(), pos);
                                        }
                                    }
                                }
                            }

                            //Ores
                            if (y <= 80) {
                                if (world.getRandom().nextInt(1200) == 0) {
                                    if (world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.GRASS_BLOCK.getDefaultState(), BlocksT.COPPER_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, world.getBlockState(pos), BlocksT.COPPER_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 60) {
                                if (world.getRandom().nextInt(1240) == 0) {
                                    if (world.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, Blocks.GRASS_BLOCK.getDefaultState(), BlocksT.IRON_ORE.get().getDefaultState());
                                    } else if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, world.getBlockState(pos), BlocksT.IRON_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 10) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, world.getBlockState(pos), BlocksT.GOLD_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= -150) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == BlocksT.ASH.get().getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, BlocksT.ASH.get().getDefaultState(), BlocksT.HELLSTONE_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 60) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, world.getBlockState(pos), BlocksT.RUBY_ORE.get().getDefaultState());
                                    }
                                }
                            }
                            if (y <= 60) {
                                if (world.getRandom().nextInt(1340) == 0) {
                                    if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState()) {
                                        placeOre(world, world.getRandom(), pos, world.getRandom().nextInt(12) + 3, world.getBlockState(pos), BlocksT.SAPPHIRE_ORE.get().getDefaultState());
                                    }
                                }
                            }

                            if (TerrariaMod.CONFIG.generateExtrasInVanilla) {
                                if (y <= 20) {
                                    if (cave_biome == 0) {
                                        pos.set(x, y, z);
                                        if (world.getBlockState(pos) == Blocks.STONE.getDefaultState() ||
                                                world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() || world.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
                                            world.setBlockState(pos, BlocksT.MUD.get().getDefaultState(), 0);
                                            pos.set(x, y + 1, z);
                                            if (world.isChunkLoaded(pos))
                                                if (world.getBlockState(pos) == Blocks.AIR.getDefaultState() ||
                                                        world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState()) {


                                                    if (random.nextInt(100) == 0) {
                                                        if (random.nextBoolean()) {

                                                            int height = random.nextInt(5) + 7;
                                                            for (int j = y; j < y + height; j++) {
                                                                pos.set(x, j, z);
                                                            }
                                                            for (int i = - 2; i <= + 2; i++) {
                                                                for (int j = - 2; j <= + 2; j++) {
                                                                    pos.set(x + i, y + height, z + j);

                                                                    if (Math.abs(i) == 2 && Math.abs(j) == 2) {
                                                                        if (random.nextInt(4) == 0) continue;
                                                                    }

                                                                    if (Math.abs(i) == 1 && j == 0 || Math.abs(j) == 1 && i == 0 || i == 0 && j == 0) {
                                                                        for (int k = 0; k < 2; k++) {
                                                                            if (i != 0 || j != 0) {
                                                                                if (k == 1) {
                                                                                    if (random.nextInt(4) == 0) continue;
                                                                                }
                                                                            }
                                                                            pos.set(x + i, y + height + 1 + k, z + j);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    pos.set(x, y, z);
                                                    world.setBlockState(pos, BlocksT.MUSHROOM_GRASS.get().getDefaultState(), 0);
                                                    if (world.getRandom().nextInt(20) == 0) {
                                                        GenerateGiantMushroom(world, x, y, z, pos);
                                                    }

                                                    if (world.getBlockState(pos) == BlocksT.MUSHROOM_GRASS.get().getDefaultState()) {
                                                        if (world.getRandom().nextInt(70) == 0) {
                                                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) == Blocks.AIR.getDefaultState()) {
                                                                world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.GLOWING_MUSHROOM.get().getDefaultState(), 0);
                                                            }
                                                        }
                                                    }
                                                }
                                        }
                                    } else {
                                        if (cave_biome == 1) {
                                            if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                    world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState())
                                                world.setBlockState(pos, BlocksT.MARBLE.get().getDefaultState(), 0);
                                        } else {
                                            if (cave_biome == 2) {
                                                if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                        world.getBlockState(pos) == Blocks.STONE.getDefaultState() || world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState())
                                                    world.setBlockState(pos, BlocksT.GRANITE.get().getDefaultState(), 0);
                                            } else {
                                                if (cave_biome == 3) {
                                                    pos.set(x, y, z);

                                                    if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                            world.getBlockState(pos) == Blocks.STONE.getDefaultState())
                                                    {
                                                        //Stuff
                                                    }

                                                } else {
                                                    if (cave_biome == 4) {
                                                        if (world.getBlockState(pos) == BlocksT.EBONSTONE.get().getDefaultState() ||
                                                                world.getBlockState(pos) == Blocks.STONE.getDefaultState() ||
                                                                world.getBlockState(pos) == Blocks.COBWEB.getDefaultState() ||
                                                                world.getBlockState(pos) == Blocks.DEEPSLATE.getDefaultState())
                                                        {
                                                            pos.set(x, y + 1, z);
                                                            if (world.getBlockState(pos) == Blocks.AIR.getDefaultState() ||
                                                                    world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState())
                                                            {
                                                                if (random.nextInt(10) <= 4) {
                                                                    world.setBlockState(pos, Blocks.COBWEB.getDefaultState(), 0);
                                                                }
                                                            }
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (TerrariaMod.CONFIG.customWorldGen) {
            info.cancel();
        }
    }

    private void PlaceVine(StructureWorldAccess world, BlockPos pos) {
        if (world.getBlockState(pos) == BlocksT.DIRT_BLOCK.get().getDefaultState() || world.getBlockState(pos) == BlocksT.GRASS_BLOCK.get().getDefaultState() && world.isAir(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()))) {
            int length = 3 + world.getRandom().nextInt(5);

            for (int l = 0; l <= length; l++) {
                if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - l, pos.getZ())) == Blocks.AIR.getDefaultState()) {
                    world.setBlockState(new BlockPos(pos.getX(), pos.getY() - l, pos.getZ()), BlocksT.VINE.get().getDefaultState(), 0);
                }
            }
        }
    }

    private void GeneratePurityTrees(StructureWorldAccess world, int x, int y, int z, BlockPos.Mutable pos) {
        pos.set(x, y, z);
        if (world.getBlockState(pos).getBlock() == BlocksT.GRASS_BLOCK.get() || world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
            if (world.getRandom().nextInt(80) == 0) {
                int height = world.getRandom().nextInt(10) + 4;
                world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.FOREST_STUMP.get().getDefaultState(), 0);
                for (int h = 2; h <= height; h++) {
                    world.setBlockState(new BlockPos(pos.getX(), pos.getY() + h, pos.getZ()), BlocksT.FOREST_STEM.get().getDefaultState(), 0);
                }
                world.setBlockState(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ()), BlocksT.FOREST_TOP.get().getDefaultState(), 0);
            }
        }
    }

    private void GenerateGiantMushroom(StructureWorldAccess world, int x, int y, int z, BlockPos.Mutable pos) {
        pos.set(x, y, z);
        if (world.getBlockState(pos).getBlock() == BlocksT.MUSHROOM_GRASS.get()) {
            int height = world.getRandom().nextInt(6) + 4;
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get().getDefaultState(), 0);
            for (int h = 2; h <= height; h++) {
                world.setBlockState(new BlockPos(pos.getX(), pos.getY() + h, pos.getZ()), BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get().getDefaultState(), 0);
            }
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ()), BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get().getDefaultState(), 0);
        }
    }

    public boolean placeOre(StructureWorldAccess worldIn, Random rand, BlockPos pos, int size, BlockState target, BlockState state) {

        if (worldIn.getBlockState(pos) != Blocks.WATER.getDefaultState() && worldIn.getBlockState(pos) != Blocks.LAVA.getDefaultState()) {
            float f = rand.nextFloat() * (float)Math.PI;
            float f1 = (float)size / 8.0F;
            int i = MathHelper.ceil(((float)size / 16.0F * 2.0F + 1.0F) / 2.0F);
            double d0 = (double)((float)pos.getX() + MathHelper.sin(f) * f1);
            double d1 = (double)((float)pos.getX() - MathHelper.sin(f) * f1);
            double d2 = (double)((float)pos.getZ() + MathHelper.cos(f) * f1);
            double d3 = (double)((float)pos.getZ() - MathHelper.cos(f) * f1);
            int j = 2;
            double d4 = (double)(pos.getY() + rand.nextInt(3) - 2);
            double d5 = (double)(pos.getY() + rand.nextInt(3) - 2);
            int k = pos.getX() - MathHelper.ceil(f1) - i;
            int l = pos.getY() - 2 - i;
            int i1 = pos.getZ() - MathHelper.ceil(f1) - i;
            int j1 = 2 * (MathHelper.ceil(f1) + i);
            int k1 = 2 * (2 + i);

            for(int l1 = k; l1 <= k + j1; ++l1) {
                for(int i2 = i1; i2 <= i1 + j1; ++i2) {
                    return func_207803_a(worldIn, rand, target, state, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1, size);
                }
            }
        }
        return false;
    }

    protected boolean func_207803_a(StructureWorldAccess worldIn, Random random, BlockState target, BlockState state, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_, int size) {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
        double[] adouble = new double[size * 4];

        for(int j = 0; j < size; ++j) {
            float f = (float)j / (float)size;
            double d0 = MathHelper.lerp((double)f, p_207803_4_, p_207803_6_);
            double d2 = MathHelper.lerp((double)f, p_207803_12_, p_207803_14_);
            double d4 = MathHelper.lerp((double)f, p_207803_8_, p_207803_10_);
            double d6 = random.nextDouble() * (double)size / 16.0D;
            double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            adouble[j * 4 + 0] = d0;
            adouble[j * 4 + 1] = d2;
            adouble[j * 4 + 2] = d4;
            adouble[j * 4 + 3] = d7;
        }

        for(int l2 = 0; l2 < size - 1; ++l2) {
            if (!(adouble[l2 * 4 + 3] <= 0.0D)) {
                for(int j3 = l2 + 1; j3 < size; ++j3) {
                    if (!(adouble[j3 * 4 + 3] <= 0.0D)) {
                        double d12 = adouble[l2 * 4 + 0] - adouble[j3 * 4 + 0];
                        double d13 = adouble[l2 * 4 + 1] - adouble[j3 * 4 + 1];
                        double d14 = adouble[l2 * 4 + 2] - adouble[j3 * 4 + 2];
                        double d15 = adouble[l2 * 4 + 3] - adouble[j3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                            if (d15 > 0.0D) {
                                adouble[j3 * 4 + 3] = -1.0D;
                            } else {
                                adouble[l2 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(int i3 = 0; i3 < size; ++i3) {
            double d11 = adouble[i3 * 4 + 3];
            if (!(d11 < 0.0D)) {
                double d1 = adouble[i3 * 4 + 0];
                double d3 = adouble[i3 * 4 + 1];
                double d5 = adouble[i3 * 4 + 2];
                int k = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
                int k3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
                int l = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
                int i1 = Math.max(MathHelper.floor(d1 + d11), k);
                int j1 = Math.max(MathHelper.floor(d3 + d11), k3);
                int k1 = Math.max(MathHelper.floor(d5 + d11), l);

                for(int l1 = k; l1 <= i1; ++l1) {
                    double d8 = ((double)l1 + 0.5D - d1) / d11;
                    if (d8 * d8 < 1.0D) {
                        for(int i2 = k3; i2 <= j1; ++i2) {
                            double d9 = ((double)i2 + 0.5D - d3) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D) {
                                for(int j2 = l; j2 <= k1; ++j2) {
                                    double d10 = ((double)j2 + 0.5D - d5) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                                        int k2 = l1 - p_207803_16_ + (i2 - p_207803_17_) * p_207803_19_ + (j2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
                                        if (!bitset.get(k2)) {
                                            bitset.set(k2);
                                            blockpos$mutableblockpos.set(l1, i2, j2);
                                            if (target == worldIn.getBlockState(blockpos$mutableblockpos)) {
                                                worldIn.setBlockState(blockpos$mutableblockpos, state, 2);
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return i > 0;
    }

	/*
	private void GeneratePurityGrass(Chunkworld world, int x, int y, int z, BlockPos.Mutable pos, BlockPos.Mutable pos) {
		pos.set(x, y, z);
		if (world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
			if (world.getRandom().nextInt(10) == 0) {
				pos.set(x, y + 1, z);
				if (world.isChunkLoaded(pos))
				world.setBlockState(pos, Blocks.GRASS.get().getDefaultState(), 0);
			}
		}
	}
	*/

    public boolean placeOre2(StructureWorldAccess worldIn, Random rand, BlockPos pos, int size, BlockState target, BlockState state) {

        if (target == worldIn.getBlockState(pos) && worldIn.getBlockState(pos) != Blocks.WATER.getDefaultState() && worldIn.getBlockState(pos) != Blocks.LAVA.getDefaultState()) {
            int maxLineLength = 4;
            int pY = pos.getY();
            int currentPlacement = 0;
            for (int s = 0; s <= size; s++) {
                if (currentPlacement < maxLineLength) {
                    currentPlacement = s;
                }
                worldIn.setBlockState(new BlockPos(pos.getX() + currentPlacement, pY, pos.getZ()), state, 0);

                if (currentPlacement >= maxLineLength) {
                    currentPlacement = 0;
                    pY -= 1;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public void placeStuff(StructureWorldAccess worldIn, BlockState placeBlock, Random rand, BlockPos pos) {
        worldIn.setBlockState(pos, placeBlock, 0);
    }
}