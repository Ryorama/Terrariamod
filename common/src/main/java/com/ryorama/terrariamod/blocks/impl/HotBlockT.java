package com.ryorama.terrariamod.blocks.impl;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotBlockT extends BlockT {
    public HotBlockT(Settings properties, float hardness, float difficulty) {
        super(properties, hardness, difficulty);
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.damage(world.getDamageSources().hotFloor(), 1);
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
