package kmerrill285.trewrite.items;

import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;

public class SummoningItem extends ItemT {
	public SummoningItem(String name) {
		super(new Properties().group(ItemGroup.COMBAT), name);
		this.setMaxStack(1);
		this.MODIFIER_TYPE = EnumModifierType.MAGIC;
		
		this.animation = ItemT.STAFF_ANIMATION;
	}
	
	public static int getMaxSummons(PlayerEntity player) {
		return 1;
	}
}
