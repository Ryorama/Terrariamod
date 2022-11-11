package com.ryorama.terrariamod.entity.collectables;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Iterator;
import java.util.List;

public class HeartEntity extends MobEntity implements IAnimatable {

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

    public void tick() {
        super.tick();
        this.setNoGravity(false);
        ++this.age;
        if (this.age > 6000 || this.dead) {
            this.remove(RemovalReason.DISCARDED);
        }

        if (this.age > 6000) {
            this.dead = true;
        }

        if (this.dead) {
            this.remove(RemovalReason.DISCARDED);
        } else {
            World world = this.world;
            List players = world.getPlayers();
            double dist = 2.147483647E9D;
            PlayerEntity closest = null;
            Iterator var7 = players.iterator();

            while(var7.hasNext()) {
                PlayerEntity player = (PlayerEntity)var7.next();
                double d = player.getPos().distanceTo(this.getPos());
                if (d < dist) {
                    dist = d;
                    closest = player;
                }
            }

            if (this.collidesWith(closest) && !world.isClient()) {
                ServerPlayerEntity player = (ServerPlayerEntity)closest;
                player.heal(20.0F);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }
}
