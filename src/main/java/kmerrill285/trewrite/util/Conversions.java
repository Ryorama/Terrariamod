package kmerrill285.trewrite.util;

import net.minecraft.client.Minecraft;

public class Conversions {
   public static float conversion = 25.0F;
   public static float feetToMeters = 0.3048F;
   public static float metersToFeet = 3.28084F;
   public static float buyToSell = 0.2F;
   public static float sellToBuy = 5.0F;

   public static float convertToIngame(float num) {
      return num / conversion;
   }

   public static float convertToRealtime(float num) {
      return num * conversion;
   }

   public static int buyToSell(int buy) {
      return (int)((float)buy * buyToSell);
   }

   public static int sellToBuy(int sell) {
      return (int)((float)sell * sellToBuy);
   }

   public static int toMCTicks(int terrariaTicks) {
      return terrariaTicks / 3;
   }

   public static int toTerrariaTicks(int MCTicks) {
      return MCTicks * 3;
   }

   public static int toScreenX(float x) {
      if (Minecraft.getInstance() != null && Minecraft.getInstance().mainWindow != null) {
         int scaledWidth = Minecraft.getInstance().mainWindow.getScaledWidth();
         return (int)((float)scaledWidth * (x / (float)scaledWidth));
      } else {
         return (int)x;
      }
   }

   public static int toScreenY(float y) {
      if (Minecraft.getInstance() != null && Minecraft.getInstance().mainWindow != null) {
         int scaledHeight = Minecraft.getInstance().mainWindow.getScaledHeight();
         return (int)((float)scaledHeight * (y / (float)scaledHeight));
      } else {
         return (int)y;
      }
   }
}
