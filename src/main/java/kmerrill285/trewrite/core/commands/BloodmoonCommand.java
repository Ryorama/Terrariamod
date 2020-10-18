package kmerrill285.trewrite.core.commands;

import java.util.Collection;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;

public class BloodmoonCommand implements Command {
	
	private static final BloodmoonCommand CMD = new BloodmoonCommand();

	   public static LiteralArgumentBuilder register(CommandDispatcher cmd) {
	      return (LiteralArgumentBuilder)Commands.literal("bloodmoon").executes(new BloodmoonCommand());
	   }

	   private static int activateBloodMoon(CommandSource source, Collection entities, Entity entity) {
		   if (Minecraft.getInstance().world.getDayTime() % 24000L > 15000 && Minecraft.getInstance().world.getDayTime() % 24000L < 22000) {
			   WorldStateHolder.get(Minecraft.getInstance().world).bloodmoon = true;
			   return 1;
		   }
		   else {
			   return 1;
		   }
	   }
	   
	   public int run(CommandContext context) throws CommandSyntaxException {
		      return activateBloodMoon((CommandSource)context.getSource(), (Collection)null, EntityArgument.getEntity(context, "destination"));
	   }

}
