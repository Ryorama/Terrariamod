package com.ryorama.terrariamod.items.terraria.broadswords;

import com.ryorama.terrariamod.items.impl.BroadswordT;

public class FieryGreatsword extends BroadswordT {
	public FieryGreatsword() {
		super();
		this.damage = 36;
		this.knockback = 6.5f;
		this.useTime = 34;
		this.sellPrice = 5400;
		this.setTooltip("It's made out of fire!");
		this.setMaterial();
		this.scale = 2.0f;
	}
}
