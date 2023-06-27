package com.ryorama.terrariamod.buffs.terraria.helpful;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.util.Identifier;

public class RegenerationBuff extends BuffT {
    public RegenerationBuff() {
        super("Regeneration", false, new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/regeneration.png"));
    }

    @Override
    public void tick() {
        super.tick();

        if (isActive) {
            System.out.println("Has regen");
        }
    }
}
