package com.ryorama.terrariamod.items.impl;

import net.minecraft.item.ItemGroups;

public class ShortswordT extends ItemT {

    public ShortswordT() {
        super(new Settings().arch$tab(ItemGroups.COMBAT).maxCount(1));
        this.melee = true;
        this.maxStack = 1;

        this.animation = ItemT.SHORTSWORD_ANIMATION;
    }
}