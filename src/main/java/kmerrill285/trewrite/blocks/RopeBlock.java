package kmerrill285.trewrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class RopeBlock extends CrossedBlock {


     public RopeBlock(boolean material, String name, String drop) {
		super(Properties.create(Material.EARTH).doesNotBlockMovement(), 0, 0, true, false, false, material, name, drop);
	}
     
     private static final VoxelShape field_220121_d = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 16.0D - 4.0D, 16.0D, 16.0D - 4.0D);
     private static final VoxelShape field_220123_f = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
     private static final VoxelShape field_220124_g = VoxelShapes.fullCube().withOffset(0.0D, -1.0D, 0.0D);
     
     public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
         return field_220121_d;
     }
     
	@Override public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, net.minecraft.entity.LivingEntity entity) { 
		
		return true; 
		}
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      return true;
	   }


}
