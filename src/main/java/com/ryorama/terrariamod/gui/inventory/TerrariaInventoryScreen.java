package com.ryorama.terrariamod.gui.inventory;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.ui.UIRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class TerrariaInventoryScreen extends AbstractInventoryScreen<TerrariaInventoryScreen.TerrariaInventoryScreenHandler> {
	
	public static Identifier SLOT_TEXTURE = new Identifier(TerrariaMod.modid, "textures/gui/inventory/slot");
	
	public TerrariaInventoryScreen(PlayerEntity player) {
		super(new TerrariaInventoryScreen.TerrariaInventoryScreenHandler(), player.getInventory(), LiteralText.EMPTY);
	}

	public static int maxSlots = 40;
	
	public static final SimpleInventory INVENTORY = new SimpleInventory(40);
	
	@Environment(EnvType.CLIENT)
	public static class TerrariaInventoryScreenHandler extends ScreenHandler {
		public static DefaultedList<ItemStack> itemList = DefaultedList.of();
		
		public TerrariaInventoryScreenHandler() {
			super((ScreenHandlerType)null, 0);
			
			for (int i = 0; i <= maxSlots; i++) {
				for (int k = 0; k < 10; k++) {
					for (int j = 0; k < 4; j++) {
						this.addSlot(new TerrariaSlot(TerrariaInventoryScreen.INVENTORY, i, k * 2, j * 2));
						
						if (itemList.size() > 0) {
							for (int m = 0; m <= this.itemList.size(); i++) {
								TerrariaInventoryScreen.INVENTORY.setStack(i, this.itemList.get(m));
							}
						}
					}
				}
			}
		}

		@Override
		public boolean canUse(PlayerEntity player) {
			return true;
		}
		
		public static void addItem(ItemStack stack) {
			itemList.add(stack);
		}
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		for (int i = 0; i <= maxSlots; i++) {
			for (int k = 0; k < 10; k++) {
				for (int j = 0; k < 4; j++) {
					UIRenderer.instance.renderOverlay(SLOT_TEXTURE, 1f, 32, 32, k * 2, j * 2, -90);
				}
			}
		}
	}
}
