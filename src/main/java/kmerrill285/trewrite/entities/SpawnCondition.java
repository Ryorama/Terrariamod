package kmerrill285.trewrite.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpawnCondition {
   public static double VERY_COMMON = 1.0D;
   public static double COMMON = 0.5D;
   public static double UNCOMMON = 0.1D;
   public static double RARE = 0.025D;
   public List blocks;
   public int minElevation;
   public int maxElevation;
   public double spawnChance;
   public boolean ocean;
   public static HashMap spawnConditions = new HashMap();

   public SpawnCondition(int minElevation, int maxElevation, double spawnChance, Block... blocks) {
      this.minElevation = minElevation;
      this.maxElevation = maxElevation;
      this.ocean = false;
      this.blocks = Arrays.asList(blocks);
      this.spawnChance = spawnChance;
   }

   public SpawnCondition(int minElevation, int maxElevation, boolean ocean, double spawnChance, Block... blocks) {
      this.minElevation = minElevation;
      this.maxElevation = maxElevation;
      this.ocean = ocean;
      this.blocks = Arrays.asList(blocks);
      this.spawnChance = spawnChance;
   }

   public static boolean canSpawn(EntityType entity, BlockPos pos, World world, Random rand) {
      SpawnCondition condition = (SpawnCondition)spawnConditions.get(entity);
      BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
      if (condition == null) {
         return false;
      } else {
         boolean spawnableBlock = false;

         for(int i = 0; i < 3; ++i) {
            if (world.getBlockState(pos2).getMaterial().blocksMovement()) {
               spawnableBlock = condition.blocks.contains(world.getBlockState(pos2).getBlock());
               break;
            }

            pos2 = pos2.down();
         }

         boolean spawn = pos.getY() >= condition.minElevation && pos.getY() <= condition.maxElevation && spawnableBlock && rand.nextDouble() <= condition.spawnChance;
         return condition.ocean && (new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ())).distanceTo(new Vec3d(0.0D, (double)pos.getY(), 0.0D)) >= 5000.0D ? spawn : spawn;
      }
   }
}
