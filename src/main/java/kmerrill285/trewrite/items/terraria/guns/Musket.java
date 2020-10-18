package kmerrill285.trewrite.items.terraria.guns;

import kmerrill285.trewrite.items.Gun;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;

public class Musket extends Gun {
	public Musket() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "musket", 31);
		this.knockback = 5.25f;
		this.useTime = 36;
		this.velocity = 9;
		this.setBuySell(20000);
		this.critChance = 11;
		this.scale = 2.0f;
		
		rotX = 45;
		offsY = -scale * 0.10;
		offsZ = -scale * 0.25;
	}
}
