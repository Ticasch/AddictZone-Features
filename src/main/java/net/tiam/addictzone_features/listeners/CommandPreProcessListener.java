package net.tiam.addictzone_features.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.SpyCMD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CommandPreProcessListener implements Listener {
    private List<String> helpList = setUpedList();
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String msg = e.getMessage();
        Player player = e.getPlayer();
        SpyCMD.SPYED.keySet().forEach(spyed -> {
            if (SpyCMD.SPYED.get(spyed) == spyed) {
                if (player != spyed)
                    spyed.sendMessage(prefix + "§7[§cSpy-All§7] " + player.getName() + "§8➜ §b" + e.getMessage());
            } else if (SpyCMD.SPYED.get(spyed) == player) {
                spyed.sendMessage(prefix + "[§cSpy-Specific§7] " +  player.getName() + "§8➜ §b" + e.getMessage());
            }
        });
    }

    @EventHandler
    public void onHelp(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled())
            return;
        Player player = event.getPlayer();
        String msg = event.getMessage().split(" ")[0];
        HelpTopic helpTopic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
        if (helpTopic == null) {
            event.setCancelled(true);
            player.sendMessage(prefix + "Diesen Befehl gibt es nicht. Benutze §b/Help §7für Hilfe.");
        }
    }

    @EventHandler
    public void onBlock(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled())
            return;
        String message = event.getMessage().split(" ")[0];
        if (!event.getPlayer().isOp() && this.helpList.contains(message.toLowerCase())) {
            event.getPlayer().sendMessage(noperm);
            event.setCancelled(true);
        }
        if ((message.toLowerCase().startsWith("/mv") || message.toLowerCase().startsWith("/multiverse")) && !event.getPlayer().isOp()) {
            event.getPlayer().sendMessage(noperm);
            event.setCancelled(true);
        }
    }

    public List<String> setUpedList() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(new String[] {
                "/?", "/bukkit:?", "/help", "/bukkit:help", "/pl", "/plugin", "/plugins", "/bukkit:pl", "/bukkit:plugins", "/bukkit:plugin",
                "/me", "/say", "/tell", "/targetoffset", "/to", "/multiverse", "/mv" }));
        return list;
    }
}
