package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.callbacks.PlayerEquipArmorCallback;
import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;
import com.ryorama.terrariamod.items.ItemT;
import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.client.TMusicTicker;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.weather.WeatherBase;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	private List<PlayerEquipArmorCallback> playerEquipArmorCallbacks = new ArrayList<>();
	public float newMaxHealth = 100;
	
	private int ticks = 0;

	public boolean firstUpdate = false;
	
	public PlayerEntity player;

	public int tmpMana = 20;
	public int tmpMaxMana = 20;

	public boolean droppedTombstoneForDeath = false;

	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo info) {

		ticks++;

		if (MinecraftClient.getInstance().player != null && !WorldDataT.hasStartingTools) {
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
			this.setHealth(100);

			MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), 20);
			MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA), 20);

			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_PICKAXE, 1));
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_AXE, 1));
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_SHORTSWORD, 1));
			
			WorldDataT.hasStartingTools = true;
		}
		
		if (MinecraftClient.getInstance().player != null) {

			if (!firstUpdate && WorldDataT.hasStartingTools) {
				MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), tmpMana);
				MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA), tmpMaxMana);
				firstUpdate = true;
			}

			if (MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) < MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA))) {
				MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) + 1);
			}

			MinecraftClient.getInstance().player.getHungerManager().setFoodLevel(10);
			player = MinecraftClient.getInstance().player;
		}
		
		if (MinecraftClient.getInstance().world != null) {
			if (!TMusicTicker.bossMusicOverride) {
		        boolean day = MinecraftClient.getInstance().world.getTimeOfDay() >= 1000 && MinecraftClient.getInstance().world.getTimeOfDay() <= 13000;
		        if (player != null) {
		        	/*
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
		        	*/
		        	
		        	if (player != null) {
		        		 BlockPos cameraPos = this.player.getBlockPos();
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
                             if (ticks % 100 == 0) {
			                     for(int x = -15; x < 15; ++x) {
			                        for(int y = -15; y < 15; ++y) {
			                           for(int z = -15; z < 15; ++z) {
			                              BlockPos pos2 = new BlockPos(cameraPos.getX() + x, cameraPos.getY() + y, cameraPos.getZ() + z);
				                              if (world.isChunkLoaded(pos2)) {
			                      
				                              Block block = world.getBlockState(pos2).getBlock();
				                              
				                              if (block == BlocksT.GRASS_BLOCK) {
				                            	  ++highlands;
				                              }
				                              
				                              if (block == BlocksT.SNOW) {
				                                  ++snow;
				                              }
				                              
				                              if (block == BlocksT.STONE_BLOCK || block == BlocksT.GRANITE && player.getPos().y <= 10) {
				                            	  ++cave;
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
				                              if (block == BlocksT.ASH && player.getPos().y <= -125) {
				                                  ++underworld;
				                              }
				                           }
				                        }
				                     }
				                 }
				                 
				                 if (!TMusicTicker.bossMusicOverride) {
					                 if (underworld > 15 && TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
					                	 if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
						                	 TMusicTicker.getTrack(TAudio.UNDERWORLD);
					                	 }
					                 }
					                  
					                 if (cave_corruption > 15 && player.getPos().y <= 10 && TMusicTicker.currentMusic != TAudio.UNDERGROUND_CORRUPTION) {
					                	 if (TMusicTicker.currentMusic != TAudio.UNDERGROUND_CORRUPTION) {
						                	 TMusicTicker.getTrack(TAudio.UNDERGROUND_CORRUPTION);
					                	 }
					                 }
			
					                 if (cave > 15 && TMusicTicker.currentMusic != TAudio.UNDERGROUND && player.getPos().y <= 10) {
					                	 if (TMusicTicker.currentMusic != TAudio.UNDERGROUND) {
						                	 TMusicTicker.getTrack(TAudio.UNDERGROUND);
					                	 }
					                 }
					                  
					                 if (corruption > 15 && TMusicTicker.currentMusic != TAudio.CORRUPTION) {
					                	 if (TMusicTicker.currentMusic != TAudio.CORRUPTION) {
						                	 TMusicTicker.getTrack(TAudio.CORRUPTION);
					                	 }
					                 }
					                 
					                 if (snow > 15 && TMusicTicker.currentMusic != TAudio.SNOW) {
					                	 if (TMusicTicker.currentMusic != TAudio.SNOW) {
						                	 TMusicTicker.getTrack(TAudio.SNOW);
					                	 }
					                 }
					                 
					                 if (mushroom > 15 && TMusicTicker.currentMusic != TAudio.MUSHROOM) {
						                	 TMusicTicker.getTrack(TAudio.MUSHROOM);
					                 }
					                 
					                 if (highlands > 15) {
					                	 
					                	 if (!TMusicTicker.weatherMusicOverride) {
					                		 if (!day && TMusicTicker.currentMusic != TAudio.NIGHT) {
						                		 if (TMusicTicker.currentMusic != TAudio.NIGHT) {
								                	 TMusicTicker.getTrack(TAudio.NIGHT);
							                	 }
						                	 } else if (day && TMusicTicker.currentMusic != TAudio.DAYONE) {
						                		 if (TMusicTicker.currentMusic != TAudio.DAYONE) {
								                	 TMusicTicker.getTrack(TAudio.DAYONE);
							                	 }
						                	 } 
					                	 }
					                 }
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
		droppedTombstoneForDeath = false;
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
	}
	
	@Inject(at = @At("HEAD"), method = "writeCustomDataToNbt")
	public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
		nbt.putDouble("newMaxHealth", this.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH));
		nbt.putInt("mana", MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)));
		nbt.putInt("max_mana", MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA)));
	}
	
	@Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
	public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(nbt.getDouble("newMaxHealth"));
		newMaxHealth = (float)nbt.getDouble("newMaxHealth");

		if (WorldDataT.hasStartingTools) {
			tmpMana = nbt.getInt("mana");
			tmpMaxMana = nbt.getInt("max_mana");
		}
	}

	@Inject(at = @At("HEAD"), method = "equipStack", cancellable = true)
	public void equipStack(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
		ActionResult action = PlayerEquipArmorCallback.EVENT.invoker().call(slot, stack);

		if (action == ActionResult.FAIL) {
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "onDeath")
	public void onDeath(DamageSource source, CallbackInfo ci) {
		if (!droppedTombstoneForDeath) {
			this.getEntityWorld().setBlockState(this.getBlockPos(), BlocksT.TOMBSTONE.getDefaultState());

			droppedTombstoneForDeath = true;
		}
	}
}
