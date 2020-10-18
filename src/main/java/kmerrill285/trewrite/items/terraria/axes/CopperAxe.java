package kmerrill285.trewrite.items.terraria.axes;

import java.util.List;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CopperAxe extends Axe {

	public CopperAxe() {
		super();
		this.axe = 35;
		this.damage = 3;
		this.knockback = 4.5f;
		this.useTime = 30;
		this.speed = 21;
		this.sellPrice = 80;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("copper_axe");
		this.range = -1;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Bonus: -1 range"));
	}

}
