package com.ryorama.terrariamod.entities;

import java.util.Iterator;
import java.util.List;

import com.ryorama.terrariamod.core.inventory.InventorySlot;
import com.ryorama.terrariamod.core.inventory.InventoryTerraria;
import com.ryorama.terrariamod.core.inventory.container.ContainerTerrariaInventory;
import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.core.items.modifiers.ItemModifier;
import com.ryorama.terrariamod.core.network.NetworkHandler;
import com.ryorama.terrariamod.core.network.server.SPacketSyncInventoryTerraria;
import com.ryorama.terrariamod.items.ItemT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.util.Util;
import com.ryorama.terrariamod.world.WorldEvents;
import com.ryorama.terrariamod.world.WorldStateHolder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

public class EntityItemT extends Entity implements IEntityAdditionalSpawnData {
	   public String item = "";
	   public int stack = 1;
	   public int modifier = -1;
	   public int pickupDelay = 0;
	   private int age = 0;
	   public float hoverStart = 0.1F;
	   public boolean dead = false;
	   public boolean hitGround = false;
	   public boolean grabbed = false;

	   public EntityItemT(EntityType type, World worldIn) {
	      super(type, worldIn);
	      this.hitGround = true;
	   }

	   public EntityItemT(World worldIn) {
	      super(EntitiesT.ITEM, worldIn);
	      this.hitGround = true;
	   }

