package net.tiam.addictzone_features.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.TpaToggleManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TpahereCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String noperm = prefix + MainClass.NoPerm;
    String servername = MainClass.ServerName;
    private MainClass plugin;

    public static HashMap<Player, List<Player>> TELEPORTINQUIRIES = new HashMap<>();

    public TpahereCMD(MainClass plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(prefix + "Dieser Spieler ist nicht online.");
                return true;
            }
            if (target.getName().equals(player.getName())) {
                player.sendMessage(prefix + "Du kannst dir selbst keine Tpahere senden!");
                return true;
            }
            if (new TpaToggleManager(target.getUniqueId().toString()).getToggledTpa() == true) {
                player.sendMessage(prefix + "Dieser Spieler hat seine §bTpahere §7deaktiviert.");
                return true;
            }
            TextComponent click = new TextComponent(prefix + "§bKlicke§7: [");
            TextComponent accept = new TextComponent("§a§lAkzeptieren");
            accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + player.getName()));
            TextComponent strich = new TextComponent(" §7| ");
            TextComponent deny = new TextComponent("§c§lAblehnen");
            deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny " + player.getName()));
            TextComponent klammer = new TextComponent("§7]");
            click.addExtra((BaseComponent)accept);
            click.addExtra((BaseComponent)strich);
            click.addExtra((BaseComponent)deny);
            click.addExtra((BaseComponent)klammer);
            TextComponent cancelStart = new TextComponent(prefix + "Du hast §b" + target.getName() + " §7eine Tpahere geschickt.\n" + prefix + "§bKlicke§7: [");
            TextComponent cancel = new TextComponent("§c§lZurücknehmen");
            cancel.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpacancel " + target.getName()));
            TextComponent cancelEnd = new TextComponent("§7]");
            cancelStart.addExtra((BaseComponent)cancel);
            cancelStart.addExtra((BaseComponent)cancelEnd);
            if (TELEPORTINQUIRIES.containsKey(target)) {
                List<Player> list = TELEPORTINQUIRIES.get(target);
                if (list.contains(player)) {
                    player.sendMessage(prefix + "Du hast §b" + target.getName() + " §7bereits eine Tpahere geschickt.");
                } else {
                    list.add(player);
                    TELEPORTINQUIRIES.replace(target, list);
                    player.spigot().sendMessage((BaseComponent)cancelStart);
                    target.sendMessage(prefix + "§b" + target.getName() + " §7hat dir eine §bTpahere §7geschickt.");
                    target.spigot().sendMessage((BaseComponent)click);
                    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, () -> {
                        if (list.contains(player)) {
                            list.remove(player);
                            TELEPORTINQUIRIES.replace(target, list);
                            player.sendMessage(prefix + "§7Deine §bTpahere §7an §b" + target.getName() + "§7ist abgelaufen.");
                            target.sendMessage(prefix + "§7Die §bTpahere §7von §b" + player.getName() + " &7ist abgelaufen.");
                        }
                    },1200L);
                }
            } else {
                List<Player> list = new ArrayList<>();
                list.add(player);
                TELEPORTINQUIRIES.put(target, list);
                player.spigot().sendMessage((BaseComponent)cancelStart);
                target.sendMessage(prefix + "§b" + player.getName() + " §7hat dir eine §bTpahere §7geschickt.");
                target.spigot().sendMessage((BaseComponent)click);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, () -> {
                    if (list.contains(player)) {
                        list.remove(player);
                        TELEPORTINQUIRIES.replace(player, list);
                        player.sendMessage(prefix + "§7Deine §bTpahere §7an §b" + target.getName() + "§7ist abgelaufen.");
                        target.sendMessage(prefix + "§7Die §bTpahere §7von §b" + player.getName() + " &7ist abgelaufen.");
                    }
                },1200L);
            }
        } else {
            player.sendMessage(prefix + "Benutze: §b/Tpahere §7<§bSpieler§7>");
        }
        return false;
    }
}
