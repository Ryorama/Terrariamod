package kmerrill285.trewrite.items.terraria.axes;

import java.util.List;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CobaltWarAxe extends Axe {

	public CobaltWarAxe() {
		super();
		this.axe = 70;
		this.damage = 33;
		this.knockback = 5;
		this.useTime = 35;
		this.speed = 13;
		this.sellPrice = 80;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("cobalt_axe");
		this.range = -1;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Bonus: -1 range"));
	}
	
}
