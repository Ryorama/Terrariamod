package com.ryorama.terrariamod.items.impl;

import net.minecraft.world.item.Item;

public class ArrowT extends ItemT {

    public boolean fireDamage = false;

    public int arrowDamage = 1;

    public ArrowT(Item.Properties settings) {
        super(settings);
    }
}