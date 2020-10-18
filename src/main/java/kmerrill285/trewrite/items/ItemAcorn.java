package kmerrill285.trewrite.items;

import java.util.List;

import javax.annotation.Nullable;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.Torch;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import kmerrill285.trewrite.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ItemAcorn extends ItemT {

	public Block block;
	
	public ItemAcorn(BlockT blockIn, String name) {
		super(new Properties().group(ItemGroup.BUILDING_BLOCKS));
		setRegistryName("acorn");
		this.maxStack = 999;
		this.block = blockIn;
		ItemsT.items.put(name, this);
		this.itemName = name;
		this.MODIFIER_TYPE = EnumModifierType.NONE;
	}

	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (this.getBlock() instanceof BlockT)
			
			
		if (((BlockT)this.getBlock()).health > 0)
			tooltip.add(new StringTextComponent("heals " + ((BlockT)this.getBlock()).health + " health"));
		if (((BlockT)this.getBlock()).mana > 0)
			tooltip.add(new StringTextComponent("restores " + ((BlockT)this.getBlock()).mana + " mana"));
		if (((BlockT)this.getBlock()).consumable)
			tooltip.add(new StringTextComponent("consumable"));
		if (((BlockT)this.getBlock()).material)
			tooltip.add(new StringTextComponent("material"));
		if (((BlockT)this.getBlock()).tooltip.isEmpty() == false)
			tooltip.add(new StringTextComponent(""+((BlockT)this.getBlock()).tooltip));
	}
	
	
	
	/**
	    * Called when this item is used when targetting a Block
	    */
	public ActionResultType onItemUse(ItemUseContext context) {
	      ActionResultType actionresulttype = this.tryPlace(new BlockItemUseContext(context));
	      return actionresulttype != ActionResultType.SUCCESS && this.isFood() ? this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType() : actionresulttype;
	   }
	public ActionResultType tryPlace(BlockItemUseContext p_195942_1_) {
	      if (!p_195942_1_.canPlace()) {
	         return ActionResultType.FAIL;
	      } else {
	         BlockItemUseContext blockitemusecontext = this.func_219984_b(p_195942_1_);
	         if (blockitemusecontext == null) {
	            return ActionResultType.FAIL;
	         } else {
	            BlockState blockstate = this.getStateForPlacement(blockitemusecontext);
	            if (blockstate == null) {
	               return ActionResultType.FAIL;
	            } else if (!this.placeBlock(blockitemusecontext, blockstate)) {
	               return ActionResultType.FAIL;
	            } else {
	               BlockPos blockpos = blockitemusecontext.getPos();
	               World world = blockitemusecontext.getWorld();
	               PlayerEntity playerentity = blockitemusecontext.getPlayer();
	               ItemStack itemstack = blockitemusecontext.getItem();
	               BlockState blockstate1 = world.getBlockState(blockpos);
	               Block block = blockstate1.getBlock();
	               if (block == BlocksT.PALM_TREE && this.block instanceof Torch) {
	            	   return ActionResultType.FAIL;
	               }
//	               if (block == blockstate.getBlock()) {
//	                  blockstate1 = this.func_219985_a(blockpos, world, itemstack, blockstate1);
//	                  this.onBlockPlaced(blockpos, world, playerentity, itemstack, blockstate1);
//	                  block.onBlockPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);
//	                  if (playerentity instanceof ServerPlayerEntity) {
//	                     CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos, itemstack);
//	                  }
//	               }

//	               SoundType soundtype = blockstate1.getSoundType(world, blockpos, p_195942_1_.getPlayer());
//	               world.playSound(playerentity, blockpos, this.func_219983_a(blockstate1), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
//	               itemstack.shrink(1);
	               SoundType soundtype = blockstate1.getSoundType(world, blockpos, p_195942_1_.getPlayer());
		            world.playSound(playerentity, blockpos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
		            if (world.isRemote && Util.terrariaInventory == false)
		            itemstack.shrink(1);
		            if (world.isRemote && Util.terrariaInventory == true)
		            if (ContainerTerrariaInventory.inventory.hotbar[ContainerTerrariaInventory.inventory.hotbarSelected].stack != null) {
			            ContainerTerrariaInventory.inventory.hotbar[ContainerTerrariaInventory.inventory.hotbarSelected].stack.size--;
			            if (ContainerTerrariaInventory.inventory.hotbar[ContainerTerrariaInventory.inventory.hotbarSelected].stack.size <= 0) {
			            	ContainerTerrariaInventory.inventory.hotbar[ContainerTerrariaInventory.inventory.hotbarSelected].stack = null;
			            }
		            }
		            if (!world.isRemote) {
		            	InventoryTerraria inventory = WorldEvents.getOrLoadInventory(playerentity);
		            	
		            	if (inventory.open == false) {
		            		itemstack.shrink(1);
		            	} else {
		            		if (inventory.hotbar[inventory.hotbarSelected].stack != null) {
					            inventory.hotbar[inventory.hotbarSelected].stack.size--;
					            if (inventory.hotbar[inventory.hotbarSelected].stack.size <= 0) {
					            	inventory.hotbar[inventory.hotbarSelected].stack = null;
					            }
				            }
		            	}
		            	
		            	
		            }
	               return ActionResultType.SUCCESS;
	            }
	         }
	      }
	   }

	   @Nullable
	   public BlockItemUseContext func_219984_b(BlockItemUseContext p_219984_1_) {
	      return p_219984_1_;
	   }

	   protected boolean onBlockPlaced(BlockPos p_195943_1_, World p_195943_2_, @Nullable PlayerEntity p_195943_3_, ItemStack p_195943_4_, BlockState p_195943_5_) {
	      return setTileEntityNBT(p_195943_2_, p_195943_3_, p_195943_1_, p_195943_4_);
	   }

	   @Nullable
	   protected BlockState getStateForPlacement(BlockItemUseContext p_195945_1_) {
	      BlockState BlockState = this.getBlock().getStateForPlacement(p_195945_1_);
	      return BlockState != null && this.canPlace(p_195945_1_, BlockState) ? BlockState : null;
	   }

	   protected boolean canPlace(BlockItemUseContext p_195944_1_, BlockState p_195944_2_) {
		      PlayerEntity playerentity = p_195944_1_.getPlayer();
		      ISelectionContext iselectioncontext = playerentity == null ? ISelectionContext.dummy() : ISelectionContext.forEntity(playerentity);
		      return (!this.func_219987_d() || p_195944_2_.isValidPosition(p_195944_1_.getWorld(), p_195944_1_.getPos())) && p_195944_1_.getWorld().func_217350_a(p_195944_2_, p_195944_1_.getPos(), iselectioncontext);
		   }
	   protected boolean func_219987_d() {
		      return true;
		   }
	   protected boolean placeBlock(BlockItemUseContext p_195941_1_, BlockState p_195941_2_) {
	      return p_195941_1_.getWorld().setBlockState(p_195941_1_.getPos(), p_195941_2_, 11);
	   }

	   public static boolean setTileEntityNBT(World worldIn, @Nullable PlayerEntity player, BlockPos pos, ItemStack stackIn) {
	      MinecraftServer minecraftserver = worldIn.getServer();
	      if (minecraftserver == null) {
	         return false;
	      } else {
	    	  CompoundNBT nbttagcompound = stackIn.getChildTag("BlockEntityTag");
	         if (nbttagcompound != null) {
	            TileEntity tileentity = worldIn.getTileEntity(pos);
	            if (tileentity != null) {
	               if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (player == null || !player.canUseCommandBlock())) {
	                  return false;
	               }

	               CompoundNBT nbttagcompound1 = tileentity.write(new CompoundNBT());
	               CompoundNBT nbttagcompound2 = nbttagcompound1.copy();
	               nbttagcompound1.merge(nbttagcompound);
	               nbttagcompound1.putInt("x", pos.getX());
	               nbttagcompound1.putInt("y", pos.getY());
	               nbttagcompound1.putInt("z", pos.getZ());
	               if (!nbttagcompound1.equals(nbttagcompound2)) {
	                  tileentity.read(nbttagcompound1);
	                  tileentity.markDirty();
	                  return true;
	               }
	            }
	         }

	         return false;
	      }
	   }

	   /**
	    * Returns the unlocalized name of this item.
	    */
	   public String getTranslationKey() {
	      return this.getBlock().getTranslationKey();
	   }

	   /**
	    * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	    */
	   public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
	      if (this.isInGroup(group)) {
	         this.getBlock().fillItemGroup(group, items);
	      }

	   }
	   
	   public Block getBlock() {
		   return this.block;
	   }
}
