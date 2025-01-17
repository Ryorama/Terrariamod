package com.ryorama.terrariamod.entities.terraria.hostile.slime;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityBlueSlime extends EntitySlimeBase {
	public EntityBlueSlime(EntityType<? extends EntityBlueSlime> entityType, Level world) {
		super(entityType, world);
		this.damage = 7;
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(24);
	}
}
