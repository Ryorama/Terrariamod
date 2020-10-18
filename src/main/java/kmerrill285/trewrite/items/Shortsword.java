package kmerrill285.trewrite.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Shortsword extends Broadsword {

	public Shortsword() {
		super();
		this.melee = true;
		this.scale = 1.0;
		this.animation = ItemT.SHORTSWORD_ANIMATION;
	}
	
}

