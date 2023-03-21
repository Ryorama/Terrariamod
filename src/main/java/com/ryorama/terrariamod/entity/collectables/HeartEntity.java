package com.ryorama.terrariamod.entity.collectables;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HeartEntity extends MobEntity implements GeoAnimatable {

    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(new RawAnimation().addAnimation("animation.heart.idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getFactory() {
        return factory;
    }

    @Override
    public void registerControllers(AnimatableManager data) {
        data.addController(new AnimationController(this, "controller", 0, this::predicate));
    }

    public HeartEntity(EntityType<HeartEntity> entityType, World world) {
        super(entityType, world);
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1);
        setHealth(getMaxHealth());
    }

    public boolean isInvulnerable() {
        return true;
    }

    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void onPlayerCollision(PlayerEntity entity) {
        entity.heal(20);
        this.remove(RemovalReason.DISCARDED);
    }
}
