package com.ryorama.terrariamod.blocks.terraria.chests;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.impl.ChestT;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.entities.terraria.block.FrozenChestBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public class FrozenChest extends ChestT {

    public FrozenChest(Settings settings) {
        super(settings, () -> EntitiesT.FROZEN_CHEST.get());
    }

    @Override
    public Identifier getTexture() {
        return new Identifier(TerrariaMod.MOD_ID, "entity/chest/frozen_chest");
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FrozenChestBlockEntity(pos, state);
    }
}
