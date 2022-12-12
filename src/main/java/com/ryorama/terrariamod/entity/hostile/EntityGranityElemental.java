package com.ryorama.terrariamod.entity.hostile;

import java.util.ArrayList;

import com.ryorama.terrariamod.api.entity.IHostile;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entity.EntityBaseMob;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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

public class EntityGranityElemental extends EntityBaseMob implements IHostile, IAnimatable, IParticleListener {

	public EntityGranityElemental(EntityType<? extends EntityBaseMob> entityType, World worldIn) {
		super(entityType, worldIn);
		this.setMaxHealth(this, 120, 30, false);
		this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(5);
	}

	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.getHealth() > 0) {
			event.getController().registerParticleListener(this);
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.granite_elemental.hover", true));
		} else if (this.getHealth() <= 0) {
			return PlayState.STOP;
		}
		return PlayState.CONTINUE;
	}
	@Override
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);
		
		if (this.isAlive()) {
			this.dealDamage(playerIn, DamageSource.mob(this), 15);
		}
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
	
	@Override
	public void AI() {
		PlayerEntity target = null;
		double distance = 1000;
		   
		 for(int i = 0; i < this.world.getPlayers().size(); ++i) {
	              double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPos().distanceTo(this.getPos());
	              if (dist < distance) {
	                 distance = dist;
	                 target = (PlayerEntity)this.world.getPlayers().get(i);
	              }
		
		 }
		 this.setNoGravity(true);
		 this.noClip = true;
		 if (target != null) {
			 if (this.getX() < target.getX()) {
				 this.velX = 0.1f;
			 } else {
				 this.velX = -0.1f;
			 }
			 
			 if (this.getZ() < target.getZ()) {
				 this.velZ = 0.1f;
			 } else {
				 this.velZ = -0.1f;
			 }
	
			if (this.getY() < target.getY()) {
				 this.velY = 0.1f;
			} else {
				this.velY = -0.1f;
			}
		 }
		this.setVelocity(velX, velY, velZ);
	}
	
	@Override
	public void initProps(EntityProps paramEntityProps) {
		
	}
	
	@Override
	public void drops() {
		ItemEntity ie = new ItemEntity(world, this.getPos().x, this.getPos().y, this.getPos().z, new ItemStack(BlocksT.GRANITE.asItem(), 20 + random.nextInt(10)));
		world.spawnEntity(ie);
		ie.setPosition(this.getPos().x, this.getPos().getY(), this.getPos().z);
	}
	
	@Override
	public NbtCompound saveData(NbtCompound tag) {
		return null;
	}
	@Override
	public void loadData(NbtCompound tag) {
		
	}

	@Override
	public void summonParticle(ParticleKeyFrameEvent particleKeyFrameEvent) {

	}
}