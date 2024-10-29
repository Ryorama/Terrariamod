package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffsT;
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

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            if (TerrariaMod.CONFIG.replaceSpecialDamageWithDebuffs) {
                BuffsT.AddBuffToEntity((LivingEntity) entity, 1, BuffsT.ON_FIRE);
            } else {
                entity.damage(world.getDamageSources().hotFloor(), 1);
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
