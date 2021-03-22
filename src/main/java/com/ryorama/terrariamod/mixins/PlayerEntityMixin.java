package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
		
	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo info) {
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
	}
	
	/*
	@Inject(at = @At("HEAD"), method = "writeNbt")
	public CompoundTag writeNbt(CompoundTag tag) {
		if (tag.contains("NewHealth")) {
			return tag;
		} else {
			tag.putInt("NewHealth", this.newHealth);
		}
		return tag;	
	}
	
	@Inject(at = @At("HEAD"), method = "readNbt")
	public void readNbt(CompoundTag tag) {
		newHealth = tag.getInt("NewHealth");
	}
	*/
}
