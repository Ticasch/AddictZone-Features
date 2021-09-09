package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WbCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender p = (CommandSender) sender;
        if (p instanceof Player) {
            ((Player) p).openWorkbench(((Player) p).getLocation(), true);
        } else {
            p.sendMessage(MainClass.Prefix + "Du musst ein Spieler sein.");
        }
        return false;
    }
}
