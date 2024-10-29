package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffsT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.Object;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput {

    @Shadow
    private int fireTicks;

    @Inject(at = @At("HEAD"), method = "setOnFireFromLava")
    public void setOnFireFromLava(CallbackInfo ci) {
        if (TerrariaMod.CONFIG.replaceSpecialDamageWithDebuffs) {
            fireTicks = 0;
            if (((Entity) (Object) this) instanceof LivingEntity) {
                BuffsT.AddBuffToEntity(((LivingEntity) (Object) this), 1, BuffsT.ON_FIRE);
            }
            return;
        }
    }

    @Inject(at = @At("HEAD"), method = "setOnFireFor")
    public void setOnFireFor(int seconds, CallbackInfo ci) {
        if (TerrariaMod.CONFIG.replaceSpecialDamageWithDebuffs) {
            if (((Entity) (Object) this) instanceof LivingEntity) {
                BuffsT.AddBuffToEntity(((LivingEntity) (Object) this), 1, BuffsT.ON_FIRE);
            }
            return;
        }
    }
}
