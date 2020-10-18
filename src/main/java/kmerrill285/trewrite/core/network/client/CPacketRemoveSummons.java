package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;

import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;


public class CPacketRemoveSummons {
	
	
	public CPacketRemoveSummons() {
	}
	
	public void encode(PacketBuffer buf) {
    }
	
	public CPacketRemoveSummons(PacketBuffer buf) {
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ServerPlayerEntity sender = ctx.get().getSender();
			if (sender != null) {
				if (WorldEvents.summons.get(sender.getScoreboardName()) != null) {
					for (Entity e : WorldEvents.summons.get(sender.getScoreboardName())) {
						e.remove();
					}
					WorldEvents.summons.get(sender.getScoreboardName()).clear();
					WorldEvents.summons.put(sender.getScoreboardName(), null);
				}
				if (WorldEvents.pets.get(sender.getScoreboardName()) != null) {
					WorldEvents.pets.get(sender.getScoreboardName()).remove();
					WorldEvents.pets.put(sender.getScoreboardName(), null);
				}
				if (WorldEvents.light_pets.get(sender.getScoreboardName()) != null) {
					WorldEvents.light_pets.get(sender.getScoreboardName()).remove();
					WorldEvents.light_pets.put(sender.getScoreboardName(), null);
				}
			}
        });
        ctx.get().setPacketHandled(true);
	}
}
