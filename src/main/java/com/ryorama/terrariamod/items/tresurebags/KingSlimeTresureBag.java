package com.ryorama.terrariamod.items.tresurebags;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.api.TresureBagT;
import net.minecraft.item.ItemStack;

public class KingSlimeTresureBag extends TresureBagT {

    public KingSlimeTresureBag(Settings settings) {
        super(settings);

        addLoot(new ItemStack(ItemsT.NINJA_HELMET, 1));
        addLoot(new ItemStack(ItemsT.NINJA_CHESTPLATE, 1));
        addLoot(new ItemStack(ItemsT.NINJA_LEGGINGS, 1));
        addLoot(new ItemStack(ItemsT.ROYAL_GEL, 1));
    }

    @Override
    public boolean isBossBag() {
        return true;
    }
}
