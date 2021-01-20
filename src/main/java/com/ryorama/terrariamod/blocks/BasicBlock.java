package com.ryorama.terrariamod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.BlockState;

public class BasicBlock extends Block {

	public BasicBlock(Properties properties) {
		super(properties);
	}
	
	private boolean fullCube = true;
	
	
	public boolean isFullCube(BlockState state) {
	      return fullCube;
	}
	
	public BasicBlock setFullCube(boolean fullCube) {
		this.fullCube = fullCube;
		return this;
	}
	
	
	
}