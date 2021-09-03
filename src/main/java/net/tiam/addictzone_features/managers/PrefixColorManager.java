package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.util.FileBuilder;

import java.util.UUID;

public class PrefixColorManager {
    private String uuid;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "prefixcolor.yml");

    public PrefixColorManager(String uuid) {
        this.uuid = uuid;
    }

    public void setTabColor(String color) {
        this.fileBuilder.setValue(this.uuid, String.valueOf(color));
        this.fileBuilder.save();
    }

    public String getTabColor() {
        if (this.fileBuilder.getString(this.uuid) == null)
            return null;
        return this.fileBuilder.getString(this.uuid);
    }
}