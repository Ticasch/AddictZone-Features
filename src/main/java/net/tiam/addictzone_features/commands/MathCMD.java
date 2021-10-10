package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class MathCMD implements CommandExecutor {
    private static long input;
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
                        if (!isLong(args[1])) {
                            c.sendMessage(prefix + "Du musst eine gültige Zahl angeben.");
                            return true;
                        }
                            c.sendMessage(prefix + "Die Dezimalzahl §b" + args[1] + " §7ist binär: §b" + Long.toBinaryString(Long.parseLong(args[1])) + "§7.");
                    } else if (args[0].equalsIgnoreCase("Dez")) {
                        String output = "";
                        if (!isLong(args[1])) {
                            c.sendMessage(prefix + "Du musst eine gültige Zahl angeben.");
                            return true;
                        }
                        long binZahl = Long.parseLong(args[1]);
                        long anzahlVerschiebung=0;
                        long dezZahl=0;
                        long restWert=0;
                        while (binZahl !=0){
                            restWert=binZahl % 10;
                            dezZahl = dezZahl+(long)(restWert*(Math.pow(2, anzahlVerschiebung)));
                            binZahl=binZahl / 10;
                            anzahlVerschiebung=anzahlVerschiebung+1;
                        }
                        output = output + dezZahl;
                        String count = NumberFormat(dezZahl);
                        String countReplaced = count.replace(".", "_").replace(",", ";");
                        String countString = countReplaced.replace("_", ",").replace(";", ".");
                        c.sendMessage(prefix + "Die Binärzahl §b" + args[1] + " §7ist dezimal: §b" + countString + "§7.");
                    } else if (args[0].equalsIgnoreCase("hex")) {
                        if (!isLong(args[1])) {
                            c.sendMessage(prefix + "Du musst eine gültige Zahl angeben.");
                            return true;
                        }
                        input = Long.parseLong(args[1]);
                        c.sendMessage(prefix + "Die Dezimalzahl §b" + input + " §7ist Hexadezimal: §b" + Long.toHexString(input) + "§7.");
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
    public String NumberFormat(long count) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        return decimalFormat.format(count);
    }
    public boolean isLong(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException|NullPointerException e) {
            return false;
        }
        return true;
    }
}
