package kmerrill285.trewrite.blocks;

import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaChest;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketOpenChestTerraria;
import kmerrill285.trewrite.core.network.client.CPacketRequestInventoryChest;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class DirtBlock extends BlockT {

	public DirtBlock(Properties properties) {
		super(properties, BlocksT.GROUND_HARDNESS, 10.0f, "dirt_block");
		this.pick = true;
		this.material = true;
	}

	public boolean canSupport(BlockState state) {
		if (state.getBlock().getDefaultState() == BlocksT.MUSHROOM.getDefaultState() ||
				state.getBlock().getDefaultState() == BlocksT.FLOWER.getDefaultState()) {
			return true;
		}
		return false;
	}
	
	public void onBlockClicked (BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		
	}
	
}
