package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.items.api.IRareItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ItemT extends Item {

	public int rarity;
	
	public ItemT(Settings settings) {
		super(settings);
	}
	
	public ItemT setRarity(int rarity) {
		this.rarity = rarity;
		return this;
	}
	
	@Override
	public Text getName(ItemStack stack) {
		if (rarity == 1) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.GREY);
		} else if (rarity == 2) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.WHITE);
		} else if (rarity == 3) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.BLUE);
		} else if (rarity == 4) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.GREEN);
		} else if (rarity == 5) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.ORANGE);
		} else if (rarity == 6) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.LIGHT_RED);
		} else if (rarity == 7) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.LIGHT_PURPLE);
		} else if (rarity == 8) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.YELLOW);
		} else if (rarity == 9) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.RED);
		} else if (rarity == 10) {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.PURPLE);
		} else {
			return new TranslatableText(this.getTranslationKey(stack)).formatted(IRareItem.WHITE);
		}
	}
	
	 public ItemStack stack(int i) {
		return new ItemStack(this, i);
	 }
}
 