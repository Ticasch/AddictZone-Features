package net.tiam.addictzone_features.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.tiam.addictzone_features.MainClass;
import org.jetbrains.annotations.NotNull;

public class TphereCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = prefix + MainClass.NoPerm;
        String servername = MainClass.ServerName;
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("tphere")) {
            if (c instanceof Player) {
                if (c.hasPermission(servername + ".tphere")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                            return true;
                        }
                        if (target.hasPermission(servername + ".tphere.ignore")) {
                            if (c.hasPermission(servername + ".tphere.ignore.bypass")) {
                                target.teleport(((Player) c).getLocation());
                                target.sendMessage(prefix + "Du wurdest zu §b" + c.getName() + " §7teleportiert.");
                                c.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde zu dir teleportiert.");
                            } else {
                                c.sendMessage(prefix + "Du kannst diesen Spieler nicht zu dir teleportieren.");
                            }
                        } else {
                            target.teleport(((Player) c).getLocation());
                            target.sendMessage(prefix + "Du wurdest zu §b" + c.getName() + " §7teleportiert.");
                            c.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde zu dir teleportiert.");
                        }
                    } else {
                        c.sendMessage(prefix + "Benutze: §b/Tphere §7<§bSpieler§7>");
                    }
                } else {
                    c.sendMessage(noperm);
                }
            } else {
                c.sendMessage(prefix + "Du musst ein Spieler sein.");
            }
        }
        return false;
    }
}