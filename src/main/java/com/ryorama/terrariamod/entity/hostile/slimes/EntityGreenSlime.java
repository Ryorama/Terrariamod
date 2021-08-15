package com.ryorama.terrariamod.entity.hostile.slimes;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IParticleListener;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityGreenSlime extends EntitySlimeBase implements IAnimatable, IParticleListener {

	public EntityProps props2;
	
	private AnimationFactory factory = new AnimationFactory(this);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.getHealth() > 0) {
			event.getController().registerParticleListener(this);
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.slime.squish", true));
		} else if (this.getHealth() <= 0) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.slime.death", false));
		}
	
		return PlayState.CONTINUE;
	}
	
	public EntityGreenSlime(EntityType<? extends EntityGreenSlime> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void drops() {
		ItemEntity ie = new ItemEntity(world, this.getPos().x, this.getPos().y, this.getPos().z, new ItemStack(ItemsT.gel(this.random.nextInt(2), "ffee00").getItem()));
		world.spawnEntity(ie);
		ie.setPosition(this.getPos().x, this.getPos().getY(), this.getPos().z);
	}
	
	@Override
	public void initProps(EntityProps props) {
		this.props2 = props;
		props.damage = 4;
		props.lifeMax = 15;
		props.hitSound = TAudio.NPC_HIT1;
		props.deathSound = TAudio.NPC_KILL1;
	}

	@Override
	public NbtCompound saveData(NbtCompound tag) {
		tag.putFloat("lifemax", props2.lifeMax);
		tag.putFloat("damage", props2.damage);
		return tag;
	}

	@Override
	public void loadData(NbtCompound tag) {
		props2.lifeMax = tag.getFloat("lifemax");
		props2.damage = tag.getFloat("damage");
	}
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
	
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));		
	}

	@Override
	public void summonParticle(ParticleKeyFrameEvent particleKeyFrameEvent) {

	}
}