	   public EntityItemT(World worldIn, double x, double y, double z, ItemStackT stack) {
	      super(EntitiesT.ITEM, worldIn);
	      this.setPosition(x, y, z);
	      if (stack == null) {
	         this.item = ItemsT.getStringForItem(ItemsT.DIRT_BLOCK);
	         this.stack = 1;
	         this.modifier = -1;
	      } else {
	         this.item = ItemsT.getStringForItem(stack.item);
	         this.stack = stack.size;
	         this.modifier = stack.modifier;
	      }

	      this.hitGround = true;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public boolean isInRangeToRenderDist(double distance) {
	      double d0 = 64.0D * getRenderDistanceWeight();
	      return distance < d0 * d0;
	   }

	   public EntitySize getSize(Pose pose) {
	      return EntitySize.fixed(0.5F, 0.5F);
	   }

	   public void setItem(ItemStackT item) {
	      this.item = ItemsT.getStringForItem(item.item);
	      this.stack = item.size;
	      this.modifier = item.modifier;
	      if (item.size == 1) {
	         this.stack = 1;
	      }

	   }

	   public ItemStackT getItem() {
	      if (this.item == null) {
	         System.out.println("item is null!");
	         return new ItemStackT(ItemsT.getItemFromString("dirt_block"), this.stack, ItemModifier.getModifier(this.modifier));
	      } else {
	         return new ItemStackT(ItemsT.getItemFromString(this.item), this.stack, ItemModifier.getModifier(this.modifier));
	      }
	   }

	   public boolean cannotPickup() {
	      return true;
	   }

	   public boolean canUpdate() {
	      return true;
	   }

	   public void canUpdate(boolean value) {
	   }

	   public int getAge() {
	      return this.age;
	   }

	   public boolean canDespawn() {
	      return true;
	   }

	   public void applyEntityCollision(Entity entityIn) {
	   
	   }

	   public void tick() {
	      if (this.onGround) {
	         this.hitGround = true;
	      }

	      super.tick();
	      if (!this.world.isRemote() && this.getItem() != null) {
	         if (this.getItem().item instanceof ItemT) {
	            ItemT item = (ItemT)this.getItem().item;
	            if (item.lightValue > 0) {
	               WorldStateHolder.get(this.world).setLight(this.getPosition(), item.lightValue, this.world.getDimension().getType());
	            }
	         }

	         if (this.getItem().item == ItemsT.FALLEN_STAR) {
	            WorldStateHolder.get(this.world).setLight(this.getPosition(), 15, this.world.getDimension().getType());
	         }
	      }

	      this.setNoGravity(false);
	      ++this.age;
	      boolean moving = false;
	      if (!this.hitGround) {
	         this.move(MoverType.SELF, new Vec3d(0.0D, -1.0D, 0.0D));
	      }

	      if (this.getItem() != null && this.getItem().item == ItemsT.FALLEN_STAR) {
	         if (!this.hitGround) {
	            for(int i = 0; i < 10; ++i) {
	               this.world.addParticle(ParticleTypes.END_ROD, this.getPosX(), this.getPosY(), this.getPosZ(), this.rand.nextDouble() - 0.5D, this.rand.nextDouble(), this.rand.nextDouble() - 0.5D);
	            }
	         }

	         if (this.world.getDayTime() % 24000L < 15000L || this.world.getDayTime() % 24000L > 22000L) {
	            this.remove();
	         }
	      }

	      if (this.pickupDelay > 0) {
	         --this.pickupDelay;
	         this.move(MoverType.SELF, new Vec3d(0.0D, -0.4000000059604645D, 0.0D));
	      } else {
	         if (this.stack <= 0 || this.item == null || this.age > 6000 || this.dead) {
	            this.remove();
	         }

	         if (this.age > 6000) {
	            this.dead = true;
	         }

	         if (this.dead) {
	            this.remove();
	            this.removed = true;
	         } else {
	            List items = this.world.getEntitiesWithinAABB(EntityItemT.class, new AxisAlignedBB(this.getPositionVec().add(-5.0D, -5.0D, -5.0D), this.getPositionVec().add(5.0D, 5.0D, 5.0D)));
	            this.noClip = false;

	            for(int i = 0; i < items.size(); ++i) {
	               EntityItemT item = (EntityItemT)items.get(i);
	               if (item != this && item.ticksExisted > this.ticksExisted && item.getItem().item instanceof ItemT && item.stack + this.stack <= ((ItemT)item.getItem().item).maxStack) {
	                  if (this.getPositionVec().distanceTo(item.getPositionVec()) <= 1.0D) {
	                     item.stack += this.stack;
	                     this.remove();
	                     this.removed = true;
	                     return;
	                  }

	                  this.noClip = true;
	                  this.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
	               }
	            }

	            World world = this.world;
	            List players = world.getPlayers();
	            double dist = 2.147483647E9D;
	            PlayerEntity closest = null;
	            Iterator var8 = players.iterator();

	            while(var8.hasNext()) {
	               PlayerEntity player = (PlayerEntity)var8.next();
	               double d = player.getPositionVector().distanceTo(this.getPositionVector());
	               if (d < dist) {
	                  dist = d;
	                  closest = player;
	               }
	            }

	            if (closest != null && dist < 5.0D) {
	               InventoryTerraria inventory = WorldEvents.getOrLoadInventory(closest);
	               if (world.isRemote) {
	                  inventory = ContainerTerrariaInventory.inventory;
	               }

	               if (inventory != null && ItemsT.getItemFromString(this.item) != null && !this.dead) {
	                  InventorySlot slot = null;

	                  int i;
	                  for(i = 0; i < inventory.hotbar.length; ++i) {
	                     if (inventory.hotbar[i].stack == null && slot == null) {
	                        slot = inventory.hotbar[i];
	                     }

	                     if (inventory.hotbar[i].stack != null && inventory.hotbar[i].stack.item == ItemsT.getItemFromString(this.item)) {
	                        if (inventory.hotbar[i].stack.item instanceof ItemT) {
	                           if (inventory.hotbar[i].stack.size < ((ItemT)inventory.hotbar[i].stack.item).maxStack) {
	                              slot = inventory.hotbar[i];
	                              break;
	                           }
	                        } else if (inventory.hotbar[i].stack.size < inventory.hotbar[i].stack.itemForRender.getMaxStackSize()) {
	                           slot = inventory.hotbar[i];
	                           break;
	                        }
	                     }
	                  }

	                  if (slot == null) {
	                     for(i = 0; i < inventory.main.length; ++i) {
	                        if (inventory.main[i].stack == null && slot == null) {
	                           slot = inventory.main[i];
	                        }

	                        if (inventory.main[i].stack != null && inventory.main[i].stack.item == ItemsT.getItemFromString(this.item)) {
	                           if (inventory.main[i].stack.item instanceof ItemT) {
	                              if (inventory.main[i].stack.size < ((ItemT)inventory.main[i].stack.item).maxStack) {
	                                 slot = inventory.main[i];
	                                 break;
	                              }
	                           } else if (inventory.main[i].stack.size < inventory.main[i].stack.itemForRender.getMaxStackSize()) {
	                              slot = inventory.main[i];
	                              break;
	                           }
	                        }
	                     }
	                  }

	                  if (slot != null) {
	                     float newX = this.lerp((float)this.getPosX(), (float)closest.getPosX(), 0.35F);
	                     float newY = this.lerp((float)this.getPosY(), (float)closest.getPosY(), 0.35F);
	                     float newZ = this.lerp((float)this.getPosZ(), (float)closest.getPosZ(), 0.35F);
	                     this.setPosition(newX, newY, newZ);
	                     moving = true;
	                     if (dist < 0.25D) {
	                        if (slot.stack == null) {
	                           if (!world.isRemote && !this.grabbed) {
	                              slot.stack = new ItemStackT(ItemsT.getItemFromString(this.item), this.stack, ItemModifier.getModifier(this.modifier));
	                              this.stack = 0;
	                           }
	                        } else if (!world.isRemote && !this.grabbed) {
	                           ItemStackT var10000 = slot.stack;
	                           var10000.size += this.stack;
	                           if (slot.stack.item instanceof ItemT) {
	                              if (slot.stack.size > ((ItemT)slot.stack.item).maxStack) {
	                                 this.stack = slot.stack.size - ((ItemT)slot.stack.item).maxStack;
	                                 slot.stack.size = ((ItemT)slot.stack.item).maxStack;
	                              } else {
	                                 this.stack = 0;
	                              }
	                           } else if (slot.stack.size > slot.stack.itemForRender.getMaxStackSize()) {
	                              this.stack = slot.stack.size - slot.stack.itemForRender.getMaxStackSize();
	                              slot.stack.size = slot.stack.itemForRender.getMaxStackSize();
	                           } else {
	                              this.stack = 0;
	                           }
	                        } else {
	                           this.stack = 0;
	                        }

	                        if (!world.isRemote) {
	                           ServerPlayerEntity player = (ServerPlayerEntity)closest;
	                           if (!this.dead && !this.removed) {
	                              world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.5F, 2.0F + this.rand.nextFloat() * 2.0F - 1.0F);
	                           }

	                           if (!this.grabbed) {
	                              NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
	                                 return player;
	                              }), new SPacketSyncInventoryTerraria(0, slot.area, slot.id, slot.stack));
	                              this.grabbed = true;
	                              this.remove();
	                           }
	                        } else {
	                           Util.resetRecipes = true;
	                        }
	                     }
	                  }
	               }
	            }

