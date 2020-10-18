package kmerrill285.trewrite.items;

import kmerrill285.trewrite.items.vanilla.EnchantedGoldenApple;
import kmerrill285.trewrite.items.vanilla.GoldenApple;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemOverrides {
   public static final DeferredRegister ITEMS;
   public static final RegistryObject GOLDEN_APPLE;
   public static final RegistryObject ENCHANTED_GOLDEN_APPLE;

   static {
      ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, "minecraft");
      GOLDEN_APPLE = ITEMS.register("golden_apple", () -> {
         return new GoldenApple(new Properties());
      });
      ENCHANTED_GOLDEN_APPLE = ITEMS.register("enchanted_golden_apple", () -> {
         return new EnchantedGoldenApple(new Properties());
      });
   }
}
