package com.ryorama.terrariamod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class TreeSegment extends BlockT {
		   
	public TreeSegment(FabricBlockSettings properties, float hardness, float difficulty) {
		super(properties, hardness, difficulty);
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction.getAxis() == direction.getAxis().Y) {
			if (world.getBlockState(neighborPos) == Blocks.AIR.getDefaultState()) {
				for (int y = neighborPos.getY(); y >= neighborPos.getY() + 12; y++) {
					world.setBlockState(new BlockPos(neighborPos.getX(), y, neighborPos.getZ()), Blocks.AIR.getDefaultState(), 0);
				}
			}
			return Blocks.AIR.getDefaultState();
		} else {
			return state;
		}
	}
} 