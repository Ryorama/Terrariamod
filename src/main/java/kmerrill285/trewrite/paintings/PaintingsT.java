package kmerrill285.trewrite.paintings;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class PaintingsT {
   @SubscribeEvent
   public static void registerPaintings(Register event) {
      event.getRegistry().registerAll(new PaintingType[0]);
   }
}
