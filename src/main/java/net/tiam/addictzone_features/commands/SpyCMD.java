package net.tiam.addictzone_features.commands;

import java.util.HashMap;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpyCMD implements CommandExecutor {
    public static HashMap<Player, Player> SPYED = new HashMap<>();
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target, player = (Player)sender;
        if (!player.hasPermission(servername + ".spy")) {
            player.sendMessage(noperm);
            return true;
        }
        switch (args.length) {
            case 0:
                if (!SPYED.containsKey(player)) {
                    SPYED.put(player, player);
                    player.sendMessage(prefix + "Du beobachtest nun alle Spieler.");
                    break;
                }
                SPYED.remove(player);
                player.sendMessage(prefix + "Beobachtermodus deaktiviert.");
                break;
            case 1:
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                    return true;
                }
                if (!SPYED.containsKey(player)) {
                    SPYED.put(player, target);
                    player.sendMessage(prefix + "Du beobachtest nun §b" + target.getName() + "§7.");
                    break;
                }
                SPYED.remove(player);
                player.sendMessage(prefix + "Beobachtermodus deaktiviert.");
                break;
        }
        return false;
    }
}
