package kmerrill285.trewrite.util;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

import kmerrill285.stackeddimensions.blocks.BlockRegistry;
import kmerrill285.stackeddimensions.networking.SPacketRefreshDimensionRenderer;
import kmerrill285.stackeddimensions.networking.SPacketSendChunk;
import kmerrill285.trewrite.core.network.NetworkHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.network.PacketDistributor;

public class Util {
	
	public static ResourceLocation dimension, above, below;
	
   public static boolean stacked_dimensions = false;
   public static float terminalVelocity = 51.0F;
   public static int renderMana;
   public static int renderMaxMana;
   public static int renderCoins;
   public static int renderPotionSickness;
   public static int dayTime;
   public static int renderManaSickness;
   public static int renderManaSicknessEffect;
   public static String watchTime = "";
   public static boolean terrariaInventory = true;
   public static boolean justClosedInventory = false;
   public static boolean resetRecipes = false;
   public static int projectileCooldown = 0;
   public static double entitySpawnRate = 0.01D;
   public static double minSpawnDistance = 15.0D;
   public static int surfaceLevel = 100;
   public static int skyLevel = 175;
   public static int caveLevel = 65;
   public static int underworldLevel = 0;
   public static double starChance = 0.005625D;
   public static boolean refreshDimensionRenderer;
   public static ArrayList<SPacketSendChunk> chunksend = new ArrayList<SPacketSendChunk>();
   public static RayTraceResult blockHit;
   public static boolean INVERSE_GRAVITY = false;
   public static long LAST_SPACETAP = 0L;
   public static int renderBuild;
   public static int renderCalming;
   public static int renderIronskin;
   public static int renderSwiftness;
   public static int renderNightOwl;
   public static int renderGills;
   public static int renderRegeneration;
   public static int renderMining;
   public static int renderArchery;
   public static int renderHunter;
   public static int renderFeatherfall;
   public static int renderFlipper;
   public static int renderGravitation;
   public static int renderHeartreach;
   public static int renderInvisibility;
   public static int renderThorns;
   public static int renderWaterWalking;
   public static int renderShine;
   public static int renderBattle;
   public static int renderObsidianSkin;
   public static int renderMagicPower;
   public static int renderManaRegeneration;
   public static int renderTitan;
   public static int renderWeakDebuff;
   public static int renderHorrified;

   public static boolean isChristmas() {
      return LocalDateTime.now().getMonth() == Month.DECEMBER && LocalDateTime.now().getDayOfMonth() >= 15 && LocalDateTime.now().getDayOfMonth() <= 31;
   }

   public static int randomValue(int min, int max, Random random) {
      return random.nextInt(max - min) + min;
   }

   public static int randomValue(int min, int max) {
      return (new Random()).nextInt(max - min) + min;
   }

   public static void makeFieldAccessible(Field field) throws Exception {
      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);

      try {
         modifiers.setInt(field, field.getModifiers() & -17);
         modifiers.setInt(field, field.getModifiers() & -5);
         modifiers.setInt(field, field.getModifiers() | 1);
      } catch (IllegalArgumentException var3) {
         var3.printStackTrace();
      } catch (IllegalAccessException var4) {
         var4.printStackTrace();
      }

   }
   
   public static DimensionType getAbove() {
		if (above == null) return null;
		return Util.getDimension(above);
	}
	
	public static DimensionType getBelow() {
		if (below == null) return null;
		return Util.getDimension(below);
	}

   public static String getTimerString(int seconds) {
      int mins = seconds / 60;
      int secs = seconds - mins * 60;
      return mins + ":" + secs;
   }

   public static double getAngle(int x, int y) {
      return 4.71238898038469D - Math.atan2((double)y, (double)x);
   }

   public static double lerp(double a, double b, double f) {
      return a * (1.0D - f) + b * f;
   }

   public static String getSpeedString(int speed) {
      String sp = "Insanely Fast Speed";
      if (speed >= 0) {
         sp = "Insanely Fast Speed";
      }

      if (speed >= 9) {
         sp = "Very Fast Speed";
      }

      if (speed >= 21) {
         sp = "Fast Speed";
      }

      if (speed >= 26) {
         sp = "Average Speed";
      }

      if (speed >= 31) {
         sp = "Slow Speed";
      }

      if (speed >= 36) {
         sp = "Very Slow Speed";
      }

      if (speed >= 46) {
         sp = "Extremely Slow Speed";
      }

      if (speed >= 56) {
         sp = "Snail Speed";
      }

      return sp;
   }

   public static String getKnockbackString(float knockback) {
      String sp = "No Knockback";
      if (knockback > 11.0F) {
         sp = "Insane Knockback";
      }

      if (knockback <= 11.0F) {
         sp = "Extremely Strong Knockback";
      }

      if (knockback <= 9.0F) {
         sp = "Very Strong Knockback";
      }

      if (knockback <= 7.0F) {
         sp = "Strong Knockback";
      }

      if (knockback <= 6.0F) {
         sp = "Average Knockback";
      }

      if (knockback <= 4.0F) {
         sp = "Weak Knockback";
      }

      if (knockback <= 3.0F) {
         sp = "Very Weak Knockback";
      }

      if (knockback <= 1.5F) {
         sp = "Extremely Weak Knockback";
      }

      if (knockback == 0.0F) {
         sp = "No Knockback";
      }

      return sp;
   }
	
	public static DimensionType getDimension(ResourceLocation location) {
		return DimensionManager.getRegistry().getValue(location).orElse(null);
	}
	
	public static EntityType<?> getEntity(ResourceLocation location) {
		return Registry.ENTITY_TYPE.getValue(location).orElse(null);
	}
	
	public static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationType, BlockPos destinationPos)
	{
		
		double iposX = player.posX + 0;
		double iposZ = player.posZ + 0;
		
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
			if (nextWorld.getBlockState(pos2).getBlock() == Blocks.AIR ||
					nextWorld.getBlockState(pos2).getBlock() == Blocks.CAVE_AIR) {
				nextWorld.setBlockState(pos2, BlockRegistry.DIMENSION_BLOCK.getDefaultState());
			}
		} else {
			nextWorld.setBlockState(pos2, BlockRegistry.DIMENSION_BLOCK.getDefaultState());
		}
		NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SPacketRefreshDimensionRenderer());
		player.attemptTeleport(iposX, destinationPos.getY() + destinationPos.getY() < 50 ? 1 : 0, iposZ, true);
	}

}
