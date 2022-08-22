package com.ryorama.terrariamod.crafting;

import com.ryorama.terrariamod.blocks.BlocksT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class CraftingRecipe {
    public ItemStack[] input;
    public ItemStack output;
    public Block block;
    public boolean usesWater;

    public CraftingRecipe(ItemStack output, Block block, ItemStack... itemStackT) {
        this.input = itemStackT;
        this.output = output;
        this.block = block;
    }

    public CraftingRecipe(ItemStack output, boolean usesWater, ItemStack... itemStackT) {
        this.input = itemStackT;
        this.output = output;
        this.usesWater = usesWater;
    }
}
