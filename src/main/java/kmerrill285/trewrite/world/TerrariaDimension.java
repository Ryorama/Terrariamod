package kmerrill285.trewrite.world;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;

public class TerrariaDimension extends OverworldDimension {
   public Vec3d currentColor = new Vec3d(0.0D, 0.0D, 0.0D);
   public Vec3d newColor = new Vec3d(0.0D, 0.0D, 0.0D);

   public TerrariaDimension(World worldIn, DimensionType typeIn) {
      super(worldIn, typeIn);
   }

   public Vec3d getFogColor(float celestialAngle, float partialTicks) {
      if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.posY < (double)Util.caveLevel) {
         return this.currentColor.mul(1.5D, 1.5D, 1.5D);
      } else if (this.world.getDayTime() % 24000L > 15000L && this.world.getDayTime() < 22000L) {
         return new Vec3d(0.0D, 0.0D, 0.0D);
      } else {
         Vec3d mix = this.currentColor.add(super.getFogColor(celestialAngle, partialTicks)).mul(1.5D, 1.5D, 1.5D);
         return new Vec3d(mix.x / 2.0D, mix.y / 2.0D, mix.z / 2.0D);
      }
   }

   public boolean isNether() {
      return false;
   }

   public MusicType getMusicType() {
      if (this.world.getDayTime() % 24000L > 15000L && this.world.getDayTime() < 22000L) {
         return MusicType.MENU;
      } else {
         return Minecraft.getInstance().player != null && Minecraft.getInstance().player.posY < (double)Util.caveLevel ? MusicType.NETHER : MusicType.CREATIVE;
      }
   }

   public Vec3d getSkyColor(BlockPos cameraPos, float partialTicks) {
      World world = this.getWorld();
      int corruption = 0;
      int highlands = 0;
      int dark = 0;
      int desert = 0;
      int mushroom = 0;
      int jungle = 0;
      int snow = 0;
      if (world != null) {
         for(int x = -15; x < 15; ++x) {
            for(int y = -15; y < 15; ++y) {
               for(int z = -15; z < 15; ++z) {
                  BlockPos pos2 = new BlockPos(cameraPos.getX() + x, cameraPos.getY() + y, cameraPos.getZ() + z);
                  Block block = world.getBlockState(pos2).getBlock();
                  if (block == BlocksT.HIGHLANDS_GRASS) {
                     ++highlands;
                  }

                  if (block == BlocksT.SNOW || block == BlocksT.ICE) {
                     ++snow;
                  }

                  if (block == BlocksT.PODZOL || block == BlocksT.DEEP_MUD || block == BlocksT.BOG_GRASS) {
                     ++dark;
                  }

                  if (block == BlocksT.CORRUPT_GRASS || block == BlocksT.EBONSTONE || block == BlocksT.EBONSAND || block == BlocksT.PURPLE_ICE) {
                     ++corruption;
                  }

                  if (block == BlocksT.JUNGLE_GRASS) {
                     ++jungle;
                  }

                  if (block == BlocksT.MUSHROOM_GRASS) {
                     ++mushroom;
                  }

                  if (block == BlocksT.SAND && (new Vec3d((double)cameraPos.getX(), (double)cameraPos.getY(), (double)cameraPos.getZ())).distanceTo(new Vec3d(0.0D, (double)cameraPos.getY(), 0.0D)) < 4500.0D) {
                     ++desert;
                  }
               }
            }
         }
      }

      this.newColor = super.getSkyColor(cameraPos, partialTicks);
      if (cameraPos.getY() > Util.skyLevel) {
         this.newColor = new Vec3d(0.0D, 0.0D, 0.0D);
      }

      if (desert > 15) {
         this.newColor = new Vec3d(0.75D, 0.75D, 0.5D);
      }

      if (highlands > 15) {
         this.newColor = new Vec3d(0.5D, 0.5D, 0.699999988079071D);
      }

      if (mushroom > 15) {
         this.newColor = new Vec3d(0.25D, 0.25D, 0.75D);
      }

      if (jungle > 15) {
         this.newColor = new Vec3d(0.5D, 0.5D, 0.25D);
      }

      if (dark > 15) {
         this.newColor = new Vec3d(0.25D, 0.25D, 0.125D);
      }

      if (corruption > 15) {
         this.newColor = new Vec3d(0.25D, 0.0D, 0.25D);
      }

      if (this.world.getDayTime() % 24000L > 15000L && this.world.getDayTime() < 22000L) {
         this.newColor = new Vec3d(0.0D, 0.0D, 0.0D);
      }

      if (cameraPos.getY() < Util.caveLevel) {
         this.newColor = new Vec3d(0.25D, 0.0D, 0.0D);
      }

      BlockPos rand = cameraPos.add(world.rand.nextInt(100) - 50, -30, world.rand.nextInt(100) - 50);
      long e = world.getGameTime() % 5L;
      if (!Minecraft.getInstance().isGamePaused() && world.rand.nextInt(10) == 0 && e == 0L) {
         for(int i = 0; i < 35; ++i) {
            BlockPos pos2 = new BlockPos(rand.getX(), rand.getY() + i, rand.getZ());
            float speed = 0.25F;
            if (world.getBlockState(new BlockPos(pos2.getX(), pos2.getY() + 1, pos2.getZ())).getBlock() == Blocks.AIR) {
               int a;
               if (world.getBlockState(pos2).getBlock() == BlocksT.CORRUPT_GRASS || world.getBlockState(pos2).getBlock() == BlocksT.EBONSTONE || world.getBlockState(pos2).getBlock() == BlocksT.EBONSAND) {
                  for(a = 0; a < world.rand.nextInt(10); ++a) {
                     world.addParticle(ParticleTypes.PORTAL, (double)pos2.getX() + world.rand.nextDouble() - 0.5D, (double)pos2.up().getY(), (double)pos2.getZ() + world.rand.nextDouble() - 0.5D, 0.0D, 0.10000000149011612D, 0.0D);
                  }
               }

               if (world.getBlockState(pos2).getBlock() == BlocksT.SAND || world.getBlockState(pos2).getBlock() == BlocksT.RED_SAND || world.getBlockState(pos2).getBlock() == BlocksT.EBONSAND) {
                  for(a = 0; a < world.rand.nextInt(10); ++a) {
                     world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos2.getX(), (double)pos2.up().getY(), (double)pos2.getZ(), (world.rand.nextDouble() - 0.5D) * (double)speed, 0.009999999776482582D, world.rand.nextDouble() * (double)speed + (double)a / 10.0D * (double)speed);
                  }
               }
            }
         }
      }

      double mul = 0.019999999552965164D;
      this.currentColor = new Vec3d(this.currentColor.x + (this.newColor.x - this.currentColor.x) * mul, this.currentColor.y + (this.newColor.y - this.currentColor.y) * mul, this.currentColor.z + (this.newColor.z - this.currentColor.z) * mul);
      return this.currentColor;
   }
}
