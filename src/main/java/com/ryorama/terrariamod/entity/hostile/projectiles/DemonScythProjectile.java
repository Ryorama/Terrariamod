package com.ryorama.terrariamod.entity.hostile.projectiles;

import java.util.ArrayList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
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

public class DemonScythProjectile extends LivingEntity implements IAnimatable, IParticleListener {

	public int damage = 40;
	
	public int waitingCooldownMax = 100;
	
	public int waitingCooldownCurrent = 0;
	
	public boolean alreadyCalculatedPos = false;
	
	public double targetX;
	public double targetY;
	public double targetZ;
	
	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.getHealth() > 0) {
			event.getController().registerParticleListener(this);
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.demon_scythe.spin", true));
		} else if (this.getHealth() <= 0) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.demon_scythe.death", false));
		}
		
		return PlayState.CONTINUE;
	}
	
	public DemonScythProjectile(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);
		
		if (this.isAlive()) {
			playerIn.damage(DamageSource.mob(this), damage);
			if (alreadyCalculatedPos) {
				this.kill();
			}
		}
	}
	
	@Override
	public void onBlockCollision(BlockState state) {
		if (state != Blocks.AIR.getDefaultState()) {
			if (this.isAlive()) {
				if (alreadyCalculatedPos) {
					this.kill();
				}
			}
		}
	}
	
	public void tick() {
		super.tick();
		
		if (this.isAlive()) {
			double motionY = this.getVelocity().y;
	    	double motionX = this.getVelocity().x;
	    	double motionZ = this.getVelocity().z;
	    	motionY = 0;
	    	this.setNoGravity(true);
	
			PlayerEntity target = null;
			double distance = 1000;
			   
			 for(int i = 0; i < this.world.getPlayers().size(); ++i) {
		              double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPos().distanceTo(this.getPos());
		              if (dist < distance) {
		                 distance = dist;
		                 target = (PlayerEntity)this.world.getPlayers().get(i);
		              }
			 }
		 			 
			 if (target != null) {	
				 if (!alreadyCalculatedPos) {
					 waitingCooldownCurrent += 1;
					 
					 if (waitingCooldownCurrent >= waitingCooldownMax) {
						 targetX = target.getX();
						 targetY = target.getY();
						 targetZ = target.getZ();
						 
						 alreadyCalculatedPos = true;
					 }
				 }
				 
				 if (alreadyCalculatedPos) {
					 
					 if (targetX < this.getX()) {
						 motionX = -0.4f;
					 } else {
						 motionX = 0.4f;
					 }
					 
					 if (targetZ < this.getZ()) {
						 motionZ = -0.4f;
					 } else {
						 motionZ = 0.4f;
					 }
					 
					 if (targetY < this.getY()) {
						 motionY = -0.4f;
					 } else {
						 motionY = 0.4f;
					 }
					 
					 this.setVelocity(motionX, motionY, motionZ);
				 }
			 }
		 }
	}

	@Override
	public <A extends IAnimatable> void summonParticle(ParticleKeyFrameEvent<A> event) {		
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
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));		
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
