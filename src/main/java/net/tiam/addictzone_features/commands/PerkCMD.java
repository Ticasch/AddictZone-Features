package net.tiam.addictzone_features.commands;

import de.tr7zw.nbtapi.NBTItem;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.PerkManager;
import net.tiam.addictzone_features.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PerkCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @Override
    public boolean onCommand(@NotNull CommandSender c, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (c instanceof ConsoleCommandSender) {
            c.sendMessage(prefix + "Du musst ein Spieler sein.");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("Perk")) {
            openInv1((Player) c);
        }
        return false;
    }
        public void openInv1(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§9§lPerks §7Seite §b1 §7von §b2");
        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, placeholder);
        PerkManager manager = new PerkManager(p.getUniqueId().toString());
        ItemStack keep_InvStack = new ItemBuilder("§c§lKeep-Inventory", Material.ENDER_CHEST, 1).build();
        ItemMeta keep_InvMeta = keep_InvStack.getItemMeta();
        keep_InvMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du verlierst beim Tod keine Items.", "", statusString(p, PerkManager.Perk.Keep_Inventory.getPerm(), manager.getPerkStatus(PerkManager.Perk.Keep_Inventory))}));
        if (manager.getPerkStatus(PerkManager.Perk.Keep_Inventory)) {
            keep_InvMeta.addEnchant(Enchantment.LURE, 0, true);
            keep_InvMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        keep_InvStack.setItemMeta(keep_InvMeta);
        ItemStack keep_XpStack = new ItemBuilder("§c§lKeep-XP", Material.EXPERIENCE_BOTTLE, 1).build();
        ItemMeta keep_XpMeta = keep_XpStack.getItemMeta();
        keep_XpMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du verlierst beim Tod keine XP.", "", statusString(p, PerkManager.Perk.Keep_XP.getPerm(), manager.getPerkStatus(PerkManager.Perk.Keep_XP))}));
        if (manager.getPerkStatus(PerkManager.Perk.Keep_XP)) {
            keep_XpMeta.addEnchant(Enchantment.LURE, 0, true);
            keep_XpMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        keep_XpStack.setItemMeta(keep_XpMeta);
        ItemStack eileStack = new ItemBuilder("§c§lEile", Material.GOLDEN_PICKAXE, 1).build();
        ItemMeta eileMeta = eileStack.getItemMeta();
        eileMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cEile §b5 §7Effekt.", "", statusString(p, PerkManager.Perk.Eile.getPerm(), manager.getPerkStatus(PerkManager.Perk.Eile))}));
        if (manager.getPerkStatus(PerkManager.Perk.Eile)) {
            eileMeta.addEnchant(Enchantment.LURE, 0, true);
            eileMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        eileStack.setItemMeta(eileMeta);
        ItemStack stärkeStack = new ItemBuilder("§c§lStärke", Material.DIAMOND_AXE, 1).build();
        ItemMeta stärkeMeta = stärkeStack.getItemMeta();
        stärkeMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cStärke §b2 §7Effekt.", "", statusString(p, PerkManager.Perk.Stärke.getPerm(), manager.getPerkStatus(PerkManager.Perk.Stärke))}));
        if (manager.getPerkStatus(PerkManager.Perk.Stärke)) {
            stärkeMeta.addEnchant(Enchantment.LURE, 0, true);
            stärkeMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        stärkeStack.setItemMeta(stärkeMeta);
        ItemStack heilungStack = new ItemBuilder("§c§lHeilung", Material.GOLDEN_APPLE, 1).build();
        ItemMeta heilungMeta = heilungStack.getItemMeta();
        heilungMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cRegeneration §b2 §7Effekt.", "", statusString(p, PerkManager.Perk.Heilung.getPerm(), manager.getPerkStatus(PerkManager.Perk.Heilung))}));
        if (manager.getPerkStatus(PerkManager.Perk.Heilung)) {
            heilungMeta.addEnchant(Enchantment.LURE, 0, true);
            heilungMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        heilungStack.setItemMeta(heilungMeta);
        ItemStack kein_HungerStack = new ItemBuilder("§c§lKein Hunger", Material.GOLDEN_CARROT, 1).build();
        ItemMeta kein_HungerMeta = kein_HungerStack.getItemMeta();
        kein_HungerMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du verlierst keinen Hunger mehr.", "", statusString(p, PerkManager.Perk.Kein_Hunger.getPerm(), manager.getPerkStatus(PerkManager.Perk.Kein_Hunger))}));
        if (manager.getPerkStatus(PerkManager.Perk.Kein_Hunger)) {
            kein_HungerMeta.addEnchant(Enchantment.LURE, 0, true);
            kein_HungerMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        kein_HungerStack.setItemMeta(kein_HungerMeta);
        ItemStack plot_FlyStack = new ItemBuilder("§c§lPlot-Fly", Material.FEATHER, 1).build();
        ItemMeta plot_FlyMeta = plot_FlyStack.getItemMeta();
        plot_FlyMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du kannst auf Plots, auf denen du Rechte hast, fliegen!", "", statusString(p, PerkManager.Perk.Plot_Fly.getPerm(), manager.getPerkStatus(PerkManager.Perk.Plot_Fly))}));
        if (manager.getPerkStatus(PerkManager.Perk.Plot_Fly)) {
            plot_FlyMeta.addEnchant(Enchantment.LURE, 0, true);
            plot_FlyMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        plot_FlyStack.setItemMeta(plot_FlyMeta);
        ItemStack closeStack = new ItemBuilder("§4Schließen", Material.BARRIER, 1).build();
        ItemMeta closeMeta = closeStack.getItemMeta();
        ItemStack pageStack = new ItemBuilder("§9Nächste Seite", Material.MAP, 1).build();
        ItemMeta pageMeta = pageStack.getItemMeta();
        NBTItem keep_InvItem = new NBTItem(keep_InvStack);
        keep_InvItem.setObject("perk", PerkManager.Perk.Keep_Inventory);
        NBTItem keep_XpItem = new NBTItem(keep_XpStack);
        keep_XpItem.setObject("perk", PerkManager.Perk.Keep_XP);
        NBTItem eileItem = new NBTItem(eileStack);
        eileItem.setObject("perk", PerkManager.Perk.Eile);
        NBTItem stärkeItem = new NBTItem(stärkeStack);
        stärkeItem.setObject("perk", PerkManager.Perk.Stärke);
        NBTItem heilungItem = new NBTItem(heilungStack);
        heilungItem.setObject("perk", PerkManager.Perk.Heilung);
        NBTItem kein_HungerItem = new NBTItem(kein_HungerStack);
        kein_HungerItem.setObject("perk", PerkManager.Perk.Kein_Hunger);
        NBTItem plot_FlyItem = new NBTItem(plot_FlyStack);
        plot_FlyItem.setObject("perk", PerkManager.Perk.Plot_Fly);
        NBTItem closeItem = new NBTItem(closeStack);
        closeItem.setObject("close", true);
        NBTItem pageItem = new NBTItem(pageStack);
        pageItem.setObject("page-2", true);
        inv.setItem(10, keep_InvItem.getItem());
        inv.setItem(12, keep_XpItem.getItem());
        inv.setItem(14, eileItem.getItem());
        inv.setItem(16, stärkeItem.getItem());
        inv.setItem(29, heilungItem.getItem());
        inv.setItem(31, kein_HungerItem.getItem());
        inv.setItem(33, plot_FlyItem.getItem());
        inv.setItem(49, closeItem.getItem());
        inv.setItem(50, pageItem.getItem());
        p.openInventory(inv);
    }
    public void openInv2(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§9§lPerks §7Seite §b2 §7von §b2");
        PerkManager manager = new PerkManager(p.getUniqueId().toString());
        ItemStack placeHolderStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeHolderMeta = placeHolderStack.getItemMeta();
        placeHolderMeta.setDisplayName(" ");
        placeHolderStack.setItemMeta(placeHolderMeta);
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, placeHolderStack);
        ItemStack speedStack = new ItemBuilder("§c§lSpeed", Material.SUGAR, 1).build();
        ItemMeta speedMeta = speedStack.getItemMeta();
        speedMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cSnelligkeit §b2 §7Effekt.", "", statusString(p, PerkManager.Perk.Speed.getPerm(), manager.getPerkStatus(PerkManager.Perk.Speed))}));
        if (manager.getPerkStatus(PerkManager.Perk.Speed)) {
            speedMeta.addEnchant(Enchantment.LURE, 0, true);
            speedMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        speedStack.setItemMeta(speedMeta);
        ItemStack taucherStack = new ItemBuilder("§c§lTaucher", Material.TRIDENT, 1).build();
        ItemMeta taucherMeta = taucherStack.getItemMeta();
        taucherMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cGunst des Delfins §7Effekt.", "", statusString(p, PerkManager.Perk.Taucher.getPerm(), manager.getPerkStatus(PerkManager.Perk.Taucher))}));
        if (manager.getPerkStatus(PerkManager.Perk.Taucher)) {
            taucherMeta.addEnchant(Enchantment.LURE, 0, true);
            taucherMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        taucherStack.setItemMeta(taucherMeta);
        ItemStack jumpStack = new ItemBuilder("§c§lSprungkraft", Material.RABBIT_FOOT, 1).build();
        ItemMeta jumpMeta = jumpStack.getItemMeta();
        jumpMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cSprungkraft §b2 §7Effekt.", "", statusString(p, PerkManager.Perk.Sprungkraft.getPerm(), manager.getPerkStatus(PerkManager.Perk.Sprungkraft))}));
        if (manager.getPerkStatus(PerkManager.Perk.Sprungkraft)) {
            jumpMeta.addEnchant(Enchantment.LURE, 0, true);
            jumpMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        jumpStack.setItemMeta(jumpMeta);
        ItemStack glowStack = new ItemBuilder("§c§lGlow", Material.GLOWSTONE_DUST, 1).build();
        ItemMeta glowMeta = glowStack.getItemMeta();
        glowMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cLeuchten §7Effekt.", "", statusString(p, PerkManager.Perk.Glow.getPerm(), manager.getPerkStatus(PerkManager.Perk.Glow))}));
        if (manager.getPerkStatus(PerkManager.Perk.Glow)) {
            glowMeta.addEnchant(Enchantment.LURE, 0, true);
            glowMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        glowStack.setItemMeta(glowMeta);
        ItemStack feuerSchutzStack = new ItemBuilder("§c§lFeuerschutz", Material.LAVA_BUCKET, 1).build();
        ItemMeta feuerSchutzMeta = feuerSchutzStack.getItemMeta();
        feuerSchutzMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhlätst den §cFeuerresistenz §7Effekt.", "", statusString(p, PerkManager.Perk.Feuerschutz.getPerm(), manager.getPerkStatus(PerkManager.Perk.Feuerschutz))}));
        if (manager.getPerkStatus(PerkManager.Perk.Feuerschutz)) {
            feuerSchutzMeta.addEnchant(Enchantment.LURE, 0, true);
            feuerSchutzMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        feuerSchutzStack.setItemMeta(feuerSchutzMeta);
        ItemStack nightStack = new ItemBuilder("§c§lNachtsicht", Material.ENDER_EYE, 1).build();
        ItemMeta nightMeta = nightStack.getItemMeta();
        nightMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du erhältst den §cNachtsicht §7Effekt.", "", statusString(p, PerkManager.Perk.Nachtsicht.getPerm(), manager.getPerkStatus(PerkManager.Perk.Nachtsicht))}));
        if (manager.getPerkStatus(PerkManager.Perk.Nachtsicht)) {
            nightMeta.addEnchant(Enchantment.LURE, 0, true);
            nightMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        nightStack.setItemMeta(nightMeta);
        ItemStack atmungStack = new ItemBuilder("§c§lAtmung", Material.WATER_BUCKET, 1).build();
        ItemMeta atmungMeta = atmungStack.getItemMeta();
        atmungMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du kannst unterwasser atmen.", "", statusString(p, PerkManager.Perk.Atmung.getPerm(), manager.getPerkStatus(PerkManager.Perk.Atmung))}));
        if (manager.getPerkStatus(PerkManager.Perk.Atmung)) {
            atmungMeta.addEnchant(Enchantment.LURE, 0, true);
            atmungMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        atmungStack.setItemMeta(atmungMeta);
        ItemStack closeStack = new ItemBuilder("§4Schließen", Material.BARRIER, 1).build();
        ItemMeta closeMeta = closeStack.getItemMeta();
        ItemStack pageStack = new ItemBuilder("§9Vorherige Seite", Material.MAP, 1).build();
        ItemMeta pageMeta = pageStack.getItemMeta();
        NBTItem speedItem = new NBTItem(speedStack);
        speedItem.setObject("perk", PerkManager.Perk.Speed);
        NBTItem taucherItem = new NBTItem(taucherStack);
        taucherItem.setObject("perk", PerkManager.Perk.Taucher);
        NBTItem jumpItem = new NBTItem(jumpStack);
        jumpItem.setObject("perk", PerkManager.Perk.Sprungkraft);
        NBTItem glowItem = new NBTItem(glowStack);
        glowItem.setObject("perk", PerkManager.Perk.Glow);
        NBTItem feuerSchutzItem = new NBTItem(feuerSchutzStack);
        feuerSchutzItem.setObject("perk", PerkManager.Perk.Feuerschutz);
        NBTItem nightItem = new NBTItem(nightStack);
        nightItem.setObject("perk", PerkManager.Perk.Nachtsicht);
        NBTItem atmungItem = new NBTItem(atmungStack);
        atmungItem.setObject("perk", PerkManager.Perk.Atmung);
        NBTItem closeItem = new NBTItem(closeStack);
        closeItem.setObject("close", true);
        NBTItem pageItem = new NBTItem(pageStack);
        pageItem.setObject("page-1", true);
        inv.setItem(10, speedItem.getItem());
        inv.setItem(12, taucherItem.getItem());
        inv.setItem(14, jumpItem.getItem());
        inv.setItem(16, glowItem.getItem());
        inv.setItem(29, feuerSchutzItem.getItem());
        inv.setItem(31, nightItem.getItem());
        inv.setItem(33, atmungItem.getItem());
        inv.setItem(48, pageItem.getItem());
        inv.setItem(49, closeItem.getItem());
        p.openInventory(inv);
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
}
