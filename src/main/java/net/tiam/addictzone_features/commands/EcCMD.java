package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
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
                } else if (player.hasPermission(servername + ".ec.interact")) {
                    player.openInventory((Inventory)target.getEnderChest());
                } else {
                    Inventory inv = Bukkit.createInventory((InventoryHolder)target, InventoryType.ENDER_CHEST, "§b"+ target.getName() + "'s Ec");
                    inv.setContents(target.getEnderChest().getContents());
                    player.openInventory(inv);
                }
            } else {
                player.sendMessage(prefix + "Benutze: §b/Ec §7<§c[OPTIONAL]§bSpieler§7>");
            }
        }
        return false;
    }
}
