package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("home")) {
            if (!(c instanceof Player)) {
                c.sendMessage(prefix + "Du musst ein Spieler sein.");
                return true;
            }
            if (args.length == 1) {
                String home = String.valueOf(args[0]);
                if (new HomeManager(((Player) c).getUniqueId().toString(), home.toUpperCase()).getHome() == null) {
                    c.sendMessage(prefix + "Dieses Home existiert nicht.");
                    return true;
                }
                ((Player) c).teleport(new HomeManager(((Player) c).getUniqueId().toString(), home.toUpperCase()).getHome());
                c.sendMessage(prefix + "Du hast dich erfolgreich zu deinem Home §b" + home + " §7teleportiert.");
            } else {
                c.sendMessage(prefix + "Benutze: §b/Home §7<§bHomes§7>");
            }
        }
        return false;
    }
}
