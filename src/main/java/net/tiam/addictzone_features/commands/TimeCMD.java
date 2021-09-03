package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TimeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String prefix = MainClass.Prefix;
        String noperm = prefix + MainClass.NoPerm;
        String servername = MainClass.ServerName;
        String SENDER;
        String usage = prefix + "Benutze: §b/Time §7<§bTag§7|§bNacht§7> <[§cOPTIONAL§7]§bWelt§7>";
        CommandSender c = (CommandSender) sender;
        if (c instanceof Player) {
            SENDER = c.getName();
        } else {
            SENDER = servername;
        }
        if (cmd.getName().equalsIgnoreCase("Time")) {
            if (c.hasPermission(servername + ".Time")) {
                if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("Tag")) {
                            for (World all : Bukkit.getWorlds()) {
                                all.setTime(1000L);
                            }
                            c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bTag §7geändert.");
                        } else if (args[0].equalsIgnoreCase("night") || args[0].equalsIgnoreCase("Nacht")) {
                            for (World all : Bukkit.getWorlds()) {
                                all.setTime(13000L);
                            }
                            c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bTag §7geändert.");
                        } else {
                            c.sendMessage(usage);
                        }
                } else if (args.length == 2) {
                        World world = Bukkit.getWorld(args[1]);
                        if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("Tag")) {
                            if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("*")) {
                                for (World all : Bukkit.getWorlds()) {
                                    all.setTime(1000L);
                                }
                                c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bTag §7geändert.");
                            } else {
                                if (world == null) {
                                    c.sendMessage(prefix + "Diese Welt existiert nicht.");
                                    return true;
                                }
                                world.setTime(1000L);
                                c.sendMessage(prefix + "Du hast die Tageszeit in der Welt §b" + world.getName() + " §7zu §bTag §7geändert.");
                            }
                        } else if (args[0].equalsIgnoreCase("night") || args[0].equalsIgnoreCase("nacht")) {
                            if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("*")) {
                                for (World all : Bukkit.getWorlds()) {
                                    all.setTime(13000L);
                                }
                                c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bNacht §7geändert.");
                            } else {
                                if (world == null) {
                                c.sendMessage(prefix + "Diese Welt existiert nicht.");
                                return true;
                            }
                                world.setTime(13000L);
                                c.sendMessage(prefix + "Du hast die Tageszeit in der Welt §b" + world.getName() + " §7zu §bNacht §7geändert.");
                            }
                        } else {
                            c.sendMessage(usage);
                        }
                } else {
                    c.sendMessage(usage);
                }
            } else {
                c.sendMessage(noperm);
            }
        } else if (cmd.getName().equalsIgnoreCase("day")) {
            if (c.hasPermission(servername + ".Time")) {
                if (args.length == 0) {
                    for (World all : Bukkit.getWorlds()) {
                        all.setTime(1000L);
                    }
                        c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bTag §7geändert.");
                } else if (args.length == 1) {
                        World world = Bukkit.getWorld(args[0]);
                        if (args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("*")) {
                            for (World all : Bukkit.getWorlds()) {
                                all.setTime(1000L);
                            }
                            c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bTag §7geändert.");
                        } else {
                            if (world == null) {
                            c.sendMessage(prefix + "Diese Welt existiert nicht.");
                            return true;
                        }
                            world.setTime(1000L);
                            c.sendMessage(prefix + "Du hast die Tageszeit in der Welt §b" + world.getName() + " §7zu §bTag §7geändert.");
                        }
                } else {
                    c.sendMessage(usage);
                }
            } else {
                c.sendMessage(noperm);
            }
        } else if (cmd.getName().equalsIgnoreCase("night")) {
            if (c.hasPermission(servername + ".Time")) {
                if (args.length == 0) {
                    for (World all : Bukkit.getWorlds()) {
                        all.setTime(13000L);
                    }
                        c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bNacht §7geändert.");
                } else if (args.length == 1) {
                        World world = Bukkit.getWorld(args[0]);
                        if (args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("*")) {
                            for (World all : Bukkit.getWorlds()) {
                                all.setTime(13000L);
                            }
                            c.sendMessage(prefix + "Du hast die Tageszeit in allen Welten zu §bNacht §7geändert.");
                        } else {
                            if (world == null) {
                                c.sendMessage(prefix + "Diese Welt existiert nicht.");
                                return true;
                            }
                            world.setTime(13000L);
                            c.sendMessage(prefix + "Du hast die Tageszeit in der Welt §b" + world.getName() + " §7zu §bNacht §7geändert.");
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
