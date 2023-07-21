package com.ryorama.terrariamod.entities.terraria.hostile.slime;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public class EntityGreenSlime extends EntitySlimeBase implements GeoAnimatable {

	public EntityGreenSlime(EntityType<? extends EntityGreenSlime> entityType, World world) {
		super(entityType, world);
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(14);
	}

	@Override
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);

		if (this.isAlive()) {
			playerIn.damage(getEntityWorld().getDamageSources().mobAttack(this), 6);
		}
	}
}
