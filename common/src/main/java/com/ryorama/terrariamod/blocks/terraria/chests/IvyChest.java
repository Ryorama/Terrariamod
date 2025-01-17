package com.ryorama.terrariamod.blocks.terraria.chests;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.impl.ChestT;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.entities.terraria.block.IvyChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class IvyChest extends ChestT {

    public IvyChest(BlockBehaviour.Properties settings) {
        super(settings, () -> EntitiesT.IVY_CHEST.get());
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(TerrariaMod.MOD_ID, "entity/chest/ivy_chest");
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IvyChestBlockEntity(pos, state);
    }
}
