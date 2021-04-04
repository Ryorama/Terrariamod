package com.ryorama.terrariamod.entity.hostile;

import java.util.ArrayList;

import javax.activation.DataHandler;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.EntityBaseMob;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.items.ItemsT;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IParticleListener;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityDemonEye extends EntityBaseMob implements IAnimatable, IParticleListener {

	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public EntityProps props2;
	
	public boolean spawnedBlood = false;
		
	public float rx, ry, rz;
	
	public boolean collision;

	public boolean bounce;
	
	public double velX, velY, velZ;
	public double oldVelX, oldVelY, oldVelZ;
	
	public static final TrackedData<Integer> typed_data = DataTracker.registerData(EntityDemonEye.class, TrackedDataHandlerRegistry.INTEGER);
	
	public double speed = 2;
	public double acc = 0.01;
	
	public int money = 75;
	
	public int damage = 18;
	
	public float kbResist = 0.2f;
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.getHealth() > 0) {
			event.getController().registerParticleListener(this);
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.demon_eye.fly", true));
		} else if (this.getHealth() <= 0) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.demon_eye.death", false));
		}
		return PlayState.CONTINUE;
	}
	
	public EntityDemonEye(EntityType<? extends LivingEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		onInitialSpawn();
	}
	
	public void onInitialSpawn() {
		this.getDataTracker().startTracking(EntityDemonEye.typed_data, 1);
    	this.getDataTracker().set(EntityDemonEye.typed_data, random.nextInt(6));

		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(60);
		this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2);
		this.damage = 18;
		this.kbResist = 0.2f;
	}
	   
	@Override
	public void AI() {
		if (this.isAlive()) {
	        boolean night = MinecraftClient.getInstance().world.getTimeOfDay() <= 23999 && MinecraftClient.getInstance().world.getTimeOfDay() >= 13000;

	    	double motionY = this.getVelocity().y;
	    	double motionX = this.getVelocity().x;
	    	double motionZ = this.getVelocity().z;
	        motionY = 0;
	        this.setNoGravity(true);
	        this.fallDistance = 0;
			this.pitch = 0;
			this.yaw = 0;
			this.headYaw = 0;
			World world = this.world;
			PlayerEntity target = null;
			double distance = 1000;
			   
			 for(int i = 0; i < this.world.getPlayers().size(); ++i) {
		              double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPos().distanceTo(this.getPos());
		              if (dist < distance) {
		                 distance = dist;
		                 target = (PlayerEntity)this.world.getPlayers().get(i);
		              }
			
			 }
			
			 if (world.getBlockState(new BlockPos(getPos().getX(), getPos().getY() - 1, getPos().getZ())).getBlock().getDefaultState() == Blocks.WATER.getDefaultState()) {
				if (velY < 0) {
					velY = 3;
				}
			}
			
			if (this.noClip == false) {
				if (world.getBlockState(new BlockPos(this.getPos().x, this.getPos().y - 0.5f, this.getPos().z)).getMaterial().isSolid()) {
					this.velY = 2.0f;
				}
			}
			
			if (bounce == true) {
				double absX = Math.abs(motionX);
				double absY = Math.abs(motionY);
				double absZ = Math.abs(motionZ);
				
				if (onGround == false) {
	//			if (absX > absY) {
					velX = oldVelX * -0.5;
					if (velX > 0 && velX < 2) {
						velX = 2;
					}
					if (velX < 0 && velX > -2) {
						velX = -2;
					}
	//			}
				
	//			if (absZ > absY) {
					velZ = oldVelZ * -0.5;
					if (velZ > 0 && velZ < 2) {
						velZ = 2;
					}
					if (velZ < 0 && velZ > -2) {
						velZ = -2;
					}
	//			}
				}
				
				if (this.onGround == true) {
					velY = oldVelY * -0.5;
					if (velY > 0 && velY < 1) {
						velY = 1;
					}
					if (velY < 0 && velY > -1) {
						velY = -1;
					}
				}
				
			}
			
			if (night == false) {
				velY = 2f;
			}
			else
			if (target != null) {
				if (velX > -4 && this.getPos().x > target.getPos().x + target.getWidth()) {
					velX -= 0.08;
					if (velX > 4) {
						velX -= 0.04;
					}
					else if (velX > 0) {
						velX -= 0.2;
					}
					if (velX < -4) {
						velX = -4;
					}
				} else if (velX < 4 && this.getPos().x + 1 < target.getPos().x) {
					velX += 0.08;
					if (velX < -4) {
						velX += 0.04;
					}
					else if (velX < 0) {
						velX += 0.2;
					}
					if (velX > 4) {
						velX = 4;
					}
				}
				
				if (velZ > -4 && this.getPos().z > target.getPos().z + target.getWidth()) {
					velZ -= 0.08;
					if (velZ > 4) {
						velZ -= 0.04;
					}
					else if (velZ > 0f) {
						velZ -= 0.2;
					}
					if (velZ < -4) {
						velZ = -4;
					}
				} else if (velZ < 4f && this.getPos().z + 1 < target.getPos().z) {
					velZ += 0.08f;
					if (velZ < -4) {
						velZ += 0.04;
					}
					else if (velZ < 0f) {
						velZ += 0.2;
					}
					if (velZ > 4) {
						velZ = 4;
					}
				}
				
				if (velY > -2.5 && this.getPos().y > target.getPos().y + target.getHeight()) {
					velY -= 0.1f;
					if (velY > 2.5) {
						velY -= 0.05;
					} else if (velY > 0f) {
						velY -= 0.15;
					}
					if (velY < -2.5) {
						velY = -2.5;
					}
				} else if (velY < 2.5 && this.getPos().y + 1 < target.getPos().y) {
					velY += 0.1f;
					if (velY < -2.5) {
						velY += 0.05;
					}
					else if (velY < 0) {
						velY += 0.15;
					}
					if (velY > 2.5) {
						velY = 2.5;
					}
				}
				
				
			}
			
			
			bounce = false;
			oldVelX = velX + 0;
			oldVelY = velY + 0;
			oldVelZ = velZ + 0;
			motionX = velX * 0.075f;
			motionY = velY * 0.075f;
			motionZ = velZ * 0.075f;
			
			this.yaw = (float)Math.toDegrees(Math.atan2(velZ, velX)) - 90;

	    	this.setVelocity(motionX, motionY, motionZ); 
		} else {
			this.setVelocity(0, -0.5f, 0);
			if (!spawnedBlood) {
				world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.RED_CONCRETE.getDefaultState()), getPos().x, getPos().y, getPos().z, 0, 0, 0);
				spawnedBlood = true;
			}
		}
	}

	public void lookRandomly() {
		this.setRotation(this.headYaw + this.random.nextInt(3), this.pitch);
	}
	
	@Environment(EnvType.CLIENT)
	public boolean shouldRender(double distance) {
		double d = this.getBoundingBox().getAverageSideLength() * 4.0D;
		if (Double.isNaN(d) || d == 0.0D) {
			d = 4.0D;
		}
		d *= 64.0D;
		return distance < d * d;
	}
	
	@Environment(EnvType.CLIENT)
	public Box getVisibilityBoundingBox() {
		return this.getBoundingBox();
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
	public void drops() {
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.gel(this.random.nextInt(2), "ffee00").getItem()));
		}
	}
	
	@Override
	public void initProps(EntityProps props) {
	}

	@Override
	public CompoundTag saveData(CompoundTag tag) {
		return tag;
	}

	@Override
	public void loadData(CompoundTag tag) {
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

	@Override
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);
		
		if (this.isAlive()) {
			playerIn.damage(DamageSource.mob(this), damage);
		}
	}
	
	@Override
	public <A extends IAnimatable> void summonParticle(ParticleKeyFrameEvent<A> event) {
	}
}
