package kmerrill285.trewrite.items.terraria.potions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class PotionTest extends Potion {

	public PotionTest(Properties properties, String name, boolean consumable, boolean autoBuff) {
		super(properties, name, consumable, autoBuff);
	}

	@Override
	protected boolean doPotionStuff(World world, PlayerEntity player) {
		return false;
	}

}
