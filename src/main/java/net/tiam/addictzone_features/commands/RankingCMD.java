package net.tiam.addictzone_features.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

import org.bukkit.entity.Player;

public class RankingCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String line = MainClass.Line;
    String usage = MainClass.Prefix + "§7Benutze: ";
    LuckPerms luckPerms = LuckPermsProvider.get();
    String gifter;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        //CommandClass in work
        CommandSender p = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("ranking")) {
                if (p instanceof ConsoleCommandSender) {
                    gifter = servername;
                } else if (p instanceof Player) {
                    gifter = p.getName();
                }
            if (p.hasPermission(servername + "gift")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (args.length == 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        User user = luckPerms.getUserManager().getUser(String.valueOf(target));
                        String groupName = String.valueOf(args[1]);
                        Group group = luckPerms.getGroupManager().getGroup(groupName);
                        if (group == null) {
                            p.sendMessage(prefix + "Diese Gruppe exsitiert nicht.");
                        } else if (target == null) {
                            p.sendMessage(prefix + "Dieser Spielr ist nicht online.");
                        } else {
                            if (!p.hasPermission(servername + ".Gift.Group." + groupName)) {
                                p.sendMessage(prefix + "Du hast keine Rechte diese Gruppe zu vergeben.");
                            } else {
                                String groupNameMetaPrefix = luckPerms.getGroupManager().getGroup(groupName).getCachedData().getMetaData().getPrefix();
                                p.sendMessage(line);
                                p.sendMessage("");
                                p.sendMessage(prefix + "Du hast dem Spieler §b" + target.getName() + " §7den Rang " + groupNameMetaPrefix.replace('&', '§') + " §7für §b7 Tage §7gegeben.");
                                p.sendMessage("");
                                p.sendMessage(line);
                                target.sendMessage(line);
                                target.sendMessage("");
                                target.sendMessage(prefix + "Der Spieler §b" + gifter + " §7hat dir den Rang " + groupNameMetaPrefix.replace('&', '§') + " §7für §b7 Tage §7gegeben.");
                                target.sendMessage("");
                                target.sendMessage(line);
                                all.sendMessage(prefix + "Der Spieler §b" + gifter + " §7hat§b " + target.getName() + " §7den Rang " + groupNameMetaPrefix.replace('&', '§') + " §7für §b7 Tage §7gegeben.");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + target.getName() + " parent addtemp " + groupName + " " + 86400*7 + "s");
                            }
                        }
                    } else if (args.length == 3) {
                        Player target = Bukkit.getPlayer(args[0]);
                        int rankingTime = Integer.parseInt(args[2]);
                        int newRankingTime = rankingTime * 86400;
                        int permittedRankingTime = permittedTimePermission((Player) p);
                        String groupName = String.valueOf(args[1]);
                        Group group = luckPerms.getGroupManager().getGroup(groupName);
                        User user = luckPerms.getUserManager().getUser(String.valueOf(target));
                        if (group == null) {
                            p.sendMessage(prefix + "Diese Gruppe existiert nicht.");
                        } else if (target == null) {
                            p.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                        } else if (!p.hasPermission(servername + ".Gift.Group." + groupName)) {
                            p.sendMessage(prefix + "Du hast keine Rechte diese Gruppe zu vergeben.");
                        } else if (!(permittedRankingTime >= rankingTime)) {
                            p.sendMessage(prefix + "Du kannst diese Gruppe nur maximal §b " + permittedRankingTime + " Tage §7vergeben.");
                        } else {
                            String groupNameMetaPrefix = luckPerms.getGroupManager().getGroup(groupName).getCachedData().getMetaData().getPrefix();
                            p.sendMessage(line);
                            p.sendMessage("");
                            p.sendMessage(prefix + "Du hast dem Spieler §b" + target.getName() + " §7den Rang " + groupNameMetaPrefix.replace('&', '§') + " §7für §b" + rankingTime + " Tage §7gegeben.");
                            p.sendMessage("");
                            p.sendMessage(line);
                            target.sendMessage(line);
                            target.sendMessage("");
                            target.sendMessage(prefix + "Der Spieler §b" + gifter + " §7hat dir den Rang " + groupNameMetaPrefix.replace('&', '§') + " §7für §b" + rankingTime + " Tage §7gegeben.");
                            target.sendMessage("");
                            target.sendMessage(line);
                            all.sendMessage(prefix + "Der Spieler §b" + gifter + " §7hat§b " + target.getName() + " §7den Rang " + groupNameMetaPrefix.replace('&', '§') + " §7für §b" + rankingTime + " Tage §7gegeben.");
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + target.getName() + " parent addtemp " + groupName + " " + newRankingTime + "s");
                        }
                    } else {
                        p.sendMessage(usage + "§b/Raning §7<§bSpieler§7> <§bRang§7> <§c[OPTIONAL]§bZeit§7>");
                    }
                }
            } else {
                p.sendMessage(noperm);

        }
    }
        return false;
}

    public static int permittedTimePermission(Player player) {
        String servername = MainClass.ServerName;
        for (int i = 1000; i >= 0; i--) {
            if (player.hasPermission(servername + ".Gift.Time." + i)) {
                return i;
            }
        }
        return 0;
    }
    public void addPermission(User user, String permission) {
        user.data().add(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }


}
