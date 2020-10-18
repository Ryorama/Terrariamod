package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.entities.projectiles.EntityArrowT;
import kmerrill285.trewrite.items.Bow;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.terraria.arrows.WoodenArrow;
import net.minecraft.item.ItemGroup;

public class MoltenFury extends Bow {
	public MoltenFury() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "molten_fury", 0);
		this.knockback = 2;
		this.useTime = 22;
		this.velocity = 8f;
		this.damage = 31;
		this.setBuySell(5400);
		this.setTooltip("Lights wooden arrows ablaze");
		
	}
	
	public void onShoot(EntityArrowT arrow) {
		
		if (arrow.arrow instanceof WoodenArrow) {
			arrow.arrow = ItemsT.FLAMING_ARROW;
			arrow.setFire(10);
		}
	}
	
	public void setCraftingRecipes() {
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MOLTEN_FURY, 1), BlocksT.IRON_ANVIL, new ItemStackT(ItemsT.HELLSTONE_BAR, 15)));
	}
}
