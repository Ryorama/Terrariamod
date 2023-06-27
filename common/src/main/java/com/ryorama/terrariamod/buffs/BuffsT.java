package com.ryorama.terrariamod.buffs;

import com.ryorama.terrariamod.buffs.terraria.helpful.RegenerationBuff;

import java.util.ArrayList;
import java.util.List;

public class BuffsT {

    public static List<BuffT> buffs = new ArrayList<>();

    public static RegenerationBuff REGENERATION = new RegenerationBuff();

    public static void init() {
        buffs.add(REGENERATION);
    }
}
