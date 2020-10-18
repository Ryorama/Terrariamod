package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import kmerrill285.trewrite.events.ScoreboardEvents;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.scoreboard.Score;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketChangeScore {
   private String score;
   private int add;

   public CPacketChangeScore(String score, int add) {
      this.score = score;
      this.add = add;
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.score);
      buf.writeInt(this.add);
   }

   public CPacketChangeScore(PacketBuffer buf) {
      this.score = buf.readString(100).trim();
      this.add = buf.readInt();
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity player = ((Context)ctx.get()).getSender();
         Score sc = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, this.score);
         sc.setScorePoints(this.add);
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
