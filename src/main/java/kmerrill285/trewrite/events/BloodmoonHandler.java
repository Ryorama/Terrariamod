package kmerrill285.trewrite.events;

import java.util.Random;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BloodmoonHandler {
   public static BloodmoonHandler INSTANCE;
   static Random rand = new Random();
   private static ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("trewrite:textures/environment/moon_phases.png");
   private static ResourceLocation BLOOD_MOON_PHASES_TEXTURES = new ResourceLocation("trewrite:textures/environment/blood_moon_phases.png");

   public static void handleBloodmoon(World worldIn) {
      boolean night = worldIn.getDayTime() % 24000L > 15000L && worldIn.getDayTime() % 24000L < 22000L;
      if (!WorldStateHolder.get(worldIn).bloodmoon && night) {
         WorldRenderer.MOON_PHASES_TEXTURES = MOON_PHASES_TEXTURES;
         if (rand.nextInt(5000) <= 5) {
            WorldStateHolder.get(worldIn).bloodmoon = true;
            worldIn.getServer().getPlayerList().sendMessage((new StringTextComponent("A bloodmoon is rising!")).applyTextStyles(new TextFormatting[]{TextFormatting.RED, TextFormatting.BOLD}));
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
