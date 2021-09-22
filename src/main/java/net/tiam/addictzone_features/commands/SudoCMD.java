package net.tiam.addictzone_features.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class SudoCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(servername + ".sudo")) {
            if (args.length >= 2) {
                String msg = "";
                for (int i = 1; i != args.length; i++)
                    msg = msg + args[i] + " ";
                Player t = Bukkit.getPlayer(args[0]);
                if (t == null) {
                    sender.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                    return true;
                }
                if (msg.startsWith("/")) {
                    Bukkit.getServer().dispatchCommand(t.getPlayer(), msg.replace("/", ""));
                } else {
                    t.getPlayer().chat(msg);
                }
            } else {
                sender.sendMessage(prefix + "Benutze: §b/Sudo §7<§bSpieler§7> <§bNachricht§7>");
            }
        } else {
            sender.sendMessage(noperm);
        }
        return false;
    }
    public static UUID getUUIDFromName(String name){
        try {
            Object o = new JsonParser().parse(new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream())));
            if (o instanceof JsonObject)
                return UUID.fromString(((JsonObject) o).get("id").getAsString().replaceFirst("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)",
                        "$1-$2-$3-$4-$5"));
        } catch (IOException ignored) {
            return null;
        }
        return null;
    }
}
