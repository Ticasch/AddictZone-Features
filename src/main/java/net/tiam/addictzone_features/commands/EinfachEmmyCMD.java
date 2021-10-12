package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EinfachEmmyCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("EinfachEmmy")) {
            if (!p.hasPermission(MainClass.ServerName + ".emmy")) {
                p.sendMessage(MainClass.NoPerm);
                return true;
            }
            p.sendTitle("§c§lMod §7| EinfachEmmy", "§7Status: §bFotze");
            p.sendActionBar("§cYouTube: §7Klicke auf den Link im Chat!");
            p.sendMessage(MainClass.Prefix + "§7EinfachEmmy's YouTube: §bhttps://www.youtube.com/c/EinfachEmmy");
            int i = 1;
        }
        return false;
    }
}
