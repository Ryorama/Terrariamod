package com.ryorama.terrariamod.mixins;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {

    @Inject(at = @At("HEAD"), method = "register", cancellable = true)
    private static void register(String id, EntityAttribute attribute, CallbackInfoReturnable<EntityAttribute> info) {
        if (id.equals("generic.max_health")) {
            info.setReturnValue(Registry.register(Registries.ATTRIBUTE, id, new ClampedEntityAttribute("attribute.name.generic.max_health", 20.0D, 1.0D, 1000000.0D).setTracked(true)));
        }

        if (id.equals("generic.armor")) {
            info.setReturnValue(Registry.register(Registries.ATTRIBUTE, id, new ClampedEntityAttribute("attribute.name.generic.armor", 0.0D, 0.0D, 1000.0D).setTracked(true)));
        }
    }
}