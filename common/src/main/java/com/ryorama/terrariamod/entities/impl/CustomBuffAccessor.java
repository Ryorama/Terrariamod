package com.ryorama.terrariamod.entities.impl;

import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface CustomBuffAccessor {
    List<BuffT> GetActiveBuffs();
    List<ResourceLocation> GetActiveBuffIcons();
    void SetActiveBuffs(List<BuffT> buffs);

    void AddBuff(float duration, BuffT buff);
}
