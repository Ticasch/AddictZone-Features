package net.tiam.addictzone_features.commands;

import de.tr7zw.nbtapi.NBTItem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.PrefixColorGroupManager;
import net.tiam.addictzone_features.managers.PrefixColorManager;
import net.tiam.addictzone_features.managers.PrefixManager;
import net.tiam.addictzone_features.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PrefixCMD implements CommandExecutor {
    String mprefix = MainClass.Prefix;
    String noperm = MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String SENDER;
    LuckPerms luckPerms = LuckPermsProvider.get();
    @Override
    public boolean onCommand(@NotNull CommandSender c, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (c instanceof ConsoleCommandSender) {
            c.sendMessage(mprefix + "Du musst ein Spieler sein.");
            return true;
        }
        if (!c.hasPermission(servername + ".Prefix"))
            return true;
        openInv1((Player) c);
        return false;
    }
        public void openInv1(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§9§lPrefixes §7Seite §b1 §7von §b2");
        ItemStack placeHolderStack = new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build();
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, placeHolderStack);
        PrefixManager manager = new PrefixManager(p.getUniqueId().toString());
        PrefixColorManager colorManager = new PrefixColorManager(p.getUniqueId().toString());
        ItemStack blackStack = new ItemBuilder("§0§lSchwarz", Material.BLACK_CONCRETE, 1).build();
        ItemMeta blackMeta = blackStack.getItemMeta();
        blackMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bSchwarz§7." , "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.BLACK.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.BLACK.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.BLACK.getColor())) {
            blackMeta.addEnchant(Enchantment.LURE, 0, true);
            blackMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        blackStack.setItemMeta(blackMeta);
        ItemStack darkBlueStack = new ItemBuilder("§1§lDunkelblau", Material.BLUE_CONCRETE, 1).build();
        ItemMeta darkBlueMeta = darkBlueStack.getItemMeta();
        darkBlueMeta.setLore(Arrays.asList(new String[] { "",  "§7➥ Du änderst deine Prefixfarbe zu §bDunkelblau§7." , "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.DARKBLUE.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKBLUE.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKBLUE.getColor())) {
            darkBlueMeta.addEnchant(Enchantment.LURE, 0, true);
            darkBlueMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        darkBlueStack.setItemMeta(darkBlueMeta);
        ItemStack darkGreenStack = new ItemBuilder("§1§lDunkelgrün", Material.GREEN_CONCRETE, 1).build();
        ItemMeta darkGreenMeta = darkGreenStack.getItemMeta();
        darkGreenMeta.setLore(Arrays.asList(new String[] { "",  "§7➥ Du änderst deine Prefixfarbe zu §bDunkelgrün§7." , "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.DARKGREEN.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKGREEN.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKGREEN.getColor())) {
            darkGreenMeta.addEnchant(Enchantment.LURE, 0, true);
            darkGreenMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        darkGreenStack.setItemMeta(darkGreenMeta);
        ItemStack darkAquaStack = new ItemBuilder("§3§lTürkies", Material.CYAN_CONCRETE, 1).build();
        ItemMeta darkAquaMeta = darkAquaStack.getItemMeta();
        darkAquaMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bTürkies§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.DARKAQUA.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKAQUA.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKAQUA.getColor())) {
            darkAquaMeta.addEnchant(Enchantment.LURE, 0, true);
            darkAquaMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        darkAquaStack.setItemMeta(darkAquaMeta);
        ItemStack darkRedStack = new ItemBuilder("§4§lDunkelrot", Material.RED_CONCRETE, 1).build();
        ItemMeta darkRedMeta = darkRedStack.getItemMeta();
        darkRedMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bDunkelrot§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.DARKRED.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKRED.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKRED.getColor())) {
            darkRedMeta.addEnchant(Enchantment.LURE, 0, true);
            darkRedMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        darkRedStack.setItemMeta(darkRedMeta);
        ItemStack purpleStack = new ItemBuilder("§5§lViolett", Material.PURPLE_CONCRETE, 1).build();
        ItemMeta purpleMeta = purpleStack.getItemMeta();
        purpleMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bViolett§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.PURPLE.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.PURPLE.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.PURPLE.getColor())) {
            purpleMeta.addEnchant(Enchantment.LURE, 0, true);
            purpleMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        purpleStack.setItemMeta(purpleMeta);
        ItemStack goldStack = new ItemBuilder("§6§lOrange", Material.ORANGE_CONCRETE, 1).build();
        ItemMeta goldMeta = goldStack.getItemMeta();
        goldMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bOrange§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.GOLD.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.GOLD.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.GOLD.getColor())) {
            goldMeta.addEnchant(Enchantment.LURE, 0, true);
            goldMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        goldStack.setItemMeta(goldMeta);
        ItemStack grayStack = new ItemBuilder("§7§lHellgrau", Material.LIGHT_GRAY_CONCRETE, 1).build();
        ItemMeta grayMeta = grayStack.getItemMeta();
        grayMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bHellgrau§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.GRAY.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.GRAY.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.GRAY.getColor())) {
            grayMeta.addEnchant(Enchantment.LURE, 0, true);
            grayMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        grayStack.setItemMeta(grayMeta);
        ItemStack closeStack = new ItemBuilder("§4Schließen", Material.BARRIER, 1).build();
        ItemStack pageStack = new ItemBuilder("§9Nächste Seite", Material.MAP, 1).build();
        ItemStack defaultStack = new ItemBuilder("§bStandart", Material.NETHER_STAR, 1).build();
        ItemMeta defaultMeta = defaultStack.getItemMeta();
        String defaultPrefix = new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getTabGroupColor();
        for (PrefixManager.Prefix d : manager.getPrefix()) {
            if (d.getColor().equals(new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getTabGroupColor()))
                defaultMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zum Standart (§" + d.getColor() + d.getName() + "§7)"}));
        }
        defaultStack.setItemMeta(defaultMeta);
        NBTItem defaultItem = new NBTItem(defaultStack);
        defaultItem.setObject("prefix-default", true);
        NBTItem closeItem = new NBTItem(closeStack);
        closeItem.setObject("close", true);
        NBTItem pageItem = new NBTItem(pageStack);
        pageItem.setObject("page-2", true);
        NBTItem blackItem = new NBTItem(blackStack);
        blackItem.setObject("prefix", PrefixManager.Prefix.BLACK);
        NBTItem darkBlueItem = new NBTItem(darkBlueStack);
        darkBlueItem.setObject("prefix", PrefixManager.Prefix.DARKBLUE);
        NBTItem darkAquaItem = new NBTItem(darkAquaStack);
        darkAquaItem.setObject("prefix", PrefixManager.Prefix.DARKAQUA);
        NBTItem darkGreenItem = new NBTItem(darkGreenStack);
        darkGreenItem.setObject("prefix", PrefixManager.Prefix.DARKGREEN);
        NBTItem darkRedItem = new NBTItem(darkRedStack);
        darkRedItem.setObject("prefix", PrefixManager.Prefix.DARKRED);
        NBTItem purpleItem = new NBTItem(purpleStack);
        purpleItem.setObject("prefix", PrefixManager.Prefix.PURPLE);
        NBTItem goldItem = new NBTItem(goldStack);
        goldItem.setObject("prefix", PrefixManager.Prefix.GOLD);
        NBTItem grayItem = new NBTItem(grayStack);
        grayItem.setObject("prefix", PrefixManager.Prefix.GRAY);
        inv.setItem(10, blackItem.getItem());
        inv.setItem(12, darkBlueItem.getItem());
        inv.setItem(14, darkGreenItem.getItem());
        inv.setItem(16, darkAquaItem.getItem());
        inv.setItem(28, darkRedItem.getItem());
        inv.setItem(30, purpleItem.getItem());
        inv.setItem(32, goldItem.getItem());
        inv.setItem(34, grayItem.getItem());
        inv.setItem(49, closeItem.getItem());
        inv.setItem(50, pageItem.getItem());
        inv.setItem(53, defaultItem.getItem());
        p.openInventory(inv);
    }
    public void openInv2(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§9§lPrefixes §7Seite §b2 §7von §b2");
        ItemStack placeHolderStack = new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build();
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, placeHolderStack);
        PrefixManager manager = new PrefixManager(p.getUniqueId().toString());
        PrefixColorManager colorManager = new PrefixColorManager(p.getUniqueId().toString());
        ItemStack darkGrayStack = new ItemBuilder("§8§lDunkelgrau", Material.GRAY_CONCRETE, 1).build();
        ItemMeta darkGrayMeta = darkGrayStack.getItemMeta();
        darkGrayMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bDunkelgrau§7." , "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.DARKGRAY.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKGRAY.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.DARKGRAY.getColor())) {
            darkGrayMeta.addEnchant(Enchantment.LURE, 0, true);
            darkGrayMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        darkGrayStack.setItemMeta(darkGrayMeta);
        ItemStack blueStack = new ItemBuilder("§9§lBlau", Material.BLUE_CONCRETE_POWDER, 1).build();
        ItemMeta blueMeta = blueStack.getItemMeta();
        blueMeta.setLore(Arrays.asList(new String[] { "",  "§7➥ Du änderst deine Prefixfarbe zu §bBlau§7." , "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.BLUE.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.BLUE.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.BLUE.getColor())) {
            blueMeta.addEnchant(Enchantment.LURE, 0, true);
            blueMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        blueStack.setItemMeta(blueMeta);
        ItemStack greenStack = new ItemBuilder("§a§lHellgrün", Material.LIME_CONCRETE, 1).build();
        ItemMeta greenMeta = greenStack.getItemMeta();
        greenMeta.setLore(Arrays.asList(new String[] { "",  "§7➥ Du änderst deine Prefixfarbe zu §bHellgrün§7." , "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.GREEN.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.GREEN.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.GREEN.getColor())) {
            greenMeta.addEnchant(Enchantment.LURE, 0, true);
            greenMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        greenStack.setItemMeta(greenMeta);
        ItemStack aquaStack = new ItemBuilder("§b§lHellblau", Material.LIGHT_BLUE_CONCRETE, 1).build();
        ItemMeta aquaMeta = aquaStack.getItemMeta();
        aquaMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bHellblau§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.AQUA.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.AQUA.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.AQUA.getColor())) {
            aquaMeta.addEnchant(Enchantment.LURE, 0, true);
            aquaMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        aquaStack.setItemMeta(aquaMeta);
        ItemStack redStack = new ItemBuilder("§c§lRot", Material.RED_CONCRETE_POWDER, 1).build();
        ItemMeta redMeta = redStack.getItemMeta();
        redMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bRot§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.RED.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.RED.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.RED.getColor())) {
            redMeta.addEnchant(Enchantment.LURE, 0, true);
            redMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        redStack.setItemMeta(redMeta);
        ItemStack magentaStack = new ItemBuilder("§d§lMagenta", Material.MAGENTA_CONCRETE, 1).build();
        ItemMeta magentaMeta = magentaStack.getItemMeta();
        magentaMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bMagenta§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.MAGENTA.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.MAGENTA.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.MAGENTA.getColor())) {
            magentaMeta.addEnchant(Enchantment.LURE, 0, true);
            magentaMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        magentaStack.setItemMeta(magentaMeta);
        ItemStack yellowStack = new ItemBuilder("§e§lGelb", Material.YELLOW_CONCRETE, 1).build();
        ItemMeta yellowMeta = yellowStack.getItemMeta();
        yellowMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bGelb§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.YELLOW.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.YELLOW.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.YELLOW.getColor())) {
            yellowMeta.addEnchant(Enchantment.LURE, 0, true);
            yellowMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        yellowStack.setItemMeta(yellowMeta);
        ItemStack whiteStack = new ItemBuilder("§f§lWeiß", Material.WHITE_CONCRETE, 1).build();
        ItemMeta whiteMeta = whiteStack.getItemMeta();
        whiteMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zu §bWeiß§7.", "", statusString(p, servername + ".Prefix." + PrefixManager.Prefix.WHITE.getName(), manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.WHITE.getColor()))}));
        if (manager.getPrefixStatus(p.getUniqueId().toString(), PrefixManager.Prefix.WHITE.getColor())) {
            whiteMeta.addEnchant(Enchantment.LURE, 0, true);
            whiteMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS } );
        }
        whiteStack.setItemMeta(whiteMeta);
        ItemStack closeStack = new ItemBuilder("§4Schließen", Material.BARRIER, 1).build();
        ItemStack pageStack = new ItemBuilder("§9Vorherige Seite", Material.MAP, 1).build();
        ItemStack defaultStack = new ItemBuilder("§bStandart", Material.NETHER_STAR, 1).build();
        ItemMeta defaultMeta = defaultStack.getItemMeta();
        String defaultPrefix = new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getTabGroupColor();
        for (PrefixManager.Prefix d : manager.getPrefix()) {
            if (d.getColor().equals(new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getTabGroupColor()))
                defaultMeta.setLore(Arrays.asList(new String[] { "", "§7➥ Du änderst deine Prefixfarbe zum Standart (§" + d.getColor() + d.getName() + "§7)"}));
        }
        defaultStack.setItemMeta(defaultMeta);
        NBTItem defaultItem = new NBTItem(defaultStack);
        defaultItem.setObject("prefix-default", true);
        NBTItem closeItem = new NBTItem(closeStack);
        closeItem.setObject("close", true);
        NBTItem pageItem = new NBTItem(pageStack);
        pageItem.setObject("page-1", true);
        NBTItem darkGrayItem = new NBTItem(darkGrayStack);
        darkGrayItem.setObject("prefix", PrefixManager.Prefix.DARKGRAY);
        NBTItem blueItem = new NBTItem(blueStack);
        blueItem.setObject("prefix", PrefixManager.Prefix.BLUE);
        NBTItem aquaItem = new NBTItem(aquaStack);
        aquaItem.setObject("prefix", PrefixManager.Prefix.AQUA);
        NBTItem greenItem = new NBTItem(greenStack);
        greenItem.setObject("prefix", PrefixManager.Prefix.GREEN);
        NBTItem redItem = new NBTItem(redStack);
        redItem.setObject("prefix", PrefixManager.Prefix.RED);
        NBTItem magentaItem = new NBTItem(magentaStack);
        magentaItem.setObject("prefix", PrefixManager.Prefix.MAGENTA);
        NBTItem yellowItem = new NBTItem(yellowStack);
        yellowItem.setObject("prefix", PrefixManager.Prefix.YELLOW);
        NBTItem whiteItem = new NBTItem(whiteStack);
        whiteItem.setObject("prefix", PrefixManager.Prefix.WHITE);
        inv.setItem(10, darkGrayItem.getItem());
        inv.setItem(12, blueItem.getItem());
        inv.setItem(14, greenItem.getItem());
        inv.setItem(16, aquaItem.getItem());
        inv.setItem(28, redItem.getItem());
        inv.setItem(30, magentaItem.getItem());
        inv.setItem(32, yellowItem.getItem());
        inv.setItem(34, whiteItem.getItem());
        inv.setItem(49, closeItem.getItem());
        inv.setItem(48, pageItem.getItem());
        inv.setItem(53, defaultItem.getItem());
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
