package com.ryorama.terrariamod.client.fx;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.callbacks.PlayerEquipArmorCallback;
import com.ryorama.terrariamod.items.ItemsT;
import ladysnake.satin.api.managed.ManagedFramebuffer;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform4f;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

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
