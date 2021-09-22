package net.tiam.addictzone_features.commands;

import com.sun.tools.jdi.Packet;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;


public class InvseeCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.NoPerm;
    String servername = MainClass.ServerName;
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (!player.hasPermission(servername + ".invsee")) {
            player.sendMessage(noperm);
            return true;
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                return true;
            }
            if (player.hasPermission(servername + ".invsee.interact")) {
                player.openInventory((Inventory)target.getInventory());
            } else {
                Inventory inv = Bukkit.createInventory((InventoryHolder)target, InventoryType.PLAYER, "§b"+ target.getName() + "'s Inventar");
                inv.setContents(target.getInventory().getContents());
                player.openInventory(inv);
            }
            player.sendMessage(prefix + "hast das Inventar von §b" + target.getName() + " §7geöffnet.");
        } else {
            player.sendMessage(prefix + "Benutze: §b/Invsee §7<§bSpieler§7>");
        }
        return false;
    }

}

