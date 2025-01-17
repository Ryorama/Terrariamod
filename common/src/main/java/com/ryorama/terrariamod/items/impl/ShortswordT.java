package com.ryorama.terrariamod.items.impl;

public class ShortswordT extends ItemT {

    public ShortswordT() {
        super(new Properties().stacksTo(1));
        this.melee = true;
        this.maxStack = 1;

        this.animation = ItemT.SHORTSWORD_ANIMATION;
    }
}