package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BcCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = MainClass.Prefix + MainClass.NoPerm;
        String servername = MainClass.ServerName;
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("bc")) {
            if (c.hasPermission(servername + ".bc")) {
                if (args.length >= 1) {
                    String msg = "";
                    for (int i = 0; i != args.length; i++)
                    msg = msg + args[i] + " ";
                    msg = ChatColor.translateAlternateColorCodes('&', msg);
                    Bukkit.broadcastMessage("§9§lTest-Server §8➜ §7" + msg);
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Bc §7<§bText§7>");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
