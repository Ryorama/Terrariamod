package com.ryorama.terrariamod.utils;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.registry.Registry;

public class RemoveMaxHealthLimit {

	public static final ClampedEntityAttribute MAX_HEALTH = (ClampedEntityAttribute) new ClampedEntityAttribute("generic.maxHealth", 20.0D, 1.0D, 100000000.0D).setTracked(true);

}
