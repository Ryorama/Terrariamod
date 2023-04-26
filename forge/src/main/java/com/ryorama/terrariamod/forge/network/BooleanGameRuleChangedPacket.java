package com.ryorama.terrariamod.forge.network;

import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
import net.minecraft.network.PacketByteBuf;

public class BooleanGameRuleChangedPacket {

    private final String key;
    private final boolean value;

    @SuppressWarnings("unused")
    public BooleanGameRuleChangedPacket(PacketByteBuf buffer) {
        key = buffer.readString();
        value = buffer.readBoolean();
    }

    public BooleanGameRuleChangedPacket(String key, boolean value) {
        this.key = key;
        this.value = value;
    }

    @SuppressWarnings("unused")
    void encode(PacketByteBuf buffer) {
        buffer.writeString(key);
        buffer.writeBoolean(value);
    }

    void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> GameRulesT.updateValue(key, value));
        context.get().setPacketHandled(true);
    }
}
