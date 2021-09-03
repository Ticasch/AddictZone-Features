package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WeatherCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = prefix + MainClass.NoPerm;
        String servername = MainClass.ServerName;
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("sun")) {
            if (c.hasPermission(servername + ".Weather")) {
                for (World all : Bukkit.getWorlds()) {
                    all.setStorm(false);
                    all.setThundering(false);
                }
                c.sendMessage(prefix + "Du hast das Wetter zu §bKlar §7geändert.");
            } else {
                c.sendMessage(noperm);
            }
        } else if (cmd.getName().equalsIgnoreCase("rain")) {
            if (c.hasPermission(servername + ".weather")) {
                for (World all : Bukkit.getWorlds()) {
                    all.setStorm(true);
                }
                c.sendMessage(prefix + "Du hast das Wetter zu §bRegen §7geändert.");
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
