package kmerrill285.trewrite.core.client;

import io.netty.channel.rxtx.RxtxChannelConfig;
import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.core.client.particles.DustManager;
import kmerrill285.trewrite.core.client.particles.LeafParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Trewrite.modid);

    public static RegistryObject<BasicParticleType> LEAF = PARTICLES.register("leaf", () -> new BasicParticleType(true));

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleRegistry.LEAF.get(), LeafParticle.Factory::new);
    }
}
