package kmerrill285.trewrite.world.client.render;

import java.lang.reflect.Field;

import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(value=Dist.CLIENT)
public class StackedWorldRenderer extends WorldRenderer {

	public StackedWorldRenderer(Minecraft mcIn) {
		super(mcIn);
		
	}
	
	@Override
	public void loadRenderers() {
		super.loadRenderers();
	}
	
	@Override
	public void setupTerrain(ActiveRenderInfo p_215320_1_, ICamera p_215320_2_, int p_215320_3_, boolean p_215320_4_) {
		super.setupTerrain(p_215320_1_, p_215320_2_, p_215320_3_, p_215320_4_);
	}

}
