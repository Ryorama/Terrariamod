package com.ryorama.terrariamod.items.weapons.broadswords;

import com.ryorama.terrariamod.items.api.Broadsword;

public class FieryGreatsword extends Broadsword {	
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
