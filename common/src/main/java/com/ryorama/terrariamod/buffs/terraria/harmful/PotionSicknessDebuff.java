package com.ryorama.terrariamod.buffs.terraria.harmful;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.util.Identifier;

public class PotionSicknessDebuff extends BuffT {
    public PotionSicknessDebuff() {
        super(new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/potion_sickness.png"));
    }
}
