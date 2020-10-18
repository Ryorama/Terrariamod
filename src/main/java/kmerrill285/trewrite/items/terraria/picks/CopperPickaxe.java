package kmerrill285.trewrite.items.terraria.picks;

import java.util.List;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CopperPickaxe extends Pickaxe {

	public CopperPickaxe() {
		super();
		this.pick = 35;
		this.damage = 4;
		this.knockback = 2f;
		this.useTime = 23;
		this.speed = 15;
		this.sellPrice = 100;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("copper_pickaxe");
		this.range = -1;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Bonus: -1 range"));
	}

}
