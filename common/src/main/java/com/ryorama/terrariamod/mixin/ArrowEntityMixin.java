package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.entities.impl.ArrowEntityT;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends PersistentProjectileEntity implements ArrowEntityT {

    public boolean doesFireDamage = false;

    protected ArrowEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "onHit")
    protected void onHit(LivingEntity target, CallbackInfo info) {
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
