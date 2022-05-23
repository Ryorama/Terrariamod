package com.ryorama.terrariamod.items.tresurebags;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.api.TresureBagT;
import net.minecraft.item.ItemStack;

import java.util.List;

public class KingSlimeTresureBag extends TresureBagT {

    public KingSlimeTresureBag(Settings settings) {
        super(settings);
    }

    public void addLoot(List<ItemStack> loot) {
        loot.add(new ItemStack(ItemsT.NINJA_HELMET, 1));
        loot.add(new ItemStack(ItemsT.NINJA_CHESTPLATE, 1));
        loot.add(new ItemStack(ItemsT.NINJA_LEGGINGS, 1));
    }
}
