package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWarpCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
            if (!(c instanceof Player)) {
                c.sendMessage(prefix + "Du musst ein Spieler sein.");
                return true;
            }
            if (c.hasPermission(servername + ".setwarp")) {
                if (args.length == 1) {
                    String warp = String.valueOf(args[0]);
                    if (!(new WarpManager(warp.toUpperCase()).getWarp() == null)) {
                        c.sendMessage(prefix + "Dieses Warp existiert bereits.");
                    } else {
                        c.sendMessage(prefix + "Du hast das Warp §b" + warp + " §7erfolgreich gesetzt.");
                        new WarpManager(warp.toUpperCase()).setWarp(((Player)c).getLocation());
                    }
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Setwarp §7<§bWarp§7>");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
