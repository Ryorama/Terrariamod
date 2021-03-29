package com.ryorama.terrariamod.entity.hostile.slimes;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public class EntityGreenSlime extends EntitySlimeBase {

	public EntityProps props2;
	
	public EntityGreenSlime(EntityType<? extends LivingEntity> entityType, World world) {
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
		props.damage = 4;
		props.lifeMax = 15;
		props.hitSound = TAudio.NPC_HIT1;
		props.deathSound = TAudio.NPC_KILL1;
	}

	@Override
	public CompoundTag saveData(CompoundTag tag) {
		tag.putFloat("lifemax", props2.lifeMax);
		tag.putFloat("damage", props2.damage);
		return tag;
	}

	@Override
	public void loadData(CompoundTag tag) {
		props2.lifeMax = tag.getFloat("lifemax");
		props2.damage = tag.getFloat("damage");
	}
}
