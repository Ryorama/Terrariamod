package com.ryorama.terrariamod.mixins;

import de.keksuccino.fancymenu.menu.fancy.helper.layoutcreator.LayoutEditorUI;
import de.keksuccino.fancymenu.menu.fancy.helper.ui.MenuBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayoutEditorUI.class)
public class LayoutEditorUIMixin {

    /*
    @Shadow
    public MenuBar bar;

    @Inject(at = @At("TAIL"), method = "updateUI")
    public void updateUI(CallbackInfo info) {
        bar.setVisible(false);
    }

     */

}
