package com.ryorama.terrariamod.blocks;

import java.util.Random;

import javax.swing.text.html.BlockView;

import com.ryorama.terrariamod.items.ItemsT;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class LightPlantT extends LightBlockT {

	public LightPlantT(FabricBlockSettings properties, float hardness, float difficulty, int lightLevel) {
		super(properties.nonOpaque(), hardness, difficulty, lightLevel);
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
	
}
