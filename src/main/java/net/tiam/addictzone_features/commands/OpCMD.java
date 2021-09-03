package net.tiam.addictzone_features.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import net.tiam.addictzone_features.MainClass;
import org.bukkit.entity.Player;

public class OpCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
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
        if (cmd.getName().equalsIgnoreCase("op")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (c.hasPermission(servername + ".op") || c.hasPermission(servername + ".op.interact")) {
                    if (target == null) {
                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                    } else {
                        if (target.isOp()) {
                            c.sendMessage(prefix + "Dieser Spieler ist bereits Operator.");
                        } else {
                            c.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden alle Rechte zugeteilt.");
                            target.sendMessage(prefix + "Dir wurde der Operatorstatus von §b" + SENDER + " §7zugeteilt");
                            target.sendMessage(prefix + "Du ignorierst nun alle Beschränkungen.");
                            target.setOp(true);
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + target.getName() + " permission set * true");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission(servername + ".op.notify") && all != c && all != target) {
                                    all.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7der Operatorstatus zugewiesen.");
                                }
                            }
                        }
                    }
                } else {
                    c.sendMessage(noperm);
                }
            } else if (c.hasPermission(servername + ".op") || c.hasPermission(servername + ".op.interact")) {
                c.sendMessage(prefix + "Benutze: §b/Op §7<§bSpieler§7>");
            } else {
                c.sendMessage(noperm);
            }
        } else if (cmd.getName().equalsIgnoreCase("deop")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (c.hasPermission(servername + ".deop") || c.hasPermission(servername + ".op.interact")) {
                    if (target == null) {
                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                    } else {
                        if (!target.isOp()) {
                            c.sendMessage(prefix + "Dieser Spieler ist kein Operator.");
                        } else {
                            c.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurde der Operatorstatus entzogen.");
                            target.sendMessage(prefix + "Dir wurde der Operatorstatus von §b" + SENDER + " §7entzogen.");
                            target.setOp(false);
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + target.getName() + " permission clear");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission(servername + ".op.notify") && all != c && all != target) {
                                    all.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7der Operatorstatus entzogen.");
                                }
                            }
                        }
                    }
                } else {
                    c.sendMessage(noperm);
                }
            } else if (c.hasPermission(servername + ".deop") || c.hasPermission(servername + ".op.interact")) {
                c.sendMessage(prefix + "Benutze: §b/Op §7<§bSpieler§7>");
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
