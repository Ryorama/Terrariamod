package com.ryorama.terrariamod.items.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ryorama.terrariamod.items.ItemModifier;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;

public class ItemStackT {
	
	   public Item item;
	   public int size;
	   public ItemStack itemForRender;
	   public int modifier;
	   public int renderStack;

	   public ItemStackT(Item item, ItemModifier modifier) {
	      this.item = item;
	      this.size = 1;
	      this.itemForRender = new ItemStack(item);
	      this.modifier = ItemModifier.getIdForModifier(modifier);
	   }

	   public ItemStackT(String item, ItemModifier modifier) {
	      this.item = ItemsT.getItemFromString(item);
	      this.size = 1;
	      this.itemForRender = new ItemStack(ItemsT.getItemFromString(item));
	      this.modifier = ItemModifier.getIdForModifier(modifier);
	   }

	   public ItemStackT(Item item) {
	      this.item = item;
	      this.size = 1;
	      this.itemForRender = new ItemStack(item);
	      this.modifier = -1;
	   }

	   public ItemStackT(String item) {
	      this.item = ItemsT.getItemFromString(item);
	      this.size = 1;
	      this.itemForRender = new ItemStack(ItemsT.getItemFromString(item));
	      this.modifier = -1;
	   }

	   public ItemStackT(Item item, int size) {
	      this.item = item;
	      this.size = size;
	      this.itemForRender = new ItemStack(item);
	      this.modifier = -1;
	   }

	   public ItemStackT(String item, int size) {
	      System.out.println(item);
	      this.item = ItemsT.getItemFromString(item);
	      this.size = size;
	      this.itemForRender = new ItemStack(ItemsT.getItemFromString(item));
	      this.modifier = -1;
	   }

	   public ItemStackT(Item item, int size, ItemModifier modifier) {
	      this.item = item;
	      this.size = size;
	      this.itemForRender = new ItemStack(item);
	      if (modifier != null) {
	         double speed = modifier.speed;
	         if (ItemModifier.getModifier(this.modifier) != null) {
	            this.itemForRender.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "Tool modifier", ((double)((ItemT)item).useTime * (speed / 100.0D) - (double)((ItemT)item).useTime * (speed / 100.0D) * 1.1D + 1.0D) * (speed / 100.0D), Operation.ADDITION), EquipmentSlot.MAINHAND);
	         }
	      }

	      this.modifier = ItemModifier.getIdForModifier(modifier);
	   }

	   public ItemStackT(String item, int size, ItemModifier modifier) {
	      this(item, size);
	      if (modifier != null) {
	         double speed = modifier.speed;
	         if (ItemModifier.getModifier(this.modifier) != null) {
	            this.itemForRender.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "Tool modifier", ((double)((ItemT)this.item).useTime * (speed / 100.0D) - (double)((ItemT)this.item).useTime * (speed / 100.0D) * 1.1D + 1.0D) * (speed / 100.0D), Operation.ADDITION), EquipmentSlot.MAINHAND);
	         }
	      }

	      this.modifier = ItemModifier.getIdForModifier(modifier);
	   }

	   public void reforge(Item item) {
	      this.modifier = ItemModifier.getIdForModifier(ItemModifier.getRandomModifier(item));
	      if (ItemModifier.getModifier(this.modifier) != null) {
	         double speed = ItemModifier.getModifier(this.modifier).speed;
	         this.itemForRender.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"), "Tool modifier", (double)((ItemT)item).useTime * (speed / 100.0D) - (double)((ItemT)item).useTime * (speed / 100.0D) * 1.1D + 1.0D, Operation.ADDITION), EquipmentSlot.MAINHAND);
	      }

	   }

	   public List getTooltip(PlayerEntity player, TooltipContext advanced) {
	      List tooltips = new ArrayList();
	      ItemModifier mod = ItemModifier.getModifier(this.modifier);
	      if (this.itemForRender != null) {
	         tooltips.addAll(this.itemForRender.getTooltip(player, advanced));
	      }

	      if (mod != null) {
	         tooltips.add(new TranslatableText(mod.name));
	         mod.addTooltips(tooltips);
	      }

	      return tooltips;
	   }
	}