package net.tiam.addictzone_features.listeners;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.EcCMD;
import net.tiam.addictzone_features.commands.InvseeCMD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

public class InventoryClickListener implements Listener {
    String servername = MainClass.ServerName;
    String prefix = MainClass.Prefix;
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        InventoryView v = player.getOpenInventory();
        if (v.getTopInventory() != null && v.getTopInventory().getSize() == 36)
            if (!player.hasPermission(servername + ".invsee.interact")) {
                event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onEcClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        InventoryView v = player.getOpenInventory();
        if (v.getTopInventory() != null && v.getTopInventory().getSize() == 27)
            if (!player.hasPermission(servername + ".ec.interact")) {
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        Player player = (Player)event.getPlayer();
    }

    @EventHandler
    public void onEcClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (EcCMD.uuid.contains(player.getUniqueId().toString()))
            EcCMD.uuid.remove(player.getUniqueId().toString());
    }

}
