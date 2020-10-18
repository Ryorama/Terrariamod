package kmerrill285.trewrite.core.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuideGUI extends Screen {

	private static final int WIDTH = 256;
    private static final int HEIGHT = 171;
	
	private ResourceLocation GUI = new ResourceLocation("trewrite:textures/gui/guide_gui.png");
	
	protected GuideGUI() {
		super(new TranslationTextComponent("screen.trewrite.guide"));
	}
	
	@Override
		protected void init() {
			super.init();
	}

	@Override
	public void render(final int mouseX, final int mouseY, final float partialTicks) {
		this.renderBackground();
		this.minecraft.getTextureManager().bindTexture(GUI);
		int relX = (this.width - WIDTH) / 2;	
        int relY = (this.height - HEIGHT) / 2;
        this.blit(relX, relY, 0, 0, WIDTH, HEIGHT);
		super.render(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	public static void openGui() {
		if (Minecraft.getInstance().world.isRemote) {
			Minecraft.getInstance().displayGuiScreen(new GuideGUI());
			Minecraft.getInstance().displayGuiScreen(new GuideGUI());
		}
	}	
}
