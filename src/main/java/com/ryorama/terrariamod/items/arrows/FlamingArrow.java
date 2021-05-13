package com.ryorama.terrariamod.items.arrows;

import com.ryorama.terrariamod.items.Arrow;

public class FlamingArrow extends Arrow {

	public FlamingArrow(Settings settings) {
		super(settings);
		this.arrowDamage = 6;
		this.fireDamage = true;
	}
}
