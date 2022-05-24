package com.ryorama.terrariamod.items.api;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TresureBagT extends ItemT {

    public List<ItemStack> loot = new ArrayList<>();

    public TresureBagT(Settings settings) {
        super(settings);
        isConsumable(true);
    }

    public void addLoot(ItemStack stack) {
        this.loot.add(stack);
    }

    public boolean isBossBag() {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, worldIn, tooltip, context);
        if (isBossBag()) {
            tooltip.add(new TranslatableText("Expert").formatted(Formatting.LIGHT_PURPLE));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (this.loot.size() > 0) {
            for (int e = 0; e <= this.loot.size() - 1; e++) {
                ItemEntity drop = new ItemEntity(world, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), this.loot.get(e));
                drop.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
                world.spawnEntity(drop);
            }
            playerEntity.getInventory().getMainHandStack().decrement(1);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
