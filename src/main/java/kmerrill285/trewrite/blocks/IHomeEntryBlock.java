package kmerrill285.trewrite.blocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IHomeEntryBlock {
	  void open(World paramWorld, BlockPos paramBlockPos);
	  
	  void close(World paramWorld, BlockPos paramBlockPos);
}