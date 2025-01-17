package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffsT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class HotBlockT extends BlockT {
    public HotBlockT(BlockBehaviour.Properties properties, float hardness, float difficulty) {
        super(properties, hardness, difficulty);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            if (TerrariaMod.CONFIG.replaceSpecialDamageWithDebuffs) {
                BuffsT.AddBuffToEntity((LivingEntity) entity, 1, BuffsT.ON_FIRE);
            } else {
                entity.hurt(world.damageSources().hotFloor(), 1);
            }
        }

        super.stepOn(world, pos, state, entity);
    }
}
