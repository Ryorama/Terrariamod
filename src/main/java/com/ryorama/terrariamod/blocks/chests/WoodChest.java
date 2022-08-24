package com.ryorama.terrariamod.blocks.chests;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.api.ChestT;
import com.ryorama.terrariamod.entity.block.FrozenChestBlockEntity;
import com.ryorama.terrariamod.entity.block.WoodChestBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public class WoodChest extends ChestT {

    public WoodChest(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(settings, supplier);
    }

    @Override
    public Identifier getTexture() {
        return new Identifier(TerrariaMod.MODID, "block/chests/wood_chest");
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WoodChestBlockEntity(pos, state);
    }
}
