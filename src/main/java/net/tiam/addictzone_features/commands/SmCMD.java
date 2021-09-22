package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SmCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("Sm")) {
sendSmMessage(sender);
        }
        return false;
    }

    public void sendSmMessage(CommandSender c) {
        String prefix = MainClass.Prefix;
        String line = MainClass.Line;
        c.sendMessage(line);
        c.sendMessage(prefix + "§7TeamSpeak: §bAddictZone.net");
        c.sendMessage(prefix + "§7Forum: §bhttps://AdictZone.net/Forum");
        c.sendMessage(prefix + "§7Discord: §bhttps://discord.gg/c8SBWBS3Du");
        c.sendMessage(prefix + "§7Insta: §c//Implements Insta Linktree");
        c.sendMessage(prefix + "§7Twitter: §c//Implements Twitter Linktree");
        c.sendMessage(prefix + "§7E-Mail: §bcontact@addictzone.net");
        c.sendMessage(line);
    }
}
