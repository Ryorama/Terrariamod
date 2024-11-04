package com.ryorama.terrariamod.items.impl;

public class BroadswordT extends ItemT {

    public BroadswordT() {
        super(new Settings().maxCount(1));
        this.melee = true;
        this.maxStack = 1;

        this.animation = ItemT.BROADSWORD_ANIMATION;
    }
}