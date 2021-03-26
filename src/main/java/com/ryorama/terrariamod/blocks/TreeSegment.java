package com.ryorama.terrariamod.blocks;

import java.util.Random;

import javax.swing.text.html.BlockView;

import com.ryorama.terrariamod.items.ItemsT;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class TreeSegment extends BlockT {
		   
	public static Random rand = new Random();
	
	public TreeSegment(FabricBlockSettings properties, float hardness, float difficulty) {
		super(properties, hardness, difficulty, new ItemStack(ItemsT.WOOD, rand.nextInt(3) + 1));
	}
	
	public boolean isFullCube(BlockState state) {
		return false;
	}
	
	public boolean isOpaqueCube(BlockState state) {
		return false;
	}
	

	public static VoxelShape createCuboidShape(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax) {
	      return VoxelShapes.cuboid(0f, 0f, 0f, 0.7f, 1f, 0.7f);
	}
}