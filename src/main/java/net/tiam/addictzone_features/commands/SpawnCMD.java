package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
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
        if (cmd.getName().equalsIgnoreCase("Spawn")) {
            if (args.length == 0) {
                if (c instanceof Player) {
                    if (new WarpManager("SPAWN").getWarp() == null) {
                        c.sendMessage(prefix + "Dieses Warp existiert nicht.");
                    } else {
                        ((Player) c).teleport(new WarpManager("SPAWN").getWarp());
                        c.sendMessage(prefix + "Du wurdest zum §6Spawn §7teleportiert.");
                    }
                } else {
                    c.sendMessage(prefix + "Du musst ein Spieler sein.");
                }
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!c.hasPermission(servername + ".warp.other")) {
                    c.sendMessage(noperm);
                } else if (target == null) {
                    c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                } else if (new WarpManager("SPAWN").getWarp() == null) {
                    c.sendMessage(prefix + "Dieses Warp existiert nicht.");
                } else {
                    target.teleport(new WarpManager("SPAWN").getWarp());
                    c.sendMessage(prefix + "Du hast §b" + target.getName() + " §7zum §6Spawn §7teleportiert.");
                    target.sendMessage(prefix + "Du wrudest von §b" + SENDER + " §7zum §6Spawn §7teleportiert.");
                }
            } else {
                if (c.hasPermission(servername + "warp.other")) {
                    c.sendMessage(prefix + "Benutze: §b/Warp §7<§bWarp§7> <[§cOPTIONAL§7]§bSpieler§7>");
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Warp §7<§bWarp§7>");
                }
            }
        }
        return false;
    }
}
