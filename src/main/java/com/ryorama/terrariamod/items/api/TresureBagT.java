package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.utils.math.ImprovedRandom;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.*;

public class TresureBagT extends ItemT {

    public Map<ItemStack, Integer> loot = new HashMap<>();
    //public List<Float> chance = new ArrayList<>();

    public TresureBagT(Settings settings) {
        super(settings);
        isConsumable(true);
    }

    public void addLoot(ItemStack stack, int chance) {
        this.loot.put(stack, chance);
    }

    public void addContentsToLoot() {
        loot.clear();
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
        if (!world.isClient()) {
            addContentsToLoot();

            System.out.println(loot.size());
            if (loot.size() > 0) {
                for (Map.Entry e : loot.entrySet()) {
                    System.out.println(e.getValue());
                    if (e.getValue().equals(-1)) {
                        ItemEntity drop = new ItemEntity(world, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), (ItemStack) e.getKey());
                        drop.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
                        world.spawnEntity(drop);
                    } else if (new ImprovedRandom(rand).next(0, (Integer) e.getValue()) == 0) {
                        ItemEntity drop = new ItemEntity(world, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), (ItemStack) e.getKey());
                        drop.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
                        world.spawnEntity(drop);
                    }
                }
                playerEntity.getInventory().getMainHandStack().decrement(1);
            }
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
