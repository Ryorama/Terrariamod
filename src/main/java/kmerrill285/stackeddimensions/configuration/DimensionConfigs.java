package kmerrill285.stackeddimensions.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.util.ResourceLocation;

public class DimensionConfigs {
   public static ArrayList configs = new ArrayList();

   public static DimensionConfiguration getConfig(ResourceLocation location) {
      Iterator var1 = configs.iterator();

      DimensionConfiguration config;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         config = (DimensionConfiguration)var1.next();
      } while(!config.dimension.getPath().equals(location.getPath()));

      return config;
   }
}
