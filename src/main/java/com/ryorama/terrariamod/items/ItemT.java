package com.ryorama.terrariamod.items;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;
import com.ryorama.terrariamod.util.Conversions;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ItemT extends Item {


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
	
	public int speed;
	
	public boolean material;
	
	public String itemName;
	
	public int maxStack = 999;
	
	public int range = 0;
	
	public int lightValue;
	
	public double saturation, nutrition;
	
	public UseAction useAction = UseAction.NONE;
	
	protected ItemT(Properties properties) {
		super(properties);
	}
	
	protected ItemT() {
		super (new Properties());
	}
	
	public ItemT(Properties properties, String name) {
		super(properties);
		this.setLocation(name);
	}
	
	public ItemT setPotionSickness(int n) {
		this.potionSickness = n;
		return this;
	}
	
	public ItemT setTooltip(String tooltip) {
		this.tooltip = tooltip;
		return this;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		
		if (pick > 0)
			tooltip.add(new StringTextComponent(pick + "% pickaxe power"));
		if (axe > 0)
			tooltip.add(new StringTextComponent(axe + "% axe power"));
		if (hammer > 0)
			tooltip.add(new StringTextComponent(hammer + "% hammer power"));
		if (melee)
			tooltip.add(new StringTextComponent(damage + " melee damage"));
		if (ranged)
			tooltip.add(new StringTextComponent(damage + " ranged damage"));
		if (throwing)
			tooltip.add(new StringTextComponent(damage + " throwing damage"));
		if (summon)
			tooltip.add(new StringTextComponent(damage + " summon damage"));
		if (magic)
			tooltip.add(new StringTextComponent(damage + " magic damage"));
		if (velocity > 0)
			tooltip.add(new StringTextComponent(velocity + " velocity"));
		if (defense > 0)
			tooltip.add(new StringTextComponent(defense + " defense"));
		if (mana > 0)
			tooltip.add(new StringTextComponent("uses " + damage + " mana"));
		if (critChance > 0 && damage > 0)
			tooltip.add(new StringTextComponent(new String(critChance + "% critical strike chance").replace(".0", "")));
		if (isAmmo)
			tooltip.add(new StringTextComponent("ammo"));
		if (heal > 0)
			tooltip.add(new StringTextComponent("heals " + heal + " health"));
		if (manaHeal > 0)
			tooltip.add(new StringTextComponent("restores " + manaHeal + " mana"));
		if (consumable)
			tooltip.add(new StringTextComponent("consumable"));
		if (accessory)
			tooltip.add(new StringTextComponent("accessory"));
		if (material)
			tooltip.add(new StringTextComponent("material"));
		if (tooltip.isEmpty() == false)
			tooltip.add(new StringTextComponent(""+this.tooltip));
		
	}
	
	public int getLightValue() {
		return this.lightValue;
	}
	
	public int getUseDuration(ItemStack stack) {
		return useTime;
	}
	
	public UseAction getUseAction(ItemStack stack) {
		return useAction;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		onUse(null, null, playerIn, worldIn, handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
	
	
	public void onUse(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		
	}
	
	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		
	}
	
	public void setCraftingRecipes() {
		
	}
	
	public boolean onAttack(Entity target, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn, double attackMod) {
		
			
		
		if (target != null) {
			if (new Random().nextInt(100) <= critChance) {
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (damage * 2 * attackMod));
			}
			else
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (damage * attackMod));
		}
		return true;
	}
	
	protected void setLocation(String name) {
		this.setRegistryName(new ResourceLocation("trewrite", name));
		ItemsT.items.put(ItemsT.getStringForItem(this), this);
		this.itemName = name;
	}
	
	
	
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
	      Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
	      if (equipmentSlot == EquipmentSlotType.MAINHAND) {
	    	  
	    	  if (this.melee) {
	    		  multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double)this.damage - 1, Operation.ADDITION));
	 	          multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", this.useTime - (this.useTime * 1.1) + 1, Operation.ADDITION));
	    	  } else {
	 	          multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", this.useTime - (this.useTime * 1.1) + 1, Operation.ADDITION));
	    	  }
	        }

	      return multimap;
	 }
	
	public ItemT setFoodStats(double nutrition, double saturation) {
		this.nutrition = nutrition;
		this.saturation = saturation;
		return this;
	}

	public ItemT setBuySell(int sell) {
		this.sellPrice = sell;
		this.buyPrice = Conversions.sellToBuy(sell);
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
