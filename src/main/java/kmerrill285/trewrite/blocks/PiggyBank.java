package kmerrill285.trewrite.blocks;

import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaChest;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketOpenChestTerraria;
import kmerrill285.trewrite.core.network.client.CPacketRequestInventoryChest;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class PiggyBank extends BasicDirectional {
//	Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
//	boolean hammer, boolean material, String name, String drop
	

	public PiggyBank(Properties properties, String name) {
		super(properties, 0, 0, true, true, true, false, name, name);
	}

	
// 	public CPacketRequestInventoryChest(String playername, String worldFile, String position) {
   public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	   
	   if (worldIn.isRemote)
		   if (Minecraft.getInstance() != null) {
				Minecraft mc = Minecraft.getInstance();
				if (mc.player != null) {
	       		NetworkHandler.INSTANCE.sendToServer(new CPacketOpenChestTerraria(mc.player.getScoreboardName()));
	       		NetworkHandler.INSTANCE.sendToServer(new CPacketRequestInventoryChest(mc.player.getScoreboardName(), Minecraft.getInstance().getIntegratedServer().getWorldName(), mc.player.getScoreboardName()+",piggy_bank"));
	       		ContainerTerrariaChest.position = mc.player.getScoreboardName()+",piggy_bank";
				}
			}
	   
	  
	   return true;
   }
	
}
