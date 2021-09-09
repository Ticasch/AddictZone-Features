package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.MsgToggleManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MsgToggleCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (player.hasPermission(servername + ".msgtoggle")) {
            if (new MsgToggleManager(player.getUniqueId().toString()).getToggledMsg() == true) {
                new MsgToggleManager(player.getUniqueId().toString()).setToggledMsg(false);
                player.sendMessage(prefix + "Du hast deine Nachrichten aktiviert.");
            } else {
                new MsgToggleManager(player.getUniqueId().toString()).setToggledMsg(true);
                player.sendMessage(prefix + "Du hast deine Nachrichten deaktiviert.");
            }
        } else {
            player.sendMessage(noperm);
        }
        return false;
    }
}
