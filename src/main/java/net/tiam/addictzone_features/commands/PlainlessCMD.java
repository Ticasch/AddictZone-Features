package net.tiam.addictzone_features.commands;


import net.luckperms.api.*;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.util.Random;
import java.util.UUID;

public class PlainlessCMD implements CommandExecutor {

    public static String ColorChar;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = MainClass.Prefix + MainClass.NoPerm;
        String offline = prefix + "§7Dieser Spieler ist nicht online.";
        String serverName = MainClass.ServerName;
        String use = MainClass.Prefix + "§7Benutze:" ;
        String SENDER;
        UUID Puuid;
        CommandSender p = (CommandSender) sender;
        if (p instanceof Player) {
            SENDER = p.getName();
            Puuid = Bukkit.getPlayerUniqueId(p.getName());
        } else {
            SENDER = serverName;
        }
        LuckPerms luckPerms = LuckPermsProvider.get();

        if (cmd.getName().equalsIgnoreCase("AddictZone")) {
            if (!p.hasPermission(serverName + ".Interact")) {
                p.sendMessage(noperm);
            } else {
                //EconomyProvider economyProvider = new EconomyProvider(target);
                //ConfigProvider configProvider = new ConfigProvider();
                Double money = 1.0;//Double.valueOf(economyProvider.getEconomyPlayer().getMoney());
                Double bank = 1.0;//Double.valueOf(economyProvider.getEconomyPlayer().getBank());
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("restart")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.kickPlayer(prefix + "Server Restart!");
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
                        }
                    } else if (args[0].equalsIgnoreCase("getprefix") || args[0].equalsIgnoreCase("prefix")) {
                        p.sendMessage(prefix + "§7Der Serverweite Systemprefix liebt ist " + prefix);
                    } else if (args[0].equalsIgnoreCase("randomplayer")) {
                        String r = ((Player)Bukkit.getOnlinePlayers().toArray()[(new Random()).nextInt(Bukkit.getOnlinePlayers().size())]).getDisplayName();
                        p.sendMessage(prefix + "Der Spieler §b" + r + " §7wurde ausgewählt.");
                    } else if (args[0].equalsIgnoreCase("permissions")) {
                        sendPermissionMessage1(p);
                    } else {
                        sendUsageMessage(p);
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (args[0].equalsIgnoreCase("rang")) {
                        UUID Tuuid = Bukkit.getPlayerUniqueId(String.valueOf(target));
                        if (target == null) {
                            p.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                        } else {
                            p.sendMessage(prefix + "Fick dich");
                        }
                    } else if (args[0].equalsIgnoreCase("setprefix")) {
                        String newPrefix = String.valueOf(args[1]);
                        newPrefix = ChatColor.translateAlternateColorCodes('&', newPrefix);
                        p.sendMessage(prefix + "§7Du hast den Serverweiten §bSystemprefix §7zu " + args[1].replace('&', '§') + " §7gesetzt.");
                        //configProvider.setPrefix("Prefix", String.valueOf(args[1]));
                    }  else if (args[0].equalsIgnoreCase("op")) {
                        if (target == null) {
                            p.sendMessage(offline);
                        } else {
                            if (target.isOp()) {
                                p.sendMessage(prefix + "Dieser Spieler ist bereits Operator.");
                            } else {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden alle Rechte zugeteilt.");
                                    target.sendMessage(prefix + "Dir wurde der Operatorstatus von §b" + SENDER + " §7zugeteilt");
                                    target.sendMessage(prefix + "Du ignorierst nun alle Beschränkungen.");
                                    all.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7der Operatorstatus zugewiesen");
                                    target.setOp(true);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + target.getName() + " permission set * true");
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("deop")) {
                        if (target == null) {
                            p.sendMessage(offline);
                        } else {
                            if (!target.isOp()) {
                                p.sendMessage(prefix + "Dieser Spieler ist kein Operator.");
                            } else {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurde der Operatorstatus entzogen.");
                                    target.sendMessage(prefix + "Dir wurde der Operatorstatus von §b" + SENDER + " §7entzogen.");
                                    all.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7der Operatorstatus entzogen.");
                                    target.setOp(false);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + target.getName() + " permission clear");
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("UUID")) {
                        if (target == null) {
                            p.sendMessage(offline);
                        } else {
                            p.sendMessage(prefix + "§7Die UUID von §b" + target.getName() + " §7ist: §b" + target.getPlayer().getUniqueId() + "§7.");
                        }
                    } else if (args[0].equalsIgnoreCase("permissions")) {
                        if (args[1].equalsIgnoreCase("1")) {
                            sendPermissionMessage1(p);
                        } else {
                            p.sendMessage(prefix + "Wähle zwischen den Seiten §b1-1");
                        }
                    } else {
                        sendUsageMessage(p);
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("randomnumber")) {
                        if (!isDouble(args[1]) || !isDouble(args[2])) {
                            p.sendMessage(prefix + "Du musst ganze Zahlen angeben!");
                        } else {
                            int result = getRandomIntegerBetweenRange(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                            p.sendMessage(prefix + "Deine Zufallszahl zwischen §b" + args[1] + " §7und §b" + args[2] + " §7 beträgt: §b" + result);
                        }
                    } else if (args[0].equalsIgnoreCase("randomdouble")) {
                        if (!isDouble(args[1]) || !isDouble(args[2])) {
                            p.sendMessage(prefix + "Du musst ganze Zahlen angeben!");
                        } else {
                            double result = getRandomDoubleBetweenRange(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                            p.sendMessage(prefix + "Deine Zufallszahl zwischen §b" + args[1] + " §7und §b" + args[2] + " §7 beträgt: §b" + result);
                        }
                    } else if (args[0].equalsIgnoreCase("setcolor")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        String ColorChar = String.valueOf(args[2]);
                    } else {
                        p.sendMessage(prefix + "Hurensohn");
                    }
                } else if (args.length == 4) {
                } else {
                    sendUsageMessage(p);
                }
            }

        }
        return false;
    }
    public void sendUsageMessage (CommandSender p) {
        String prefix = MainClass.Prefix;
        String line = MainClass.Line;
        String servername = MainClass.ServerName;
        p.sendMessage(line);
        p.sendMessage(prefix + "Benutze:");
        p.sendMessage(prefix + "§b/" + servername + " §brestart");
        p.sendMessage(prefix + "§b/" + servername + " §bgetPrefix§7|§bPrefix");
        p.sendMessage(prefix + "§b/" + servername + " §bsetprefix §7<§bPrefix§7>");
        p.sendMessage(prefix + "§b/" + servername + " §bOp§7|§bDeop <§bSpieler§7>");
        p.sendMessage(prefix + "§b/" + servername + " §bUUID§7 <§bSpieler§7>");
        p.sendMessage(prefix + "§b/" + servername + " §bRandomnumber§7|§brandomdouble");
        p.sendMessage(prefix + "§b/" + servername + " §brandomplayer");
        p.sendMessage(prefix + "§b/" + servername + " §bpermissions");
        p.sendMessage(line);
    }
    public void sendPermissionMessage1 (CommandSender c) {
        String prefix = MainClass.Prefix;
        String line = MainClass.Line;
        String servername = MainClass.ServerName;
        c.sendMessage(line);
        c.sendMessage(prefix + "Seite §b1 §7von §b1");
        c.sendMessage(prefix);
        c.sendMessage(prefix + "§b/" + servername + "§7:");
        c.sendMessage(prefix + "§b" + servername + ".Interact");
        c.sendMessage(prefix);
        c.sendMessage(prefix + "§b/Op§7:");
        c.sendMessage(prefix + "§b" + servername + ".Op§7|§b" + servername + ".Deop§7");
        c.sendMessage(prefix + "§b" + servername + ".Op.Interact§7|§b" + servername + ".Op.Notify");
        c.sendMessage(prefix);
        c.sendMessage(prefix + "§b/Gm§7:");
        c.sendMessage(prefix + "§b" + servername + ".Gm.use§7|§b" + servername + ".Gm.§7<§b0§7-§b3§7>");
        c.sendMessage(prefix + "§b" + servername + ".Gm.other§7|§b" + servername + ".Gm.other.§7<§b0§7-§b3§7>");
        c.sendMessage(prefix + "§b" + servername + ".Gm.notify");
        c.sendMessage(line);
    }

    public int getRandomIntegerBetweenRange(int obereGrenze, int untereGrenze) {
        int difference = obereGrenze - untereGrenze;
        int random = (int)(Math.random() * difference) + untereGrenze;
        return random;
    }
    public double getRandomDoubleBetweenRange(double obereGrenze, double untereGrenze) {
        double difference = obereGrenze - untereGrenze;
        double random = (double)(Math.random() * difference) + untereGrenze;
        return random;
    }

    public static boolean isDouble(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException|NullPointerException nfe) {
            return false;
        } return true;
    }
}
