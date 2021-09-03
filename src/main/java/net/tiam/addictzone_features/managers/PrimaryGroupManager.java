package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.util.FileBuilder;

import java.util.UUID;

public class PrimaryGroupManager {
    private String uuid;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "primarygroups.yml");

    public PrimaryGroupManager(String uuid) {
        this.uuid = uuid;
    }

    public void setPimaryGroup(String group) {
        this.fileBuilder.setValue(this.uuid, String.valueOf(group));
        this.fileBuilder.save();
    }

    public String getPrimaryGroup() {
        if (this.fileBuilder.getString(this.uuid) == null)
            return null;
        return this.fileBuilder.getString(this.uuid);
    }
}