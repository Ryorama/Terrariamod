package com.ryorama.terrariamod.core.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TerrariaInventoryContainerHandler implements INamedContainerProvider {

    public static final ResourceLocation ID = new ResourceLocation("trewrite", "inventory");

    public String getGuiID() {
        return ID.toString();
    }
    
	@Override
	public Container createMenu(int arg0, PlayerInventory arg1, PlayerEntity arg2) {
		return new ContainerTerrariaInventory(arg0, arg1, null);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getGuiID());
	}
}