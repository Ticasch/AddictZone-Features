package net.tiam.addictzone_features.listeners;

import com.sun.tools.javac.Main;
import de.tr7zw.nbtapi.NBTItem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.PrefixCMD;
import net.tiam.addictzone_features.managers.PrefixColorGroupManager;
import net.tiam.addictzone_features.managers.PrefixColorManager;
import net.tiam.addictzone_features.managers.PrefixManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class PrefixListener implements Listener {
    String mprefix = MainClass.Prefix;
    String servername = MainClass.ServerName;
    private final LuckPerms luckPerms = LuckPermsProvider.get();
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().startsWith("§9§lPrefixes §7Seite")) {
            if (e.getClickedInventory() == null)
                return;
            ItemStack clickedStack = e.getCurrentItem();
            e.setCancelled(true);
            if (clickedStack == null)
                return;
            if (!clickedStack.hasItemMeta())
                return;
            NBTItem nbtItem = new NBTItem(clickedStack);
            if (nbtItem.hasKey("prefix").booleanValue()) {
                PrefixManager.Prefix prefix = nbtItem.getObject("prefix", PrefixManager.Prefix.class);
                PrefixColorManager manager = new PrefixColorManager(p.getUniqueId().toString());
                if (p.hasPermission(servername + ".Prefix." + prefix.getName())) {
                    ItemMeta nbtMeta = clickedStack.getItemMeta();
                    List<String> lore = nbtMeta.getLore();
                    if (manager.getTabColor().equals(prefix.getColor())) {
                        return;
                    }
                    new PrefixColorManager(p.getUniqueId().toString()).setTabColor(prefix.getColor());
                    if (e.getView().getTitle().equals("§9§lPrefixes §7Seite §b1 §7von §b2")) {
                        new PrefixCMD().openInv1(p);
                    } else if (e.getView().getTitle().equals("§9§lPrefixes §7Seite §b2 §7von §b2")) {
                        new PrefixCMD().openInv2(p);
                    }
                    p.sendMessage(mprefix + "Du hast deine Prefixfarbe zu §" + prefix.getColor() + prefix.getName() + " §7geändert.");
                }
            } else if (nbtItem.hasKey("prefix-default").booleanValue()) {
                PrefixManager manager = new PrefixManager(p.getUniqueId().toString());
                String color = "";
                String colorName = "";
                for (PrefixManager.Prefix d : manager.getPrefix()) {
                    if (d.getColor().equals(new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getTabGroupColor())) {
                        colorName = d.getColor() + d.getName();
                        color = d.getColor();
                    }
                }
                if (new PrefixColorManager(p.getUniqueId().toString()).getTabColor().equals(color)) {
                    return;
                }
                p.sendMessage(mprefix + "Du hast deine Prefixfarbe zu §" + colorName + " §7geändert.");
                new PrefixColorManager(p.getUniqueId().toString()).setTabColor(color);
                if (e.getView().getTitle().equals("§9§lPrefixes §7Seite §b1 §7von §b2")) {
                    new PrefixCMD().openInv1(p);
                } else if (e.getView().getTitle().equals("§9§lPrefixes §7Seite §b2 §7von §b2")) {
                    new PrefixCMD().openInv2(p);
                }
            } else if (nbtItem.hasKey("close").booleanValue()) {
                p.closeInventory();
            } else if (nbtItem.hasKey("page-1").booleanValue()) {
                new PrefixCMD().openInv1(p);
            } else if (nbtItem.hasKey("page-2").booleanValue()) {
                new PrefixCMD().openInv2(p);
            }
        }
    }
}
