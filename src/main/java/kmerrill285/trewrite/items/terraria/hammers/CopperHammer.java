package kmerrill285.trewrite.items.terraria.hammers;

import java.util.List;

import kmerrill285.trewrite.items.Hammer;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CopperHammer extends Hammer {

	public CopperHammer() {
		super();
		this.hammer = 35;
		this.damage = 4;
		this.knockback = 5.5f;
		this.useTime = 33;
		this.speed = 23;
		this.sellPrice = 80;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("copper_hammer");
		this.range = -1;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Bonus: -1 range"));
	}

}
