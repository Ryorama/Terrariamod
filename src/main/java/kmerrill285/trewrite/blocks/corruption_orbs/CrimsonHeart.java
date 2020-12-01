package kmerrill285.trewrite.blocks.corruption_orbs;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.CrossedBlock;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SpawnReason;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CrimsonHeart extends CrossedBlock implements IWaterLoggable  {

	
	public CrimsonHeart() {
		
		super(Properties.create(Material.EARTH).doesNotBlockMovement().lightValue(8), BlocksT.GROUND_HARDNESS, 15, false, false, true, false, "crimson_heart", "none");
	}
	
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		int orbs = WorldStateHolder.get(world).shadowOrbsMined;
		
		if (orbs == 0) {
			EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.MUSKET, 1));
			EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.MUSKET_BALL, 100));
		} else {
			int i = world.rand.nextInt(4);
			if (i == 0) {
				EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.MUSKET, 1));
				EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.MUSKET_BALL, 100));
			}
			if (i == 1) {
				EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.VILETHORN, 1));
			}
			if (i == 2) {
				EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.BALL_O_HURT, 1));
			}
			if (i == 3) {
				EntityItemT.spawnItem(world, pos, new ItemStackT(ItemsT.BAND_OF_STARPOWER, 1));
			}
		}
		
		if (!world.isRemote()) {
			if (orbs % 3 == 0) {
		    	world.getServer().getPlayerList().sendMessage(new StringTextComponent("A horrible chill goes down your spine...").applyTextStyles(TextFormatting.BLUE, TextFormatting.BOLD));
			}
			if (orbs % 3 == 1) {
		    	world.getServer().getPlayerList().sendMessage(new StringTextComponent("Screams echo around you...").applyTextStyles(TextFormatting.BLUE, TextFormatting.BOLD));
			}
			if (orbs % 3 == 2) {
				world.addEntity(EntitiesT.BOC.create(world, null, null, null, new BlockPos(Minecraft.getInstance().player.getPosition().getPosX() + 10, Minecraft.getInstance().player.getPosition().getPosY(), Minecraft.getInstance().player.getPosition().getPosZ() + 10), SpawnReason.EVENT, false, false));
			}
	    }
		
		WorldStateHolder.get(world).meteoriteSpawn &= world.getRandom().nextBoolean();
		WorldStateHolder.get(world).shadowOrbsMined++;
	}
	
	public IFluidState getFluidState(BlockState state) {
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	   }

}
