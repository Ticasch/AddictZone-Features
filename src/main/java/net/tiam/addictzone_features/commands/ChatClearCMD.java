package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ChatClearCMD implements CommandExecutor {

    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String SENDER;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("cc")) {
            CommandSender c = (CommandSender) sender;
            if (c instanceof Player) {
                SENDER = c.getName();
            } else if (c instanceof ConsoleCommandSender) {
                SENDER = "AddictZone";
            }
            if (!c.hasPermission(servername + ".ChatClear")) {
                c.sendMessage(noperm);
                return true;
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (!all.hasPermission(servername + ".ChatClear.Ignore"))
                    for (int i = 1; i < 151; i++)
                        all.sendMessage("");
            }
            Bukkit.broadcastMessage(prefix + "Der Chat wurde von §b" + SENDER + " §7geleert.");
        }
        return false;
    }
}
