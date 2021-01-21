package com.ryorama.terrariamod.util;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public abstract class ScreenWTFMojang<T extends Container> extends ContainerScreen<T>
{
	/**
	* A handy tooltip list for rendering it.
	* Add in either renderBG or renderFG.
	* Renders ontop of the entire GUI.
	*/
	protected final List<String> tooltip = Lists.newArrayList();
	
	public ScreenWTFMojang(T container, PlayerInventory inv, ITextComponent label)
	{
		super(container, inv, label);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		renderBackground();
		GlStateManager.enableBlend();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		tooltip.clear();
		super.render(mouseX, mouseY, partialTicks);
		renderTooltip(tooltip, mouseX, mouseY, font);
		renderHoveredToolTip(mouseX, mouseY);
	}
}
