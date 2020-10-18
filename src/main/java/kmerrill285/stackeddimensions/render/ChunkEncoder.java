package kmerrill285.stackeddimensions.render;

import kmerrill285.stackeddimensions.configuration.DimensionConfigs;
import kmerrill285.stackeddimensions.configuration.DimensionConfiguration;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;

public class ChunkEncoder {
   public static void readIntoSection(Chunk chunk, int i, PacketBuffer buf) {
      if (chunk != null) {
         ChunkSection[] storage = chunk.getSections();
         int s = buf.readInt();
         int y = buf.readInt();
         if (s == 1) {
            if (storage[i] == Chunk.EMPTY_SECTION) {
               storage[i] = new ChunkSection(y);
            }

            storage[i].read(buf);
         } else {
            storage[i] = Chunk.EMPTY_SECTION;
         }

      }
   }

   public static void writeSection(Chunk chunk, int i, PacketBuffer buf) {
      if (chunk != null) {
         ChunkSection[] storage = chunk.getSections();
         if (storage[i] == Chunk.EMPTY_SECTION) {
            buf.writeInt(0);
         } else {
            buf.writeInt(1);
         }

         buf.writeInt(storage[i].getYLocation());
         if (storage[i] != Chunk.EMPTY_SECTION) {
            storage[i].write(buf);
         }

      }
   }

   public static void readIntoChunk(Chunk chunk, PacketBuffer buf) {
      if (chunk != null) {
         ChunkSection[] storage = chunk.getSections();
         int[] s = new int[storage.length];

         for(int i = 0; i < storage.length; ++i) {
            s[i] = buf.readInt();
         }

         int[] y = new int[storage.length];

         int i;
         for(i = 0; i < storage.length; ++i) {
            y[i] = buf.readInt();
         }

         for(i = 0; i < storage.length; ++i) {
            if (s[i] == 1) {
               if (storage[i] == Chunk.EMPTY_SECTION) {
                  storage[i] = new ChunkSection(y[i]);
               }

               storage[i].read(buf);
            } else {
               storage[i] = Chunk.EMPTY_SECTION;
            }
         }

      }
   }

   public static PacketBuffer encodeChunk(Chunk chunk, PacketBuffer buf) {
      ChunkSection[] storage = chunk.getSections();
      int min = 0;
      int max = 256;
      DimensionConfiguration config = DimensionConfigs.getConfig(chunk.getWorld().dimension.getType().getRegistryName());
      if (config != null) {
         min = config.getMin() + 256;
         max = config.getMax();
      }

      min = (int)Math.round((double)min / 16.0D);
      max = (int)Math.round((double)max / 16.0D);

      int i;
      for(i = 0; i < storage.length; ++i) {
         if (i != min && i != max - 1) {
            buf.writeInt(0);
         } else if (storage[i] != Chunk.EMPTY_SECTION) {
            buf.writeInt(1);
         } else {
            buf.writeInt(0);
         }
      }

      for(i = 0; i < storage.length; ++i) {
         if (i != min && i != max - 1) {
            buf.writeInt(0);
         } else if (storage[i] != Chunk.EMPTY_SECTION) {
            buf.writeInt(storage[i].getYLocation());
         } else {
            buf.writeInt(0);
         }
      }

      for(i = 0; i < storage.length; ++i) {
         if ((i == min || i == max - 1) && storage[i] != Chunk.EMPTY_SECTION) {
            storage[i].write(buf);
         }
      }

      return buf;
   }
}
