package kmerrill285.trewrite.core.inventory.npc;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerHead;
import kmerrill285.trewrite.entities.npc.EntityOldMan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OldManGui extends Screen {

	private static final int WIDTH = 256;
    private static final int HEIGHT = 171;
	
	private ResourceLocation GUI = new ResourceLocation("trewrite:textures/gui/chat_box.png");
	
	protected OldManGui() {
		super(new TranslationTextComponent("screen.trewrite.old_man"));
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
	     this.addButton(new Button(relX + 45, relY + 145, 160, 20, "Summon", button -> summonBoss()));
	     super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	public static void openGui() {
		if (Minecraft.getInstance().world.isRemote) {
			Minecraft.getInstance().displayGuiScreen(new OldManGui());
		}
	}
	
	public void summonBoss() {
			EntityOldMan.oldManSummon = true;
	}
}
