package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SethomeCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("sethome")) {
            if (!(c instanceof Player)) {
                c.sendMessage(prefix + "Du musst ein spieler sein.");
                return true;
            }
            if (args.length == 1) {
                int MaxHomeCount = MaxCountPermission(((Player) c).getPlayer());
                String home = String.valueOf(args[0]);
                if (!(new HomeManager((((Player) c).getUniqueId().toString()), home.toUpperCase()).getHome() == null)) {
                   c.sendMessage(prefix + "Dieses Home existiert bereits.");
                   return true;
                }
                if (new HomeManager(((Player) c).getUniqueId().toString(), home.toUpperCase()).getCount() >= MaxHomeCount) {
                    c.sendMessage(prefix + "Du kannst nur maximal §b" + MaxHomeCount + " §7Homes setzen.");
                    return true;
                }
                c.sendMessage(prefix + "Du hast dich erfolgreich das Home §b" + home + " §7gesetzt.");
                new HomeManager(((Player) c).getUniqueId().toString(), home.toUpperCase()).setHome(((Player) c).getLocation());
                new HomeManager(((Player) c).getUniqueId().toString(), home.toUpperCase()).setCount(new HomeManager(((Player) c).getUniqueId().toString(), home.toUpperCase()).getCount() + 1);
            } else {
                c.sendMessage(prefix + "Benutze: §b/Sethome §7<§bHome§7>");
            }
        }
        return false;
    }
    public static int MaxCountPermission(Player player) {
        String servername = MainClass.ServerName;
        for (int i = 1000; i >= 0; i--) {
            if (player.hasPermission(servername + ".Home.Homes." + i)) {
                return i;
            }
        }
        return 0;
    }
}
