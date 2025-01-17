package com.ryorama.terrariamod.mixin;

import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SimpleParticleType.class)
public interface DefaultParticleTypeMixin {
    @Invoker("<init>")
    static SimpleParticleType init(boolean alwaysShow) {
        return null;
    }
}