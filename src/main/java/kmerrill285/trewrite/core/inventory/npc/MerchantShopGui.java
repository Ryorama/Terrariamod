package kmerrill285.trewrite.core.inventory.npc;

import java.util.Random;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.npc.EntityMerchant;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class MerchantShopGui extends Screen {
		
	private static final int WIDTH = 256;
    private static final int HEIGHT = 171;
	
	private ResourceLocation GUI = new ResourceLocation("trewrite:textures/gui/chat_box.png");
	
	protected MerchantShopGui() {
		super(new TranslationTextComponent("screen.trewrite.merchant"));
	}
	
	@Override
		protected void init() {
		 int relX = (this.width - WIDTH) / 2;
	     int relY = (this.height - HEIGHT) / 2; 
	     super.init();
	}

	@Override
	public void render(final int mouseX, final int mouseY, final float partialTicks) {
		this.minecraft.getTextureManager().bindTexture(GUI);
		 int relX = (this.width - WIDTH) / 2;
	     int relY = (this.height - HEIGHT) / 2;
	     this.blit(relX, relY, 0, 0, WIDTH, HEIGHT);
	     this.addButton(new Button(relX + 20, relY + 145, 200, 20, "Lesser Healing Potion: 3 Sliver", button -> buyLesserhealingPotions()));
	     super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	public static void openGui() {
		if (Minecraft.getInstance().world.isRemote) {
			Minecraft.getInstance().displayGuiScreen(new MerchantShopGui());
		}
	}
	
	public void buyLesserhealingPotions() {
		EntityMerchant.lhp = true;
	}
	
}
