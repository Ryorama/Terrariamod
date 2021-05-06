package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.client.TMusicTicker;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public boolean hasGivingStartingItems = false;
	
	public PlayerEntity player;
		
	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo info) {
	
		if (MinecraftClient.getInstance().player != null && !hasGivingStartingItems) {
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
			
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_PICKAXE, 1));
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_AXE, 1));
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.COPPER_SHORTSWORD, 1));
			
			hasGivingStartingItems = true;
		}
		
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.getHungerManager().setFoodLevel(10);
			player = MinecraftClient.getInstance().player;
		}
		
		if (MinecraftClient.getInstance().world != null) {
			if (!TMusicTicker.bossMusicOverride) {
		        boolean day = MinecraftClient.getInstance().world.getTimeOfDay() >= 1000 && MinecraftClient.getInstance().world.getTimeOfDay() <= 13000;
		        if (player != null) {
			        if (player.getY() >= 10) {
			        	if (TMusicTicker.currentMusic != TAudio.DAYONE) {
				        	if (day) {
				        		TMusicTicker.getTrack(TAudio.DAYONE);
				        	}
						} else if (TMusicTicker.currentMusic != TAudio.NIGHT) {
							if (!day) {
								TMusicTicker.getTrack(TAudio.NIGHT);
							}
						}
					} else {
						if (player.getY() <= 10 && player.getY() >= -125) {
							if (TMusicTicker.currentMusic != TAudio.UNDERGROUND) {
								TMusicTicker.getTrack(TAudio.UNDERGROUND);
							}
						} else if (player.getY() <= -125) {
							if (TMusicTicker.currentMusic != TAudio.UNDERWORLD) {
								TMusicTicker.getTrack(TAudio.UNDERWORLD);
							}
						}
					}
		        }
			}
		}
	}
	
	/*
	@Inject(at = @At("HEAD"), method = "writeNbt")
	public CompoundTag writeNbt(CompoundTag tag) {
		if (tag.contains("NewHealth")) {
			return tag;
		} else {
			tag.putInt("NewHealth", this.newHealth);
		}
		return tag;	
	}
	
	@Inject(at = @At("HEAD"), method = "readNbt")
	public void readNbt(CompoundTag tag) {
		newHealth = tag.getInt("NewHealth");
	}
	*/
}
