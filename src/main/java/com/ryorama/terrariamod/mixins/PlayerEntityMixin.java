package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.biomes.BiomeCorruption;
import com.ryorama.terrariamod.biomes.BiomeDesert;
import com.ryorama.terrariamod.biomes.BiomeJungle;
import com.ryorama.terrariamod.biomes.BiomePurity;
import com.ryorama.terrariamod.biomes.BiomeSnow;
import com.ryorama.terrariamod.client.TMusicTicker;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public boolean hasGivingStartingItems = false;
	
	public float newMaxHealth = 100;
	
	public PlayerEntity player;
		
	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo info) {
		
		if (MinecraftClient.getInstance().player != null && !hasGivingStartingItems) {
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
			this.setHealth(100);
			
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_PICKAXE, 1));
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_AXE, 1));
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_SHORTSWORD, 1));
			
			hasGivingStartingItems = true;
		}
		
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.getHungerManager().setFoodLevel(10);
			player = MinecraftClient.getInstance().player;
		}
		
		if (MinecraftClient.getInstance().world != null) {
			if (!TMusicTicker.bossMusicOverride) {
		        boolean day = MinecraftClient.getInstance().world.getTimeOfDay() >= 1000 && MinecraftClient.getInstance().world.getTimeOfDay() <= 13000;
		        if (player != null) {
		        	if (player.world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ())) == BiomePurity.PUTITY) {
				        if (player.getY() >= 10) {
				        	if (TMusicTicker.currentMusic != TAudio.DAYONE) {
					        	if (day) {
					        		TMusicTicker.getTrack(TAudio.DAYONE);
					        	}
							} else if (TMusicTicker.currentMusic != TAudio.NIGHT) {
								if (!day) {
									TMusicTicker.getTrack(TAudio.NIGHT);
								}
							}
						} else {
							if (player.getY() <= 10 && player.getY() >= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERGROUND) {
									TMusicTicker.getTrack(TAudio.UNDERGROUND);
								}
							} else if (player.getY() <= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
									TMusicTicker.getTrack(TAudio.UNDERWORLD);
								}
							}
						}
		        	} else if (player.world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ())) == BiomeCorruption.CORRUPTION) {
				        if (player.getY() >= 10) {
				        	if (TMusicTicker.currentMusic != TAudio.CORRUPTION) {
								TMusicTicker.getTrack(TAudio.CORRUPTION);
							}
						} else {
							if (player.getY() <= 10 && player.getY() >= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERGROUND_CORRUPTION) {
									TMusicTicker.getTrack(TAudio.UNDERGROUND_CORRUPTION);
								}
							} else if (player.getY() <= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
									TMusicTicker.getTrack(TAudio.UNDERWORLD);
								}
							}
						}
		        	} else if (player.world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ())) == BiomeDesert.DESERT) {
				        if (player.getY() >= 10) {
				        	if (TMusicTicker.currentMusic != TAudio.DESERT) {
								TMusicTicker.getTrack(TAudio.DESERT);
							}
						} else {
							if (player.getY() <= 10 && player.getY() >= -125) {
								if (TMusicTicker.currentMusic != TAudio.DESERT) {
									TMusicTicker.getTrack(TAudio.DESERT);
								}
							} else if (player.getY() <= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
									TMusicTicker.getTrack(TAudio.UNDERWORLD);
								}
							}
						}
		        	} else if (player.world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ())) == BiomeSnow.SNOW) {
				        if (player.getY() >= 10) {
				        	if (TMusicTicker.currentMusic != TAudio.SNOW) {
								TMusicTicker.getTrack(TAudio.SNOW);
							}
						} else {
							if (player.getY() <= 10 && player.getY() >= -125) {
								if (TMusicTicker.currentMusic != TAudio.SNOW) {
									TMusicTicker.getTrack(TAudio.SNOW);
								}
							} else if (player.getY() <= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
									TMusicTicker.getTrack(TAudio.UNDERWORLD);
								}
							}
						}
		        	} else if (player.world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ())) == BiomeJungle.JUNGLE) {
				        if (player.getY() >= 10) {
				        	if (TMusicTicker.currentMusic != TAudio.JUNGLE) {
								TMusicTicker.getTrack(TAudio.JUNGLE);
							}
						} else {
							if (player.getY() <= 10 && player.getY() >= -125) {
								if (TMusicTicker.currentMusic != TAudio.JUNGLE) {
									TMusicTicker.getTrack(TAudio.JUNGLE);
								}
							} else if (player.getY() <= -125) {
								if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
									TMusicTicker.getTrack(TAudio.UNDERWORLD);
								}
							}
						}
		        	}
		        } 
			}
		}
	}
	
	@Inject(at = @At("HEAD"), method = "requestRespawn")
	public void requestRespawn(CallbackInfo info) {
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
	}
	
	@Inject(at = @At("HEAD"), method = "writeCustomDataToNbt")
	public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
		nbt.putDouble("newMaxHealth", this.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH));
	}
	
	@Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
	public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(nbt.getDouble("newMaxHealth"));
		newMaxHealth = (float)nbt.getDouble("newMaxHealth");
	}
}
