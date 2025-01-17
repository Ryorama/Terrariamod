package com.ryorama.terrariamod.entities.terraria.hostile.slime;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class EntityGreenSlime extends EntitySlimeBase {

	public EntityGreenSlime(EntityType<? extends EntityGreenSlime> entityType, Level world) {
		super(entityType, world);
		this.damage = 6;
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(14);
	}
}
