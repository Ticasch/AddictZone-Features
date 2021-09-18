package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;

public class AfkManager {
    private String uuid;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "afk.yml");

    public AfkManager(String uuid) {
        this.uuid = uuid;
    }

    public void setAfk(boolean toggled) {
        this.fileBuilder.setValue(this.uuid, Boolean.valueOf(toggled));
        this.fileBuilder.save();
    }

    public boolean getAfk() {
        return this.fileBuilder.getBoolean(this.uuid);
    }
}

