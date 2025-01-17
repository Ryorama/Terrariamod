package com.ryorama.terrariamod.items.impl;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.impl.enums.EnumModifierType;
import com.ryorama.terrariamod.items.impl.interfaces.IRareItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class ItemT extends Item {

    public int rarity;

    public int buyPrice;
    public int sellPrice;

    public boolean melee, throwing, ranged, summon, magic;
    public int mana = 0;
    public int damage = 0;
    public float knockback = -1;
    public float critChance = 4;
    public int useTime = -1;
    public float pick, axe, hammer;
    public ItemT ammo;
    public boolean isAmmo;
    public int stackSize;
    public String tooltip = "";
    public int heal;
    public int manaHeal;
    public boolean consumable;
    public boolean autoReuse;
    public boolean accessory;
    public float velocity;
    public int defense;
    public String shoot = "";
    public double scale = 1.0f;
    public double rotX, rotY, rotZ;
    public double offsX, offsY, offsZ;
    public String animation = "";
    public static String BOW_ANIMATION = "bow", STAFF_ANIMATION = "staff", BROADSWORD_ANIMATION = "broadsword", SHORTSWORD_ANIMATION = "shortsword",
            PICKAXE_ANIMATION = "pickaxe", AXE_ANIMATION = "axe", HAMMER_ANIMATION = "hammer", THROWING_ANIMATION = "throwing", BUILDING_ANIMATION = "building",
            GUN_ANIMATION = "gun";

    public int potionSickness, manaSickness;

    public String nameFormatting = "";
    public int speed;
    public boolean isExpert;
    public boolean material;

    public String itemName;

    public int maxStack = 999;

    public int range = 0;

    public int lightValue;

    public double saturation, nutrition;

    public EnumModifierType MODIFIER_TYPE = EnumModifierType.NONE;

    public UseAnim useAction = UseAnim.NONE;

    public Random rand = new Random();

    public ItemT(Properties settings) {
        super(settings.arch$tab(ItemsT.TERRARIAMOD_GROUP));

        if (rarity == 1) {
            nameFormatting = "§525252";
        } else if (rarity == 3) {
            nameFormatting = "§294cff";
        }
    }

    public ItemT setRarity(int rarity) {
        this.rarity = rarity;
        return this;
    }

    public ItemT isConsumable(boolean consumable) {
        this.consumable = consumable;
        return this;
    }

    public ItemT isExpert(boolean isExpert) {
        this.isExpert = isExpert;
        return this;
    }

    @Override
    public Component getName(ItemStack stack) {
        if (rarity == 1) {
            return Component.translatable(nameFormatting + this.getDescriptionId(stack)); //.formatted(IRareItem.GREY)
        } else if (rarity == 2) {
            return Component.translatable(this.getDescriptionId(stack)); //.formatted(IRareItem.WHITE)
        } else if (rarity == 3) {
            return Component.translatable(nameFormatting + this.getDescriptionId(stack)); //.formatted(IRareItem.BLUE)
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

    public ItemT setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag context) {
        if (consumable) {
            tooltip.add(Component.translatable("Consumable"));
        }
        if (isExpert) {
            tooltip.add(Component.translatable("Expert").withStyle(ChatFormatting.LIGHT_PURPLE));
        }

        if (pick > 0)
            tooltip.add(Component.translatable(pick + "% pickaxe power"));
        if (axe > 0)
            tooltip.add(Component.translatable(axe + "% axe power"));
        if (hammer > 0)
            tooltip.add(Component.translatable(hammer + "% hammer power"));
        if (melee)
            tooltip.add(Component.translatable(damage + " melee damage"));
        if (ranged)
            tooltip.add(Component.translatable(damage + " ranged damage"));
        if (throwing)
            tooltip.add(Component.translatable(damage + " throwing damage"));
        if (summon)
            tooltip.add(Component.translatable(damage + " summon damage"));
        if (magic)
            tooltip.add(Component.translatable(damage + " magic damage"));
        if (velocity > 0)
            tooltip.add(Component.translatable(velocity + " velocity"));
        if (defense > 0)
            tooltip.add(Component.translatable(defense + " defense"));
        if (mana > 0)
            tooltip.add(Component.translatable("uses " + damage + " mana"));
        if (critChance > 0 && damage > 0)
            tooltip.add(Component.translatable(new String(critChance + "% critical strike chance").replace(".0", "")));
        if (isAmmo)
            tooltip.add(Component.translatable("ammo"));
        if (heal > 0)
            tooltip.add(Component.translatable("heals " + heal + " health"));
        if (manaHeal > 0)
            tooltip.add(Component.translatable("restores " + manaHeal + " mana"));
        if (accessory)
            tooltip.add(Component.translatable("accessory"));
        if (material)
            tooltip.add(Component.translatable("material"));
        if (!this.tooltip.equals(""))
            tooltip.add(Component.translatable(""+this.tooltip));
    }

    public ItemStack stack(int i) {
        return new ItemStack(this, i);
    }

    public int getUseDuration(ItemStack stack) {
        return useTime;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return useAction;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target != null) {
            DamageSources source = target.level().damageSources();
            if (attacker instanceof Player)
                if (new Random().nextInt(100) <= critChance) {
                    target.hurt(source.playerAttack((Player) attacker), damage * 2);
                }
                else {
                    target.hurt(source.playerAttack((Player) attacker), damage);
                }
        }
        return true;
    }

    public ItemT setFoodStats(double nutrition, double saturation) {
        this.nutrition = nutrition;
        this.saturation = saturation;
        return this;
    }

    public ItemT setLightValue(int light) {
        this.lightValue = light;
        return this;
    }

    public ItemT setConsumable() {
        this.consumable = true;
        return this;
    }

    public ItemT setHeal(int heal) {
        this.heal = heal;
        return this;
    }

    public ItemT setManaHeal(int manaHeal) {
        this.manaHeal = manaHeal;
        return this;
    }

    public ItemT setMaterial() {
        this.material = true;
        return this;
    }

    public ItemT setMaxStack(int maxStack) {
        this.maxStack = maxStack;
        return this;
    }

    public ItemT setManaSickness(int i) {
        this.manaSickness = i;
        return this;
    }

    public ItemT setItemName(String string) {
        this.itemName = string;
        return this;
    }

    public ItemT setAmmo() {
        this.isAmmo = true;
        return this;
    }
}