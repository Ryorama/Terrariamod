package com.ryorama.terrariamod.blocks.obsticals;

import com.ryorama.terrariamod.blocks.api.BlockT;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import javax.swing.text.html.BlockView;

public class Spike extends BlockT {
    public Spike(FabricBlockSettings properties, float hardness, float difficulty) {
        super(properties.nonOpaque(), hardness, difficulty);
    }

    public boolean isFullCube(BlockState state) {
        return false;
    }

    public int getOpacity(BlockState state, World worldIn, BlockPos pos) {
        return 0;
    }

    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    public VoxelShape getVisualShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public void onEntityCollision(World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.GENERIC, 40);
    }
}
