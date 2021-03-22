package com.ryorama.terrariamod.entity;

import java.util.ArrayList;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.mixins.PlayerEntityMixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class EntitySlimeBase extends EntityBaseMob {
	
	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();

	public boolean onGroundLastTick;
	
	public EntitySlimeBase(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void AI() {
		if (this.onGround && !this.onGroundLastTick) {
			double motionY = this.velY;
		    double motionX = this.velX;
		    double motionZ = this.velZ;
		    
		    lookRandomly();
		    
		    if (this.random.nextInt(1) == 0) {
		    	motionY = 1.5f;
		    	motionX = 1.5f;
		    } else {
		    	motionY = 1.5f;
		    	motionZ = 1.5f;
		    }
		    this.setVelocity(motionX, motionY, motionZ);
		    motionX = 0;
		    motionY = 0;
		    motionZ = 0;
		    this.setVelocity(motionX, motionY, motionZ);
		}
		this.onGroundLastTick = this.onGround;
	}

	public void lookRandomly() {
		this.setRotation(this.headYaw + this.random.nextInt(3), this.pitch);
	}

	@Override
	public void initProps(EntityProps props) {
		props.lifeMax = 15;
		props.damage = 4;
	}

	@Override
	public void drops() {
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.gel(this.random.nextInt(2), "ffee00").getItem()));
		}
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
	
	public EntityDimensions getDimensions(EntityPose pose) {
		return EntitiesT.GREEN_SLIME.getDimensions();
		
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
}
