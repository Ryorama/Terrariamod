package kmerrill285.trewrite.items.terraria.hammers;

import java.util.List;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.items.Hammer;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WoodenHammer extends Hammer {

	public WoodenHammer() {
		super();
		this.hammer = 25;
		this.damage = 2;
		this.knockback = 5.5f;
		this.useTime = 37;
		this.speed = 25;
		this.sellPrice = 10;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("wooden_hammer");
		this.range = -1;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Bonus: -1 range"));
	}
	
	public void addCraftingRecipes() {
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOODEN_HAMMER, 1), BlocksT.WORKBENCH, new ItemStackT(ItemsT.WOOD, 8)));
	}

}
