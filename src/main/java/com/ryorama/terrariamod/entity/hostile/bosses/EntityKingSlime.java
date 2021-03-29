package com.ryorama.terrariamod.entity.hostile.bosses;

import java.util.ArrayList;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.EntityBaseMob;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.entity.IBoss;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityKingSlime extends EntityBaseMob implements IBoss, IAnimatable {
	
	public EntityKingSlime(EntityType<? extends LivingEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		if (world.isClient) {
			this.setBossIcon();
			this.activateBoss();
		}
	}

	private AnimationFactory factory = new AnimationFactory(this);
	
	public float animCooldown = 0;
	
	public boolean isTeleportingP1 = false;
	public boolean isTeleportingP2 = false;
	
	public boolean isTeleporting = false;
	
	public boolean startTeleport = false;
	
	public boolean onGroundLastTick;
	
	public boolean jumping = false;
	public float jumpCooldown;
	
	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public EntityProps props2;
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (isTeleportingP1 == true && animCooldown == 50) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kingslime.shrink", false));
		}
		if (isTeleportingP2 == true && animCooldown == 70) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kingslime.grow", false));
		}
		
		if (isTeleportingP1 && animCooldown == 55) {
			preformTeleport();
		}
		
		if (isTeleportingP2 && animCooldown >= 75) {
			finishTeleportingSteps();
		}
		
		return PlayState.CONTINUE;
	}
	
	public void finishTeleportingSteps() {
		isTeleportingP2 = false;
		isTeleporting = false;
		this.setVelocity(0, 0, 0);
	}

	private void preformTeleport() {
		isTeleportingP1 = false;
		startTeleport = true;
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
 	public void AI() {
		if (isTeleporting) {
			animCooldown += 1;
		}
		
		if (!isTeleportingP1 || !isTeleportingP2) {
			if (!jumping) {
				jumpCooldown += 1;
				if (jumpCooldown >= 60) {
					jumping = true;
					jumpCooldown = 0;
				}
			}
			
			double motionY = this.velY;
		    double motionX = this.velX;
		    double motionZ = this.velZ;
			if (this.onGround && jumping) {
			   
			    if (this.random.nextInt(1) == 0) {
				    if (this.random.nextInt(2) == 0) {
				    	motionY = 0.2f;
				    	motionX = 0.3f;
				    } else {
				    	motionY = 0.2f;
				    	motionZ = 0.3f;
				    }
			    } else {
			    	if (this.random.nextInt(2) == 0) {
				    	motionY = -0.2f;
				    	motionX = -0.3f;
				    } else {
				    	motionY = -0.2f;
				    	motionZ = -0.3f;
				    }
			    }
			    this.setVelocity(motionX, motionY, motionZ);
			}
			
			if (!isTeleporting) {
				if (this.random.nextInt(10) == 0) {
					kingSlimeteleport();
				}
			}
			
			if (!this.onGround && jumping) {
				jumping = false;
			}
			
			if (startTeleport) {
				
				if (!this.world.isClient) {
					System.out.println("tp2");
					this.teleport(this.getPos().x + 60, this.getPos().y + 60, this.getPos().z + 60, false);
				}
				
				startTeleport = false;
				isTeleportingP2 = true;
			}
		}

		this.updateBossHealthBar();
		
		if (this.getHealth() <= 0 && world.isClient) {
			this.defeatedBoss();
			return;
		}
	}

	public void kingSlimeteleport() {
		if (isTeleportingP1 == false && isTeleportingP2 == false) {
			isTeleportingP1 = true;
			isTeleporting = true;
			animCooldown = 0;
		}
	}

	@Override
	public void initProps(EntityProps props) {
		this.props2 = props;
		props.lifeMax = 2000;
		props.damage = 40;
		props.hitSound = TAudio.NPC_HIT1;
		props.deathSound = TAudio.NPC_KILL1;
	}

	@Override
	public void drops() {
		
	}

	@Override
	public CompoundTag saveData(CompoundTag tag) {
		tag.putFloat("lifemax", props2.lifeMax);
		tag.putFloat("damage", props2.damage);
		return tag;
	}

	@Override
	public void loadData(CompoundTag tag) {
		props2.lifeMax = tag.getFloat("lifemax");
		props2.damage = tag.getFloat("damage");
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
	public SoundEvent setBossMusic() {
		return TAudio.BOSS1;
	}

	@Override
	public Identifier bossIcon() {
		return new Identifier(TerrariaMod.modid, "textures/ui/boss_icons/kingslime_icon.png");
	}

	@Override
	public float getBossHealth() {
		return this.getHealth();
	}

	@Override
	public float getBossMaxHealth() {
		return this.getMaxHealth();
	}
}
