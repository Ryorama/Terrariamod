package kmerrill285.trewrite.world.biome.surfacebuilders;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = "trewrite", bus=Mod.EventBusSubscriber.Bus.MOD)
public class SurfaceBuilders {
   public static final SurfaceBuilder TERRARIA_SURFACE_BUILDER = new TerrariaSurfaceBuilder();
   private static IForgeRegistry registry;

   @SubscribeEvent
   public static void onRegisterBiomes(RegistryEvent.Register<SurfaceBuilder<?>> event) {
      if (registry == null) {
         registry = event.getRegistry();
      }

      registerSurfaceBuilder(TERRARIA_SURFACE_BUILDER, "terraria_surface_builder");
   }

   public static SurfaceBuilder registerSurfaceBuilder(SurfaceBuilder surface, String name) {
      if (registry == null) {
         throw new NullPointerException("Didnt set surface registry");
      } else {
         surface.setRegistryName("trewrite", name);
         registry.register(surface);
         return surface;
      }
   }
}
