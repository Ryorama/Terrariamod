package com.ryorama.terrariamod.buffs;

import com.ryorama.terrariamod.buffs.terraria.helpful.RegenerationBuff;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class BuffsT {

    public static List<BuffT> buffs = new ArrayList<>();

    public static RegenerationBuff REGENERATION = new RegenerationBuff();

    public static void init() {
        buffs.add(REGENERATION);
    }

    public static void AddBuffToEntity(LivingEntity entity, float duration, BuffT buff) {
        if (entity instanceof CustomBuffAccessor) {
            ((CustomBuffAccessor)entity).AddBuff(duration, buff);
        }
    }
}
