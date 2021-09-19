package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelhomeCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("delhome")) {
            if (!(c instanceof Player)) {
                c.sendMessage(prefix + "Du musst ein Spieler sein.");
                return true;
            }
            if (args.length == 1) {
                String home = String.valueOf(args[0]).toUpperCase();
                if (new HomeManager(((Player) c).getUniqueId().toString(), home).getHome() == null) {
                    c.sendMessage(prefix + "Dieses Home existiert nicht.");
                    return true;
                }
                c.sendMessage(prefix + "Du musst diese Aktion bestätigen.");
            } else if (args.length == 2) {
                if (!args[1].equalsIgnoreCase("confirm")) {
                    c.sendMessage(prefix + "Benutze: §b/Delhome §7<§bHome§7>");
                    return true;
                }
                String home = String.valueOf(args[0]).toUpperCase();
                String showhome = String.valueOf(args[0]);
                if (new HomeManager(((Player) c).getUniqueId().toString(), home).getHome() == null) {
                    c.sendMessage(prefix + "Dieses Home existiert nicht.");
                    return true;
                }
                c.sendMessage(prefix + "Du hast dein Home §b" + showhome + " §7erfolgreich gelöscht.");
                new HomeManager(((Player) c).getUniqueId().toString(), home).setHome(null);
                new HomeManager(((Player) c).getUniqueId().toString(), home).setCount(new HomeManager(((Player) c).getUniqueId().toString(),home).getCount() - 1);
            } else {
                c.sendMessage(prefix + "Benutze: §b/Delhome §7<§bHome§7>");
            }
        }
        return false;
    }
}
