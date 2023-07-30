package com.ryorama.terrariamod.items.terraria.picks;

import com.ryorama.terrariamod.items.impl.PickaxeT;

public class PlatinumPickaxe extends PickaxeT {

	public PlatinumPickaxe() {
		super();
		this.pick = 59;
		this.damage = 6;
		this.knockback = 2;
		this.useTime = 20;
		this.speed = 15;
		this.sellPrice = 2000;
		this.setTooltip("Can mine Meteorite");
	}

}
