package com.ryorama.terrariamod.items.impl;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;

public class BroadswordT extends ItemT {

    public BroadswordT() {
        super(new Settings().maxCount(1));
        this.melee = true;
        this.maxStack = 1;

        this.animation = ItemT.BROADSWORD_ANIMATION;
    }
}