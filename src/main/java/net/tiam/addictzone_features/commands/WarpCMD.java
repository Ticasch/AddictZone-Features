package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (args.length == 1) {
                String warp = String.valueOf(args[0]);
                if (!(c instanceof Player)) {
                    c.sendMessage(prefix + "Du musst ein Spieler sein.");
                } else if (new WarpManager(String.valueOf(warp.toUpperCase())).getWarp() == null) {
                    c.sendMessage(prefix + "Dieses Warp existiert nicht.");
                } else {
                    c.sendMessage(prefix + "Du hast dich erfolgreich zum Warp §b" + warp + " §7teleportiert.");
                    ((Player) c).teleport(new WarpManager(warp.toUpperCase()).getWarp());
                }
            } else {
                c.sendMessage(prefix + "Benutze: §b/Warp §7<§bWarp§7>");
            }
        }
        return false;
    }
}
