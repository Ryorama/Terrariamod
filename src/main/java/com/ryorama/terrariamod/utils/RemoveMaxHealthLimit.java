package com.ryorama.terrariamod.utils;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RemoveMaxHealthLimit {
	
	public static final EntityAttribute GENERIC_MAX_HEALTH = register("generic.max_health", (new ClampedEntityAttribute("attribute.name.generic.max_health", 20.0D, 1.0D, 100000000.0D).setTracked(true)));

	private static EntityAttribute register(String id, EntityAttribute attribute) {
	      return Registry.register(Registries.ATTRIBUTE, id, attribute);
	}
}
