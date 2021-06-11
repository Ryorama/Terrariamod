package kmerrill285.trewrite.mixin;

import net.minecraft.client.gui.IngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(IngameGui.class)
public class InGameGuiMixin {

    @Overwrite
    protected void renderHotbar(float partialTicks) {

    }
}
