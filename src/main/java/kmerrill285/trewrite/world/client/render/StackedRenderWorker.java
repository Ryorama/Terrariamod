package kmerrill285.trewrite.world.client.render;

import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.ChunkRenderTask;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker;

public class StackedRenderWorker extends ChunkRenderWorker {

	public StackedRenderWorker(ChunkRenderDispatcher chunkRenderDispatcherIn,
			RegionRenderCacheBuilder regionRenderCacheBuilderIn) {
		super(chunkRenderDispatcherIn, regionRenderCacheBuilderIn);
	}
	

}
