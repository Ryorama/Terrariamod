package com.ryorama.terrariamod.mixins;

import de.keksuccino.fancymenu.FancyMenu;
import de.keksuccino.fancymenu.menu.fancy.helper.CustomizationHelperUI;
import de.keksuccino.fancymenu.menu.fancy.helper.ui.MenuBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomizationHelperUI.class)
public class CustomizationHelperUIMixin {

    /*
    @Shadow
    public static MenuBar bar;


    @Inject(at = @At("TAIL"), method = "updateUI")
    private static void updateUI(CallbackInfo info) {
        bar.setVisible(false);
    }
     */
}