	            if (!moving) {
	               this.move(MoverType.SELF, new Vec3d(0.0D, -0.5D, 0.0D));
	            }

	         }
	      }
	   }

	   public float lerp(float a, float b, float f) {
	      return a * (1.0F - f) + b * f;
	   }

	   protected void registerData() {
	   }

	   public void read(CompoundNBT compound) {
	      super.read(compound);
	      this.age = compound.getInt("age");
	      this.item = compound.getString("item");
	      this.stack = compound.getInt("size");
	   }

	   public void readAdditional(CompoundNBT compound) {
	      this.age = compound.getInt("age");
	      this.item = compound.getString("item");
	      this.stack = compound.getInt("size");
	   }

	   public void writeAdditional(CompoundNBT compound) {
	      compound.putInt("age", this.age);
	      compound.putString("item", this.item);
	      compound.putInt("size", this.stack);
	   }

	   public void writeSpawnData(PacketBuffer buffer) {
	      buffer.writeString(this.item);
	      buffer.writeInt(this.stack);
	      buffer.writeInt(this.pickupDelay);
	   }

	   public void readSpawnData(PacketBuffer additionalData) {
	      this.item = additionalData.readString(100);
	      this.stack = additionalData.readInt();
	      this.pickupDelay = additionalData.readInt();
	   }

	   public static EntityItemT spawnItem(World worldIn, BlockPos pos, ItemStackT stack, int pickupDelay) {
	      EntityItemT item = (EntityItemT)EntitiesT.ITEM.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
	      item.setItem(stack);
	      item.pickupDelay = pickupDelay;
	      worldIn.addEntity(item);
	      return item;
	   }

	   public static EntityItemT spawnItem(World worldIn, BlockPos pos, ItemStackT stack) {
	      return spawnItem(worldIn, pos, stack, 0);
	   }

	   public IPacket createSpawnPacket() {
	      return NetworkHooks.getEntitySpawningPacket(this);
	   }
}