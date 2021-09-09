package net.tiam.addictzone_features.commands;

import com.sun.tools.jdi.Packet;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class InvseeCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    private MainClass instance;
    public static boolean interact;

    public static ArrayList<String> uuid = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(prefix + "Du musst ein Spieler sein.");
        } else {
            if (args.length == 1) {
                if (!player.hasPermission(servername + ".invsee")) {
                    player.sendMessage(noperm);
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                } else {
                    Inventory ec = target.getInventory();
                    player.sendMessage(prefix + "Du hast das Inventar von §b" + target.getName() + " §7geöffnet");
                    if (!player.hasPermission(servername + ".invsee.interact")) {
                        interact = false;
                    } else {
                        interact = true;
                    }
                    player.openInventory((Inventory) ec);
                }
            } else {
                player.sendMessage(prefix + "Benutze: §b/Invsee §7<§bSpieler§7>");
            }
        }
        return false;
    }
}

