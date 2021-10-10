package net.tiam.addictzone_features.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.SocialMediaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SmCMD implements CommandExecutor {
    String prefix = MainClass.Prefix;
    String line = MainClass.Line;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("Sm")) {
            Msg(sender);
        }
        return false;
    }
    public void Msg(CommandSender c) {
        if (new SocialMediaManager().getList().size() > 0) {
            c.sendMessage(line);
            for (int i = 0; i <= new SocialMediaManager().getList().size() - 1; i++) {
                //TextComponent Eintrag = new TextComponent(prefix + new SocialMediaManager().getName(new SocialMediaManager().getList().get(i)) + ": §b");
                //TextComponent Link = new TextComponent(new SocialMediaManager().getLink(new SocialMediaManager().getList().get(i)));
                //Link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, new SocialMediaManager().getLink(new SocialMediaManager().getList().get(i))));
                //Eintrag.addExtra(Link);
                //c.sendMessage(Eintrag);
                c.sendMessage(prefix + new SocialMediaManager().getName(new SocialMediaManager().getList().get(i)) + ": §b" + new SocialMediaManager().getLink(new SocialMediaManager().getList().get(i)));
            }
            c.sendMessage(line);
        } else {
            c.sendMessage(prefix + "Bitte konfiguriere den Social-Media Eintrag.");
        }
    }
}
