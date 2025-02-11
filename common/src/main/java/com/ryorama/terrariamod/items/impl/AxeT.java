package com.ryorama.terrariamod.items.impl;

import net.minecraft.world.item.Item;

public class AxeT extends ItemT {

    public AxeT() {
        super(new Item.Properties().stacksTo(1));
        this.melee = true;
        this.maxStack = 1;
        this.animation = ItemT.PICKAXE_ANIMATION;
        this.scale = 2.0;
    }
}