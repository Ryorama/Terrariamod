package com.ryorama.terrariamod.entity.hostile.bosses;

import java.util.ArrayList;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.IBoss;
import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;

import com.ryorama.terrariamod.ui.BossBar;
import net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Arm;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityEyeOfCthulhu extends FlyingEntity implements IBoss, IAnimatable {
	//PugzAreCute: Looks like constructor missing, Error: Error:(38, 14) All entities must have a constructor that takes one net.minecraft.world.World parameter.

	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public int damage = 18;
	public int ticksExisted;
	public float rx;
	public float ry;
	public float rz;
	public boolean collision;
	public boolean bounce;
	public double velX;
	public double velY;
	public double velZ;
	public double oldVelX;
	public double oldVelY;
	public double oldVelZ;
	public double speed = 2.0D;
	public double acc = 0.01D;
	public static int phase = 1;
	public boolean spawnEyes = true;
	public int dashed = 0;
	public int eyes = 0;
	public int eyesNeeded = 0;
	public int maxHealth;
	public float bosshealth;
	public double transformedRotation = 0.0D;
	public int defense = 0;
	public static boolean isEyeAlive = false;
	public static boolean isEyeAlive2 = false;
	public boolean ALREADY_SPAWNED = false;
	public boolean REMOVED = false;
	public BlockPos lastTarget;
	public boolean transformed = false;

   	public AnimationController animationController;
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		animationController = event.getController();
		if (isAlive()) {
			if (this.getHealth() <= this.getMaxHealth() / 2 && transformed == false) {
				animationController.setAnimation(new AnimationBuilder().addAnimation("animation.eye_of_cthulhu_phase1.transition", false));
			}

			if (phase == 1) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.eye_of_cthulhu_phase1.fly", false));
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.eye_of_cthulhu_phase2.fly", false));
			}
		}
		
		return PlayState.CONTINUE;
	}
	
	public EntityEyeOfCthulhu(EntityType<? extends EntityEyeOfCthulhu> entityType, World world) {
		super(entityType, world);
		if (world.isClient) {
			this.setBossIcon();
			this.activateBoss();
		}

	    if (!world.isClient()) {
	    	for (int i = 0; i <= this.world.getServer().getPlayerManager().getPlayerList().size() - 1; i++) {
		    	this.world.getServer().getPlayerManager().getPlayerList().get(i).sendMessage(new TranslatableText("The Eye of Cthulhu has awoken!").formatted(Formatting.BOLD).formatted(Formatting.LIGHT_PURPLE), false);
	    	}
	    }
	    
	    this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1024);
	    this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(35);
		setHealth(getMaxHealth());
	    phase = 0;
	    isEyeAlive = true;
	}
	
	public boolean canRenderOnFire() {
		return false;
	}

	public void setFire(int seconds) {
	}
	
	public void tick() {
		ticksExisted += 1;

		if (world.isClient()) {
			this.updateBossHealthBar();
			if (this.getHealth() <= 0) {
				BossBar.isRendering = false;
			}
		}

		isEyeAlive2 = isEyeAlive;
	      this.ALREADY_SPAWNED = true;
	      boolean despawn = true;

	      for(int i = 0; i < this.world.getPlayers().size(); ++i) {
	         if (((PlayerEntity)this.world.getPlayers().get(i)).getHealth() > 0.0F) {
	            despawn = false;
	            break;
	         }
	      }

	      if (despawn) {
	         this.REMOVED = true;
	         isEyeAlive = false;
	         this.kill();
	      }

	      super.tick();
	      this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(9999.0D);
	      this.setNoGravity(true);
	      this.noClip = true;
	      double motionY = this.getVelocity().y;
	      double motionX = this.getVelocity().x;
	      double motionZ = this.getVelocity().z;
	      if (phase == 1) {
	         this.defense = 12;
	      } else {
	         this.defense = 0;
	      }

	      if (!this.world.isClient) {
	         motionY = 0.0D;
	         this.setPitch(0);
	         this.setYaw(0);
	         this.headYaw = 0.0F;
	         PlayerEntity target = null;
	         double distance = 1000.0D;

	         for(int i = 0; i < this.world.getPlayers().size(); ++i) {
	            double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPos().distanceTo(this.getPos());
	            if (dist < distance) {
	               distance = dist;
	               target = (PlayerEntity)this.world.getPlayers().get(i);
	            }
	         }

	         boolean day = this.world.getTimeOfDay() >= 1000 && this.world.getTimeOfDay() <= 13000;
	         
	         if (day) {
	        	 this.setVelocity(0, 1, 0);
	         }
	         
	         if (phase == 0) {
	            phase = 1;
	         }

	         if (phase == 1 || phase == 2) {
	            if (this.spawnEyes) {
	               if (this.eyesNeeded == 0) {
	                  this.eyesNeeded = this.random.nextInt(2) + 2;
	                  if (this.getHealth() <= (float)this.maxHealth * 0.25F) {
	                     this.eyesNeeded = 1;
	                  }
	               } else if (this.ticksExisted % 35 == 0 && this.random.nextBoolean()) {
	                  if (this.eyes < this.eyesNeeded) {
	                     ++this.eyes;
	                     if (phase == 1 && this.world.getEntitiesByClass(EntityDemonEye.class, this.getBoundingBox().expand(15.0D, 15.0D, 15.0D), EntityPredicates.VALID_LIVING_ENTITY).size() <= 8) {
	                        EntityDemonEye eye = new EntityDemonEye(EntitiesT.DEMON_EYE, this.world);
	                        eye.setPosition(this.getX(), this.getY(), this.getZ());
	                        eye.setHealth(8.0F);
	                        eye.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(12.0D);
	                        this.world.spawnEntity(eye);
	                        eye.money = 0;
	                        eye.noClip = true;
	                        eye.setCustomName(new TranslatableText("Servant of Cthulhu"));
	                     }
	                  } else {
	                     this.spawnEyes = false;
	                     this.dashed = 0;
	                  }
	               }

	               if (target != null) {
	            	   
	            	   if (!target.isAlive()) {
	            		   isEyeAlive = false;
	            	   }
	            	   
	                  if (this.velX > -4.0D && this.getX() > target.getX() + (double)target.getWidth()) {
	                     this.velX -= 0.08D;
	                     if (this.velX > 4.0D) {
	                        this.velX -= 0.04D;
	                     } else if (this.velX > 0.0D) {
	                        this.velX -= 0.2D;
	                     }

	                     if (this.velX < -4.0D) {
	                        this.velX = -4.0D;
	                     }
	                  } else if (this.velX < 4.0D && this.getX() + 1.0D < target.getX()) {
	                     this.velX += 0.08D;
	                     if (this.velX < -4.0D) {
	                        this.velX += 0.04D;
	                     } else if (this.velX < 0.0D) {
	                        this.velX += 0.2D;
	                     }

	                     if (this.velX > 4.0D) {
	                        this.velX = 4.0D;
	                     }
	                  }

	                  if (this.velZ > -4.0D && this.getZ() > target.getZ() + (double)target.getWidth()) {
	                     this.velZ -= 0.08D;
	                     if (this.velZ > 4.0D) {
	                        this.velZ -= 0.04D;
	                     } else if (this.velZ > 0.0D) {
	                        this.velZ -= 0.2D;
	                     }

	                     if (this.velZ < -4.0D) {
	                        this.velZ = -4.0D;
	                     }
	                  } else if (this.velZ < 4.0D && this.getZ() + 1.0D < target.getZ()) {
	                     this.velZ += 0.07999999821186066D;
	                     if (this.velZ < -4.0D) {
	                        this.velZ += 0.04D;
	                     } else if (this.velZ < 0.0D) {
	                        this.velZ += 0.2D;
	                     }

	                     if (this.velZ > 4.0D) {
	                        this.velZ = 4.0D;
	                     }
	                  }

	                  if (this.velY > -2.5D && this.getY() > target.getY() + (double)target.getHeight() + 5.0D) {
	                     this.velY -= 0.30000001192092896D;
	                     if (this.velY > 2.5D) {
	                        this.velY -= 0.05D;
	                     } else if (this.velY > 0.0D) {
	                        this.velY -= 0.15D;
	                     }

	                     if (this.velY < -2.5D) {
	                        this.velY = -2.5D;
	                     }
	                  } else if (this.velY < 2.5D && this.getY() + 1.0D < target.getY() + 5.0D) {
	                     this.velY += 0.30000001192092896D;
	                     if (this.velY < -2.5D) {
	                        this.velY += 0.05D;
	                     } else if (this.velY < 0.0D) {
	                        this.velY += 0.15D;
	                     }

	                     if (this.velY > 2.5D) {
	                        this.velY = 2.5D;
	                     }
	                  }
	               }
	            } else {
	               this.velX *= 0.949999988079071D;
	               this.velY *= 0.949999988079071D;
	               this.velZ *= 0.949999988079071D;
	               float speed = 2.0F;
	               if (phase == 2) {
	                  speed = 2.0F;
	               }

	               if (this.getHealth() <= (float)this.maxHealth * 0.4F) {
	                  speed = 2.0F;
	               }

	               boolean fast = false;
	               Vec3d motion = new Vec3d(this.velX, this.velY, this.velZ);
	               if (target != null && (motion.length() <= 1.0D || this.getHealth() <= (float)this.maxHealth * 0.4F && this.getHealth() >= (float)this.maxHealth * 0.25F && this.dashed >= 3 && motion.length() <= 2.5D || this.getHealth() <= (float)this.maxHealth * 0.25F && motion.length() < 2.5D)) {
	                  if (this.getHealth() > (float)this.maxHealth * 0.4F) {
	                     if (this.dashed < 3) {
	                        ++this.dashed;
	                     } else {
	                        this.eyesNeeded = 0;
	                        this.spawnEyes = true;
	                        this.eyes = 0;
	                     }
	                  } else if (this.getHealth() < (float)this.maxHealth * 0.25F) {
	                     speed = 4.0F;
	                     fast = true;
	                     if (this.lastTarget == null) {
	                        this.lastTarget = target.getBlockPos();
	                     }

	                     if (this.dashed < 5) {
	                        ++this.dashed;
	                     } else {
	                        this.dashed = 0;
	                        speed = 1.0F;
	                        this.lastTarget = null;
	                     }
	                  } else {
	                     if (this.lastTarget == null) {
	                        this.lastTarget = target.getBlockPos();
	                     }

	                     if (this.dashed < 6) {
	                        ++this.dashed;
	                     } else {
	                        this.dashed = 0;
	                        this.lastTarget = null;
	                     }

	                     if (this.dashed < 3) {
	                        speed = 2.0F;
	                     } else {
	                        speed = 4.0F;
	                        fast = true;
	                     }
	                  }

	                  if (target != null) {
	                     Vec3d direction = new Vec3d(target.prevX - target.getVelocity().x - this.getX(), target.prevY - target.getVelocity().y - this.getY(), target.prevZ - target.getVelocity().z - this.getZ());
	                     direction = (new Vec3d(direction.x * 100.0D, direction.y * 100.0D, direction.z * 100.0D)).normalize();
	                     direction = new Vec3d(direction.x * (double)speed, direction.y * (double)speed, direction.z * (double)speed);
	                     this.velX = direction.x * 2.0D;
	                     this.velY = direction.y * 2.0D;
	                     this.velZ = direction.z * 2.0D;
	                  }
	               }
	            }
	         }

	         if (this.getHealth() <= this.getMaxHealth() / 2 && transformed == false) {
	        	transformed = true;
	        	world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), TAudio.ROAR_0, SoundCategory.PLAYERS, 100, 1);
	            phase = 2;
	         }

	         this.bounce = false;
	         this.oldVelX = this.velX + 0.0D;
	         this.oldVelY = this.velY + 0.0D;
	         this.oldVelZ = this.velZ + 0.0D;
	         motionX = this.velX * 0.25D;
	         motionY = this.velY * 0.25D;
	         motionZ = this.velZ * 0.25D;
	         if (target != null) {
	            this.lookAt(EntityAnchor.EYES, target.getPos());
	         }

	         if (target != null) {
	            this.rx = (float)Math.toDegrees(Math.atan2(this.getY() - target.getY(), this.getX() - target.getX()));
	            this.rz = (float)Math.toDegrees(Math.atan2(this.getY() - target.getY(), this.getZ() - target.getX()));
	         }
	      }

	      this.setVelocity(motionX, motionY, motionZ);

	      this.maxHurtTime = 0;
	      if (phase != 1 && this.transformedRotation < 1790.0D) {
	         if (this.ticksExisted % 10 == 0 && this.world.getEntitiesByClass(EntityDemonEye.class, this.getBoundingBox().expand(15.0D, 15.0D, 15.0D), EntityPredicates.VALID_LIVING_ENTITY).size() <= 8) {
	            EntityDemonEye eye = new EntityDemonEye(EntitiesT.DEMON_EYE, this.getEntityWorld());
	            eye.setPosition(this.getX(), this.getY(), this.getZ());
	            eye.setHealth(8.0F);
	            eye.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(12.0D);
	            this.world.spawnEntity(eye);
	            eye.money = 0;
	            eye.noClip = true;
	            eye.setCustomName(new TranslatableText("Servant of Cthulhu"));
	         }

	         this.transformedRotation += (1800.0D - this.transformedRotation) * 0.07999999821186066D;
	         this.ry = (float)this.transformedRotation;
	         this.rx = 0.0F;
	         this.rz = 0.0F;
	         this.velX = 0.0D;
	         this.velY = 0.0D;
	         this.velZ = 0.0D;
	         motionX = 0.0D;
	         motionY = 0.0D;
	         motionZ = 0.0D;
	         this.setYaw(this.ry);
	      }
	}

	public void remove(Entity.RemovalReason reason) {
		super.remove(reason);
		System.out.println("Test");
		if (world.isClient()) {
			this.defeatedBoss();
		}
	}
	
	public void dropLoot(DamageSource source, boolean b) {
		if (!world.isClient()) {
			for (int i = 0; i <= this.world.getServer().getPlayerManager().getPlayerList().size() - 1; i++) {
				this.world.getServer().getPlayerManager().getPlayerList().get(i).sendMessage(new TranslatableText("The Eye of Cthulhu has been defeated!").formatted(Formatting.BOLD).formatted(Formatting.LIGHT_PURPLE), false);
			}
		}

		if (this.getHealth() <= 0) {
			if (world.isClient()) {
				this.defeatedBoss();
			}
			return;
		}
	 }
	
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
	}

	protected int getAttackStrength() {
		return 18;
	}

	@Override
	public Identifier bossIcon() {
		return new Identifier(TerrariaMod.MODID, "textures/ui/boss_icons/eoc_icon.png");
	}

	@Override
	public float getBossHealth() {
		return this.getHealth();
	}

	@Override
	public float getBossMaxHealth() {
		return this.getMaxHealth();
	}


	@Override
	public Iterable<ItemStack> getArmorItems() {
		return armorItems;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {
		
	}

	@Override
	public Arm getMainArm() {
		return Arm.LEFT;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
