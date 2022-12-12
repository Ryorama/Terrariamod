package com.ryorama.terrariamod.entity.hostile;

import com.ryorama.terrariamod.api.entity.IHostile;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.ITerrariaEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;

public class EntitySporeZombie extends ZombieEntity implements IAnimatable, IHostile, ITerrariaEntity {

    public int damage = 17;

    private AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getHealth() > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zombie.idle", true));
        } else if (this.getHealth() <= 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zombie.idle", false));
        }

        return PlayState.CONTINUE;
    }

    public EntitySporeZombie(EntityType<? extends ZombieEntity> entityType, World worldIn) {
        super(entityType, worldIn);
        onInitSpawn();
    }

    public EntitySporeZombie(World world) {
        super(EntitiesT.SPORE_ZOMBIE, world);
        onInitSpawn();
    }

    public void onInitSpawn() {
        this.setMaxHealth(this, 80, 20, false);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(4);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void onPlayerCollision(PlayerEntity playerIn) {
        super.onPlayerCollision(playerIn);

        if (this.isAlive()) {
            this.dealDamage(playerIn, DamageSource.mob(this), damage);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }
}
