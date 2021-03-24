package com.ryorama.terrariamod.entity.hostile.slimes;

import java.util.ArrayList;

import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.EntityBaseMob;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public abstract class EntitySlimeBase extends EntityBaseMob {
	
	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public boolean onGroundLastTick;
	
	public boolean jumping = false;
	public float jumpCooldown;
	
	public EntitySlimeBase(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	public void AI() {
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
		
		if (!this.onGround && jumping) {
			jumping = false;
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
