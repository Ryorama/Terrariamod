package com.ryorama.terrariamod.items.tools.picks;

import com.ryorama.terrariamod.items.api.Pickaxe;

public class DeathbringerPickaxe extends Pickaxe {

	public DeathbringerPickaxe() {
		super();
		this.pick = 70;
		this.damage = 12;
		this.knockback = 3.5f;
		this.useTime = 22;
		this.speed = 14;
		this.sellPrice = 2000;
		this.setTooltip("Can mine Hellstone");
	}

}
