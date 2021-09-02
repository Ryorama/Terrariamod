package com.ryorama.terrariamod.entity.hostile;

import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.EntityBaseMob;
import com.ryorama.terrariamod.entity.EntityProps;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;

public class EntityDrippler extends EntityBaseMob implements IAnimatable, AnimationController.IParticleListener {

    public int damage = 20;

    public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();

    private AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getHealth() > 0) {
            event.getController().registerParticleListener(this);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.drippler.idle", true));
        } else if (this.getHealth() <= 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.drippler.idle", false));
        }

        return PlayState.CONTINUE;
    }

    public EntityDrippler(EntityType<? extends EntityBaseMob> entityType, World worldIn) {
        super(entityType, worldIn);
        onInitSpawn();
    }

    public void onInitSpawn() {
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(80);
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
            playerIn.damage(DamageSource.mob(this), damage);
        }
    }

    public Iterable<ItemStack> getArmorItems() {
        return armorItems;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return Arm.LEFT;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 20));
    }

    @Override
    public void AI() {
        if (this.isAlive()) {

            this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));

            double motionY = this.getVelocity().y;
            double motionX = this.getVelocity().x;
            double motionZ = this.getVelocity().z;
            motionY = 0;
            this.setNoGravity(true);
            this.fallDistance = 0;
            this.setPitch(0);
            this.setYaw(0);
            this.headYaw = 0;
            World world = this.world;
            PlayerEntity target = null;
            double distance = 1000;

            for(int i = 0; i < this.world.getPlayers().size(); ++i) {
                double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPos().distanceTo(this.getPos());
                if (dist < distance) {
                    distance = dist;
                    target = (PlayerEntity)this.world.getPlayers().get(i);
                }

            }

            if (target != null) {
                if (target.getX() < this.getX()) {
                    motionX = -0.05f;
                } else {
                    motionX = 0.05f;
                }

                if (target.getZ() < this.getZ()) {
                    motionZ = -0.05f;
                } else {
                    motionZ = 0.05f;
                }

                if (target.getY() < this.getY()) {
                    motionY = -0.05f;
                } else {
                    motionY = 0.05f;
                }

                this.setYaw((float)target.getYaw() - 180);
            }
            this.setVelocity(motionX, motionY, motionZ);
        } else {
            this.setVelocity(0, -0.5f, 0);
        }
    }

    @Override
    public void initProps(EntityProps paramEntityProps) {

    }

    @Override
    public void drops() {

    }

    @Override
    public NbtCompound saveData(NbtCompound tag) {
        return null;
    }

    @Override
    public void loadData(NbtCompound tag) {

    }

    @Override
    public void summonParticle(ParticleKeyFrameEvent particleKeyFrameEvent) {

    }
}