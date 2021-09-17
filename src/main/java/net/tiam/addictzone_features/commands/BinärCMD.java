package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class BinärCMD implements CommandExecutor {
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
                        String output = "";
                        input = Integer.parseInt(args[1]);
                        int dezZahl = input;
                        int anzahlStellen = 0;
                        int dezZahlZwei = dezZahl;
                        while (dezZahlZwei != 0) {
                            dezZahlZwei = dezZahlZwei / 2;
                            anzahlStellen++;
                        }
                        int zahlen[] = new int[anzahlStellen];
                        for (int i = 0; i < anzahlStellen; i++) {
                            zahlen[i] = dezZahl % 2;
                            dezZahl = dezZahl / 2;
                        }
                        for (int i = anzahlStellen - 1; i >= 0; i--)
                            output = output + zahlen[i];
                            c.sendMessage(prefix + "Die Dezimalzahlrzahl §b" + input + " §7ist binär: §b" + Integer.toBinaryString(input) + "§7.");
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
