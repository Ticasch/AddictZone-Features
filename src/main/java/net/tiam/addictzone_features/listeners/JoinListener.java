package net.tiam.addictzone_features.listeners;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.VanishCMD;
import net.tiam.addictzone_features.managers.*;
import net.tiam.addictzone_features.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static org.bukkit.enchantments.Enchantment.*;

public class JoinListener implements Listener {
    private final LuckPerms luckPerms = LuckPermsProvider.get();
    String mprefix = MainClass.Prefix;
    String servername = MainClass.ServerName;
    private User user;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = String.valueOf(player.getUniqueId());
        String group = String.valueOf(this.luckPerms.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
        if (new PrefixColorManager(uuid).getTabColor() == null) {
            new PrefixColorManager(uuid).setTabColor(new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getTabGroupColor());
            //new PrefixColorManager(uuid).setTabColor("7");
        }
        if (VanishCMD.VANISHED.contains(player) || player.hasPermission(servername + ".vanish.auto")) {
            String suffix;
            Bukkit.getOnlinePlayers().forEach(all -> {
                if (player != all && !all.hasPermission(servername + ".vanish.see"))
                    all.hidePlayer((Plugin) MainClass.getPlugin(), player);
            });
            if (!VanishCMD.VANISHED.contains(player))
                VanishCMD.VANISHED.add(player);
            String pcolor = new PrefixColorManager(uuid).getTabColor();
            String prefix = "§" + pcolor + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + " §8| ";
            if (this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix() == null) {
                suffix = "";
            } else {
                suffix = " " + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix().replace("&", "§");
            }
            String color = "c";
            (new TablistManager(player)).setScoreboard(prefix, suffix, color);
            player.sendMessage(mprefix + "Vanish aktiviert.");
        }
        VanishCMD.VANISHED.stream().filter(vanished -> !player.hasPermission(servername + ".vanish.see")).forEachOrdered(vanished -> player.hidePlayer((Plugin) MainClass.getPlugin(), vanished));
        if (!VanishCMD.VANISHED.contains(player))
            (new TablistManager(player)).setScoreboard();
        event.setJoinMessage(null);
        event.getPlayer().clearTitle();

        if (!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().teleport(new WarpManager("SPAWN").getWarp());
            Bukkit.getScheduler().runTaskAsynchronously((Plugin) MainClass.getPlugin(), () -> {
                player.getInventory().setItem(9, new ItemStack(Material.OAK_LOG, 16));
                player.getInventory().setItem(10, new ItemStack(Material.BIRCH_LOG, 16, (short) 1));
                player.getInventory().setItem(11, new ItemStack(Material.SPRUCE_LOG, 16, (short) 2));
                player.getInventory().setItem(13, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_HELMET, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(15, new ItemStack(Material.DARK_OAK_LOG, 16));
                player.getInventory().setItem(16, new ItemStack(Material.JUNGLE_LOG, 16));
                player.getInventory().setItem(17, new ItemStack(Material.ACACIA_LOG, 16));
                player.getInventory().setItem(18, new ItemStack(Material.OAK_SAPLING, 16));
                player.getInventory().setItem(19, new ItemStack(Material.BIRCH_SAPLING, 16));
                player.getInventory().setItem(20, new ItemStack(Material.SPRUCE_SAPLING, 16));
                player.getInventory().setItem(22, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_CHESTPLATE, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(24, new ItemStack(Material.DARK_OAK_SAPLING, 16));
                player.getInventory().setItem(25, new ItemStack(Material.JUNGLE_SAPLING, 16));
                player.getInventory().setItem(26, new ItemStack(Material.ACACIA_SAPLING, 16));
                player.getInventory().setItem(31, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_LEGGINGS, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(0, new ItemStack(Material.BREAD, 64));
                player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 64));
                player.getInventory().setItem(2, new ItemBuilder("§9§lStarter Schwert", Material.IRON_SWORD, 1, DAMAGE_ALL, 2).build());
                player.getInventory().setItem(4, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_BOOTS, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(6, new ItemBuilder("§9§lStarter Schaufel", Material.IRON_SHOVEL, 1, DIG_SPEED, 2).build());
                player.getInventory().setItem(7, new ItemBuilder("§9§lStarter Axt", Material.IRON_AXE, 1, DIG_SPEED, 2).build());
                player.getInventory().setItem(8, new ItemBuilder("§9§lStarter Spitzhacke", Material.IRON_PICKAXE, 1, DIG_SPEED, 2).build());
                player.getInventory().setItem(12, new ItemStack(Material.WARPED_STEM, 16));
                player.getInventory().setItem(14, new ItemStack(Material.CRIMSON_STEM, 16));
                player.getInventory().setItem(27, new ItemStack(Material.CHEST, 4));
                player.getInventory().setItem(28, new ItemStack(Material.FURNACE, 2));
                player.getInventory().setItem(34, new ItemStack(Material.CRAFTING_TABLE, 2));
                player.getInventory().setItem(35, new ItemStack(Material.BARREL, 4));
                player.sendMessage(MainClass.Line);
                player.sendMessage("");
                player.sendMessage(mprefix + "Willkommen auf §9§lAddictZone.net§7!");
                player.sendMessage("");
                player.sendMessage(MainClass.Line);
                player.teleport(new WarpManager("Spawn").getWarp());
            });
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (VanishCMD.VANISHED.contains(event.getPlayer()))
            VanishCMD.VANISHED.remove(event.getPlayer());
        event.setQuitMessage(null);
        if (new AfkManager(event.getPlayer().getUniqueId().toString()).getAfk() == true) {
            new AfkManager(event.getPlayer().getUniqueId().toString()).setAfk(false);
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if (VanishCMD.VANISHED.contains(event.getPlayer()))
            VanishCMD.VANISHED.remove(event.getPlayer());
        event.setLeaveMessage(null);
        if (new AfkManager(event.getPlayer().getUniqueId().toString()).getAfk() == true) {
            new AfkManager(event.getPlayer().getUniqueId().toString()).setAfk(false);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + event.getPlayer().getUniqueId() + " meta removesuffix 2000");
        }
    }

    @EventHandler
    public void onDeath(PlayerRespawnEvent e) {
        e.getPlayer().teleport(new WarpManager("SPAWN").getWarp());
    }

    @EventHandler
    public void onWorldSwitch(PlayerChangedWorldEvent e) {
        if (!e.getPlayer().hasPermission(servername + ".fly.worldswitch")) {
            e.getPlayer().setFlying(false);
            e.getPlayer().setAllowFlight(false);
        }
    }
}
