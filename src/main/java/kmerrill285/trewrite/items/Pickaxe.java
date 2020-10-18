package kmerrill285.trewrite.items;

import java.util.List;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Pickaxe extends ItemT {

	public Pickaxe() {
		super(new Properties().group(ItemGroup.TOOLS).maxStackSize(1));
		this.melee = true;
		this.maxStack = 1;
		MODIFIER_TYPE = EnumModifierType.MELEE;
		this.animation = ItemT.PICKAXE_ANIMATION;
		this.scale = 2.0;
	}

	public void onUse(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		if (pos != null && player != null) {
			Block block = worldIn.getBlockState(pos).getBlock();
			if (block.getDefaultState() == BlocksT.GRASS_PATH.getDefaultState()) {
				List<LivingEntity> list = worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 1, pos.getY() + 3, pos.getZ()));
				
				for (LivingEntity en : list) {
					if (en.onGround == true && en.isAirBorne == false)
					en.move(MoverType.SHULKER_BOX, new Vec3d(0, 0.25f, 0.0f));
				}
				if (player.getPositionVector().distanceTo(new Vec3d(pos.getX(), pos.getY(), pos.getZ())) <= 2.0f) {
					player.move(MoverType.SHULKER_BOX, new Vec3d(0, 0.25f, 0.0f));
					player.posY+=0.25f;
				}
				//NetworkHooks.getEntitySpawningPacket
				
				net.minecraft.util.SoundEvent e = SoundEvents.ITEM_HOE_TILL;
				player.swingArm(handIn);
				
				SoundType sound = block.getDefaultState().getSoundType(worldIn, pos, player);
				worldIn.playSound(null, pos, e, SoundCategory.BLOCKS, sound.getVolume(), sound.getPitch());
				
				worldIn.setBlockState(pos, BlocksT.DIRT_BLOCK.getDefaultState());
				
				
			}
			 
			if (block.getDefaultState() == BlocksT.GRASS_BLOCK.getDefaultState() ||
					block.getDefaultState() == BlocksT.CORRUPT_GRASS.getDefaultState() ||
							block.getDefaultState() == BlocksT.HIGHLANDS_GRASS.getDefaultState() ||
									block.getDefaultState() == BlocksT.SAVANNA_GRASS.getDefaultState() ||
											block.getDefaultState() == BlocksT.PODZOL.getDefaultState()) {
				
				net.minecraft.util.SoundEvent e = SoundEvents.ITEM_HOE_TILL;
				player.swingArm(handIn);
				
				SoundType sound = block.getDefaultState().getSoundType(worldIn, pos, player);
				worldIn.playSound(null, pos, e, SoundCategory.BLOCKS, sound.getVolume(), sound.getPitch());
				
				worldIn.setBlockState(pos, BlocksT.DIRT_BLOCK.getDefaultState());
			}
			
			if (block.getDefaultState() == BlocksT.JUNGLE_GRASS.getDefaultState() ||
					block.getDefaultState() == BlocksT.BOG_GRASS.getDefaultState() ||
							block.getDefaultState() == BlocksT.MUSHROOM_GRASS.getDefaultState()) {
				
				net.minecraft.util.SoundEvent e = SoundEvents.ITEM_HOE_TILL;
				player.swingArm(handIn);
				
				SoundType sound = block.getDefaultState().getSoundType(worldIn, pos, player);
				worldIn.playSound(null, pos, e, SoundCategory.BLOCKS, sound.getVolume(), sound.getPitch());
				
				worldIn.setBlockState(pos, BlocksT.MUD.getDefaultState());
			}
		}
	}
	
	
	
}
