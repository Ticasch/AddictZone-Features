package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelWarpCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("delwarp")) {
            if (!(c instanceof Player)) {
                c.sendMessage(prefix + "Du musst ein Spieler sein.");
                return true;
            }
            if (c.hasPermission(servername + ".delwarp")) {
                if (args.length == 1) {
                    c.sendMessage(prefix + "Du musst diese Aktion bestätigen.");
                } else if (args.length == 2) {
                    if (!args[1].equalsIgnoreCase("confirm")) {
                        c.sendMessage(prefix + "Benutze: §b/Delwarp §7<§bWarp§7> §bconfirm");
                        return true;
                    }
                    String warp = String.valueOf(args[0]);
                    if (new WarpManager(warp).getWarp() == null) {
                        c.sendMessage(prefix + "Dieses Warp existiert nicht.");
                        return true;
                    }
                    c.sendMessage(prefix + "Du hast das Warp §b" + warp + " §7erfolgreich gelöscht.");
                    new WarpManager(warp).setWarp(null);
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Delwarp §7<§bWarp§7> §bconfirm");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
