package com.ryorama.terrariamod.items.impl;

import net.minecraft.item.ItemGroups;

public class AxeT extends ItemT {

    public AxeT() {
        super(new Settings().arch$tab(ItemGroups.TOOLS).maxCount(1));
        this.melee = true;
        this.maxStack = 1;
        this.animation = ItemT.PICKAXE_ANIMATION;
        this.scale = 2.0;
    }
}