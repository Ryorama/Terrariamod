package com.ryorama.terrariamod.buffs;

import com.ryorama.terrariamod.buffs.BuffT;

import java.util.List;

public interface CustomBuffAccessor {
    public List<BuffT> GetActiveBuffs();

    public void AddBuff(float duration, BuffT buff);
}
