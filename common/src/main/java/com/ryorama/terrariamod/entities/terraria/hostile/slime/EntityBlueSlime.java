package com.ryorama.terrariamod.entities.terraria.hostile.slime;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public class EntityBlueSlime extends EntitySlimeBase {
	public EntityBlueSlime(EntityType<? extends EntityBlueSlime> entityType, World world) {
		super(entityType, world);
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(24);
	}

	@Override
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);

		if (this.isAlive()) {
			playerIn.damage(getEntityWorld().getDamageSources().mobAttack(this), 7);
		}
	}
}
