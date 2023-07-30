package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.impl.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

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

    protected ArrayList<String> allowed = new ArrayList<String>();

    public BlockT(Settings properties, float hardness, float difficulty) {
        super(properties.hardness(hardness * 0.03f));
        this.difficulty = difficulty;
    }

    public BlockT(Settings properties, float hardness, float difficulty, int luminance) {
        super(properties.hardness(hardness * 0.03f).nonOpaque().luminance(new ToIntFunction<BlockState>() {
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
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        for (int t = 0; t <= 20; t++) {
            BlockPos abovePos = new BlockPos(pos.getX(), pos.getY() + t, pos.getZ());
            Block aboveBlock = world.getBlockState(abovePos).getBlock();

            if (aboveBlock instanceof PlantT && aboveBlock != BlocksT.VINE.get() || aboveBlock instanceof TreeSegment) {
                world.breakBlock(abovePos, true);
            }
        }

        for (int t = 0; t <= 20; t++) {
            BlockPos bottomPos = new BlockPos(pos.getX(), pos.getY() - t, pos.getZ());
            Block bottomBlock = world.getBlockState(bottomPos).getBlock();

            if (bottomBlock == BlocksT.VINE.get()) {
                world.breakBlock(bottomPos, false);
            }
        }
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if (player.getMainHandStack().getItem() instanceof ItemT || player.getMainHandStack().getItem() instanceof PickaxeT || player.getMainHandStack().getItem() instanceof AxeT || player.getMainHandStack().getItem() instanceof ShortswordT || player.getMainHandStack().getItem() instanceof BroadswordT) {
            return getMiningSpeed((ItemT) player.getMainHandStack().getItem());
        } else {
            return -1;
        }
    }
}
