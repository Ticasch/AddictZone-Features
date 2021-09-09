package net.tiam.addictzone_features.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.PrefixColorGroupManager;
import net.tiam.addictzone_features.managers.PrefixColorManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PrefixCMD implements CommandExecutor {
    String mprefix = MainClass.Prefix;
    String noperm = MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String SENDER;
    LuckPerms luckPerms = LuckPermsProvider.get();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        Player p = (Player) sender;
        if (c instanceof Player) {
            SENDER = c.getName();
        } else {
            SENDER = servername;
        }
        if (cmd.getName().equalsIgnoreCase("prefix")) {
            if (c.hasPermission(servername + ".Prefix")) {
                if (args.length == 1) {
                    if (c instanceof Player) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("4") || args[0].equalsIgnoreCase("5") || args[0].equalsIgnoreCase("6") || args[0].equalsIgnoreCase("7") || args[0].equalsIgnoreCase("8") || args[0].equalsIgnoreCase("9") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("b") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("e") || args[0].equalsIgnoreCase("f") || args[0].equalsIgnoreCase("default")) {
                           String color;
                           if (args[0].equalsIgnoreCase("default")) {
                               color = new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getTabGroupColor();
                           } else {
                               color = args[0];
                           }
                            new PrefixColorManager(((Player) c).getUniqueId().toString()).setTabColor(color);
                           c.sendMessage(mprefix + "Du hast deine Prefixfarbe zu '§b" + color + "§7' geändert.");
                        } else {
                            c.sendMessage(mprefix + "Du mussteinen Gültigen Farbcode angeben.");
                        }
                    } else {
                        c.sendMessage(mprefix + "Du musst ein Spieler sein.");
                    }
                } else if (args.length == 2) {
                    if (!c.hasPermission(servername + ".Prefix.other")) {
                        c.sendMessage(noperm);
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("4") || args[0].equalsIgnoreCase("5") || args[0].equalsIgnoreCase("6") || args[0].equalsIgnoreCase("7") || args[0].equalsIgnoreCase("8") || args[0].equalsIgnoreCase("9") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("b") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("e") || args[0].equalsIgnoreCase("f") || args[0].equalsIgnoreCase("default")) {
                       String color;
                        if (args[0].equalsIgnoreCase("default")) {
                            color = new PrefixColorGroupManager(this.luckPerms.getUserManager().getUser(target.getUniqueId()).getPrimaryGroup()).getTabGroupColor();
                        } else {
                            color = args[0];
                        }
                    new PrefixColorManager(target.getUniqueId().toString()).setTabColor(color);
                        target.sendMessage(mprefix + "Deine Prefixfarbe wurde von §B" + SENDER + " §7zu '§b" + color + "§7' geändert.");
                        c.sendMessage(mprefix + "Du hast die Prefixfarbe von §b" + target.getName() + " §7zu '§b" + color + "§7' geändert.");
                    }
                } else {
                    c.sendMessage(mprefix + "Benutze: §b/Prefix §7<§bFarbcode§7>");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
