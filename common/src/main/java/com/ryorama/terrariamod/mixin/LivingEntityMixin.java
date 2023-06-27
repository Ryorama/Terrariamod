package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.CustomBuffAccessor;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, CustomBuffAccessor {

    public List<BuffT> activeBuffs = new ArrayList<>();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo info) {
        for (int b = 0; b < activeBuffs.size(); b++) {
            if (activeBuffs.get(b).getDuration() <= 0) {
                activeBuffs.remove(b);
                return;
            }
        }
    }

    @Override
    public List<BuffT> GetActiveBuffs() {
        return activeBuffs;
    }

    @Override
    public void AddBuff(float duration, BuffT buff) {
        buff.setDuration(duration);
        activeBuffs.add(buff);
    }
}
