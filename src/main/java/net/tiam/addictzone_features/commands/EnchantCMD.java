package net.tiam.addictzone_features.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnchantCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String SENDER;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            SENDER = c.getName();
        } else {
            SENDER = servername;
        }
        if (cmd.getName().equalsIgnoreCase("enchant")) {
            if (args.length == 2) {
                if (c instanceof ConsoleCommandSender) {
                    c.sendMessage(prefix + "Du musst ein Spieler sein.");
                } else {
                    String ench = String.valueOf(args[0]);
                    int lvl = Integer.parseInt(args[1]);
                        //implements ItemStorage Slots - Enchantment Overwriting;
                }
            }
        }
        return false;
    }
}
