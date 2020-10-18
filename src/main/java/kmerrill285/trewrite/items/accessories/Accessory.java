package kmerrill285.trewrite.items.accessories;

import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class Accessory extends ItemT {
	
	public int extraMana = 0;
	
    public enum AccessoryType {
    	WATCH, DEPTH_METER, BAND_OF_REGENERATION
    }
    
    public enum WearSlot {
    	NONE, HEAD, CHEST, SINGLE_LEG, SINGLE_ARM, WINGS, DIVING_FINS, BOTH_LEGS, BOTH_ARMS, GLOVES, FEET
    }
    
    public String wearTexture;
    public WearSlot wearSlot = WearSlot.NONE;
    
	public Accessory(Properties properties, String name) {
		super(properties, name);
		accessory = true;
		this.MODIFIER_TYPE = EnumModifierType.ACCESSORY;
		this.setMaxStack(1);
	}
	
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}
	
	public Accessory setWearable(WearSlot wearSlot) {
		this.wearSlot = wearSlot;
		this.wearTexture = "trewrite:textures/models/accessory/"+this.itemName+".png";
		return this;
	}
	
	public void accessoryTick(PlayerEntity player) {
		
	}
	
	public void accessoryTickClient(PlayerEntity player) {
		
	}
}
