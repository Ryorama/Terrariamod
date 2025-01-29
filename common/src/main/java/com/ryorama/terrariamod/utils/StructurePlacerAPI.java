package com.ryorama.terrariamod.utils;

import net.minecraft.ResourceLocationException;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public class StructurePlacerAPI {

    private WorldGenLevel world;
    private ResourceLocation templateName;
    private BlockPos blockPos;
    private Mirror mirror;
    private Rotation rotation;
    private boolean ignoreEntities;
    private float integrity;
    private BlockPos offset = new BlockPos(0, 0, 0);

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * @param mirror Use this to mirror the structure using <code>Mirror.#</code>
     * @param rotation Use this to rotate the structure using <code>Rotation.#</code>
     * @param ignoreEntities Set to true to block the spawning of entities saved in the structure file
     * @param integrity Set this to a value between 0f and 1f to remove some blocks from the placed structure. (All blocks = 1f)
     * @param offset Use this to offset the placing of the structure.
     * */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos, Mirror mirror, Rotation rotation, boolean ignoreEntities, float integrity, BlockPos offset){
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = mirror;
        this.rotation = rotation;
        this.ignoreEntities = ignoreEntities;
        this.integrity = integrity;
        this.offset = offset;
    }

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos) {
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = Mirror.NONE;
        this.rotation = Rotation.NONE;
        this.ignoreEntities = true;
        this.integrity = 1.0f;
        this.offset = new BlockPos(0, 0, 0);
    }

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * @param offset Use this to offset the placing of the structure.
     * */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos, BlockPos offset){
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = Mirror.NONE;
        this.rotation = Rotation.NONE;
        this.ignoreEntities = true;
        this.integrity = 1.0f;
        this.offset = offset;
    }

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * @param mirror Use this to mirror the structure using <code>Mirror.#</code>
     */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos, Mirror mirror){
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = mirror;
        this.rotation = Rotation.NONE;
        this.ignoreEntities = true;
        this.integrity = 1.0f;
        this.offset = new BlockPos(0, 0, 0);
    }

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * @param rotation Use this to rotate the structure using <code>Rotation.#</code>
     * */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos, Rotation rotation){
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = Mirror.NONE;
        this.rotation = rotation;
        this.ignoreEntities = true;
        this.integrity = 1.0f;
        this.offset = new BlockPos(0, 0, 0);
    }

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * @param mirror Use this to mirror the structure using <code>Mirror.#</code>
     * @param rotation Use this to rotate the structure using <code>Rotation.#</code>
     * */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos, Mirror mirror, Rotation rotation){
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = mirror;
        this.rotation = rotation;
        this.ignoreEntities = true;
        this.integrity = 1.0f;
        this.offset = new BlockPos(0, 0, 0);
    }

    /**
     * With this you can create placer object which will spawn
     * a new structure from an nbt file located in /data/modid/structures
     *
     * @param world The ServerWorld in which to place the structure
     * @param templateName The ResourceLocation of the structure to place, like <code>new ResourceLocation(MOD_ID, structure_name)</code>
     * @param blockPos The position of the structure
     * @param integrity Set this to a value between 0f and 1f to remove some blocks from the placed structure. (All blocks = 1f)
     * */
    public StructurePlacerAPI(WorldGenLevel world, ResourceLocation templateName, BlockPos blockPos, float integrity){
        this.world = world;
        this.templateName = templateName;
        this.blockPos = blockPos;
        this.mirror = Mirror.NONE;
        this.rotation = Rotation.NONE;
        this.ignoreEntities = true;
        this.integrity = integrity;
        this.offset = new BlockPos(0, 0, 0);
    }

    /**Use this method to load the structure into the world and
     * spawn it. You can check to see if the placing was succesful.
     */
    public boolean loadStructure() {
        if (this.templateName != null) {
            StructureTemplateManager structureTemplateManager = world.getLevel().getStructureManager();

            Optional optional;
            try {
                optional = structureTemplateManager.get(this.templateName);
            } catch (ResourceLocationException var6) {
                return false;
            }

            return !optional.isPresent() ? false : this.place((StructureTemplate)optional.get());
        } else {
            return false;
        }
    }

    /**This method is useb by the <code>loadStructure()</code> method,
     * which already checks if the structure exists or not, so use that instead*/
    public boolean place(StructureTemplate template) {
        try {
            StructurePlaceSettings structurePlacementData = (new StructurePlaceSettings()).setMirror(this.mirror).setRotation(this.rotation).setIgnoreEntities(this.ignoreEntities);
            if (this.integrity < 1.0F) {
                structurePlacementData.clearProcessors().addProcessor(new BlockRotProcessor(Mth.clamp(this.integrity, 0.0F, 1.0F))).setRandom(createRandom(this.world.getSeed()));
            }
            BlockPos blockPos2 = blockPos.offset(this.offset);
            template.placeInWorld(world, blockPos2, blockPos2, structurePlacementData, createRandom(this.world.getSeed()), 2);
            unloadStructure();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**This method unloads the structure after it has been placed.
     * No need to use it on your own, included during placement*/
    public void unloadStructure() {
        if (this.templateName != null) {
            StructureTemplateManager structureTemplateManager = world.getLevel().getStructureManager();
            structureTemplateManager.remove(this.templateName);
        }
    }

    /**This method creates a random seed for the integrity run-down effect.
     * No need to use it on your own, included during placement*/
    public static RandomSource createRandom(long seed) {
        return seed == 0L ? RandomSource.create(Util.getMillis()) : RandomSource.create(seed);
    }
}