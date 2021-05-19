package com.ryorama.terrariamod.utils;

import org.spongepowered.asm.mixin.MixinEnvironment.Side;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class ColorHelper
{	
	public static int multiply(int argb, float multi)
	{
		return packARGB(getAlpha(argb) * multi, getRed(argb) * multi, getGreen(argb) * multi, getBlue(argb) * multi);
	}
	
	public static int packARGB(float a, float r, float g, float b)
	{
		return (((int) (a * 255F)) << 24) | (((int) (r * 255F)) << 16) | (((int) (g * 255F)) << 8) | ((int) (b * 255F));
	}
	
	public static int packRGB(float r, float g, float b)
	{
		return (((int) (r * 255F)) << 16) | (((int) (g * 255F)) << 8) | ((int) (b * 255F));
	}
	
	public static int packARGB(int a, int r, int g, int b)
	{
		return (a << 24) | (r << 16) | (b << 8) | b;
	}
	
	public static int packRGB(int r, int g, int b)
	{
		return (r << 16) | (b << 8) | b;
	}
	
	public static float getAlpha(int rgb)
	{
		return ((rgb >> 24) & 0xFF) / 255F;
	}
	
	public static float getRed(int rgb)
	{
		return ((rgb >> 16) & 0xFF) / 255F;
	}
	
	public static float getGreen(int rgb)
	{
		return ((rgb >> 8) & 0xFF) / 255F;
	}
	
	public static float getBlue(int rgb)
	{
		return ((rgb >> 0) & 0xFF) / 255F;
	}
	
	public static float getBrightnessF(int rgb)
	{
		return getRed(rgb) * getGreen(rgb) * getBlue(rgb);
	}
	
	public static int getBrightnessRGB(int rgb)
	{
		int bri = (int) (getBrightnessF(rgb) * 255F);
		return bri << 16 | bri << 8 | bri;
	}
	
	@Environment(EnvType.CLIENT)
	public static void gl(int rgba)
	{
		RenderSystem.setShaderColor(getRed(rgba), getGreen(rgba), getBlue(rgba), getAlpha(rgba));
	}
	
	public static int interpolateSine(int a, int b, float progress)
	{
		return interpolate(a, b, progress <= 0F ? 0F : progress >= 1F ? 1F : (float) Math.sin(Math.toRadians(progress * 90)));
	}
	
	public static int interpolate(int a, int b, float progress)
	{
		float rs = getRed(a) * (1 - progress) + getRed(b) * progress;
		float gs = getGreen(a) * (1 - progress) + getGreen(b) * progress;
		float bs = getBlue(a) * (1 - progress) + getBlue(b) * progress;
		float as = getAlpha(a) * (1 - progress) + getAlpha(b) * progress;
		return packARGB(as, rs, gs, bs);
	}
}