package com.ryorama.terrariamod.items.terraria.picks;

import com.ryorama.terrariamod.items.impl.PickaxeT;

public class GoldPickaxe extends PickaxeT {

	public GoldPickaxe() {
		super();
		this.pick = 55;
		this.damage = 6;
		this.knockback = 2;
		this.useTime = 20;
		this.speed = 17;
		this.sellPrice = 2000;
		this.setTooltip("Can mine Meteorite");
	}

}
