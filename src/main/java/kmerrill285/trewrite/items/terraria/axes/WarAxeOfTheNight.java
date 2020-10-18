package kmerrill285.trewrite.items.terraria.axes;

import java.util.List;

import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WarAxeOfTheNight extends Axe {

	public WarAxeOfTheNight() {
		super();
		this.axe = 75;
		this.damage = 20;
		this.knockback = 6;
		this.useTime = 30;
		this.speed = 15;
		this.sellPrice = 2700;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("war_axe_of_the_night");
		
	}

}
