package kmerrill285.trewrite.events;

import java.util.Random;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class LunarHandler {
   public static LunarHandler INSTANCE;

   static Random rand = new Random();

   public static boolean alreadyAttemptedBloodMoon;
   public static boolean alreadyNight = false;

   private static ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("trewrite:textures/environment/moon_phases.png");
   private static ResourceLocation MOON_PHASES_TEXTURES2 = new ResourceLocation("trewrite:textures/environment/moon_2.png");
   private static ResourceLocation MOON_PHASES_TEXTURES3 = new ResourceLocation("trewrite:textures/environment/moon_3.png");
   private static ResourceLocation MOON_PHASES_TEXTURES4 = new ResourceLocation("trewrite:textures/environment/moon_4.png");
   private static ResourceLocation MOON_PHASES_TEXTURES5 = new ResourceLocation("trewrite:textures/environment/moon_5.png");

   private static ResourceLocation BLOOD_MOON_PHASES_TEXTURES = new ResourceLocation("trewrite:textures/environment/blood_moon_phases.png");

   public static void handleMoon(World worldIn) {
      boolean night = worldIn.getDayTime() % 24000L > 15000L && worldIn.getDayTime() % 24000L < 22000L;
      if (!WorldStateHolder.get(worldIn).bloodmoon && night) {

         SolarHandler.alreadyAttemptedSolarEclipse = false;


         if (!alreadyNight) {
            int moonTextureIndex = rand.nextInt(5);

            if (moonTextureIndex == 0) {
               WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES;
            } else if (moonTextureIndex == 1) {
               WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES2;
            } else if (moonTextureIndex == 2) {
               WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES3;
            } else if (moonTextureIndex == 3) {
               WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES4;
            } else if (moonTextureIndex == 4) {
               WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES5;
            } else {
               WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES;
            }

            alreadyNight = true;
         }

         if (rand.nextInt(5000) <= 5 & !alreadyAttemptedBloodMoon) {
            WorldStateHolder.get(worldIn).bloodmoon = true;
            worldIn.getServer().getPlayerList().sendMessage((new StringTextComponent("A bloodmoon is rising!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
            alreadyAttemptedBloodMoon = true;
         } else {
            alreadyAttemptedBloodMoon = true;
         }
      }

      if (!night) {
         WorldStateHolder.get(worldIn).bloodmoon = false;
      }

      if (WorldStateHolder.get(worldIn).bloodmoon) {
    	  WorldRenderer.MOON_PHASES_TEXTURES = BLOOD_MOON_PHASES_TEXTURES;
      }
   }
}
