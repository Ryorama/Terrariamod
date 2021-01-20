package com.ryorama.terrariamod.events;

import com.ryorama.terrariamod.blocks.BlockT;
import com.ryorama.terrariamod.core.inventory.InventorySlot;
import com.ryorama.terrariamod.core.inventory.InventoryTerraria;
import com.ryorama.terrariamod.core.inventory.container.ContainerTerrariaInventory;
import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.core.items.modifiers.ItemModifier;
import com.ryorama.terrariamod.core.network.NetworkHandler;
import com.ryorama.terrariamod.core.network.client.CPacketCloseInventoryTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketEquipItemTerraria;
import com.ryorama.terrariamod.core.network.client.CPacketRequestInventoryTerraria;
import com.ryorama.terrariamod.entities.EntityItemT;
import com.ryorama.terrariamod.items.Armor;
import com.ryorama.terrariamod.items.ItemT;
import com.ryorama.terrariamod.util.Conversions;
import com.ryorama.terrariamod.util.Util;
import com.ryorama.terrariamod.world.WorldEvents;
import com.ryorama.terrariamod.world.WorldStateHolder;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntityEvents {

	
	@SubscribeEvent
	public static void handleItemToss(ItemTossEvent event) {
		
		if (event.getPlayer() != null) {
			PlayerEntity player = event.getPlayer();
			
			if (player.world.isRemote) { 
				InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);
				
				if (Util.terrariaInventory) {
					event.setCanceled(true);
				}
			} else {
				InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);
				if (inventory != null) {
					if (inventory.open) {
						event.setCanceled(true);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void handleLivingSpawn(LivingSpawnEvent event) {
		
	}
	
	@SubscribeEvent
	public static void handleEntityDeath(LivingDeathEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			if (!player.world.isRemote) {
				if (player.world.getServer() != null)
				if (player.world.getServer().getWorld(DimensionType.OVERWORLD) != null) {
					BlockPos pos = WorldStateHolder.get(player.world.getServer().getWorld(DimensionType.OVERWORLD)).spawnPositions.get(player.getScoreboardName());
				}
				
			}
		}
		
		
		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();

			
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.ARCHERY).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.POTION_SICKNESS).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MANA_SICKNESS).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MANA_SICKNESS_EFFECT).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.BATTLE).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.BUILDER).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.CALMING).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.FEATHERFALL).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.FLIPPER).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.GILLS).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.GRAVITATION).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.HEARTREACH).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.INVISIBILITY).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.IRONSKIN).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MAGIC_POWER).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MANA_REGENERATION).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MINING).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.NIGHT_OWL).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.OBSIDIAN_SKIN).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.REGENERATION).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.SHINE).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.SWIFTNESS).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.THORNS).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.TITAN).setScorePoints(0);
			ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.WATER_WALKING).setScorePoints(0);
			
			WorldEvents.summons.put(player.getScoreboardName(), null);
			if (WorldEvents.getOrLoadInventory(player) != null) {
				WorldEvents.getOrLoadInventory(player).save(player.getScoreboardName(), player.world.getServer().getFolderName());
			}
		}
	}
	
	@SubscribeEvent
	public static void handleEntitySpawns(EntityJoinWorldEvent event) {
		
		
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntity();
			WorldEvents.summons.put(player.getScoreboardName(), null);
			
			if (player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() < 100) {
				player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
			} else {
				if (player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() < 200) {
					player.setHealth(100);
				} else
				player.setHealth((float)player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2.0f);
			}
			
			if (!player.world.isRemote) {
				if (player.world.getServer() != null)
				if (player.world.getServer().getWorld(DimensionType.OVERWORLD) != null) {
					BlockPos pos = WorldStateHolder.get(player.world.getServer().getWorld(DimensionType.OVERWORLD)).spawnPositions.get(player.getScoreboardName());
				}
				
			}
			
		}
	}
	
	@OnlyIn(value=Dist.CLIENT)
	@SubscribeEvent
	public static void handleEntitySpawnsClient(EntityJoinWorldEvent event) {
		
		
		
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntity();
			if (Minecraft.getInstance() != null)
				if (Minecraft.getInstance().player != null)
			if (player.getScoreboardName().contentEquals(Minecraft.getInstance().player.getScoreboardName())) {
				System.out.println("JOINED THE WORLD.  REQUESTING INVENTORY DATA FROM SERVER FOR WORLD: " + Minecraft.getInstance().player.world.getWorldInfo().getWorldName());
				
				if (Minecraft.getInstance().isSingleplayer()) {
					NetworkHandler.INSTANCE.sendToServer(new CPacketRequestInventoryTerraria(Minecraft.getInstance().player.getScoreboardName(),Minecraft.getInstance().getIntegratedServer().getFolderName()));

				} else {
					NetworkHandler.INSTANCE.sendToServer(new CPacketRequestInventoryTerraria(Minecraft.getInstance().player.getScoreboardName(),Minecraft.getInstance().player.getEntityWorld().getWorldInfo().getWorldName()));

				}
				
			}
			
		}
	}
	
	@SubscribeEvent
	public static void handlePlayerAttack(AttackEntityEvent event) {
		if (event.getPlayer() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getPlayer();
			
			if (player.world.isRemote)
			if (player.getSwingProgress(0) > 0.25) {
				event.setCanceled(true);
				return;
			}
			
			if (player != null) {
				if (player.getHeldItemMainhand() != null) {
					if (player.getHeldItemMainhand().getItem() != null) {
						if (player.getCooldownTracker().getCooldown(player.getHeldItemMainhand().getItem(), 0) <= 0) {
							if (player.getHeldItemMainhand().getItem() instanceof ItemT) {
								ItemT item = (ItemT)player.getHeldItemMainhand().getItem();
								item.onLeftClick(event.getTarget(), event.getTarget().getPosition(), player, player.world, Hand.MAIN_HAND);
							}
						}
					}
				}
			}
		}
				
			
	}
	
	@SubscribeEvent
	public static void handleDamage (LivingDamageEvent event) {
		if (event.getEntityLiving() != null) {
			
			if (event.getSource() == DamageSource.IN_WALL) {
				if (event.getEntityLiving() instanceof PlayerEntity == false) {
					event.setAmount(0);
					event.setCanceled(true);
					return;
				}
			}
			
			if (event.getSource() == DamageSource.DROWN || event.getSource() == DamageSource.IN_WALL) {
				if (!(event.getEntityLiving() instanceof PlayerEntity)) {
					event.setCanceled(true);
					return;
				}
			}
			
			if (event.getSource() == DamageSource.LAVA) {
				
				
				
				
				event.setAmount(event.getAmount() * 10);
				if (event.getEntityLiving() instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity)event.getEntityLiving();
					
					if (ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.WEAK).getScorePoints() > 0) {
						event.setAmount(event.getAmount() + 4);
					}
					
					int waterwalk = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.WATER_WALKING).getScorePoints();
					if (waterwalk > 0) {
						A:
						if (player.world.getBlockState(new BlockPos(player.getPositionVec().x, player.getPositionVec().y - 0.1f, player.getPositionVec().z)).getFluidState().isSource() == true) {
							
							if (player.world.getBlockState(new BlockPos(player.getPositionVec().x, player.getPositionVec().y + 2, player.getPositionVec().z)).getFluidState().isSource() == true)
								break A;
							if (Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown()) {

							} else {
//								if (player.getMotion().y < 0) {
//									player.posY -= player.getMotion().y;
//								}
								player.onGround = true;
								if (player.world.getBlockState(new BlockPos(player.getPositionVec().x, player.getPositionVec().y +0.2f, player.getPositionVec().z)).getFluidState().isSource() == true) {
									event.setAmount(0);
									event.setCanceled(true);
									return;
								} else {
									event.setAmount(0);
									event.setCanceled(true);
									return;
								}
							}
						}
					}
					
					int obsidianskin = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.OBSIDIAN_SKIN).getScorePoints();
					if (obsidianskin > 0) {
						event.setAmount(0);
						event.setCanceled(true);
						return;
					}
				}
				
			}
			if (event.getSource() == DamageSource.ON_FIRE) {
				event.setAmount(event.getAmount() * 5);
				if (event.getEntityLiving() instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity)event.getEntityLiving();
					int obsidianskin = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.OBSIDIAN_SKIN).getScorePoints();
					if (obsidianskin > 0) {
						event.setAmount(0);
						event.setCanceled(true);
						return;
					}
				}
			}
			
			if (event.getSource() == DamageSource.HOT_FLOOR) {
				if (event.getEntityLiving() instanceof PlayerEntity)
				if (!event.getEntityLiving().world.isRemote) {
					PlayerEntity player = (PlayerEntity)event.getEntityLiving();
					
					int obsidianskin = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.OBSIDIAN_SKIN).getScorePoints();
					if (obsidianskin > 0) {
						event.setAmount(0);
						event.setCanceled(true);
						return;
					}
					
					InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);
					if (inventory != null) {
						boolean hasSkull = false;
						for (int i = 0; i < inventory.accessory.length; i++) {
							InventorySlot slot = inventory.accessory[i];
						}
						if (hasSkull) {
							event.setAmount(0);
							event.setCanceled(true);
							return;
						}
					}
				}
				
				if (event.getEntityLiving() instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity)event.getEntityLiving();
					int obsidianskin = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.OBSIDIAN_SKIN).getScorePoints();
					if (obsidianskin > 0) {
						event.setAmount(0);
						event.setCanceled(true);
						return;
					}
				}
			}
			
			if (event.getEntityLiving() instanceof PlayerEntity)
				if (!event.getEntityLiving().world.isRemote) {
					PlayerEntity player = (PlayerEntity)event.getEntityLiving();
					if (ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.IRONSKIN).getScorePoints() > 0) {
						event.setAmount(event.getAmount() - 8);
						
					}
				}
			
		}
		if (event.getAmount() < 1 && event.isCanceled() == false)
			event.setAmount(1);
		
		if (event.getEntityLiving() instanceof PlayerEntity) {
			if (event.getSource().getImmediateSource() != null) {
				Entity e = event.getSource().getImmediateSource();
				PlayerEntity player = (PlayerEntity)event.getEntityLiving();
				int thorns = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.THORNS).getScorePoints();
				if (thorns > 0) {
					e.attackEntityFrom(DamageSource.GENERIC, event.getAmount() * (1.0f/3.0f));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void handleKnockback(LivingKnockBackEvent event) {
		
		if (event.getEntityLiving() != null) {
			event.getEntityLiving().setMotion(event.getEntityLiving().getMotion().x, 0, event.getEntityLiving().getMotion().z);
		}
		if (event.getOriginalAttacker() != null) {
			if (event.getOriginalAttacker() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)event.getOriginalAttacker();
				if (player.getHeldItemMainhand() != null) {
					if (player.getHeldItemMainhand().getItem() instanceof ItemT) {
						
						double attackMul = 1.0;
						InventoryTerraria inventory = null;
						if (!player.world.isRemote) {
							inventory = WorldEvents.getOrLoadInventory(player);
						}
						else {
							inventory = ContainerTerrariaInventory.inventory;
						}
						if (inventory != null) {
							InventorySlot selected = inventory.hotbar[inventory.hotbarSelected];
							if (selected != null) {
								if (selected.stack != null) {
									ItemModifier modifier = ItemModifier.getModifier(selected.stack.modifier);
									if (modifier != null) {
										attackMul += modifier.knockback / 100.0;
									}
								}
							}
						}
						int titan = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.TITAN).getScorePoints();
						if (titan > 0) attackMul *= 2.0;
						event.setStrength((float) (Conversions.feetToMeters * ((ItemT)player.getHeldItemMainhand().getItem()).knockback * 0.2f * attackMul));
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void handleEntityDamage(LivingDamageEvent event) {
		int armor = 0;
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntity();
			
			InventoryTerraria inventory = null;
			if (!player.world.isRemote) {
				inventory = WorldEvents.getOrLoadInventory(player);
			}
			else {
				inventory = ContainerTerrariaInventory.inventory;
			}
			if (inventory != null) {
				
				for (int i = 0; i < 3; i++) {
					if (inventory.armor[i].stack != null) {
						if (inventory.armor[i].stack.item instanceof Armor) {
							Armor a = (Armor)inventory.armor[i].stack.item;
							armor += a.getDefense(inventory.armor);
						}
					}
				}
				
			}
			event.setAmount(event.getAmount() - armor);
			if (event.getAmount() < 1) event.setAmount(1);
		}
		
		if (event.getSource().getImmediateSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getSource().getImmediateSource();
			double cooldownMul = 1.0;
			double attackMul = 1.0;
			
			
			
			InventoryTerraria inventory = null;
			if (!player.world.isRemote) {
				inventory = WorldEvents.getOrLoadInventory(player);
			}
			else {
				inventory = ContainerTerrariaInventory.inventory;
			}
			if (inventory != null) {
				
				for (int i = 0; i < 3; i++) {
					if (inventory.armor[i].stack != null) {
						if (inventory.armor[i].stack.item instanceof Armor) {
							Armor a = (Armor)inventory.armor[i].stack.item;
							armor += a.defense;
						}
					}
				}
				
				InventorySlot selected = inventory.hotbar[inventory.hotbarSelected];
				if (selected != null) {
					if (selected.stack != null) {
						ItemModifier modifier = ItemModifier.getModifier(selected.stack.modifier);
						if (modifier != null) {
							cooldownMul -= modifier.speed / 100.0;
							attackMul += modifier.damage / 100.0;
						}
					}
				}
			}
			
			if (player.getHeldItemMainhand() != null) {
				if (player.getHeldItemMainhand().getItem() instanceof ItemT) {
					
					ItemT item = (ItemT)player.getHeldItemMainhand().getItem();
					if (item.melee == true) {
						event.setAmount(event.getAmount() * (float)attackMul);
					}
//					player.getCooldownTracker().setCooldown(item, (int)(player.getCooldownTracker().getCooldown(item, 0.0f) * cooldownMul));
				}
			}
		}
		
	}
	
	@SubscribeEvent
	public static void handleLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {

	}
	@SubscribeEvent
	public static void handleInteract(PlayerInteractEvent event) {	
		if (event instanceof PlayerInteractEvent.LeftClickEmpty) {
			PlayerEntity player = event.getPlayer();
			float rd = 1000.0f;
			float reach = (float)player.getAttribute(PlayerEntity.REACH_DISTANCE).getBaseValue();
		      Vec3d vec3d = player.getEyePosition(0);
		      Vec3d vec3d1 = player.getLook(0);
		      Vec3d vec3d2 = vec3d.add(vec3d1.x * reach, vec3d1.y * reach, vec3d1.z * reach);
		     //   public static EntityRayTraceResult func_221269_a(World p_221269_0_, Entity p_221269_1_, Vec3d p_221269_2_, Vec3d p_221269_3_, AxisAlignedBB p_221269_4_, Predicate<Entity> p_221269_5_, double p_221269_6_) {
		}
	}
	//public ItemUseContext(PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceResultIn) {
	@SubscribeEvent
	public static void handleInteract(PlayerInteractEvent.RightClickBlock event) {		
		
	}
	
	//MAX LIFE CRYSTALS: 15 (20HP each)
	//MAX LIFE FRUIT: 20 (5HP each)
	
	@SubscribeEvent
	public static void handleInteract(PlayerInteractEvent.RightClickItem itemEvent) {
		
	}
	
	@SubscribeEvent
	public static void handleMining(BreakSpeed event) {
		
		if (Util.blockHit != null) {
			if (Util.blockHit.getType() == RayTraceResult.Type.BLOCK) {
				event.setNewSpeed(-1);
				return;
			}
		}
		
		getMiningSpeed(event);
		
	}
	
	public static void getMiningSpeed(BreakSpeed event) {
		
	}
	
	
	@SubscribeEvent
	public static void handlePlayerEvent(PlayerEvent event) {
		if (event.getPlayer() != null) {
			PlayerEntity player = event.getPlayer();
			
			
			player.setNoGravity(true);
			
			
			
			player.getFoodStats().setFoodLevel(20);
			
			Score lifeCrystals = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.LIFE_CRYSTALS);
			
			player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100 + lifeCrystals.getScorePoints() * 20);
			if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemT)
			if (player.getHeldItemMainhand().getDamage() != 0) {
				player.getHeldItemMainhand().setDamage(0);
			}
			
			Score lifeFruit = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.LIFE_FRUIT);
			
			player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100 + lifeCrystals.getScorePoints() * 20 + lifeFruit.getScorePoints() * 5);
			if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemT)
			if (player.getHeldItemMainhand().getDamage() != 0) {
				player.getHeldItemMainhand().setDamage(0);
			}
		}
		Util.watchTime = "";
	}
	
	
	@OnlyIn(value=Dist.CLIENT)
	@SubscribeEvent
	public static void handleClientLivingEvent(LivingEvent event) {
		{
			
		}
		
		if (event.getEntity() == null) return;
		if (event.getEntity().world == null) return;
		if (event.getEntity().world.isRemote == false) return;
		if (event.getEntity() != null) {
			event.getEntity().setNoGravity(true);
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)event.getEntityLiving();
				player.getAttribute(PlayerEntity.ENTITY_GRAVITY).setBaseValue(0.0f);
				player.setNoGravity(true);
				InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
				
				boolean hasBoots = false;
				double speed = 0.0f;
				if (ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.SWIFTNESS).getScorePoints() > 0) speed += 0.25;
				if (ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.WEAK).getScorePoints() > 0) speed -= 0.051;
				player.setAIMoveSpeed((float) speed);
				
				World world = player.getEntityWorld();
				Scoreboard scoreboard = player.getWorldScoreboard();
				if (Minecraft.getInstance() != null)
					if (Minecraft.getInstance().player != null)
				if (player.getScoreboardName().contentEquals(Minecraft.getInstance().player.getScoreboardName())) {
					if (Util.terrariaInventory)
						NetworkHandler.INSTANCE.sendToServer(new CPacketEquipItemTerraria(ContainerTerrariaInventory.inventory.hotbarSelected));
					else if (Util.justClosedInventory) {
						Util.justClosedInventory = false;
						NetworkHandler.INSTANCE.sendToServer(new CPacketCloseInventoryTerraria());
					}
					ScoreboardEvents.handleClientScoreboard(player, world, scoreboard);
				}
				
			}
		}
		

	}

	@SubscribeEvent
	public static void handleEntityEvent(EntityEvent event) {
		
	}
	@SubscribeEvent
	public static void handleLivingEvent(LivingEvent event) {
		
	}
}