package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

public class GmCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = MainClass.Prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String player = MainClass.Prefix + "Du musst ein Spieler sein.";
    String usage = prefix + "Benutze: §b/Gamemode §7<§b0§7-§b3§7> <§c[OPTIONAL]§bSpieler§7>";
    String SENDER;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            SENDER = c.getName();

        } else if (c instanceof ConsoleCommandSender) {
            SENDER = "AddictZone";
        } else {
            SENDER = null;
        }
        if (cmd.getName().equalsIgnoreCase("gm")) {
            if (c.hasPermission(servername + ".Gm.Use")) {
                if (args.length == 1) {

                        if (args[0].equalsIgnoreCase("0")) {
                            if (c.hasPermission(servername + ".Gm.0")) {
                                if (c instanceof Player) {
                                    ((Player) c).setGameMode(GameMode.SURVIVAL);
                                    c.sendMessage(prefix + "Du bist nun im Gamemode §b0§7.");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                            all.sendMessage(prefix + "Der Spieler §b" + c.getName() + " §7ging in den Gamemode §b0");
                                            return true;
                                        }
                                    }
                                } else {
                                    c.sendMessage(player);
                                }
                            } else {
                                c.sendMessage(prefix + "Du hast keine Rechte, in den Gamemode §b0 §7zu gehen.");
                            }
                        } else if (args[0].equalsIgnoreCase("1")) {
                            if (c.hasPermission(servername + ".Gm.1")) {
                                if (c instanceof Player) {
                                    ((Player) c).setGameMode(GameMode.CREATIVE);
                                    c.sendMessage(prefix + "Du bist nun im Gamemode §b1§7.");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                            all.sendMessage(prefix + "Der Spieler §b" + c.getName() + " §7ging in den Gamemode §b1");
                                            return true;
                                        }
                                    }
                                } else {
                                    c.sendMessage(player);
                                }
                            } else {
                                c.sendMessage(prefix + "Du hast keine Rechte, in den Gamemode §b1 §7zu gehen.");
                            }
                        } else if (args[0].equalsIgnoreCase("2")) {
                            if (c.hasPermission(servername + ".Gm.2")) {
                                if (c instanceof Player) {
                                    ((Player) c).setGameMode(GameMode.ADVENTURE);
                                    c.sendMessage(prefix + "Du bist nun im Gamemode §b2§7.");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                            all.sendMessage(prefix + "Der Spieler §b" + c.getName() + " §7ging in den Gamemode §b2");
                                            return true;
                                        }
                                    }
                                } else {
                                    c.sendMessage(player);
                                }
                            } else {
                                c.sendMessage(prefix + "Du hast keine Rechte, in den Gamemode §b2 §7zu gehen.");
                            }
                        } else if (args[0].equalsIgnoreCase("3")) {
                            if (c.hasPermission(servername + ".Gm.3")) {
                                if (c instanceof Player) {
                                    ((Player) c).setGameMode(GameMode.SPECTATOR);
                                    c.sendMessage(prefix + "Du bist nun im Gamemode §b3§7.");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                            all.sendMessage(prefix + "Der Spieler §b" + c.getName() + " §7ging in den Gamemode §b3");
                                            return true;
                                        }
                                    }
                                } else {
                                    c.sendMessage(player);
                                }
                            } else {
                                c.sendMessage(prefix + "Du hast keine Rechte, in den Gamemode §b3 §7zu gehen.");
                            }
                        }

                } else if (args.length == 2) {
                        if (c.hasPermission(servername + ".Gm.Use.Other")) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (args[0].equalsIgnoreCase("0")) {
                                if (c.hasPermission(servername + ".Gm.0")) {
                                    if (target == null) {
                                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                                    } else {
                                        c.sendMessage(prefix + "Du hast den Spieler §b" + target.getName() + " §7 in den Gamemode §b0 §7gesetzt.");
                                        target.sendMessage(prefix + "Du wurdest von §b" + SENDER + " §7in den Gamemode §b0§7 getzt.");
                                        target.setGameMode(GameMode.SURVIVAL);
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                                all.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7in den Gamemode §b0 §7gsetzt.");
                                                return true;
                                            }
                                        }
                                    }
                                } else {
                                    c.sendMessage(prefix + "Du hast keine Rechte, den Spieler §b" + target.getName() + " §7in den Gamemode §b0 §7zu setzen.");
                                }
                            } else if (args[0].equalsIgnoreCase("1")) {
                                if (c.hasPermission(servername + ".Gm.1")) {
                                    if (target == null) {
                                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                                    } else {
                                        c.sendMessage(prefix + "Du hast den Spieler §b" + target.getName() + " §7 in den Gamemode §b1 §7gesetzt.");
                                        target.sendMessage(prefix + "Du wurdest von §b" + SENDER + " §7in den Gamemode §b1§7 getzt.");
                                        target.setGameMode(GameMode.CREATIVE);
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                                all.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7in den Gamemode §b1 §7gsetzt.");
                                                return true;
                                            }
                                        }
                                    }
                                } else {
                                    c.sendMessage(prefix + "Du hast keine Rechte, den Spieler §b" + target.getName() + " §7in den Gamemode §b1 §7zu setzen.");
                                }
                            } else if (args[0].equalsIgnoreCase("2")) {
                                if (c.hasPermission(servername + ".Gm.2")) {
                                    if (target == null) {
                                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                                    } else {
                                        c.sendMessage(prefix + "Du hast den Spieler §b" + target.getName() + " §7 in den Gamemode §b2 §7gesetzt.");
                                        target.sendMessage(prefix + "Du wurdest von §b" + SENDER + " §7in den Gamemode §b2§7 getzt.");
                                        target.setGameMode(GameMode.ADVENTURE);
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                                all.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7in den Gamemode §b2 §7gsetzt.");
                                                return true;
                                            }
                                        }
                                    }
                                } else {
                                    c.sendMessage(prefix + "Du hast keine Rechte, den Spieler §b" + target.getName() + " §7in den Gamemode §b2 §7zu setzen.");
                                }
                            } else if (args[0].equalsIgnoreCase("3")) {
                                if (c.hasPermission(servername + ".Gm.3")) {
                                    if (target == null) {
                                        c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                                    } else {
                                        c.sendMessage(prefix + "Du hast den Spieler §b" + target.getName() + " §7 in den Gamemode §b3 §7gesetzt.");
                                        target.sendMessage(prefix + "Du wurdest von §b" + SENDER + " §7in den Gamemode §b3§7 getzt.");
                                        target.setGameMode(GameMode.SPECTATOR);
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission(servername + ".Gm.Notify") && all != c) {
                                                all.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde von §b" + SENDER + " §7in den Gamemode §b3 §7gsetzt.");
                                                return true;
                                            }
                                        }
                                    }
                                } else {
                                    c.sendMessage(prefix + "Du hast keine Rechte, den Spieler §b" + target.getName() + " §7in den Gamemode §b3 §7zu setzen.");
                                }
                            } else {
                                c.sendMessage(usage);
                            }
                        } else {
                            c.sendMessage(prefix + "Du hast keine Rechte, andere Spieler in Spielmodi zu setzen.");
                        }

                } else {
                    c.sendMessage(usage);
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}


