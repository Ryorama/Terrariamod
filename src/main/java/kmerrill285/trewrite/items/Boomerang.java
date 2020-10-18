package kmerrill285.trewrite.items;

import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;


public class Boomerang extends ItemT {

	
	public Boomerang(String name) {
		super(new Properties().group(ItemGroup.COMBAT), name);
		this.knockback = 8;
		this.damage = 13;
		this.velocity = 10;
		this.useTime = 15;
		this.melee = true;
		this.setBuySell(10000);
		this.MODIFIER_TYPE = EnumModifierType.MELEE;
		this.setMaxStack(1);
		
		this.animation = ItemT.THROWING_ANIMATION;
	}
	
	public void onHitEntity(Entity entity) {
		
	}
}

