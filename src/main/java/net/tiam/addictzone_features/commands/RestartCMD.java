package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RestartCMD implements CommandExecutor {
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
        if (cmd.getName().equalsIgnoreCase("restart")) {
            if (c.hasPermission(servername + ".restart")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("confirm")) {
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b60 §7Sekunden.");
                        try {
                            Thread.sleep(30000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b30 §7Sekunden.");
                        try {
                            Thread.sleep(24000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b5 §7Sekunden.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b4 §7Sekunden.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b3 §7Sekunden.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b2 §7Sekunden.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart in §b1 §7Sekunde.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + "Server Neustart...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (Player all : Bukkit.getOnlinePlayers())
                            all.kickPlayer(prefix + "§7Server Neustart");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "minecraft:stop");
                    } else {
                        c.sendMessage(prefix + "Benutze: §b/Restart confirm");
                    }
                } else {
                    c.sendMessage(prefix + "Du musst diese aktion bestätigen.");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
