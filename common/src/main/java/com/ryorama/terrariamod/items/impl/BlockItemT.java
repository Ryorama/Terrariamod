package com.ryorama.terrariamod.items.impl;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.impl.interfaces.IRareItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class BlockItemT extends BlockItem {
    public int rarity;

    public BlockItemT(Block block, Settings settings) {
        super(block, settings.arch$tab(ItemsT.TERRARIAMOD_GROUP));
    }

    public BlockItemT setRarity(int rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public Text getName(ItemStack stack) {
        if (rarity == 1) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.GREY);
        } else if (rarity == 2) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.WHITE);
        } else if (rarity == 3) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.BLUE);
        } else if (rarity == 4) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.GREEN);
        } else if (rarity == 5) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.ORANGE);
        } else if (rarity == 6) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.LIGHT_RED);
        } else if (rarity == 7) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.LIGHT_PURPLE);
        } else if (rarity == 8) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.YELLOW);
        } else if (rarity == 9) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.RED);
        } else if (rarity == 10) {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.PURPLE);
        } else {
            return Text.translatable(this.getTranslationKey(stack)).formatted(IRareItem.WHITE);
        }
    }
}
