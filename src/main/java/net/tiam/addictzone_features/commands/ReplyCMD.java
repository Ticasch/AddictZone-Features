package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.MsgToggleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length >= 1) {
            if (!MsgCMD.MESSAGES.containsKey(player)) {
                player.sendMessage(prefix + "hast noch keine Konversation gestartet!");
                return true;
            }
            if (!((Player)MsgCMD.MESSAGES.get(player)).isOnline()) {
                player.sendMessage(prefix + "Dieser Spieler ist nichtmehr online.");
                return true;
            }
            Player target = MsgCMD.MESSAGES.get(player);
            if (new MsgToggleManager(player.getUniqueId().toString()).getToggledMsg() == true && !player.hasPermission(servername + ".msgtoggle.ignore")) {
                player.sendMessage(prefix + "Dieser Spieler hat seine Nachrichten deaktiviert.");
                return true;
            }
            String message = "";
            for (int i = 0; i != args.length; i++)
                message = message + args[i] + " ";
            if (player.hasPermission(servername + ".msg.color") && player.hasPermission(servername + ".msg.format")) {
                message = ChatColor.translateAlternateColorCodes('&', message);
            } else if (player.hasPermission(servername + ".msg.color")) {
                message = ChatColor.translateAlternateColorCodes('&', message).replace("&f", "");
            }
            player.sendMessage(prefix + "§7[§bIch §8➜ §b" + target.getName() + "§7] §7" + message);
                    target.sendMessage(prefix + "§7[§bIch §8➜ §b" + player.getName() + "§7] §7" + message);
        } else {
            player.sendMessage(prefix + "Benutze: §b/r §7<§bText§7>");
        }
        return false;
    }
}