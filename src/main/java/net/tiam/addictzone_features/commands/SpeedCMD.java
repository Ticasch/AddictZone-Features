package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ProxiedCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Proxy;

public class SpeedCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    String SENDER;
    @Override
    public boolean onCommand(@NotNull CommandSender c, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (c instanceof Player) {
            SENDER = c.getName();
        } else {
            SENDER = servername;
        }
        if (!c.hasPermission(servername + ".Speed")) {
            c.sendMessage(noperm);
            return true;
        }
        if (!(c instanceof Player)) {
            c.sendMessage(prefix + "Du musst ein Spieler sein.");
            return true;
        }
        if (args.length == 1) {
            if (!isInteger(args[0])) {
                c.sendMessage(prefix + "Du musst eine Zahl angeben.");
                return true;
            }
            if (Double.parseDouble(args[0]) > 10 || Double.parseDouble(args[0]) < 0) {
                c.sendMessage(prefix + "Bitte wähle eine Zahl zwischen §b1 §7und §b10§7.");
                return true;
            }
            double speed = Double.parseDouble(args[0]) / 10;
            if (((Player) c).isFlying()) {
                c.sendMessage(prefix + "Du hast deinen Fly-Speed zu §b" + args[0] + " §7gesetzt.");
                ((Player) c).setFlySpeed((float) speed);
            } else {
                c.sendMessage(prefix + "Du hast deinen Walk-Speed zu §b" + args[0] + " §7gesetzt.");
                ((Player) c).setWalkSpeed((float) speed);
            }
        } else if (args.length == 2) {
            if (!c.hasPermission(servername + ".Speed.other"))
                return true;
            if (!isInteger(args[0])) {
                c.sendMessage(prefix + "Du musst eine Zahl angeben.");
                return true;
            }
            if (Double.parseDouble(args[0]) > 10 || Double.parseDouble(args[0]) < 0) {
                c.sendMessage(prefix + "Bitte wähle eine Zahl zwischen §b1 §7und §b10§7.");
                return true;
            }
            double speed = Double.parseDouble(args[0]) / 10;
            Player t = Bukkit.getPlayer(args[1]);
            if (t == null) {
                c.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                return true;
            }
            if (t.isFlying()) {
                c.sendMessage(prefix + "Du hast den Fly-Speed von §b" + t.getName() + " §7auf §b" + args[0] + " §7gesetzt.");
                t.sendMessage(prefix + "Dein Fly-Speed wurde von §b" + SENDER + " §7zu §b" + args[0] + " §7gesetzt.");
                t.setFlySpeed((float) speed);
            } else {
                c.sendMessage(prefix + "Du hast den Walk-Speed von §b" + t.getName() + " §7auf §b" + args[0] + " §7gesetzt.");
                t.sendMessage(prefix + "Dein Walk-Speed wurde von §b" + SENDER + " §7zu §b" + args[0] + " §7gesetzt.");
                t.setWalkSpeed((float) speed);
            }
        } else {
            c.sendMessage(prefix + "Benutze: §b/Speed §7<§bSpeed§7>");
        }
        return false;
    }
    public static boolean isInteger(String strNum) {
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException|NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
