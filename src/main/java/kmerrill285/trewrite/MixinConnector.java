package kmerrill285.trewrite;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector {
    @Override
    public void connect() {
        System.out.println("Invoking Mixin Connector");
        Mixins.addConfiguration(
                "assets/trewrite/mixins.trewrite.json"
        );
    }
}