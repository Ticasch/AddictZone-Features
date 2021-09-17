package net.tiam.addictzone_features.managers;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.commands.PlainlessCMD;
import net.tiam.addictzone_features.commands.VanishCMD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class TablistManager {
    private Scoreboard scoreboard;

    private Player player;

    private final LuckPerms luckPerms = LuckPermsProvider.get();

    private User user;

    public TablistManager(Player player) {
        this.player = player;
        this.user = this.luckPerms.getUserManager().getUser(player.getUniqueId());
        if (player.getScoreboard() == null) {
            this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        } else {
            this.scoreboard = player.getScoreboard();
        }
    }

    public void setScoreboard(String prefix, String suffix, String colorchar) {
        Team playerTeam;
        String teamName = randomString();
        if (MainClass.teams.containsKey(this.player.getUniqueId()) &&
                !teamName.equals(MainClass.teams.get(this.player.getUniqueId())))
            this.scoreboard.getTeam(MainClass.teams.get(this.player.getUniqueId())).unregister();
        if (this.scoreboard.getTeam(teamName) == null) {
            playerTeam = this.scoreboard.registerNewTeam(teamName);
        } else {
            playerTeam = this.scoreboard.getTeam(teamName);
        }
        playerTeam.setPrefix(prefix);
        playerTeam.setSuffix(suffix);
        playerTeam.setColor(ChatColor.getByChar(colorchar));
        playerTeam.addEntry(this.player.getName());
        if (MainClass.teams.containsKey(this.player.getUniqueId())) {
            MainClass.teams.replace(this.player.getUniqueId(), teamName);
        } else {
            MainClass.teams.put(this.player.getUniqueId(), teamName);
        }
        Bukkit.getOnlinePlayers().forEach(all -> all.setScoreboard(this.scoreboard));
    }

    public void setScoreboard() {
        String suffix;
        Team playerTeam;
        String uuid = String.valueOf(player.getUniqueId());
        String prefixcolor = new PrefixColorManager(uuid).getTabColor();
        String prefix = "§" + prefixcolor + this.user.getCachedData().getMetaData().getPrefix().replace("&", "§");
        if (this.luckPerms.getUserManager().getUser(this.player.getUniqueId()).getCachedData().getMetaData().getSuffix() == null) {
            suffix = "";
        } else {
            suffix = " " + this.user.getCachedData().getMetaData().getSuffix().replace("&", "§");
        }
        String teamName = randomString();
        if (MainClass.teams.containsKey(this.player.getUniqueId()) &&
                !teamName.equals(MainClass.teams.get(this.player.getUniqueId())))
            this.scoreboard.getTeam(MainClass.teams.get(this.player.getUniqueId())).unregister();
        if (this.scoreboard.getTeam(teamName) == null) {
            playerTeam = this.scoreboard.registerNewTeam(teamName);
        } else {
            playerTeam = this.scoreboard.getTeam(teamName);
        }
        playerTeam.setPrefix(prefix + " §8| ");
        playerTeam.setSuffix(suffix);
        playerTeam.setColor(ChatColor.GRAY);
        playerTeam.addEntry(this.player.getName());
        if (MainClass.teams.containsKey(this.player.getUniqueId())) {
            MainClass.teams.replace(this.player.getUniqueId(), teamName);
        } else {
            MainClass.teams.put(this.player.getUniqueId(), teamName);
        }
        Bukkit.getOnlinePlayers().forEach(all -> all.setScoreboard(this.scoreboard));
    }


    public void updateIfNecessary() {
        if (!VanishCMD.VANISHED.contains(this.player)) {
            String uuid = String.valueOf(player.getUniqueId());
            String prefixcolor = new PrefixColorManager(uuid).getTabColor();
            String suffix, prefix = "§" + prefixcolor + this.user.getCachedData().getMetaData().getPrefix().replace("&", "§");
            if (this.luckPerms.getUserManager().getUser(this.player.getUniqueId()).getCachedData().getMetaData().getSuffix() == null) {
                suffix = "";
            } else {
                suffix = " " + this.user.getCachedData().getMetaData().getSuffix().replace("&", "§");
            }
            if (!this.scoreboard.getTeam(MainClass.teams.get(this.player.getUniqueId())).getPrefix().equals(prefix + " ") || !this.scoreboard.getTeam((String) MainClass.teams.get(this.player.getUniqueId())).getSuffix().equals(" " + suffix))
                Bukkit.getScheduler().runTask(MainClass.getPlugin(), () -> setScoreboard());
        }
    }

    public String randomString() {
        String string = "" + (new TablistWeightManager(this.user.getPrimaryGroup())).getWeight();
        while ((string.getBytes()).length < 5)
            string = "0" + string;
        return string + "a" + this.player.getUniqueId().toString().substring(1, 5);
    }
}
