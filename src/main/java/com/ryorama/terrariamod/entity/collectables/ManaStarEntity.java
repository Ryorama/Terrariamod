package com.ryorama.terrariamod.entity.collectables;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ManaStarEntity extends MobEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.heart.idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    public ManaStarEntity(EntityType<ManaStarEntity> entityType, World world) {
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
        if (world.isClient()) {
            ClientPlayerEntity playerClient = ((ClientPlayerEntity)entity);
            StatHandler playerStats = playerClient.getStatHandler();

            playerStats.setStat(playerClient, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), playerStats.getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) + 20);
        }

        this.remove(RemovalReason.DISCARDED);
    }
}