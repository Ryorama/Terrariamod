package com.ryorama.terrariamod.items.impl;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.impl.interfaces.IRareItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class BlockItemT extends BlockItem {
    public int rarity;

    public BlockItemT(Block block, Properties settings) {
        super(block, settings.arch$tab(ItemsT.TERRARIAMOD_GROUP));
    }

    public BlockItemT setRarity(int rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public Component getName(ItemStack stack) {
        if (rarity == 1) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.GREY);
        } else if (rarity == 2) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.WHITE);
        } else if (rarity == 3) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.BLUE);
        } else if (rarity == 4) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.GREEN);
        } else if (rarity == 5) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.ORANGE);
        } else if (rarity == 6) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.LIGHT_RED);
        } else if (rarity == 7) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.LIGHT_PURPLE);
        } else if (rarity == 8) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.YELLOW);
        } else if (rarity == 9) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.RED);
        } else if (rarity == 10) {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.PURPLE);
        } else {
            return Component.translatable(this.getDescriptionId(stack)).withStyle(IRareItem.WHITE);
        }
    }
}
