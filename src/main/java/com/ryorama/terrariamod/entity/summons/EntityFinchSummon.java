package com.ryorama.terrariamod.entity.summons;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.ryorama.terrariamod.api.entity.IHostile;
import com.ryorama.terrariamod.world.WorldEvents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityFinchSummon extends LivingEntity {

	public Entity owner;
	public long created;
	
	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	protected EntityFinchSummon(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	public void tick() {
		super.tick();
		
		this.noClip = true;
		this.setNoGravity(true);
		if (world.isClient) return;
		
		
		if (this.owner == null) {
			remove(RemovalReason.DISCARDED);
			return;
		}
		
		if (this.owner instanceof LivingEntity) {
			LivingEntity e = (LivingEntity)owner;
			if (e.getHealth() <= 0) {
				remove(RemovalReason.DISCARDED);
				return;
			}
		}
		
		Vec3d targetPos = owner.getPos();
		Entity target = null;
		if (WorldEvents.summoningTargets.get(owner.getScoreboardTags()) != null) {
			target = WorldEvents.summoningTargets.get(owner.getScoreboardTags());
			if (target instanceof LivingEntity) {
				LivingEntity entity = (LivingEntity)target;
				if (entity.getHealth() <= 0) {
					target = null;
					WorldEvents.summoningTargets.put(owner.getScoreboardTags(), null);
				}
			}
		} else {
			List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, new Box(owner.getPos().add(-25, -25, -25), owner.getPos().add(25, 25, 25)), EntityPredicates.VALID_LIVING_ENTITY);
			double dist = 1000;
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				if (entity instanceof IHostile) {
					double d = entity.getPos().distanceTo(getPos());
					if (d < dist) {
						dist = d;
						target = entity;
					}
				}
			}
		}
	}
	
	@Override
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

}
