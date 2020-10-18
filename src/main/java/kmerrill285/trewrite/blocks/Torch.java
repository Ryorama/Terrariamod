package kmerrill285.trewrite.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Torch extends BlockT {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
	
	
	public Torch(String name, String drop) {
		super(Properties.create(Material.EARTH).sound(SoundType.WOOD).doesNotBlockMovement().lightValue(10), 0, 0, drop);
		this.pick = true;
		this.axe = true;
		this.hammer = true;
		this.setLocation(name);
	}
	
	public Torch(int light, String name, String drop) {
		super(Properties.create(Material.EARTH).sound(SoundType.WOOD).doesNotBlockMovement().lightValue(light), 0, 0, drop);
		this.pick = true;
		this.axe = true;
		this.hammer = true;
		this.setLocation(name);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return SHAPE;
	   }


	   public boolean isFullCube(BlockState state) {
	      return false;
	   }
	   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		      return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? breakBlock(worldIn.getWorld(), facingPos, facingState) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		   }
	   
	   

		   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		      return func_220055_a(worldIn, pos.down(), Direction.UP);
		   }
		   
		   @OnlyIn(Dist.CLIENT)
		   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		      double xx = (double)pos.getX() + 0.5D;
		      double yy = (double)pos.getY() + 0.7D;
		      double zz = (double)pos.getZ() + 0.5D;
		      worldIn.addParticle(ParticleTypes.SMOKE, xx, yy, zz, 0.0D, 0.0D, 0.0D);
		      worldIn.addParticle(ParticleTypes.FLAME, xx, yy, zz, 0.0D, 0.0D, 0.0D);
		   }
		   
		   public BlockRenderLayer getRenderLayer() {
			      return BlockRenderLayer.CUTOUT;
			   }
		   
//		   public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, BlockState state, BlockPos pos, Direction face) {
//			      return BlockFaceShape.UNDEFINED;
//			   }
}
