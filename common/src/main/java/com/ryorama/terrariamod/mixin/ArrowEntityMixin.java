package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.entities.impl.ArrowEntityT;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Arrow.class)
public abstract class ArrowEntityMixin extends AbstractArrow implements ArrowEntityT {

    public boolean doesFireDamage = false;

    protected ArrowEntityMixin(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "doPostHurtEffects")
    protected void doPostHurtEffects(LivingEntity target, CallbackInfo info) {
        if (doesFireDamage) {
            BuffsT.AddBuffToEntity(target, 1000, BuffsT.ON_FIRE);
        }
    }

    @Override
    public boolean DoesFireDamage() {
        return doesFireDamage;
    }

    @Override
    public void SetFireDamage(boolean fireDamage) {
        doesFireDamage = fireDamage;
    }
}
