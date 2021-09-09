package net.tiam.addictzone_features.commands;

import java.util.List;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpDenyCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                return true;
            }
            if (target.getName().equals(player.getName())) {
                player.sendMessage(prefix + "Du kannst deine eigene Tpa nicht akzeptieren!");
                return true;
            }
            if (TpaCMD.TELEPORTINQUIRIES.containsKey(player) && ((List)TpaCMD.TELEPORTINQUIRIES.get(player)).contains(target)) {
                List<Player> list = TpaCMD.TELEPORTINQUIRIES.get(player);
                list.remove(target);
                TpaCMD.TELEPORTINQUIRIES.replace(player, list);
                player.sendMessage(prefix + "Du hast die §bTpa §7von §b" + player.getName() + " §7abgelehnt.");
                target.sendMessage(prefix + "§b" + target.getName() + " §7hat dein §bTpa §7abgelehnt.");
            } else if (TpahereCMD.TELEPORTINQUIRIES.containsKey(player) && ((List) TpahereCMD.TELEPORTINQUIRIES.get(player)).contains(target)) {
                List<Player> list = TpahereCMD.TELEPORTINQUIRIES.get(player);
                list.remove(target);
                TpahereCMD.TELEPORTINQUIRIES.replace(player, list);
                player.sendMessage(prefix + "Du hast die §bTpahere §7von §b" + player.getName() + " §7abgelehnt.");
                target.sendMessage(prefix + "§b" + target.getName() + " §7hat deine §bTpahere §7abgelehnt.");
            } else {
                player.sendMessage(prefix + "Dieser Spieler hat dir keine §bTpa§7/§bTpahere §7geschickt.");
            }
        } else {
            player.sendMessage(prefix + "Benutze: §b/Tpdeny §7<§bSpieler§7>");
        }
        return false;
    }
}
