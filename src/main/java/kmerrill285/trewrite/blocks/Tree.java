package kmerrill285.trewrite.blocks;

import kmerrill285.trewrite.core.client.ParticleRegistry;
import kmerrill285.trewrite.core.client.particles.DustManager;
import kmerrill285.trewrite.core.client.particles.LeafParticle;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.AABBUtils;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftGame;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.system.CallbackI;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class Tree extends BasicPlant {

	public Random rand = new Random();

	public boolean dropped = false;

	public Tree(Properties properties, float hardness, float difficulty, boolean pick, boolean axe, boolean hammer,
			boolean material, String name, String drop) {
		super(properties.tickRandomly(), hardness, difficulty, pick, axe, hammer, material, name, drop);
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !this.isValidPosition(stateIn, worldIn, currentPos) ? breakBlock(worldIn.getWorld(), facingPos, facingState) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	public BlockState breakBlock(World worldIn, BlockPos pos, BlockState state) {
		 if (!worldIn.isRemote)
			 if (state != null)
				if (state.getBlock() instanceof BlockT) {
					if (ItemsT.getItemFromString(((BlockT)state.getBlock()).drop) != null) {
						Item drop = ItemsT.getItemFromString(((BlockT)state.getBlock()).drop);
						EntityItemT.spawnItem(worldIn, pos, new ItemStackT(drop, 1));
						
					}
				}
		 return BlocksT.AIR_BLOCK.getDefaultState();
	 }
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      BlockPos blockpos = pos.down();
	      BlockState BlockState = worldIn.getBlockState(blockpos);
	      Block block = BlockState.getBlock().getDefaultState().getBlock();
	      
	      if (block instanceof BlockT) {
	    	  boolean a = allowed.contains(((BlockT) block).name);
	    	  return a;
	      }
	      return BlockState.isSolid();
	   }

	   @Override
	   public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
			System.out.println(dropped);

		   int time = 200 + rand.nextInt(200);

		   AxisAlignedBB aabb = getDustSpreadAABB(true);
		   aabb = aabb.offset(pos);
		   Vec3d point = AABBUtils.randomPosWithin(aabb, rand), center = (new Vec3d((Vec3i)pos)).add(0.5D, 0.5D, 0.5D);

			if (rand.nextInt(5) == 0 && !dropped) {
				Item drop = ItemsT.WOOD; //Default item

				int dropIndex = rand.nextInt(6);

				if (dropIndex == 1) {
					drop = ItemsT.APPLE;
				} else if (dropIndex == 2) {
					drop = ItemsT.APRICOT;
				} else if (dropIndex == 3) {
					drop = ItemsT.PEACH;
				} else if (dropIndex == 4) {
					drop = ItemsT.WOOD;
				} else if (dropIndex == 5) {
					drop = ItemsT.ACORN;
				}

				EntityItemT.spawnItem(worldIn, new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ()), new ItemStackT(drop, 1));
				dropped = true;

				if (worldIn.isRemote) {
					for (int i = 0; i < 30; i++) {
						DustManager dustManager = new DustManager(worldIn, pos.getX(), pos.getY(), pos.getZ());
						dustManager.setWidthHeight(5);
						dustManager.setTextureName("leaf");
						dustManager.setGravity3d(0.0D, 0.05000000074505806D, 0.0D);
						dustManager.addWindToGravity(0.075f);
						dustManager.createParticle(AABBUtils.randomPosWithin(aabb, rand));
					}
				}
			} else {
				if (worldIn.isRemote) {
					for (int i = 0; i < 15; i++) {
						DustManager dustManager = new DustManager(worldIn, pos.getX(), pos.getY(), pos.getZ());
						dustManager.setWidthHeight(5);
						dustManager.setTextureName("leaf");
						dustManager.setGravity3d(0.0D, 0.05000000074505806D, 0.0D);
						dustManager.addWindToGravity(0.075f);
						dustManager.createParticle(AABBUtils.randomPosWithin(aabb, rand));
					}
				}
			}

		   	if (rand.nextInt(40) == 0 && dropped) {
				dropped = false;
		   	}
	   }

	   protected AxisAlignedBB getDustSpreadAABB(boolean randomEvent) {
			Vec3d start = new Vec3d(-0.75D, -0.4375D, -0.75D), end = start.add(2.5D, 2.0D, 2.5D);
			return new AxisAlignedBB(start.x, start.y, start.z, end.x, end.y, end.z);
		}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		AxisAlignedBB aabb = getDustSpreadAABB(true);
		aabb = aabb.offset(pos);
		Vec3d point = AABBUtils.randomPosWithin(aabb, rand), center = (new Vec3d((Vec3i)pos)).add(0.5D, 0.5D, 0.5D);

		if (worldIn.isRemote) {
			for (int i = 0; i < 30; i++) {
				DustManager dustManager = new DustManager(worldIn, pos.getX(), pos.getY(), pos.getZ());
				dustManager.setWidthHeight(5);
				dustManager.setTextureName("leaf");
				dustManager.setGravity3d(0.0D, 0.05000000074505806D, 0.0D);
				dustManager.addWindToGravity(0.075f);
				dustManager.createParticle(point);
			}
		}
	}

	public boolean getTickRandomly(BlockState state) {
		return true;
	}
}
