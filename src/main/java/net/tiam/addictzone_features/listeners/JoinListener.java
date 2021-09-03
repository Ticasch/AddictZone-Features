package net.tiam.addictzone_features.listeners;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.VanishCMD;
import net.tiam.addictzone_features.managers.PrefixColorGroupManager;
import net.tiam.addictzone_features.managers.PrefixColorManager;
import net.tiam.addictzone_features.managers.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

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
            String prefix = "§" + pcolor + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + " §7| ";
            if (this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix() == null) {
                suffix = "";
            } else {
                suffix = " " + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix().replace("&", "§");
            }
            String color = "c";
            (new TablistManager(player)).setScoreboard(prefix, suffix, color);
            player.sendMessage(mprefix + "");
        }
        VanishCMD.VANISHED.stream().filter(vanished -> !player.hasPermission(servername + ".vanish.see")).forEachOrdered(vanished -> player.hidePlayer((Plugin)MainClass.getPlugin(), vanished));
        if (!VanishCMD.VANISHED.contains(player))
            (new TablistManager(player)).setScoreboard();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (VanishCMD.VANISHED.contains(event.getPlayer()))
            VanishCMD.VANISHED.remove(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if (VanishCMD.VANISHED.contains(event.getPlayer()))
            VanishCMD.VANISHED.remove(event.getPlayer());
    }
}
