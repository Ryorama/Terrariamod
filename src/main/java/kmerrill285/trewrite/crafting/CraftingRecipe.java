package kmerrill285.trewrite.crafting;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import net.minecraft.block.Block;

public class CraftingRecipe {
   public ItemStackT[] input;
   public ItemStackT output;
   public Block block;
   public boolean usesWater;

   public CraftingRecipe(ItemStackT output, Block block, ItemStackT... itemStackT) {
      this.input = itemStackT;
      this.output = output;
      this.block = block;
   }

   public CraftingRecipe(ItemStackT output, String block, ItemStackT... itemStackT) {
      this.input = itemStackT;
      this.output = output;
      this.block = (Block)BlocksT.BLOCKS.get(block);
   }

   public CraftingRecipe(ItemStackT output, boolean usesWater, ItemStackT... itemStackT) {
      this.input = itemStackT;
      this.output = output;
      this.usesWater = usesWater;
   }
}
