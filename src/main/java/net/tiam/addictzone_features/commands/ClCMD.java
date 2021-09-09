package net.tiam.addictzone_features.commands;


import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = prefix + MainClass.NoPerm;
        String servername = MainClass.ServerName;
        String SENDER;
        CommandSender p = (CommandSender) sender;
        if (p instanceof Player) {
            SENDER = p.getName();
        } else {
            SENDER = servername;
        }
        if (!p.hasPermission(servername + ".command.chatlock")) {
            p.sendMessage(noperm);
            return true;
        }
        if (!MainClass.getClosed().booleanValue()) {
            MainClass.setClosed(Boolean.TRUE);
            Bukkit.broadcastMessage(prefix + "Der Chat wurde von §b" + SENDER + "§7 deaktiviert.");
        } else if (MainClass.getClosed().booleanValue()) {
            MainClass.setClosed(Boolean.FALSE);
            Bukkit.broadcastMessage(prefix + "Der Chat wurde von §b" + SENDER + "§7 aktiviert.");
        }
        return false;
    }
}
