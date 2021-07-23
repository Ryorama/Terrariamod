package kmerrill285.trewrite.core.sounds;

import java.util.Random;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowHead;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.LunaticCultist;
import kmerrill285.trewrite.entities.monsters.bosses.Plantera;
import kmerrill285.trewrite.entities.monsters.bosses.boc.EntityBrainOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerHead;
import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemBody;
import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemHead;
import kmerrill285.trewrite.entities.monsters.bosses.moon_lord.MoonLord;
import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronHead;
import kmerrill285.trewrite.entities.monsters.bosses.twins.Ratinizer;
import kmerrill285.trewrite.entities.monsters.bosses.twins.Spazmatism;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFlesh;
import kmerrill285.trewrite.entities.pillars.NebulaPillar;
import kmerrill285.trewrite.entities.pillars.SolarPillar;
import kmerrill285.trewrite.entities.pillars.StardustPillar;
import kmerrill285.trewrite.entities.pillars.VortexPillar;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TMusicTicker extends MusicTicker {
   private final Random random = new Random();
   private final Minecraft client;
   private ISound currentMusic;
   private int timeUntilNextMusic = 100;
   private ISound currentAmbient;
   private int timeUntilNextAmbient = 100;
   private boolean locked = false;
   private boolean lockedAmbient = false;   

   public TMusicTicker(Minecraft client) {
      super(client);
      this.client = client;
   }

   public void tick() {
      this.tickMusic();
      this.tickAmbient();
   }

   private TMusicTicker.AmbientTrack getAmbientTrackType() {
      return null;
   }

   private TMusicTicker.MusicType getMusicType() {
      if (this.client.player == null) {
         return TMusicTicker.MusicType.MENU;
      } else {
         if (Minecraft.getInstance().world != null) {
            World world = Minecraft.getInstance().world;

            if (MoonLord.isAlive == true) {
                return TMusicTicker.MusicType.MOON_LORD;
            }
            
            if (StardustPillar.isAlive == true) {
                return TMusicTicker.MusicType.LUNAR_EVENT;
            }
            
            if (VortexPillar.isAlive == true) {
                return TMusicTicker.MusicType.LUNAR_EVENT;
            }
            
            if (NebulaPillar.isAlive == true) {
                return TMusicTicker.MusicType.LUNAR_EVENT;
            }
            
            if (SolarPillar.isAlive == true) {
                return TMusicTicker.MusicType.LUNAR_EVENT;
            }
            
            if (LunaticCultist.isAlive == true) {
                return TMusicTicker.MusicType.GOLEM;
            }
            
            if (GolemHead.isAlive == true) {
                return TMusicTicker.MusicType.GOLEM;
            }
            
            if (GolemBody.isAlive == true) {
                return TMusicTicker.MusicType.GOLEM;
            }
            
            
            if (Plantera.isAlive == true) {
                return TMusicTicker.MusicType.PLANTERA;
            }
            
            if (EntityBrainOfCthulhu.isBocA == true) {
                return TMusicTicker.MusicType.BOSS3;
            }
            
            if (EntityDestroyerHead.isDesA == true) {
                return TMusicTicker.MusicType.BOSS3;
            }
            if (Ratinizer.isAlive == true) {
                return TMusicTicker.MusicType.BOSS2;
            }
            if (Spazmatism.isAlive == true) {
                 return TMusicTicker.MusicType.BOSS2;
            }                      
            if (EntityWallOfFlesh.isWoFA == true) {
               return TMusicTicker.MusicType.BOSS2;
            }
            
            if (EntitySkeletronHead.isAlive == true) {
                return TMusicTicker.MusicType.BOSS1;
             }

            if (EntityEyeOfCthulhu.isEyeAlive == true) {
               return TMusicTicker.MusicType.BOSS1;
            }

            if (EntityEowHead.isEOWAlive == true) {
               return TMusicTicker.MusicType.BOSS1;
            }

            if (WorldStateHolder.get(world).bloodmoon) {
               return TMusicTicker.MusicType.BLOODMOON;
            }

            if (this.client.player != null) {
               BlockPos cameraPos = this.client.player.getPosition();
               int corruption = 0;
               int highlands = 0;
               int dark = 0;
               int desert = 0;
               int mushroom = 0;
               int jungle = 0;
               int snow = 0;
               int beach = 0;
               int cave = 0;
               int cave_corruption = 0;
               int underworld = 0;
               int crimson = 0;
               int hallow = 0;
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

                              if (block == BlocksT.CORRUPT_GRASS || block == BlocksT.EBONSAND || block == BlocksT.PURPLE_ICE) {
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

                              if (block == BlocksT.STONE_BLOCK  && Minecraft.getInstance().player.posY <= 90.0D) {
                                 ++cave;
                              }
                              
                              if (block == BlocksT.EBONSTONE) {
                                 ++cave_corruption;
                              }

                              if (block == BlocksT.ASH_BLOCK && Minecraft.getInstance().player.posY <= 50.0D) {
                                 ++underworld;
                              }
                              if (block == BlocksT.CRIMSON_GRASS) {
                            	  ++crimson;
                              }
                              if (block == BlocksT.HALLOW_GRASS) {
                            	  ++hallow;
                              }
                              if (block == BlocksT.SAND && Minecraft.getInstance().player.posX >= 1800) {
                            	  ++beach;
                              }
                           }
                        }
                     }
                  }

                  if (underworld > 15) {
                     return TMusicTicker.MusicType.UNDERWORLD;
                  }
                  
                  if (cave_corruption > 15 && Minecraft.getInstance().player.posY <= 95.0D) {
                     return TMusicTicker.MusicType.UNDERGROUND_CORRUPTION;
                  }

                  if (cave > 15) {
                     return TMusicTicker.MusicType.UNDERGROUND;
                  }
                  
                  if (corruption > 15) {
                     return TMusicTicker.MusicType.CORRUPTION;
                  }
                  if (beach > 15) {
                      return TMusicTicker.MusicType.OCEAN;
                  }
                  
                  if (desert > 15) {
                     return TMusicTicker.MusicType.DESERT;
                  }

                  if (snow > 15) {
                     return TMusicTicker.MusicType.SNOW;
                  }
                  
                  if (crimson > 15) {
                      return TMusicTicker.MusicType.CRIMSON;
                  }
                  
                  if (hallow > 15) {
                      return TMusicTicker.MusicType.HALLOW;
                  } 
                  
                  if (jungle > 15) {
                      return TMusicTicker.MusicType.JUNGLE;
                  }
               }

            if (Trewrite.slimeRain) {
               return TMusicTicker.MusicType.SLIME_RAIN;
            }

            if (Trewrite.isMonsoon) {
               return TMusicTicker.MusicType.STORM;
            }

	            if (world.isRaining()) {
	                return TMusicTicker.MusicType.RAIN;
	            }

               boolean night = world.getDayTime() % 24000L > 15000L && world.getDayTime() % 24000L < 22000L;
               if (night) {
                  return TMusicTicker.MusicType.NIGHT;
               }

               return TMusicTicker.MusicType.DAY1;
            }
         }

         return null;
      }

   private void tickMusic() {
      TMusicTicker.MusicType musicticker$musictype = this.getMusicType();
      if (musicticker$musictype != null) {
         if (this.currentMusic != null) {
            if (!musicticker$musictype.getSound().getName().equals(this.currentMusic.getSoundLocation())) {
               this.client.getSoundHandler().stop(this.currentMusic);
               this.timeUntilNextMusic = MathHelper.nextInt(this.random, 0, musicticker$musictype.getMinDelay() / 2);
            }

            if (!this.client.getSoundHandler().isPlaying(this.currentMusic)) {
               this.currentMusic = null;
               this.timeUntilNextMusic = Math.min(MathHelper.nextInt(this.random, musicticker$musictype.getMinDelay(), musicticker$musictype.getMaxDelay()), this.timeUntilNextMusic);
            }
         }

         this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, musicticker$musictype.getMaxDelay());
         if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
            this.play(musicticker$musictype);
         }

      }
   }

   private void tickAmbient() {
      TMusicTicker.AmbientTrack musicticker$musictype = this.getAmbientTrackType();
      if (musicticker$musictype != null) {
         if (this.currentAmbient != null) {
            if (!musicticker$musictype.getSound().getName().equals(this.currentAmbient.getSoundLocation())) {
               this.client.getSoundHandler().stop(this.currentAmbient);
               this.timeUntilNextAmbient = MathHelper.nextInt(this.random, 0, musicticker$musictype.getMinDelay() / 2);
            }

            if (!this.client.getSoundHandler().isPlaying(this.currentAmbient)) {
               this.currentAmbient = null;
               this.timeUntilNextAmbient = Math.min(MathHelper.nextInt(this.random, musicticker$musictype.getMinDelay(), musicticker$musictype.getMaxDelay()), this.timeUntilNextAmbient);
            }
         }

         this.timeUntilNextAmbient = Math.min(this.timeUntilNextAmbient, musicticker$musictype.getMaxDelay());
         if (this.currentAmbient == null && this.timeUntilNextAmbient-- <= 0) {
            this.playAmbient(musicticker$musictype);
         }

      }
   }

   public void play(net.minecraft.client.audio.MusicTicker.MusicType type) {
      if (!this.locked) {
         this.currentMusic = SimpleSound.music(type.getSound());
         this.client.getSoundHandler().play(this.currentMusic);
         this.timeUntilNextMusic = Integer.MAX_VALUE;
      }
   }

   public void play(TMusicTicker.MusicType type) {
      if (!this.locked) {
         this.currentMusic = SimpleSound.music(type.getSound());
         this.client.getSoundHandler().play(this.currentMusic);
         this.timeUntilNextMusic = Integer.MAX_VALUE;
      }
   }

   public void playAmbient(TMusicTicker.AmbientTrack type) {
      if (!this.lockedAmbient) {
         this.currentAmbient = SimpleSound.music(type.getSound());
         this.client.getSoundHandler().play(this.currentAmbient);
         this.timeUntilNextAmbient = Integer.MAX_VALUE;
      }
   }

   public void stop() {
      if (this.currentMusic != null) {
         this.client.getSoundHandler().stop(this.currentMusic);
         this.currentMusic = null;
         this.timeUntilNextMusic = 0;
      }

      this.locked = false;
   }

   public void stopAmbient() {
      if (this.currentAmbient != null) {
         this.client.getSoundHandler().stop(this.currentAmbient);
         this.currentAmbient = null;
         this.timeUntilNextMusic = 0;
      }

      this.locked = false;
   }

   public void lock() {
      this.locked = true;
   }

   public void unlock() {
      this.locked = false;
   }

   public void lockAmbient() {
      this.lockedAmbient = true;
   }

   public void unlockAmbient() {
      this.lockedAmbient = false;
   }

   public boolean isPlaying(net.minecraft.client.audio.MusicTicker.MusicType type) {
      return this.currentMusic == null ? false : type.getSound().getName().equals(this.currentMusic.getSoundLocation());
   }

   public boolean isPlaying(TMusicTicker.MusicType type) {
      return this.currentMusic == null ? false : type.getSound().getName().equals(this.currentMusic.getSoundLocation());
   }

   public boolean isPlayingAmbient(TMusicTicker.AmbientTrack type) {
      return this.currentAmbient == null ? false : type.getSound().getName().equals(this.currentAmbient.getSoundLocation());
   }

   @OnlyIn(Dist.CLIENT)
   public static enum MusicType {
      MENU(TAudio.SoundEvents.MENU_MUSIC.getSound(), 20, 20),
      DAY1(TAudio.SoundEvents.DAY1.getSound(), 20, 20),
      NIGHT(TAudio.SoundEvents.NIGHT.getSound(), 20, 20),
      CORRUPTION(TAudio.SoundEvents.CORRUPTION.getSound(), 20, 20),
      SNOW(TAudio.SoundEvents.SNOW.getSound(), 20, 20),
      DESERT(TAudio.SoundEvents.DESERT.getSound(), 20, 20),
      UNDERGROUND(TAudio.SoundEvents.UNDERGROUND.getSound(), 20, 20),
      UNDERGROUND_CORRUPTION(TAudio.SoundEvents.UNDERGROUND_CORRUPTION.getSound(), 20, 20),
      BLOODMOON(TAudio.SoundEvents.BLOODMOON.getSound(), 20, 20),
      BOSS1(TAudio.SoundEvents.BOSS1.getSound(), 20, 20),
      BOSS2(TAudio.SoundEvents.BOSS2.getSound(), 20, 20),
      UNDERWORLD(TAudio.SoundEvents.UNDERWORLD.getSound(), 20, 20),
      SC(TAudio.SoundEvents.SC.getSound(), 20, 20),
	  HALLOW(TAudio.SoundEvents.HALLOW.getSound(), 20, 20),
	  CRIMSON(TAudio.SoundEvents.CRIMSON.getSound(), 20, 20),
	  OCEAN(TAudio.SoundEvents.OCEAN.getSound(), 20, 20),
	  JUNGLE(TAudio.SoundEvents.JUNGLE.getSound(), 20, 20),
	  BOSS3(TAudio.SoundEvents.BOSS3.getSound(), 20, 20),
	  LUNAR_EVENT(TAudio.SoundEvents.LUNAR_EVENT.getSound(), 20, 20),
	  MOON_LORD(TAudio.SoundEvents.MOON_LORD.getSound(), 20, 20),
	  PLANTERA(TAudio.SoundEvents.PLANTERA.getSound(), 20, 20),
	  GOLEM(TAudio.SoundEvents.GOLEM.getSound(), 20, 20),
	  RAIN(TAudio.SoundEvents.RAIN.getSound(), 20, 20),
      STORM(TAudio.SoundEvents.STORM.getSound(), 20, 20),
      SLIME_RAIN(TAudio.SoundEvents.SLIME_RAIN.getSound(), 20, 20);

      private final SoundEvent sound;
      private final int minDelay;
      private final int maxDelay;

      private MusicType(SoundEvent sound, int minDelayIn, int maxDelayIn) {
         this.sound = sound;
         this.minDelay = minDelayIn;
         this.maxDelay = maxDelayIn;
      }

      public SoundEvent getSound() {
         return this.sound;
      }

      public int getMinDelay() {
         return this.minDelay;
      }

      public int getMaxDelay() {
         return this.maxDelay;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static enum AmbientTrack {
      MENU(TAudio.SoundEvents.MENU_MUSIC.getSound(), 20, 20),
      DAY1(TAudio.SoundEvents.DAY1.getSound(), 20, 20),
      NIGHT(TAudio.SoundEvents.NIGHT.getSound(), 20, 20),
      CORRUPTION(TAudio.SoundEvents.CORRUPTION.getSound(), 20, 20),
      SNOW(TAudio.SoundEvents.SNOW.getSound(), 20, 20),
      DESERT(TAudio.SoundEvents.DESERT.getSound(), 20, 20);

      private final SoundEvent sound;
      private final int minDelay;
      private final int maxDelay;

      private AmbientTrack(SoundEvent sound, int minDelayIn, int maxDelayIn) {
         this.sound = sound;
         this.minDelay = minDelayIn;
         this.maxDelay = maxDelayIn;
      }

      public SoundEvent getSound() {
         return this.sound;
      }

      public int getMinDelay() {
         return this.minDelay;
      }

      public int getMaxDelay() {
         return this.maxDelay;
      }
   }
}
