package com.ryorama.terrariamod.items.tools;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.items.api.ItemT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class MagicMirror extends ItemT {

	public MagicMirror(Settings settings) {
		super(settings);
	}

	private boolean tick = false;
    private int tickCounter = 0;

    private ServerPlayerEntity player;
    private ClientPlayerEntity clientPlayer;
    private ServerWorld serverWorld;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (playerEntity instanceof ServerPlayerEntity) player = (ServerPlayerEntity) playerEntity; // Get server player
        else if (playerEntity instanceof ClientPlayerEntity) clientPlayer = (ClientPlayerEntity) playerEntity; // Get client player
        if (world instanceof ServerWorld) serverWorld = (ServerWorld) world; // Get server world
        world.playSound(playerEntity, playerEntity.getBlockPos(), TAudio.TELEPORT, SoundCategory.PLAYERS, 1f, 1f);

        if (!tick) {
            MinecraftClient.getInstance().particleManager.addEmitter(clientPlayer, ParticleTypes.END_ROD, 30);
            tick = true;
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        } else {
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (player != null) {
            if (tick) {
                if (tickCounter >= 30) {
                    tick = false;
                    tickCounter = 0;
                    BlockPos position;

                    if (player.getSpawnPointPosition() == null) { // Determine if set position to bed or world spawn
                        position = serverWorld.getSpawnPos();
                    } else {
                        position = player.getSpawnPointPosition();
                    }

                    System.out.println();
                    
                    player.teleport(serverWorld, position.getX(), position.getY(), position.getZ(), 0, 0); // Teleport to correct position
                } else {
                    tickCounter++;
                }
            }
        }
    }

    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0D) {
            double d = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }
}
