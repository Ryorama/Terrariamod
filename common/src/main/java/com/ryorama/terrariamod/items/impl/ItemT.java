package com.ryorama.terrariamod.items.impl;

import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.impl.enums.EnumModifierType;
import com.ryorama.terrariamod.items.impl.interfaces.IRareItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

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

    public UseAction useAction = UseAction.NONE;

    public Random rand = new Random();

    public ItemT(Item.Settings settings) {
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

	/*
	@Override
	public String getTranslationKey(ItemStack stack) {
		return nameFormatting + this.getName(stack);
	}
	 */

    @Override
    public Text getName(ItemStack stack) {
        if (rarity == 1) {
            return Text.translatable(nameFormatting + this.getTranslationKey(stack)); //.formatted(IRareItem.GREY)
        } else if (rarity == 2) {
            return Text.translatable(this.getTranslationKey(stack)); //.formatted(IRareItem.WHITE)
        } else if (rarity == 3) {
            return Text.translatable(nameFormatting + this.getTranslationKey(stack)); //.formatted(IRareItem.BLUE)
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

    public ItemT setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext context) {
        if (consumable) {
            tooltip.add(Text.translatable("Consumable"));
        }
        if (isExpert) {
            tooltip.add(Text.translatable("Expert").formatted(Formatting.LIGHT_PURPLE));
        }

        if (pick > 0)
            tooltip.add(Text.translatable(pick + "% pickaxe power"));
        if (axe > 0)
            tooltip.add(Text.translatable(axe + "% axe power"));
        if (hammer > 0)
            tooltip.add(Text.translatable(hammer + "% hammer power"));
        if (melee)
            tooltip.add(Text.translatable(damage + " melee damage"));
        if (ranged)
            tooltip.add(Text.translatable(damage + " ranged damage"));
        if (throwing)
            tooltip.add(Text.translatable(damage + " throwing damage"));
        if (summon)
            tooltip.add(Text.translatable(damage + " summon damage"));
        if (magic)
            tooltip.add(Text.translatable(damage + " magic damage"));
        if (velocity > 0)
            tooltip.add(Text.translatable(velocity + " velocity"));
        if (defense > 0)
            tooltip.add(Text.translatable(defense + " defense"));
        if (mana > 0)
            tooltip.add(Text.translatable("uses " + damage + " mana"));
        if (critChance > 0 && damage > 0)
            tooltip.add(Text.translatable(new String(critChance + "% critical strike chance").replace(".0", "")));
        if (isAmmo)
            tooltip.add(Text.translatable("ammo"));
        if (heal > 0)
            tooltip.add(Text.translatable("heals " + heal + " health"));
        if (manaHeal > 0)
            tooltip.add(Text.translatable("restores " + manaHeal + " mana"));
        if (accessory)
            tooltip.add(Text.translatable("accessory"));
        if (material)
            tooltip.add(Text.translatable("material"));
        if (!this.tooltip.equals(""))
            tooltip.add(Text.translatable(""+this.tooltip));
    }

    public ItemStack stack(int i) {
        return new ItemStack(this, i);
    }

    public int getUseDuration(ItemStack stack) {
        return useTime;
    }

    public UseAction getUseAction(ItemStack stack) {
        return useAction;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target != null) {
            DamageSources source = target.getWorld().getDamageSources();
            if (attacker instanceof PlayerEntity)
                if (new Random().nextInt(100) <= critChance) {
                    target.damage(source.playerAttack((PlayerEntity) attacker), damage * 2);
                }
                else {
                    target.damage(source.playerAttack((PlayerEntity) attacker), damage);
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