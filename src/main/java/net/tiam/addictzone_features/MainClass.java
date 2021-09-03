package net.tiam.addictzone_features;

import net.tiam.addictzone_features.commands.*;
import net.tiam.addictzone_features.listeners.AsyncPlayerChatListener;
import net.tiam.addictzone_features.listeners.CommandPreProcessListener;
import net.tiam.addictzone_features.listeners.JoinListener;
import net.tiam.addictzone_features.managers.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.UUID;

public final class MainClass extends JavaPlugin {
    private static MainClass plugin;
    public static Boolean closed = Boolean.valueOf(false);
    public static final HashMap<UUID, String> teams = new HashMap<>();
    //public static FileBuilder fileBuilder;

    public static String Prefix = "§9§lAddictZone §8➜ §7";
    public static String ServerName = "AddictZone";
    public static String NoPerm = "§7Dazu hast du keine Rechte!";
    public static Double MoneyMaxValue = 1000000.0;
    public static Double BankMaxValue = 100000000.0;
    public static Double MinPayedValue = 1.0;
    public static boolean Frozen = true;
    public static Double MoneyReset = 1000.0;
    public static String Line = "§8§m--------------------------------------------------";

    public void onEnable() {
        //ServerName = String.valueOf(fileBuilder.getString("ServerName"));
        //Prefix = String.valueOf(fileBuilder.getString("Prefix"));
        //NoPerm = String.valueOf(fileBuilder.getString("NoPerm"));
        //MoneyMaxValue = Double.valueOf(fileBuilder.getString("MoneyMaxValue"));
        //BankMaxValue = Double.valueOf(fileBuilder.getString("BankMaxValue"));
        //MinPayedValue = Double.valueOf(fileBuilder.getString("MinPayedValue"));
        //Frozen = Boolean.parseBoolean(fileBuilder.getString("FrozenBank"));
        //MoneyReset = Double.valueOf(fileBuilder.getString("MoneyReset"));
        registerCommands();
        System.out.println(Prefix + "Starte §9§lAddictZone-Features§7.");
    }

    public void onDisable() {
        System.out.println(Prefix + "Stoppe §9§lAddictZone-Features§7.");
    }
    private void registerCommands() {
        plugin = this;
        getCommand("cc").setExecutor(new ChatClearCMD());
        getCommand("msg").setExecutor(new MsgCMD());
        getCommand("AddictZone").setExecutor(new PlainlessCMD());
        getCommand("Gm").setExecutor(new GmCMD());
        getCommand("Fly").setExecutor(new FlyCMD());
        getCommand("gift").setExecutor(new GiftCMD());
        getCommand("op").setExecutor(new OpCMD());
        getCommand("deop").setExecutor(new OpCMD());
        getCommand("tp").setExecutor(new TpCMD());
        getCommand("tphere").setExecutor(new TphereCMD());
        getCommand("Time").setExecutor(new TimeCMD());
        getCommand("Day").setExecutor(new TimeCMD());
        getCommand("Night").setExecutor(new TimeCMD());
        getCommand("bc").setExecutor(new BcCMD());
        getCommand("heal").setExecutor(new HealCMD());
        getCommand("feed").setExecutor(new HealCMD());
        getCommand("spy").setExecutor(new SpyCMD());
        getCommand("vanish").setExecutor(new VanishCMD());
        getCommand("prefix").setExecutor(new PrefixCMD());
        getCommand("sun").setExecutor(new WeatherCMD());
        getCommand("rain").setExecutor(new WeatherCMD());
        Bukkit.getPluginManager().registerEvents(new CommandPreProcessListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Player all : Bukkit.getOnlinePlayers())
                (new TablistManager(all)).updateIfNecessary();
        },0L, 60L);
    }

    public static MainClass getPlugin() {
        return plugin;
    }
    public static Boolean getClosed() {
        return closed;
    }
    public static void setClosed(Boolean closed) {
        MainClass.closed = closed;
    }
}
