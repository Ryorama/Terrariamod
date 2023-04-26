package com.ryorama.terrariamod.forge.network;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            Identifier.tryParse(TerrariaMod.MOD_ID),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.registerMessage(0, IntegerGameRuleChangedPacket.class, IntegerGameRuleChangedPacket::encode, IntegerGameRuleChangedPacket::new, IntegerGameRuleChangedPacket::handle);
        INSTANCE.registerMessage(1, BooleanGameRuleChangedPacket.class, BooleanGameRuleChangedPacket::encode, BooleanGameRuleChangedPacket::new, BooleanGameRuleChangedPacket::handle);
    }
}
