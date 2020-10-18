package kmerrill285.trewrite.core.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.Commands;

public class CommandsT {
   public static void register(CommandDispatcher cmd) {
      LiteralCommandNode command = cmd.register(WormholeCommand.register(cmd));
      cmd.register((LiteralArgumentBuilder)Commands.literal("wh").redirect(command));
      
      LiteralCommandNode command2 = cmd.register(BloodmoonCommand.register(cmd));
      cmd.register((LiteralArgumentBuilder)Commands.literal("bloodmoon").redirect(command2));
   }
}
