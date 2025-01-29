package com.ryorama.terrariamod.world;


import net.minecraft.world.level.block.Block;

import java.util.List;

public class OreGen {
    public final Block oreBlock;
    public final List<Block> replacableBlocks;
    public final float genChance;
    public final int minAmnt;
    public final int maxAmt;

    public OreGen(Block oreBlock, List<Block> replacableBlocks, float genChance, int minAmnt, int maxAmt) {
        this.oreBlock = oreBlock;
        this.replacableBlocks = replacableBlocks;
        this.genChance = genChance;
        this.minAmnt = minAmnt;
        this.maxAmt = maxAmt;
    }
}
