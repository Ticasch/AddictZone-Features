package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.TpaToggleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpaToggleCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            if (!p.hasPermission(servername + ".tpatoggle")) {
                p.sendMessage(noperm);
                return true;
            }
            if (new TpaToggleManager(p.getUniqueId().toString()).getToggledTpa() == true) {
                new TpaToggleManager(p.getUniqueId().toString()).setToggledTpa(false);
                p.sendMessage(prefix + "Du hast deine §bTpa §7aktiviert.");
            } else {
                new TpaToggleManager(p.getUniqueId().toString()).setToggledTpa(true);
                p.sendMessage(prefix + "Du hast deine §bTpa §7deaktiviert.");
            }
        } else {
            p.sendMessage(prefix + "Benutze: §b/Tpatpggle");
        }
        return false;
    }
}
