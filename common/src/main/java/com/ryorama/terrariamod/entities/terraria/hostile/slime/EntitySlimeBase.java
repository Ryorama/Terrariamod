package com.ryorama.terrariamod.entities.terraria.hostile.slime;
import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;

public abstract class EntitySlimeBase extends Mob implements GeoAnimatable {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.slime.squish");
	public static ArrayList<ItemStack> armorItems = new ArrayList<>();
	
	public boolean onGroundLastTick;
	
	public boolean jumping = false;
	public float jumpCooldown;

	public float damage = 3;
	public EntitySlimeBase(EntityType<? extends EntitySlimeBase> entityType, Level world) {
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
		
		double motionY = getDeltaMovement().y();
	    double motionX = getDeltaMovement().x();
	    double motionZ = getDeltaMovement().z();
		if (this.onGround() && jumping) {
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
		    this.setDeltaMovement(motionX, motionY, motionZ);
		}
		
		if (!this.onGround() && jumping) {
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
		this.setRot(this.yHeadRot + this.random.nextInt(3), this.getXRot());
	}
	
	public Iterable<ItemStack> getArmorItems() {
		return armorItems;
	}

	@Override
	public ItemStack getItemBySlot(EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemSlot(EquipmentSlot slot, ItemStack stack) {}

	@Override
	public HumanoidArm getMainArm() {
		return HumanoidArm.LEFT;
	}

	@Override
	public void playerTouch(Player playerIn) {
		super.playerTouch(playerIn);

		if (this.isAlive()) {
			playerIn.hurt(level().damageSources().generic(), damage);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Idle", 1, this::idle));
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
		return state.setAndContinue(IDLE);
	}
}
