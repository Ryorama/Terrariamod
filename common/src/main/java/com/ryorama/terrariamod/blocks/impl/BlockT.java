package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.impl.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.ToIntFunction;

public class BlockT extends Block {

    public int buy, sell;
    public float difficulty = 1;
    public boolean pick, axe, hammer;
    public boolean material;
    public boolean harvest = true;
    public boolean consumable;

    public int potionSickness;

    public Random random = new Random();

    public int health, mana;

    public String tooltip = "";

    public String name;
    public String shape = "";

    protected ArrayList<String> allowed = new ArrayList<>();

    public BlockT(BlockBehaviour.Properties properties, float hardness, float difficulty) {
        super(properties.destroyTime(hardness * 0.03f));
        this.difficulty = difficulty;
    }

    public BlockT(BlockBehaviour.Properties properties, float hardness, float difficulty, int luminance) {
        super(properties.destroyTime(hardness * 0.03f).noOcclusion().lightLevel(new ToIntFunction<BlockState>() {
            @Override
            public int applyAsInt(BlockState value) {
                return luminance;
            }
        }));
        this.difficulty = difficulty;
    }

    public BlockT addAllowed(String... block) {
        for (String b : block)
            this.allowed.add(b);
        return this;
    }

    public float getMiningSpeed(ItemT item) {
        if (pick && item.pick >= difficulty) {
            return item.pick / difficulty / 30;
        }

        if (axe && item.axe >= difficulty) {
            return item.axe / difficulty / 30;
        }

        if (hammer && item.hammer >= difficulty) {
            return item.hammer / difficulty / 30;
        }

        return -1;
    }

    public BlockT setPick(boolean pick) {
        this.pick = pick;
        return this;
    }

    public BlockT setAxe(boolean axe) {
        this.axe = axe;
        return this;
    }

    @Override
    public void onBroken(LevelAccessor world, BlockPos pos, BlockState state) {
        for (int t = 0; t <= 20; t++) {
            BlockPos abovePos = new BlockPos(pos.getX(), pos.getY() + t, pos.getZ());
            Block aboveBlock = world.getBlockState(abovePos).getBlock();

            if (aboveBlock instanceof PlantT && aboveBlock != BlocksT.VINE.get() || aboveBlock instanceof TreeSegment) {
                world.destroyBlock(abovePos, true);
            }
        }

        for (int t = 0; t <= 20; t++) {
            BlockPos bottomPos = new BlockPos(pos.getX(), pos.getY() - t, pos.getZ());
            Block bottomBlock = world.getBlockState(bottomPos).getBlock();

            if (bottomBlock == BlocksT.VINE.get()) {
                world.destroyBlock(bottomPos, false);
            }
        }
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, Player player, BlockGetter world, BlockPos pos) {
        if (player.getMainHandItem().getItem() instanceof ItemT || player.getMainHandItem().getItem() instanceof PickaxeT || player.getMainHandStack().getItem() instanceof AxeT || player.getMainHandItem().getItem() instanceof ShortswordT || player.getMainHandItem().getItem() instanceof BroadswordT) {
            return getMiningSpeed((ItemT) player.getMainHandItem().getItem());
        } else {
            return -1;
        }
    }
}
