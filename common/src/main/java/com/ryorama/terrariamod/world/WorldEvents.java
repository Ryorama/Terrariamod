package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WorldEvents {

    public static void spawnFallenStar(Level world, Player player) {
        Vec3i spawnPos = new Vec3i((int) (player.getX() + world.getRandom().nextInt(20)), 320, (int) (player.getZ() + world.getRandom().nextInt(20)));
        ItemEntity fallenStar = new ItemEntity(world, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), ItemsT.FALLEN_STAR.get().getDefaultInstance());

        world.addFreshEntity(fallenStar);

        /*while (!fallenStar.isOnGround()) {
            world.playSound(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), TAudio.STAR_FALL, SoundCategory.MASTER, 1, 0, false);
        }*/
    }
}
