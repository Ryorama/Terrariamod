package kmerrill285.trewrite.world.dimension;

import kmerrill285.stackeddimensions.blocks.BlockRegistry;
import kmerrill285.stackeddimensions.networking.SPacketRefreshDimensionRenderer;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@EventBusSubscriber(modid="trewrite", bus=Bus.FORGE)
public class Dimensions {

	public static DimensionType THE_SKY;
	public static DimensionType UNDERGROUND;
	public static DimensionType THE_UNDERWORLD;
	
	public static final ResourceLocation skyLocation = new ResourceLocation("trewrite", "sky");
	public static final ResourceLocation undergroundLocation = new ResourceLocation("trewrite", "underground");
	public static final ResourceLocation underworldLocation = new ResourceLocation("trewrite", "the_underworld");

	@SubscribeEvent
	public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
		if (DimensionType.byName(skyLocation) == null)
		{
			Dimensions.THE_SKY = DimensionManager.registerDimension(skyLocation, DimensionRegistry.skyDimension, null, true);
			Dimensions.UNDERGROUND = DimensionManager.registerDimension(undergroundLocation, DimensionRegistry.undergroundDimension, null, false);
			Dimensions.THE_UNDERWORLD = DimensionManager.registerDimension(underworldLocation, DimensionRegistry.underworldDimension, null, false);
		}
	}
	
	// HOW TO GET TO YOUR DIMENSION

	public static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationType, BlockPos destinationPos)
	{
		if (WorldEvents.getOrLoadInventory(player) != null)
		if (WorldEvents.getOrLoadInventory(player).canSave == false) {
			System.out.println("INVENTORY LOCKED FOR " + player);
		} else {
			if (player.getEntityWorld() instanceof ServerWorld) {
				ServerWorld s = (ServerWorld)player.getEntityWorld();
				WorldEvents.getOrLoadInventory(player).save(player.getScoreboardName(), s.getServer().getFolderName());
			}
		}
		
		
		double iposX = player.posX + 0;
		double iposZ = player.posZ + 0;
		float fallDistance = player.fallDistance;
		ServerWorld nextWorld = player.getServer().getWorld(destinationType);
		nextWorld.getChunk(destinationPos);	// make sure the chunk is loaded
		
		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SPacketRefreshDimensionRenderer());
		player.teleport(nextWorld, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), player.rotationYaw, player.rotationPitch);

		
		player.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());
		if (player.getPosition().getY() == 0) {
			player.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY()+1, destinationPos.getZ());
		}
		BlockPos pos2 = new BlockPos(destinationPos.getX(), 0, destinationPos.getZ());
		if (nextWorld.getBlockState(pos2) != null) {
			if (nextWorld.getBlockState(pos2).getBlock() == BlocksT.AIR_BLOCK ||
					nextWorld.getBlockState(pos2).getBlock() == Blocks.CAVE_AIR) {
				if (!player.isSpectator())
				nextWorld.setBlockState(pos2, BlockRegistry.DIMENSION_BLOCK.getDefaultState());
			}
		} else {
			if (!player.isSpectator())
			nextWorld.setBlockState(pos2, BlockRegistry.DIMENSION_BLOCK.getDefaultState());
		}
		BlockPos A = player.getPosition();
		if (nextWorld.getBlockState(A).getMaterial().blocksMovement()) {
			nextWorld.setBlockState(A, BlocksT.AIR_BLOCK.getDefaultState());
		}
		A = A.up();
		if (A.getY() <= 256)
		if (nextWorld.getBlockState(A).getMaterial().blocksMovement()) {
			nextWorld.setBlockState(A, BlocksT.AIR_BLOCK.getDefaultState());
		}
		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SPacketRefreshDimensionRenderer());
		player.attemptTeleport(iposX, destinationPos.getY() + destinationPos.getY() < 50 ? 1 : 0, iposZ, true);

	}
}