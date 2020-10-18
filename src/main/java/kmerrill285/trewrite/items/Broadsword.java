package kmerrill285.trewrite.items;

import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Broadsword extends ItemT {

	public Broadsword() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1));
		this.melee = true;
		this.maxStack = 1;
		MODIFIER_TYPE = EnumModifierType.MELEE;
		
		this.animation = ItemT.BROADSWORD_ANIMATION;
		this.scale = 1.25f;
	}
}

