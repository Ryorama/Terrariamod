package com.ryorama.terrariamod.core.client.fx;

import com.ryorama.terrariamod.TerrariaMod;
import ladysnake.satin.api.managed.ManagedFramebuffer;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.minecraft.util.Identifier;

public class ShadersManager {

    public static ManagedShaderEffect COLOR_SHADER = ShaderEffectManager.getInstance().manage(new Identifier(TerrariaMod.MODID, "shaders/post/blit.json"),
            effect -> effect.setUniformValue("ColorModulate", 0f, 148f, 240f, 1.0f));
    public static final ManagedFramebuffer COLOR_SHADER_BUFFER = COLOR_SHADER.getTarget("final");

    public static void registerShaders() {

        /*
        PlayerEquipArmorCallback.EVENT.register(((slot, stack) -> {

            World world = null;

            if (MinecraftClient.getInstance().world != null) {
                world = MinecraftClient.getInstance().world;
            }

            if (world != null) {
                if (world.isClient) {
                    color.set(252, 3, 15, 1);
                }
            }

            return ActionResult.FAIL;
        }));
         */
    }
}
