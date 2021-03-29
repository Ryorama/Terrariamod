package com.ryorama.terrariamod.items.tools.picks;

import com.ryorama.terrariamod.items.api.Pickaxe;

public class DemonitePickaxe extends Pickaxe {

	public DemonitePickaxe() {
		super();
		this.pick = 65;
		this.damage = 9;
		this.knockback = 3;
		this.useTime = 20;
		this.speed = 15;
		this.sellPrice = 2000;
		this.setTooltip("Can mine Hellstone");
	}

}
