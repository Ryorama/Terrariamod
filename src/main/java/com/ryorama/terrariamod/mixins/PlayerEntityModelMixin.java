package com.ryorama.terrariamod.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.items.api.Shortsword;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;

@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin<T extends LivingEntity> extends BipedEntityModel<T> {
	
	public PlayerEntityModelMixin(ModelPart root) {
		super(root);
	}

	@Shadow
	private List<ModelPart> parts;
	@Shadow
	private ModelPart leftSleeve;
	@Shadow
	private ModelPart rightSleeve;
	
	@Inject(at = @At("HEAD"), method = "setAngles", cancellable = true)
	public void setAngles(T player, float distance_traveled, float movement_speed, float ticks, float movement_tilt, float possibly_rotation_yaw, CallbackInfo info) {
		if (player.getMainHandStack().getItem() instanceof Shortsword) {
			if (player.handSwinging) {
				leftArm.getTransform().of(leftArm.pivotX, leftArm.pivotY, leftArm.pivotZ, leftArm.pitch + player.handSwingTicks, leftArm.yaw, leftArm.roll);
			}
		}
	}
}
