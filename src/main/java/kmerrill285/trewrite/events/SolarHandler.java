package kmerrill285.trewrite.events;

import java.util.Random;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SolarHandler {
   public static SolarHandler INSTANCE;
   static Random rand = new Random();
   private static ResourceLocation SUN_TEXTURES = new ResourceLocation("trewrite:textures/environment/sun.png");
   private static ResourceLocation SOLAR_ECLIPSE_SUN_TEXTURES = new ResourceLocation("trewrite:textures/environment/sun_eclipse.png");

   public static void handleSolarEvents(World worldIn) {
      boolean night = worldIn.getDayTime() % 24000L < 15000L || worldIn.getDayTime() % 24000L > 22500L;
      if (!WorldStateHolder.get(worldIn).solarEclipse && night) {
         WorldRenderer.SUN_TEXTURES = SUN_TEXTURES;
         if (rand.nextInt(50) <= 5 && WorldStateHolder.get(worldIn).hardmode == true) {
            WorldStateHolder.get(worldIn).solarEclipse = true;
            worldIn.getServer().getPlayerList().sendMessage((new StringTextComponent("A solar eclipse is happening!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
         }
      }

      if (!night) {
         WorldStateHolder.get(worldIn).solarEclipse = false;
      }

      if (WorldStateHolder.get(worldIn).solarEclipse) {
    	  WorldRenderer.SUN_TEXTURES = SOLAR_ECLIPSE_SUN_TEXTURES;
      }

   }
}
