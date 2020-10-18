package kmerrill285.stackeddimensions.render;

import java.lang.reflect.Field;
import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.Util;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.chunk.ChunkRender;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;

public class StackedRenderDispatcher extends ChunkRenderDispatcher {
   public StackedRenderDispatcher(boolean p_i51518_1_) {
      super(p_i51518_1_);
   }

   public boolean updateChunkNow(ChunkRender chunkRenderer) {
      try {
         Field f = ChunkRenderDispatcher.class.getDeclaredField(StackedDimensions.DEBUG ? "renderWorker" : "field_178525_i");
         Util.makeFieldAccessible(f);
         f.set(this, new StackedRenderWorker(this, new RegionRenderCacheBuilder()));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      return super.updateChunkNow(chunkRenderer);
   }
}
