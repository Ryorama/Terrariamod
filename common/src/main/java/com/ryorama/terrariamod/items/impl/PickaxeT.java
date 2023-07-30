package com.ryorama.terrariamod.items.impl;

public class PickaxeT extends ItemT {

    public PickaxeT() {
        super(new Settings().maxCount(1));
        this.melee = true;
        this.maxStack = 1;
        this.animation = ItemT.PICKAXE_ANIMATION;
    }
}