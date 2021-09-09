package net.tiam.addictzone_features.commands;

import java.util.ArrayList;
import java.util.List;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.PrefixColorManager;
import net.tiam.addictzone_features.managers.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.ScoreboardManager;

public class VanishCMD implements CommandExecutor {
    public static List<Player> VANISHED = new ArrayList<>();

    String mprefix = MainClass.Prefix;
    String noperm = MainClass.NoPerm;
    String servername = MainClass.ServerName;
    LuckPerms luckPerms = LuckPermsProvider.get();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player instanceof Player) {
            switch (args.length) {
                case 0:
                    if (player.hasPermission(servername + ".vanish")) {
                        if (!VANISHED.contains(player)) {
                            String suffix;
                            String uuid = String.valueOf(player.getUniqueId());
                            String prefixcolor = new PrefixColorManager(uuid).getTabColor();
                            VANISHED.add(player);
                            String prefix = "§" + prefixcolor + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + " §7| ";
                            if (this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix() == null) {
                                suffix = "";
                            } else {
                                suffix = " " + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix().replace("&", "§");
                            }
                            String colorchar = "c";
                            (new TablistManager(player)).setScoreboard(prefix, suffix, colorchar);
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                if (player != all && !all.hasPermission(servername + ".vanish.see"))
                                    all.hidePlayer((Plugin) MainClass.getPlugin(), player);
                                //(new ScoreboardManager(all)).updatePlayersTeam();
                            });
                            player.sendMessage(mprefix + "Vanish aktiviert.");
                        } else {
                            VANISHED.remove(player);
                            (new TablistManager(player)).setScoreboard();
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                all.showPlayer((Plugin) MainClass.getPlugin(), player);
                                //(new ScoreboardManager(all)).updatePlayersTeam();
                            });
                            player.sendMessage(mprefix + "Vanish deaktiviert.");
                        }
                    } else {
                        player.sendMessage(noperm);
                    }
                    return false;
                case 1:
                    if (player.hasPermission(servername + ".vanish.other")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            player.sendMessage(mprefix + "Dieser Spieler ist nicht online.");
                            return true;
                        }
                        if (!VANISHED.contains(target)) {
                            String suffix;
                            String uuid = String.valueOf(player.getUniqueId());
                            String prefixcolor = new PrefixColorManager(target.getUniqueId().toString()).getTabColor();
                            VANISHED.add(target);
                            String prefix = "§" + prefixcolor + this.luckPerms.getUserManager().getUser(target.getUniqueId()).getCachedData().getMetaData().getPrefix().replace("&", "§") + " §7| ";
                            if (this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix() == null) {
                                suffix = "";
                            } else {
                                suffix = " " + this.luckPerms.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix().replace("&", "§");
                            }
                            String colorchar = "c";
                            (new TablistManager(target)).setScoreboard(prefix, suffix, colorchar);
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                if (target != all && !all.hasPermission(servername + ".vanish.see"))
                                    all.hidePlayer((Plugin) MainClass.getPlugin(), target);
                                //(new ScoreboardManager(all)).updatePlayersTeam();
                            });
                            player.sendMessage(mprefix + "Du hast §b" + target.getName() + " §7in den vanish gesetzt.");
                            target.sendMessage(mprefix + "Du wurdest von §b" + player.getName() + " §7in den Vanish gesetzt.");
                        } else {
                            VANISHED.remove(target);
                            (new TablistManager(target)).setScoreboard();
                            Bukkit.getOnlinePlayers().forEach(all -> {
                                all.showPlayer((Plugin) MainClass.getPlugin(), target);
                                //(new ScoreboardManager(all)).updatePlayersTeam();
                            });
                            player.sendMessage(mprefix + "Du hast §b" + target.getName() + " §7aus dem Vanish gesetzt.");
                            target.sendMessage(mprefix + "Du wurdest von §b" + player.getName() + " §7aus dem Vanish gesetzt.");
                        }
                    } else {
                        player.sendMessage(noperm);
                    }
                    return false;
            }
            if (player.hasPermission(servername + ".vanish") || player.hasPermission(servername + ".vanish.other")) {
                player.sendMessage(mprefix + "Benutze: §b/Vanish §7<[§cOPTIONAL§7] §bSpieler>");
            } else {
                player.sendMessage(noperm);
            }
        } else {
            player.sendMessage(MainClass.Prefix + "Du musst ein Spieler sein.");
        }
        return false;
    }
}
