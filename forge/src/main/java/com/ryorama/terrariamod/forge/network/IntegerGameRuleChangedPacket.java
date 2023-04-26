package com.ryorama.terrariamod.forge.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class IntegerGameRuleChangedPacket {

    private final String key;
    private final int value;

    @SuppressWarnings("unused")
    public IntegerGameRuleChangedPacket(PacketByteBuf buffer) {
        key = buffer.readString();
        value = buffer.readInt();
    }

    public IntegerGameRuleChangedPacket(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @SuppressWarnings("unused")
    void encode(PacketByteBuf buffer) {
        buffer.writeString(key);
        buffer.writeInt(value);
    }

    void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> GameRulesT.updateValue(key, value));
        context.get().setPacketHandled(true);
    }
}
