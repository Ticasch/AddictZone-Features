package net.tiam.addictzone_features.commands;

import java.util.List;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.TpahereCMD;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCancelCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = MainClass.Prefix;
        Player player = (Player)sender;
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                return true;
            }
            if (target.getName().equals(player.getName())) {
                player.sendMessage(prefix + "Du kannst deine eigene §bTpa§7/§bTpahere §7nicht zurücknehmen.");
                return true;
            }
            if (TpaCMD.TELEPORTINQUIRIES.containsKey(target) && ((List)TpaCMD.TELEPORTINQUIRIES.get(target)).contains(player)) {
                List<Player> list = TpaCMD.TELEPORTINQUIRIES.get(target);
                list.remove(player);
                TpaCMD.TELEPORTINQUIRIES.replace(target, list);
                player.sendMessage(prefix + "Du hast deine §bTpa §7an §b" + target.getName() + " §7zurückgenommen.");
                target.sendMessage(prefix + "§b" + player.getName() + " §7hat seine §bTpa §7zurückgenommen.");
            } else if (TpahereCMD.TELEPORTINQUIRIES.containsKey(target) && ((List)TpahereCMD.TELEPORTINQUIRIES.get(target)).contains(player)) {
                List<Player> list = TpahereCMD.TELEPORTINQUIRIES.get(target);
                list.remove(player);
                TpahereCMD.TELEPORTINQUIRIES.replace(target, list);
                player.sendMessage(prefix + "Du hast deine §bTpahere §7an §b" + target.getName() + " §7zurückgenommen.");
                target.sendMessage(prefix + "§b" + player.getName() + " §7hat seine §bTpahere §7zurückgenommen.");
            } else {
                player.sendMessage(prefix + "Du hast keine §bTpa§7/§bTpahere§7, die du zurücknehmen kannst!");
            }
        } else {
            player.sendMessage(prefix + "Benutze: §b/Tpacancel §7<§bSpieler§7>");
        }
        return false;
    }
}
