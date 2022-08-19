package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.client.TMusicTicker;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;
import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MusicTracker.class)
public class MusicTrackerMixin {
    private final Random random = new Random();
    private final MinecraftClient client;
    private SoundEvent currentMusic;
    private int timeUntilNextMusic = 100;
    private SoundEvent currentAmbient;
    private int timeUntilNextAmbient = 100;
    private boolean locked = false;
    private boolean lockedAmbient = false;

    public MusicTrackerMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci) {
        System.out.println("Terraria Music Ticker Running!");
        this.tickMusic();
        this.tickAmbient();
    }

    @Inject(at = @At("HEAD"), method = "play")
    public void play(MusicSound type, CallbackInfo ci) {
        if (!this.locked) {
            this.currentMusic = type.getSound();
            this.client.getMusicTracker().play(new MusicSound(this.currentMusic, 0, 1, true));
            this.timeUntilNextMusic = Integer.MAX_VALUE;
        }
    }

    public void playAmbient(TMusicTicker.AmbientTrack type) {
        if (!this.lockedAmbient) {
            this.currentAmbient = type.getSound();
            this.client.getMusicTracker().play(new MusicSound(this.currentAmbient, 0, 1, true));
            this.timeUntilNextAmbient = Integer.MAX_VALUE;
        }
    }

    @Inject(at = @At("HEAD"), method = "stop")
    public void stop(CallbackInfo ci) {
        if (this.currentMusic != null) {
            this.client.getMusicTracker().stop();
            this.currentMusic = null;
            this.timeUntilNextMusic = 0;
        }

        this.locked = false;
    }

    public void stopAmbient() {
        if (this.currentAmbient != null) {
            this.client.getMusicTracker().stop();
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

    public boolean isPlaying(TMusicTicker.MusicType type) {
        return this.currentMusic == null ? false : type.getSound().getId().equals(this.currentMusic.getId());
    }

    public boolean isPlayingAmbient(TMusicTicker.AmbientTrack type) {
        return this.currentAmbient == null ? false : type.getSound().getId().equals(this.currentAmbient.getId());
    }

    private TMusicTicker.AmbientTrack getAmbientTrackType() {
        return null;
    }

    private TMusicTicker.MusicType getMusicType() {
        if (this.client.player == null) {
            return TMusicTicker.MusicType.MENU;
        } else {
            if (MinecraftClient.getInstance().world != null) {
                World world = MinecraftClient.getInstance().world;

                if (EntityEyeOfCthulhu.isEyeAlive == true) {
                    return TMusicTicker.MusicType.BOSS1;
                }

                if (WorldDataT.bloodMoon) {
                    return TMusicTicker.MusicType.BLOODMOON;
                }

                if (this.client.player != null) {
                    BlockPos cameraPos = this.client.player.getCameraBlockPos();
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
                                    if (block == BlocksT.GRASS_BLOCK) {
                                        ++highlands;
                                    }

                                    if (block == BlocksT.SNOW) {
                                        ++snow;
                                    }

                                    if (block == BlocksT.CORRUPTED_GRASS_BLOCK) {
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

                                    if (block == BlocksT.STONE_BLOCK  && MinecraftClient.getInstance().player.getY() <= 20.0D) {
                                        ++cave;
                                    }

                                    if (block == BlocksT.EBONSTONE) {
                                        ++cave_corruption;
                                    }

                                    if (block == BlocksT.ASH && MinecraftClient.getInstance().player.getY() <= 180.0D) {
                                        ++underworld;
                                    }
                                }
                            }
                        }
                    }

                    if (underworld > 15) {
                        return TMusicTicker.MusicType.UNDERWORLD;
                    }

                    if (cave_corruption > 15 && MinecraftClient.getInstance().player.getY() <= 20.0D) {
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

                if (world.isRaining()) {
                    return TMusicTicker.MusicType.RAIN;
                }

                boolean night = !world.isDay();
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
                if (!musicticker$musictype.getSound().getId().equals(this.currentMusic.getId())) {
                    this.client.getMusicTracker().stop();
                    this.timeUntilNextMusic = MathHelper.nextInt(this.random, 0, musicticker$musictype.getMinDelay() / 2);
                }

                if (!this.client.getMusicTracker().isPlayingType(new MusicSound(this.currentMusic, 0, 1, true))) {
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
                if (!musicticker$musictype.getSound().getId().equals(this.currentAmbient.getId())) {
                    this.client.getMusicTracker().stop();
                    this.timeUntilNextAmbient = MathHelper.nextInt(this.random, 0, musicticker$musictype.getMinDelay() / 2);
                }

                if (!this.client.getMusicTracker().isPlayingType(new MusicSound(this.currentAmbient, 0, 1, true))) {
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
}
