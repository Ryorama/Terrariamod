package kmerrill285.stackeddimensions.configuration;

import kmerrill285.stackeddimensions.Util;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;

public class DimensionConfiguration {
   public ResourceLocation dimension;
   public ResourceLocation above;
   public ResourceLocation below;
   private int min;
   private int max;

   public DimensionConfiguration(ResourceLocation dimension, ResourceLocation above, ResourceLocation below, int min, int max) {
      this.dimension = dimension;
      this.above = above;
      this.below = below;
      this.min = min;
      this.max = max;
   }

   public int getMin() {
      return this.min - 256;
   }

   public int getMax() {
      return this.max;
   }

   public DimensionType getAbove() {
      return this.above == null ? null : Util.getDimension(this.above);
   }

   public DimensionType getBelow() {
      return this.below == null ? null : Util.getDimension(this.below);
   }

   public DimensionType getDimension() {
      return this.dimension == null ? null : Util.getDimension(this.dimension);
   }
}
