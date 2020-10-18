package kmerrill285.stackeddimensions.configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import net.minecraft.util.ResourceLocation;

public class Config {
   public static void loadConfig() {
      File file = new File("stackeddimensions/stacked_dimensions.config");
      if (!file.exists()) {
         try {
            File f = new File("stackeddimensions");
            f.mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Using the configuration file for Stacked Dimensions 1.14 is really simple!\r\nOn each line, specify a dimension, then specify the above or below dimensions.\r\nYou can use \"above\", \"below\", or both above and below.\r\nIn order for a dimension configuration to be registered though, it MUST have a dimension registered somewhere in the line.\r\nThe order in which you put \"dimension\", \"above\" and \"below\" does not matter.\r\nYou can also use \"min\" and \"max\" to specify the top and bottom of the dimension.\r\n=======================================================\r\nConfig starts below here\r\n=======================================================\r\n\r\ndimension=minecraft:overworld below=minecraft:the_nether above=minecraft:the_end\r\ndimension=minecraft:the_nether above=minecraft:overworld max=127\r\ndimension=minecraft:the_end below=minecraft:overworld");
            writer.close();
         } catch (IOException var17) {
            var17.printStackTrace();
         }
      }

      try {
         Scanner scanner = new Scanner(file);
         ArrayList lines = new ArrayList();

         while(scanner.hasNext()) {
            lines.add(scanner.nextLine());
         }

         Iterator var3 = lines.iterator();

         while(true) {
            String s;
            do {
               if (!var3.hasNext()) {
                  return;
               }

               s = (String)var3.next();
            } while(s.startsWith("#"));

            ResourceLocation dimension = null;
            ResourceLocation above = null;
            ResourceLocation below = null;
            int min = 0;
            int max = 255;
            String[] s2 = s.split(" ");
            String[] var11 = s2;
            int var12 = s2.length;

            for(int var13 = 0; var13 < var12; ++var13) {
               String s3 = var11[var13];
               String[] s4 = s3.split("=");
               if (s4.length != 0) {
                  String location;
                  if (s4[0].equals("dimension") && s4.length > 1) {
                     location = s4[1].replace("\"", "");
                     dimension = new ResourceLocation(location);
                  }

                  if (s4[0].equals("above") && s4.length > 1) {
                     location = s4[1].replace("\"", "");
                     above = new ResourceLocation(location);
                  }

                  if (s4[0].equals("below") && s4.length > 1) {
                     location = s4[1].replace("\"", "");
                     below = new ResourceLocation(location);
                  }

                  if (s4[0].equals("min") && s4.length > 1) {
                     location = s4[1].replace("\"", "");
                     min = Integer.parseInt(location);
                  }

                  if (s4[0].equals("max") && s4.length > 1) {
                     location = s4[1].replace("\"", "");
                     max = Integer.parseInt(location);
                  }
               }
            }

            if (dimension != null) {
               DimensionConfigs.configs.add(new DimensionConfiguration(dimension, above, below, min, max));
            }
         }
      } catch (Exception var18) {
         System.err.println("Could not load config file!");
         var18.printStackTrace();
         System.exit(1);
      }
   }
}
