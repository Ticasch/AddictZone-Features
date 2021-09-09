package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EcCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    private MainClass instance;

    public static ArrayList<String> uuid = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(prefix + "Du musst ein Spieler sein.");
        } else {
            if (args.length == 0) {
                player.openInventory(player.getEnderChest());
            } else if (args.length == 1) {
                if (!player.hasPermission(servername + ".ec")) {
                    player.sendMessage(noperm);
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                } else {
                    Inventory ec = target.getEnderChest();
                    player.sendMessage(prefix + "Du hast die Enderchest von §b" + target.getName() + " §7geöffnet");
                    if (!player.hasPermission(servername + ".ec.interact")) {
                        uuid.add(player.getUniqueId().toString());
                    }
                    player.openInventory((Inventory) ec);
                }
            } else {
                player.sendMessage(prefix + "Benutze: §b/Ec §7<§c[OPTIONAL]§bSpieler§7>");
            }
        }
        return false;
    }
}
