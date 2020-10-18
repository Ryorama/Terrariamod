package kmerrill285.trewrite.blocks;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MeteoriteBlock extends BlockT {


	
	public MeteoriteBlock() {
		super(Properties.create(Material.EARTH).sound(SoundType.METAL), BlocksT.STONE_HARDNESS, 50, "meteorite");
		this.pick = true;
	}

	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof PlayerEntity) {
			if (!entityIn.world.isRemote) {
				PlayerEntity player = (PlayerEntity)entityIn;
				InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);
				if (inventory != null) {
					boolean hasSkull = false;
					for (int i = 0; i < inventory.accessory.length; i++) {
						InventorySlot slot = inventory.accessory[i];
						if (slot.stack != null) {
							if (slot.stack.item == ItemsT.OBSIDIAN_SKULL) {
								hasSkull = true;
								break;
							}
						}
					}
					if (!hasSkull) {
						entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 5);
					}
				}
			}
		} else {
			entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 5);
		}
	}
   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
	   if (entityIn instanceof PlayerEntity) {
			if (!entityIn.world.isRemote) {
				PlayerEntity player = (PlayerEntity)entityIn;
				InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);
				if (inventory != null) {
					boolean hasSkull = false;
					for (int i = 0; i < inventory.accessory.length; i++) {
						InventorySlot slot = inventory.accessory[i];
						if (slot.stack != null) {
							if (slot.stack.item == ItemsT.OBSIDIAN_SKULL) {
								hasSkull = true;
								break;
							}
						}
					}
					if (!hasSkull) {
						entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 5);
					}
				}
			}
		} else {
			entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 5);
		}
   }
   
   public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
	   super.onReplaced(state, worldIn, pos, newState, isMoving);
	   
   }
   	

	   public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	   }

	   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return true;
	   }

	   public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	   }

	   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
	      return false;
	   }

	   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
	      return true;
	   }
	
}
