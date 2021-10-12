package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class PrefixManager {
    private String uuid;
    public PrefixManager(String uuid) {
        this.uuid = uuid;
    }
    public enum Prefix {
        BLACK("0", "Schwarz"),
        DARKBLUE("1", "Dunkelblau"),
        DARKGREEN("2", "Dunkelgrün"),
        DARKAQUA("3", "Türkies"),
        DARKRED("4", "Dunkelrot"),
        PURPLE("5", "Violett"),
        GOLD("6", "Orange"),
        GRAY("7", "Hellgrau"),
        DARKGRAY("8", "Dunkelgrau"),
        BLUE("9", "Blau"),
        GREEN("a", "Hellgrün"),
        AQUA("b", "Hellblau"),
        RED("c", "Hellrot"),
        MAGENTA("d", "Magenta"),
        YELLOW("e", "Gelb"),
        WHITE("f", "Weiß");
        String color;
        String name;
        Prefix(String color, String name) {
            this.name = name;
            this.color = color;
        }
        public String getName() {
            return this.name;
        }
        public String getColor() {
            return this.color;
        }
    }
    public boolean getPrefixStatus(String uuid, String arg) {
        PrefixColorManager manager = new PrefixColorManager(uuid);
        if (manager.getTabColor().equalsIgnoreCase(arg))
            return true;
        return false;
    }
    public ArrayList<Prefix> getPrefix() {
        ArrayList<Prefix> list = new ArrayList<>();
        list.addAll(Arrays.asList(new Prefix[] {
                Prefix.BLACK, Prefix.DARKBLUE, Prefix.DARKGREEN, Prefix.DARKAQUA, Prefix.DARKRED, Prefix.PURPLE, Prefix.GOLD, Prefix.GRAY, Prefix.DARKGRAY, Prefix.BLUE, Prefix.GREEN, Prefix.AQUA, Prefix.RED, Prefix.MAGENTA, Prefix.YELLOW, Prefix.WHITE
        }));
        return list;
    }
}
