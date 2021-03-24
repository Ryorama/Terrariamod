package com.ryorama.terrariamod.entity.hostile.slimes;

import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public class EntityBlueSlime extends EntitySlimeBase {

	public EntityProps props2;
	
	public EntityBlueSlime(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void drops() {
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.gel(this.random.nextInt(2), "ffee00").getItem()));
		}
	}
	
	@Override
	public void initProps(EntityProps props) {
		this.props2 = props;
		props.damage = 7;
		props.lifeMax = 20;
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
