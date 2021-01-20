package com.ryorama.terrariamod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DirtBlock extends Block {

	public DirtBlock(Properties properties) {
		super(properties);
	}
	
	public void onBlockClicked (BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		
	}
}