package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KickCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = MainClass.NoPerm;
        String servername = MainClass.ServerName;
        String line = MainClass.Line;
        String KICKER;
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            KICKER = c.getName();
        } else {
            KICKER = servername;
        }
        if (cmd.getName().equalsIgnoreCase("kick")) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (args.length < 1) {
                    c.sendMessage(prefix + "Benutze: §b/Kick §7<§bSpieler§7> <§c[OPTIONAL]§bGrund§7>");
                    return true;
                }
                if (args.length == 1) {
                    boolean kicked;
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == c) {
                        c.sendMessage(prefix + "Du kannst dich nicht selbst kicken.");
                        return true;
                    }
                    if (target == null) {
                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                        return true;
                    }
                        String message = prefix + "§c§lGEKICKT" + System.lineSeparator() + System.lineSeparator() + "§7Von: §b" + KICKER + System.lineSeparator() + "§7Grund: §b-";
                        if (c.hasPermission(servername + ".Kick")) {
                            if (!target.hasPermission(servername + ".Kick.ignore") || c.hasPermission(servername + ".Kick.ignore.bypass")) {
                                if (all.hasPermission(servername + ".Kick.Notify")) {
                                    all.sendMessage(line + System.lineSeparator() + prefix + "§c§lKick" + System.lineSeparator() + prefix + "Name: §b" + target.getName() + System.lineSeparator() + prefix + "Von: §b" + KICKER + System.lineSeparator() + prefix + "Grund: §b-" + System.lineSeparator() + line);
                                }
                                target.kickPlayer(message);
                                c.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde gekickt.");
                            } else {
                                c.sendMessage(prefix + "Du kannst diesen Spieler nicht kicken.");
                            }
                        } else {
                            c.sendMessage(noperm);
                        }

                } else if (args.length >= 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == c) {
                        c.sendMessage(prefix + "Du kannst dich nicht selbst kicken.");
                        return true;
                    }
                    if (target == null) {
                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                        return true;
                    }
                        String reason = "";
                        for (int i = 1; i != args.length; i++)
                            reason = reason + args[i] + " ";
                        reason = reason.replace('&', '§');
                        String message = prefix + " §7- §c§lGEKICKT" + System.lineSeparator() + System.lineSeparator() + "§7Von: §b" + KICKER + System.lineSeparator() + "§7Grund: §b" + reason;
                        if (c.hasPermission(servername + ".Kick")) {
                            if (!target.hasPermission(servername + ".Kick.ignore") || c.hasPermission(servername + ".Kick.ignore.bypass")) {
                                if (all.hasPermission(servername + ".Kick.notify")) {
                                    all.sendMessage(line + System.lineSeparator() + prefix + "§c§lKick" + System.lineSeparator() + prefix + "Name: §b" + target.getName() + System.lineSeparator() + prefix + "Von: §b" + KICKER + System.lineSeparator() + prefix + "Grund: §b" + reason + System.lineSeparator() + line);
                                }
                                target.kickPlayer(message);
                                c.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde gekickt.");
                            } else {
                                c.sendMessage(prefix + "Du kannst diesen Spieler nicht kicken.");
                            }
                        } else {
                            c.sendMessage(noperm);
                        }

                }
            }
        }
        return false;
    }
}

