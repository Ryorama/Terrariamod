package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.forge.network.client.ui.TerraruaUIRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TerrariaModForgeEvents {

    @SubscribeEvent
    @OnlyIn(value= Dist.CLIENT)
    public void renderGuiOverlayEvent(RenderGuiOverlayEvent.Pre event) {
        System.out.println("Rendering overlays");
        TerraruaUIRenderer.renderTerrariaHealth(event.getPoseStack());
        TerraruaUIRenderer.renderTerrariaEffects(event.getPoseStack());
        TerraruaUIRenderer.renderTerrariaMana(event.getPoseStack());
    }
}
