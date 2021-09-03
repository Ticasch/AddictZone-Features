package net.tiam.addictzone_features.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.tiam.addictzone_features.MainClass;
import org.jetbrains.annotations.NotNull;

public class TpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = prefix + MainClass.NoPerm;
        String servername = MainClass.ServerName;
        String usage = prefix + "Benutze: §b/Tp §7<§bSpieler§7>";
        String TELEPORTER;
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("tp")) {
            if (c.hasPermission(servername + ".Tp")) {
                if (c instanceof Player) {
                    TELEPORTER = c.getName();
                } else {
                    TELEPORTER = servername;
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        c.sendMessage(prefix + "Dieeer Spieler ist nicht online.");
                        return true;
                    }
                    if (c instanceof Player) {
                        if (target.hasPermission(servername + ".Tp.Ignore")) {
                            if (c.hasPermission(servername + ".Tp.ignore.Bypass")) {
                                c.sendMessage(prefix + "Du hast dich zu §b" + target.getName() + " §7teleportiert.");
                                ((Player) c).teleport(target.getLocation());
                            } else {
                                c.sendMessage(prefix + "Du kannst dich nicht zu §b" + target.getName() + " teleportieren.");
                            }
                        } else {
                            c.sendMessage(prefix + "Du hast dich zu §b" + target.getName() + " §7teleportiert.");
                            ((Player) c).teleport(target.getLocation());
                        }
                    } else {
                        c.sendMessage(prefix + "Du musst ein Spieler sein.");
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    Player toTeleport = Bukkit.getPlayer(args[0]);
                    if (target == null || toTeleport == null) {
                        c.sendMessage(prefix + "Einer dieser spieler ist nicht online.");
                        return true;
                    }
                    if (c.hasPermission(servername + ".Tp.others")) {
                        if (target.hasPermission(servername + ".Tp.ignore")) {
                            if (c.hasPermission(servername + ".Tp.ignore.bypass")) {
                                c.sendMessage(prefix + "Du hast §b" + toTeleport.getName() + " §7zu §b" + target.getName() + " §7teleportiert.");
                                toTeleport.sendMessage(prefix + "Du wurdest von §b" + TELEPORTER + " §7zu §b" + target.getName() + " §7teleportiert.");
                                target.sendMessage(prefix + "Der Spieler §b" + toTeleport.getName() + " §7wurde von §b" + c.getName() + " §7zu dir teleportiert.");
                                toTeleport.teleport(target.getLocation());
                            } else {
                                c.sendMessage(prefix + "Du kannst §b" + toTeleport.getName() + " §7nicht zu §b" + target + " §7teleportieren.");
                            }
                        } else {
                            c.sendMessage(prefix + "Du hast §b" + toTeleport.getName() + " §7zu §b" + target.getName() + " §7teleportiert.");
                            toTeleport.sendMessage(prefix + "Du wurdest von §b" + TELEPORTER + " §7zu §b" + target.getName() + " §7teleportiert.");
                            target.sendMessage(prefix + "Der Spieler §b" + toTeleport.getName() + " §7wurde von §b" + c.getName() + " §7zu dir teleportiert.");
                            toTeleport.teleport(target.getLocation());
                        }
                    } else {
                        c.sendMessage(noperm);
                    }
                } else {
                    c.sendMessage(usage);
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
