package net.tiam.addictzone_features.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("Test")) {
            int i = permissionGetter(p);
            p.sendMessage(String.valueOf(i));
        }
        return false;
    }
    public int permissionGetter(Player player) {
        if(player.hasPermission("lol.a.*")) {
            return 1000;
        }
        for (int i = 1000; i > 0; i--) {
            if (player.hasPermission("lol.a." + i)) {
                return i;
            }
        }
        return 0;
    }
}
