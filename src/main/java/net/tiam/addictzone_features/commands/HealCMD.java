package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = MainClass.NoPerm;
        String servername = MainClass.ServerName;
        String SENDER;
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            SENDER = c.getName();
        } else {
            SENDER = servername;
        }
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (c.hasPermission(servername + ".heal")) {
                if (args.length == 0) {
                    c.sendMessage(prefix + "Du hast dich geheilt.");
                    ((Player) c).setHealth(20);
                } else if (args.length == 1) {
                    if (!c.hasPermission(servername + ".heal.other")) {
                        c.sendMessage(prefix + "Du hast keine Rechte, andere Spieler zu heilen.");
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                      c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                    }  else {
                        c.sendMessage(prefix + "Du hast §b" + target.getName() + " §7geheilt.");
                        target.sendMessage(prefix + "Du wurdest von §b" + SENDER + " §7geheilt.");
                        target.setHealth(20);
                    }
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Heal §7<[§cOPTIONAL§7]§bSpieler§7>");
                }
            } else {
                c.sendMessage(noperm);
            }
        } else if (cmd.getName().equalsIgnoreCase("feed")) {
            if (c.hasPermission(servername + ".feed")) {
                if (args.length == 0) {
                    c.sendMessage(prefix + "Du hast deinen Hunger gestillt.");
                    ((Player) c).setSaturation(20);
                    ((Player) c).setFoodLevel(20);
                } else if (args.length == 1) {
                    if (!c.hasPermission(servername + ".feed.other")) {
                        c.sendMessage(prefix + "Du hast keine Rechte, den Hunger anderer Spieler zu stillen.");
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                    }  else {
                        c.sendMessage(prefix + "Du hast §b" + target.getName() + "'s§7 Hunger gestillt..");
                        target.sendMessage(prefix + "Dein Hunger wurde von §b" + SENDER + " §7gestillt.");
                        target.setSaturation(20);
                        target.setFoodLevel(20);
                    }
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Feed §7<[§cOPTIONAL§7]§bSpieler§7>");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
