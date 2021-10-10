package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.utilities.FileBuilder;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;

public class PerkManager {
    String servername = MainClass.ServerName;
    private String uuid;

    private FileBuilder fileBuilder = new FileBuilder("plugins/AddictZone-Features", "Perks.yml");

    public PerkManager(String uuid) {
        this.uuid = uuid;
    }

    public boolean getPerkStatus(Perk perk) {
        return this.fileBuilder.getBoolean(this.uuid + ".Perk." + perk);
    }
    public void switchPerkStatus(Perk perk) {
        if (getPerkStatus(perk)) {
            this.fileBuilder.setValue(this.uuid + ".Perk." + perk, false);
        } else {
            this.fileBuilder.setValue(this.uuid + ".Perk." + perk, true);
        }
        this.fileBuilder.save();
    }
    public enum Perk {
        Keep_Inventory(null, null, "Keep-Inventory", "AddictZone.Perk.Keep-Inventory"),
        Keep_XP(null, null, "Keep-XP", "AddictZone.Perk.Keep-XP"),
        Plot_Fly(null, null, "Plot-Fly", "AddictZone.Perk.Plot-Fly"),
        Kein_Hunger(null, null, "Kein Hunger", "AddictZone.Perk.Kein-Hunger"),
        Atmung(null, null, "Atmung", "AddictZone.Perk.Atmung"),
        Eile(PotionEffectType.FAST_DIGGING, Integer.valueOf(5), "Eile", "AddictZone.Perk.Eile"),
        Stärke(PotionEffectType.INCREASE_DAMAGE, Integer.valueOf(2), "Stärke", "AddictZone.Perk.Stärke"),
        Feuerschutz(PotionEffectType.FIRE_RESISTANCE, Integer.valueOf(1), "Feuerschutz", "AddictZone.Perk.Feuerschutz"),
        Speed(PotionEffectType.SPEED, Integer.valueOf(2), "Speed", "AddictZone.Perk.Speed"),
        Sprungkraft(PotionEffectType.JUMP, Integer.valueOf(2), "Sprungkraft", "AddictZone.Perk.Sprungkraft"),
        Heilung(PotionEffectType.REGENERATION, Integer.valueOf(2), "Heilung", "AddictZone.Perk.Heilung"),
        Glow(PotionEffectType.GLOWING, Integer.valueOf(1), "Glow", "AddictZone.Perk.Glow");
        PotionEffectType effect;
        Integer lvl;
        String name;
        String perm;
        Perk(PotionEffectType effect, Integer lvl, String name, String perm) {
            this.effect = effect;
            this.lvl = lvl;
            this.name = name;
            this.perm = perm;
        }
        public String getPerm() {
            return this.perm;
        }
        public PotionEffectType getEffect() {
            return this.effect;
        }
        public Integer getLvl() {
            return this.lvl;
        }
    }
    public ArrayList<String> getPerks() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(new String[] {
                "Eile", "Stärke", "Kein_Hunger", "Feuerschutz", "Atmung", "Speed", "Sprungkraft", "Heilung", "Plot_Fly", "Keep_Inventory", "Keep_XP", "Glow"
        }));
        return list;
    }
}
