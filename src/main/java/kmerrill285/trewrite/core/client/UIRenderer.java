package kmerrill285.trewrite.core.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.java.games.input.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class UIRenderer {

    public static UIRenderer instance = new UIRenderer();

    public static int scaledWidth;
    public static int scaledHeight;

    public static final Minecraft client = Minecraft.getInstance();

    public void renderOverlay(ResourceLocation texture, float opacity, float width, float height, float x, float y, int zPos) {

        scaledWidth = client.mainWindow.getScaledWidth();
        scaledHeight = client.mainWindow.getScaledHeight();

        GlStateManager.pushMatrix();
        GlStateManager.translatef(x, y, zPos);
        GlStateManager.enableAlphaTest();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(1f, 1f, 1f, opacity);
        client.getTextureManager().bindTexture(texture);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(0, (double)height, zPos).tex(0.0F, 1.0F).endVertex();
        bufferBuilder.pos((double)width, (double)height, zPos).tex(1.0F, 1.0F).endVertex();
        bufferBuilder.pos((double)width, 0, zPos).tex(1.0F, 0.0F).endVertex();
        bufferBuilder.pos(0, 0, zPos).tex(0.0F, 0.0F).endVertex();
        tessellator.draw();
        GlStateManager.disableAlphaTest();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
    }
}