package net.tiam.addictzone_features.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import net.tiam.addictzone_features.MainClass;
public class FlyCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String usage = prefix + "Benutze: §b/Fly §7<§c[OPTIONAL]§bSpieler§7>";
    String SENDER;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            SENDER = c.getName();
        } else if (c instanceof ConsoleCommandSender) {
            SENDER = "AddictZone";
        } else {
            SENDER = null;
        }
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (c.hasPermission(servername + ".Fly")) {
                if (args.length == 0) {
                    if (c instanceof Player) {
                        if (!((Player) c).isFlying()) {
                            c.sendMessage(prefix + "Fly aktiviert.");
                            ((Player) c).setAllowFlight(true);
                            ((Player) c).setFlying(true);
                        } else {
                            c.sendMessage(prefix + "Fly deaktiviert.");
                            ((Player) c).setFlying(false);
                            ((Player) c).setAllowFlight(false);
                        }
                    } else {
                        c.sendMessage(prefix + "Du musst ein Spieler sein.");
                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                        return true;
                    }
                    if (c.hasPermission(servername + ".Fly.Other")) {
                        if (!target.isFlying()) {
                            target.setAllowFlight(true);
                            target.setFlying(true);
                            target.sendMessage(prefix + "Dein Fly wurde von §b" + SENDER + " §7aktivert.");
                            c.sendMessage(prefix + "Du hast Fly für §b" + target.getName() + " §7aktiviert.");
                        } else {
                            target.setFlying(false);
                            target.setAllowFlight(false);
                            target.sendMessage(prefix + "Dein Fly wurde von §b" + SENDER + " §7deaktiviert.");
                            c.sendMessage(prefix + "Du hast Fly für §b" + target.getName() + " §7deaktivert.");
                        }
                    } else {
                        c.sendMessage(prefix + "Du hast keine Rechte, andere Spieler in den Fly-Modus zu setzen.");
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
