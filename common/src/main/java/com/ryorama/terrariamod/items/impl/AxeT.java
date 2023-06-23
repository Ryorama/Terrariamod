package com.ryorama.terrariamod.items.impl;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;

public class AxeT extends ItemT {

    public AxeT() {
        super(new Settings().maxCount(1));
        this.melee = true;
        this.maxStack = 1;
        this.animation = ItemT.PICKAXE_ANIMATION;
        this.scale = 2.0;
    }
}