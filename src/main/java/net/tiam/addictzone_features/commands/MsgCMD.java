package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MsgCMD implements CommandExecutor {
    public static HashMap<Player, Player> MESSAGES = new HashMap<>();

    String prefix = MainClass.Prefix;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (args.length >= 2) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(prefix + "§cDieser Spieler ist nicht online!");
                return true;
            }
            if (p == target) {
                p.sendMessage(prefix + "§7Du kannst dir selber keine nachrichten senden.");
                return true;
            }
            String msg = "";
            for (int i = 1; i != args.length; i++)
                msg = msg + args[i] + " ";
                if (p.hasPermission(servername + ".Msg.Color") && p.hasPermission(servername + ".Msg.Format")) {
                    msg = ChatColor.translateAlternateColorCodes('&', msg);
                } else if (p.hasPermission(servername + ".Msg.Color") && !p.hasPermission(servername + "Msg.Format")) {
                    msg = ChatColor.translateAlternateColorCodes('&', msg).replace("&l", "").replace("&o", "").replace("&m", "").replace("&n", "").replace("&k", "").replace("&r", "");

                } else if (p.hasPermission(servername + "Msg.Format") && !p.hasPermission(servername + ".Msg.Color")) {
                    msg = ChatColor.translateAlternateColorCodes('&', msg).replace("&1", "").replace("&2", "").replace("&3", "").replace("&4", "").replace("&5", "").replace("&6", "").replace("&7", "").replace("&8", "").replace("&9", "").replace("&0", "").replace("&a", "").replace("&b", "").replace("&c", "").replace("&d", "").replace("&e", "").replace("&f", "").replace("&o", "").replace("&m", "").replace("&n", "").replace("&r", "").replace("&k", "");
                }
                if (MESSAGES.containsKey(p)) {
                    MESSAGES.replace(p, target);
                } else {
                    MESSAGES.put(p, target);
                }
                if (MESSAGES.containsKey(target)) {
                    MESSAGES.replace(target, p);
                } else {
                    MESSAGES.put(target, p);
                }
                p.sendMessage(prefix + "§7[§bIch §8➜ §b" + target.getName() + "§7] " + msg);
                target.sendMessage(prefix + "§7[§b" + p.getName() + " §8➜ §bMir§7] " + msg);
        } else {
            p.sendMessage(prefix + "§7Benutze: §b/Msg §7<§bSpieler§7>");
        }
        return false;
    }
}
