package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class MathCMD implements CommandExecutor {
    private static int input;
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        CommandSender c = (CommandSender) sender;
        if (cmd.getName().equalsIgnoreCase("math")) {
            if (c.hasPermission(servername + "math.binary")) {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("bin")) {
                            c.sendMessage(prefix + "Die Dezimalzahl §b" + args[1] + " §7ist binär: §b" + Integer.toBinaryString(Integer.parseInt(args[1])) + "§7.");
                    } else if (args[0].equalsIgnoreCase("Dez")) {
                        String output = "";
                        int binZahl = Integer.parseInt(args[1]);
                        int anzahlVerschiebung=0;
                        int dezZahl=0;
                        int restWert=0;
                        while (binZahl !=0){
                            restWert=binZahl % 10;
                            dezZahl = dezZahl+(int)(restWert*(Math.pow(2, anzahlVerschiebung)));
                            binZahl=binZahl / 10;
                            anzahlVerschiebung=anzahlVerschiebung+1;
                        }
                        output = output + dezZahl;
                        c.sendMessage(prefix + "Die Binärzahl §b" + args[1] + " §7ist dezimal: §b" + dezZahl + "§7.");
                    } else if (args[0].equalsIgnoreCase("hex")) {
                        input = Integer.parseInt(args[1]);
                        c.sendMessage(prefix + "Die Dezimalzahl §b" + input + " §7ist Hexadezimal: §b" + Integer.toHexString(input) + "§7.");
                    } else {
                        c.sendMessage(prefix + "Benutze: §b/Math §7<§bBin§7|§bDez§7|§bHex§7> <§bZahl§7>");
                    }
                } else {
                    c.sendMessage(prefix + "Benutze: §b/Math §7<§bBin§7|§bDez§7|§bHex§7> <§bZahl§7>");
                }
            } else {
                c.sendMessage(noperm);
            }
        }
        return false;
    }
}
