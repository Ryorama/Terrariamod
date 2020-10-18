package kmerrill285.trewrite.items;

import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Axe extends ItemT {

	public Axe() {
		super(new Properties().group(ItemGroup.TOOLS).maxStackSize(1));
		this.melee = true;
		this.maxStack = 1;
		MODIFIER_TYPE = EnumModifierType.MELEE;
		
		this.animation = ItemT.AXE_ANIMATION;
		this.scale = 2.0;
	}

	public void onUse(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
//		if (pos != null && player != null) {
//			Block block = worldIn.getBlockState(pos).getBlock();
//			 
//			if (block.getDefaultState() == Blocks.DIRT.getDefaultState() || 
//					block.getDefaultState() == Blocks.GRASS_BLOCK.getDefaultState()) {
//				
//				net.minecraft.util.SoundEvent e = SoundEvents.ITEM_SHOVEL_FLATTEN;
//				player.swingArm(handIn);
//				
//				SoundType sound = block.getDefaultState().getSoundType(worldIn, pos, player);
//				worldIn.playSound(null, pos, e, SoundCategory.BLOCKS, sound.getVolume(), sound.getPitch());
//				
//				worldIn.setBlockState(pos, Blocks.GRASS_PATH.getDefaultState());
//			}
//		}
	}
	
	
	
}
