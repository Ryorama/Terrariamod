package kmerrill285.stackeddimensions.render;

import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BooleanSupplier;
import javax.annotation.Nullable;
import net.minecraft.client.multiplayer.ClientChunkProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.lighting.WorldLightManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkEvent.Load;

@OnlyIn(Dist.CLIENT)
public class StackedChunkProvider extends ClientChunkProvider {
   public static Biome[] biomes = new Biome[256];
   public StackedChunkProvider.ChunkArray array;
   public RenderWorld world;
   public Chunk empty = null;
   private final WorldLightManager lightManager;

   public StackedChunkProvider(RenderWorld renderWorld, int viewDistance) {
      super(renderWorld, viewDistance);

      for(int i = 0; i < 256; ++i) {
         biomes[i] = Biomes.DEFAULT;
      }

      this.world = renderWorld;
      this.array = new StackedChunkProvider.ChunkArray(adjustViewDistance(viewDistance));
      this.empty = new EmptyChunk(renderWorld, new ChunkPos(0, 0));
      this.lightManager = new WorldLightManager(this, true, renderWorld.getDimension().hasSkyLight());
   }

   private static int adjustViewDistance(int p_217254_0_) {
      return Math.max(2, p_217254_0_) + 3;
   }

   public void unloadChunk(int x, int z) {
      if (this.array.inView(x, z)) {
         int i = this.array.getIndex(x, z);
         Chunk chunk = this.array.get(i);
         if (this.isValid(chunk, x, z)) {
            this.array.unload(i, chunk, (Chunk)null);
         }
      }

   }

   @Nullable
   public Chunk func_217250_a(World world, int x, int z, PacketBuffer buf, CompoundNBT nbt, int sections, boolean load) {
      if (!this.array.inView(x, z)) {
         System.out.println("chunk is not in view!");
         return null;
      } else {
         int i = this.array.getIndex(x, z);
         Chunk chunk = (Chunk)this.array.chunks.get(i);
         if (!this.isValid(chunk, x, z)) {
            if (!load) {
               System.out.println("not enough data");
               return null;
            }

            chunk = new Chunk(world, new ChunkPos(x, z), biomes);
            this.array.replace(i, chunk);
         } else {
            this.array.replace(i, chunk);
         }

         ChunkSection[] storage = chunk.getSections();
         WorldLightManager lightmanager = this.getLightManager();
         lightmanager.func_215571_a(new ChunkPos(x, z), true);

         for(int j = 0; j < storage.length; ++j) {
            ChunkSection chunksection = storage[j];
            lightmanager.updateSectionStatus(SectionPos.of(x, j, z), ChunkSection.isEmpty(chunksection));
         }

         return chunk;
      }
   }

   private boolean isValid(Chunk chunk, int x, int z) {
      return chunk != null && chunk.getPos().x == x && chunk.getPos().z == z;
   }

   public IBlockReader getWorld() {
      return this.world;
   }

   public Chunk getChunk(int chunkX, int chunkZ, ChunkStatus requiredStatus, boolean load) {
      if (this.array.inView(chunkX, chunkZ)) {
         Chunk chunk = this.array.get(this.array.getIndex(chunkX, chunkZ));
         if (this.isValid(chunk, chunkX, chunkZ)) {
            MinecraftForge.EVENT_BUS.post(new Load(chunk));
            return chunk;
         }
      }

      return load ? this.empty : null;
   }

   public void tick(BooleanSupplier hasTimeLeft) {
   }

   public String makeString() {
      return "Streng";
   }

   public ChunkGenerator getChunkGenerator() {
      return null;
   }

   public WorldLightManager getLightManager() {
      return this.lightManager;
   }

   @OnlyIn(Dist.CLIENT)
   public class ChunkArray {
      public final AtomicReferenceArray<Chunk> chunks;
      public final int viewDistance;
      public final int sideLength;
      public volatile int centerX;
      public volatile int centerZ;
      public int loaded;

      public ChunkArray(int p_i50568_2_) {
         this.viewDistance = p_i50568_2_;
         this.sideLength = p_i50568_2_ * 2 + 1;
         this.chunks = new AtomicReferenceArray<Chunk>(this.sideLength * this.sideLength);
      }

      public int getIndex(int x, int z) {
         return Math.floorMod(z, this.sideLength) * this.sideLength + Math.floorMod(x, this.sideLength);
      }

      public void replace(int p_217181_1_, @Nullable Chunk p_217181_2_) {
         Chunk chunk = (Chunk)this.chunks.getAndSet(p_217181_1_, p_217181_2_);
         if (chunk != null) {
            --this.loaded;
            StackedChunkProvider.this.world.onChunkUnloaded(chunk);
         }

         if (p_217181_2_ != null) {
            ++this.loaded;
         }

      }

      public Chunk unload(int i, Chunk chunk, @Nullable Chunk p_217190_3_) {
         if (this.chunks.compareAndSet(i, chunk, p_217190_3_) && p_217190_3_ == null) {
            --this.loaded;
         }

         StackedChunkProvider.this.world.onChunkUnloaded(chunk);
         return chunk;
      }

      public boolean inView(int p_217183_1_, int p_217183_2_) {
         return Math.abs(p_217183_1_ - this.centerX) <= this.viewDistance * 16 && Math.abs(p_217183_2_ - this.centerZ) <= this.viewDistance * 16;
      }

      @Nullable
      public Chunk get(int p_217192_1_) {
         return (Chunk)this.chunks.get(p_217192_1_);
      }
   }
}
