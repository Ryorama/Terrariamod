package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffsT;
import net.minecraft.commands.CommandSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.Object;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityAccess, CommandSource {

    @Shadow
    private int remainingFireTicks;

    @Inject(at = @At("HEAD"), method = "lavaHurt")
    public void lavaHurt(CallbackInfo ci) {
        if (TerrariaMod.CONFIG.replaceSpecialDamageWithDebuffs) {
            remainingFireTicks = 0;
            if (((Entity) (Object) this) instanceof LivingEntity) {
                BuffsT.AddBuffToEntity(((LivingEntity) (Object) this), 1, BuffsT.ON_FIRE);
            }
            return;
        }
    }

    @Inject(at = @At("HEAD"), method = "setSecondsOnFire")
    public void setSecondsOnFire(int seconds, CallbackInfo ci) {
        if (TerrariaMod.CONFIG.replaceSpecialDamageWithDebuffs) {
            if (((Entity) (Object) this) instanceof LivingEntity) {
                BuffsT.AddBuffToEntity(((LivingEntity) (Object) this), 1, BuffsT.ON_FIRE);
            }
            return;
        }
    }
}
