package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class WorldEvents {

    public static void spawnFallenStar(World world, PlayerEntity player) {
        Vec3i spawnPos = new Vec3i((int) (player.getX() + world.getRandom().nextInt(20)), 320, (int) (player.getZ() + world.getRandom().nextInt(20)));
        ItemEntity fallenStar = new ItemEntity(world, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), ItemsT.FALLEN_STAR.get().getDefaultStack());

        world.spawnEntity(fallenStar);

        /*while (!fallenStar.isOnGround()) {
            world.playSound(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), TAudio.STAR_FALL, SoundCategory.MASTER, 1, 0, false);
        }*/
    }
}
