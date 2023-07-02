package com.ryorama.terrariamod.mixin;

import net.minecraft.particle.DefaultParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DefaultParticleType.class)
public interface DefaultParticleTypeMixin {
    @Invoker("<init>")
    static DefaultParticleType init(boolean alwaysShow) {
        return null;
    }
}