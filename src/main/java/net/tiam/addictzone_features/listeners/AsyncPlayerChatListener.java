package net.tiam.addictzone_features.listeners;


import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.tiam.addictzone_features.managers.PrefixColorManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {
    LuckPerms luckPerms = LuckPermsProvider.get();

    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (MainClass.getClosed().booleanValue() && !event.getPlayer().hasPermission(servername + ".chat.chatlock.bypass")) {
            String msg = event.getMessage();
            System.out.println(prefix + "§7[§cChat-Event§7] §b" + event.getPlayer().getName() + " §7" + msg);
            event.getPlayer().sendMessage(prefix + "Der chat ist deaktiviert." );
            event.setCancelled(true);
            return;
        }
        String message = event.getMessage();
        message = message.replace("%", "%%");
        if (event.getPlayer().hasPermission(servername + ".chat.color")) {
            message = message.replace("&", "§");
            message = message.replace("&l", "§l");
        }
        if (event.getPlayer().hasPermission(servername + ".chat.format"))
            message = message.replace("&l", "§l");
        if (event.getPlayer().hasPermission(servername + ".chat.ew"))
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (message.contains(player.getName()))
                    message = message.replace(player.getName(), "§b@" + player.getName() + "§7");
            }
        String color = new PrefixColorManager(event.getPlayer().getUniqueId().toString()).getTabColor();
        if (event.getPlayer().hasPermission(servername + ".chat.emptylines")) {
            event.setFormat("§8➜\n" + "§" + color + this.luckPerms.getUserManager().getUser(event.getPlayer().getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + " §8| §7"  + event.getPlayer().getName() + " §8➜ §7" + message + "\n§8➜");
        } else {
            event.setFormat("§" + color + this.luckPerms.getUserManager().getUser(event.getPlayer().getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + " §8| §7" + event.getPlayer().getName() + " §8➜ §7"+ message);
        }
    }
}