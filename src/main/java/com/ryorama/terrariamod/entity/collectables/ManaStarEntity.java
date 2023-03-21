package com.ryorama.terrariamod.entity.collectables;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ManaStarEntity extends MobEntity implements GeoAnimatable {

    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.heart.idle", true));

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