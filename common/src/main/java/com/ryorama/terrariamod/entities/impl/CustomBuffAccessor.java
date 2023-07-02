package com.ryorama.terrariamod.entities.impl;

import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.util.Identifier;

import java.util.List;

public interface CustomBuffAccessor {
    List<BuffT> GetActiveBuffs();
    List<Identifier> GetActiveBuffIcons();
    void SetActiveBuffs(List<BuffT> buffs);

    void AddBuff(float duration, BuffT buff);
}
