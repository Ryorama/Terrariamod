package com.ryorama.terrariamod.entities.terraria.hostile.slime;

import java.util.ArrayList;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class EntitySlimeBase extends MobEntity implements GeoAnimatable {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.slime.squish");
	private static final RawAnimation DEATH = RawAnimation.begin().thenPlay("animation.slime.death");

	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public boolean onGroundLastTick;
	
	public boolean jumping = false;
	public float jumpCooldown;
	
	public EntitySlimeBase(EntityType<? extends EntitySlimeBase> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!jumping) {
			jumpCooldown += 1;
			if (jumpCooldown >= 60) {
				jumping = true;
				jumpCooldown = 0;
			}
		}
		
		double motionY = getVelocity().getY();
	    double motionX = getVelocity().getX();
	    double motionZ = getVelocity().getZ();
		if (this.isOnGround() && jumping) {
		    lookRandomly();
		   
		    if (this.random.nextInt(1) == 0) {
			    if (this.random.nextInt(2) == 0) {
			    	motionY = 0.5f;
			    	motionX = 0.5f;
			    } else {
			    	motionY = 0.5f;
			    	motionZ = 0.5f;
			    }
		    } else {
		    	if (this.random.nextInt(2) == 0) {
			    	motionY = -0.5f;
			    	motionX = -0.5f;
			    } else {
			    	motionY = -0.5f;
			    	motionZ = -0.5f;
			    }
		    }
		    this.setVelocity(motionX, motionY, motionZ);
		}
		
		if (!this.isOnGround() && jumping) {
			jumping = false;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return TAudio.NPC_HIT1;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return TAudio.NPC_KILL1;
	}

	public void lookRandomly() {
		this.setRotation(this.headYaw + this.random.nextInt(3), this.getPitch());
	}
	
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
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);

		if (this.isAlive()) {
			playerIn.damage(getWorld().getDamageSources().generic(), 3);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Idle", 5, this::idle));
		controllers.add(new AnimationController<>(this, "Death", 5, this::death));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public double getTick(Object o) {
		return 0;
	}

	public PlayState idle(AnimationState state) {
		if (!isAlive()) {
			return PlayState.STOP;
		}

		return state.setAndContinue(IDLE);
	}

	public PlayState death(AnimationState state) {
		if (!isAlive()) {
			return state.setAndContinue(DEATH);
		}

		return PlayState.STOP;
	}
}
