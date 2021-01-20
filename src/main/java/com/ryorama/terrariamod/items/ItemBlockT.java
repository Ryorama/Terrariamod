package com.ryorama.terrariamod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.ryorama.terrariamod.blocks.BlockT;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.items.modifiers.EnumModifierType;

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
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.WorldEvents;

public class ItemBlockT extends ItemT {

	public Block block;
	
	public ItemBlockT(BlockT blockIn, String name) {
		super(new Properties().group(ItemGroup.BUILDING_BLOCKS));
		setRegistryName(blockIn.getRegistryName());
		this.maxStack = 999;
		this.block = blockIn;
		ItemsT.items.put(name, this);
		this.itemName = name;
		this.MODIFIER_TYPE = EnumModifierType.NONE;
		
		this.animation = ItemT.BUILDING_ANIMATION;
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
	   @Nullable
	   public BlockItemUseContext func_219984_b(BlockItemUseContext p_219984_1_) {
	      return p_219984_1_;
	   }

	   protected boolean onBlockPlaced(BlockPos p_195943_1_, World p_195943_2_, @Nullable PlayerEntity p_195943_3_, ItemStack p_195943_4_, BlockState p_195943_5_) {
	      return setTileEntityNBT(p_195943_2_, p_195943_3_, p_195943_1_, p_195943_4_);
	   }

	   protected boolean func_219987_d() {
		      return true;
		   }
	   protected boolean placeBlock(BlockItemUseContext p_195941_1_, BlockState p_195941_2_) {
//		   if (OverlayEvents.blockHit != null) {
//			   if (OverlayEvents.blockHit.getHitVec().y > 255) {
//				   p_195941_1_.getPos().subtract(p_195941_1_.getPos());
//				   p_195941_1_.getPos().add(OverlayEvents.blockHit.getHitVec().x, OverlayEvents.blockHit.getHitVec().y, OverlayEvents.blockHit.getHitVec().z);
//			   }
//		   }
		   
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