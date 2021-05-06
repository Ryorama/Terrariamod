package com.ryorama.terrariamod.entity.hostile.slimes;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.BossBar;

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

public class EntityBlueSlime extends EntitySlimeBase implements IAnimatable, IParticleListener{

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
	
	public EntityBlueSlime(EntityType<? extends EntityBlueSlime> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void drops() {
		ItemEntity ie = new ItemEntity(world, this.getPos().x, this.getPos().y, this.getPos().z);
		world.spawnEntity(ie);
		ie.setStack(new ItemStack(ItemsT.gel(this.random.nextInt(2), "ffee00").getItem()));
		ie.setPosition(this.getPos().x, this.getPos().getY(), this.getPos().z);
	}
	
	@Override
	public void initProps(EntityProps props) {
		this.props2 = props;
		props.damage = 7;
		props.lifeMax = 20;
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
	public <A extends IAnimatable> void summonParticle(ParticleKeyFrameEvent<A> event) {		
	}
	
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));		
	}
}
