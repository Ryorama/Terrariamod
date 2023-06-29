package com.ryorama.terrariamod.buffs;

import com.ryorama.terrariamod.buffs.terraria.harmful.PotionSicknessDebuff;
import com.ryorama.terrariamod.buffs.terraria.helpful.RegenerationBuff;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BuffsT {
    public static List<BuffT> buffs = new ArrayList<>();

    //Buffs
    public static RegenerationBuff REGENERATION = new RegenerationBuff();

    //Debuffs
    public static PotionSicknessDebuff POTION_SICKNESS = new PotionSicknessDebuff();

    public static void init() {
        buffs.add(REGENERATION);
        buffs.add(POTION_SICKNESS);
    }


    public static void AddBuffToEntity(LivingEntity entity, float duration, BuffT buff) {
        if (entity instanceof CustomBuffAccessor) {
            if (!EntityHasBuff(entity, buff)) {
                ((CustomBuffAccessor)entity).AddBuff(duration, buff);
            }
        }
    }

    public static List<BuffT> GetEntityActiveBuffs(LivingEntity entity) {
        if (entity instanceof CustomBuffAccessor) {
            return ((CustomBuffAccessor)entity).GetActiveBuffs();
        } else {
            return null;
        }
    }

    public static List<Identifier> GetEntityActiveBuffIcons(LivingEntity entity) {
        if (entity instanceof CustomBuffAccessor) {
            return ((CustomBuffAccessor)entity).GetActiveBuffIcons();
        } else {
            return null;
        }
    }

    public static boolean EntityHasBuff(LivingEntity entity, BuffT buff) {
        if (GetEntityActiveBuffs(entity).contains(buff)) {
            return true;
        } else {
            return false;
        }
    }
}
