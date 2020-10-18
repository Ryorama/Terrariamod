package kmerrill285.stackeddimensions.render;

import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker;

public class StackedRenderWorker extends ChunkRenderWorker {
   public StackedRenderWorker(ChunkRenderDispatcher chunkRenderDispatcherIn, RegionRenderCacheBuilder regionRenderCacheBuilderIn) {
      super(chunkRenderDispatcherIn, regionRenderCacheBuilderIn);
   }
}
