package com.ryorama.terrariamod.items.tresurebags;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.api.TresureBagT;
import com.ryorama.terrariamod.utils.math.ImprovedRandom;
import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.item.ItemStack;

public class EyeOfCthulhuTresureBag extends TresureBagT {

    public EyeOfCthulhuTresureBag(Settings settings) {
        super(settings);
    }

    @Override
    public void addContentsToLoot() {
        super.addContentsToLoot();
        //ToDo
        /*
        if (WorldDataT.isCorruption) {
            addLoot(new ItemStack(BlocksT.DEMONITE_ORE, 1), -1);
        } else if (WorldDataT.isCrimson) {
            addLoot(new ItemStack(BlocksT.DEMONITE_ORE, 1), -1);
        }
        */

        addLoot(new ItemStack(BlocksT.DEMONITE_ORE, new ImprovedRandom(rand).next(20, 50)), -1);
        addLoot(new ItemStack(ItemsT.SHIELD_OF_CTHULHU, 1), -1);
    }

    @Override
    public boolean isBossBag() {
        return true;
    }
}
