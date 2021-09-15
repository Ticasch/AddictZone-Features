package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (c instanceof Player) {
                if (c.hasPermission(servername + ".setwarp")) {
                    new WarpManager("Spawn").setWarp(((Player) c).getLocation());
                    c.sendMessage(prefix + "Du hast den §6Spawn §7auf deine aktzuelle Position gesetzt.");
                }
            } else {
                c.sendMessage(prefix + "Du musst ein Spieler sein.");
            }
        }
        return false;
    }
}
