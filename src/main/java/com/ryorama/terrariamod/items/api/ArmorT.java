package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.core.client.inventory.InventorySlot;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;

public class ArmorT extends ItemT {

    public enum ArmorType {
        HEAD,
        CHEST,
        LEGS
    }

    public ArmorType type;

    public ArmorT(Settings settings, ArmorType type, int defense) {
        super(settings);
        this.defense = defense;
        this.type = type;
    }

    public ArmorMaterial getArmorMaterial() {
        return ArmorMaterials.TURTLE;
    }

    public int getDefense(InventorySlot[] armor) {
        if (this == ItemsT.WOODEN_HELMET) {
            if (armor[1].stack != null && armor[2].stack != null)
                if (armor[1].stack.item == ItemsT.WOODEN_CHESTPLATE && armor[2].stack.item == ItemsT.WOODEN_LEGGINGS) {
                    return defense + 1;
                }
        }

        /*
        if (this == ItemsT.RICH_MAHOGANY_HELMET) {
            if (armor[1].stack != null && armor[2].stack != null)
                if (armor[1].stack.item == ItemsT.RICH_MAHOGANY_BREASTPLATE && armor[2].stack.item == ItemsT.RICH_MAHOGANY_GREAVES) {
                    return defense + 1;
                }
        }
        if (this == ItemsT.SILVER_HELMET) {
            if (armor[1].stack != null && armor[2].stack != null)
                if (armor[1].stack.item == ItemsT.SILVER_CHESTPLATE && armor[2].stack.item == ItemsT.SILVER_GREAVES) {
                    return defense + 3;
                }
        }
         */

        return defense;
    }
}
