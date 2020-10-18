package kmerrill285.trewrite.items.terraria.potions;

import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketChangeScore;
import kmerrill285.trewrite.events.ScoreboardEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Score;
import net.minecraft.world.World;

public class DefaultPotion extends Potion {
	private String potion;
	private int seconds;
	public DefaultPotion(Properties properties, String name, boolean consumable, boolean autoBuff, String potion, int seconds) {
		super(properties, name, consumable, autoBuff);
		this.potion = potion;
		this.seconds = seconds;
		this.setMaxStack(30);
		this.setBuySell(200);
	}

	@Override
	protected boolean doPotionStuff(World world, PlayerEntity player) {
		if (!world.isRemote()) {
			Score score = ScoreboardEvents.getScore(world.getScoreboard(), player, potion);
			score.setScorePoints(seconds * 20);
		} else {
			NetworkHandler.INSTANCE.sendToServer(new CPacketChangeScore(potion, seconds * 20));
		}
		return true;
	}

}
