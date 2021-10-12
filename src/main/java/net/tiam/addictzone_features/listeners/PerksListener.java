package net.tiam.addictzone_features.listeners;

import de.tr7zw.nbtapi.NBTItem;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.PerkCMD;
import net.tiam.addictzone_features.managers.PerkManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class PerksListener implements Listener {
    String prefix = MainClass.Prefix;
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().startsWith("§9§lPerks")) {
            if (e.getClickedInventory() == null)
                return;
            ItemStack clickedStack = e.getCurrentItem();
            if (clickedStack == null)
                return;
            e.setCancelled(true);
            if (!clickedStack.hasItemMeta())
                return;
            NBTItem nbtItem = new NBTItem(clickedStack);
            if (nbtItem.hasKey("perk").booleanValue()) {
                PerkManager.Perk perk = nbtItem.getObject("perk", PerkManager.Perk.class);
                PerkManager manager = new PerkManager(p.getUniqueId().toString());
                if (p.hasPermission(perk.getPerm())) {
                    ItemMeta i = clickedStack.getItemMeta();
                    List<String> lore = i.getLore();
                    if (manager.getPerkStatus(perk)) {
                        manager.switchPerkStatus(perk);
                        if (perk.getEffect() != null && perk.getLvl() != null) {
                            p.removePotionEffect(perk.getEffect());
                        }
                        i.removeEnchant(Enchantment.LURE);
                        lore.set(3, statusString(p, perk.getPerm(), false));
                    } else {
                        manager.switchPerkStatus(perk);
                        if (perk.getEffect() != null && perk.getLvl() != null) {
                            p.addPotionEffect(new PotionEffect(perk.getEffect(), 2147483647, perk.getLvl().intValue() - 1));
                        }
                        lore.set(3, statusString(p, perk.getPerm(), true));
                        i.addEnchant(Enchantment.LURE, 0, true);
                        i.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    }
                    i.setLore(lore);
                    clickedStack.setItemMeta(i);
                    e.getClickedInventory().setItem(e.getSlot(), clickedStack);
                }
            } else if (nbtItem.hasKey("close").booleanValue()) {
                p.closeInventory();
            } else if (nbtItem.hasKey("page-1").booleanValue()) {
                new PerkCMD().openInv1(p);
            } else if (nbtItem.hasKey("page-2").booleanValue()) {
                new PerkCMD().openInv2(p);
            }
        }
    }
    public String statusString(Player p, String perm, boolean activated) {
        String prefix = "§7➜ Status: ";
        if (p.hasPermission(perm)) {
            if (activated)
                return prefix + "§aaktiviert";
            return prefix + "§cdeaktiviert";
        }
        return prefix + "§cNicht freigeschaltet";
    }
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if (new PerkManager(e.getEntity().getUniqueId().toString()).getPerkStatus(PerkManager.Perk.Kein_Hunger)) {
            if (e.getEntity().getFoodLevel() < 20)
                e.getEntity().setFoodLevel(20);
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onWater(EntityAirChangeEvent e) {
        if (new PerkManager(e.getEntity().getUniqueId().toString()).getPerkStatus(PerkManager.Perk.Atmung))
            if (e.getEntity().isInWater())
                e.setCancelled(true);
    }
    /*@EventHandler
    public void onEnter(PlayerEnterPlotEvent e) {
        Player p = (Player) e.getPlotPlayer();
        Plot plot = e.getPlot();
        PerkManager.Perk perk = PerkManager.Perk.Plot_Fly;
        PerkManager manager = new PerkManager(p.getUniqueId().toString());
        if (manager.getPerkStatus(perk)) {
            if (plot.getOwners().contains(p.getUniqueId()) || plot.getTrusted().contains(p.getUniqueId()) || plot.getMembers().contains(p.getUniqueId())) {
                if (!p.hasPermission("AddictZone.Perk.Plot-Fly.bypass")) {
                    p.sendMessage(prefix + "Plot-Fly aktiviert.");
                    p.setAllowFlight(true);
                    p.setFlying(true);
                }
            }
        }
    }
    @EventHandler
    public void onLeave(PlayerLeavePlotEvent e) {
        Player p = (Player) e.getPlotPlayer();
        Plot plot = e.getPlot();
        PerkManager.Perk perk = PerkManager.Perk.Plot_Fly;
        PerkManager manager = new PerkManager(p.getUniqueId().toString());
        if (manager.getPerkStatus(perk)) {
            if (plot.getOwners().contains(p.getUniqueId()) || plot.getTrusted().contains(p.getUniqueId()) || plot.getMembers().contains(p.getUniqueId())) {
                if (!p.hasPermission("AddictZone.Perk.Plot-Fly.bypass")) {
                    p.sendMessage(prefix + "Plot-Fly deaktiviert.");
                    p.setAllowFlight(false);
                }
            }
        }
    } */
}
