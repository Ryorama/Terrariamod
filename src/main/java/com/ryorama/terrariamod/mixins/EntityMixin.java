package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ryorama.terrariamod.client.fx.FXDamageIndicator;
import com.ryorama.terrariamod.utils.callbacks.EntityJoinWorldCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class EntityMixin extends Entity {

	public EntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	//Up to 100
	public float deathRemovalCooldown;
	
	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo info) {
		if (!isAlive()) {
			deathRemovalCooldown += 1;
		}
	}
	
	@Overwrite
	public void updatePostDeath() {
	    if (this.deathRemovalCooldown == 120) {
	         this.remove(Entity.RemovalReason.KILLED);
	      }
	}
	
	@Inject(at = @At("HEAD"), method = "damage")
	public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
	}
}