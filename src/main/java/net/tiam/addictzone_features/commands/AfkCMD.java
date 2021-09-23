package net.tiam.addictzone_features.commands;

import jdk.jfr.Event;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.AfkManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AfkCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String SENDER;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            SENDER = c.getName();
        } else {
            SENDER = servername;
        }
        if (cmd.getName().equalsIgnoreCase("afk")) {
                if (args.length == 0) {
                    if (!(c instanceof Player)) {
                        c.sendMessage(prefix + "Du musst ein spieler sein.");
                        return true;
                    }
                    if (c.hasPermission(servername + ".afk")) {
                        if (new AfkManager(((Player) c).getUniqueId().toString()).getAfk() == false) {
                            c.sendMessage(prefix + "Du hast dich als §babwesend §7markiert.");
                            new AfkManager(((Player) c).getUniqueId().toString()).setAfk(true);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + ((Player) c).getUniqueId().toString() + " meta setsuffix 2000 &8[&cAFK&8]");
                            ((Player) c).sendTitle("§7Du bist derzeit als §c§lAFK §7markiert.", "", 1, 99999999, 1);
                        } else {
                            c.sendMessage(prefix + "Du hast dich als §bwieder anwesend §7markiert");
                            new AfkManager(((Player) c).getUniqueId().toString()).setAfk(false);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + ((Player) c).getUniqueId().toString() + " meta removesuffix 2000");
                            ((Player) c).clearTitle();
                        }
                    } else {
                        c.sendMessage(noperm);
                    }
                } else if (args.length == 1) {
                    if (c.hasPermission(servername + ".sfk.other")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                            return true;
                        }
                        if (new AfkManager(target.getUniqueId().toString()).getAfk() == false) {
                            target.sendMessage(prefix + "Du wurdest von §b" + SENDER + " §7als §babwesend §7markiert.");
                            c.sendMessage(prefix + "Du hast §b" + target.getName() + " §7als §babwesend §7markiert.");
                            new AfkManager(target.getUniqueId().toString()).setAfk(true);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getUniqueId() + " meta setsuffix 2000 §8[§cAFK§8]");
                            target.sendTitle("§7Du bist derzeit als §c§lAFK §7markiert.", "", 1, 99999999, 1);
                        } else {
                            target.sendMessage(prefix + "Du wrudest von §b" + SENDER + " §7als §bwieder anwesend §7markiert.");
                            c.sendMessage(prefix + "Du hast §b" + target.getName() + " §7als §bwieder anwesend §7markiert.");
                            new AfkManager(target.getUniqueId().toString()).setAfk(false);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getUniqueId() + " meta removesuffix 2000");
                            target.clearTitle();
                        }
                    } else {
                        c.sendMessage(prefix + "Du hast keine Rechte, Andere Spieler als §babwesend §7zu markieren.");
                    }
                } else {
                    if (c.hasPermission(servername + ".afk.other")) {
                        c.sendMessage(prefix + "Benutze: §b/Afk §7<[§cOPTIONAL§7]§bSpieler§7>");
                    } else {
                        c.sendMessage(prefix + "Benutze: §b/Afk");
                    }
                }
        }
        return false;
    }
}
