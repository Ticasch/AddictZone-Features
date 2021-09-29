package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;

public class PrimaryGroupManager {
    private String uuid;

    private FileBuilder fileBuilder = new FileBuilder("plugins/AddictZone-Features", "primarygroups.yml");



    public PrimaryGroupManager(String uuid){
        this.uuid = uuid;
    }
    public void setPrimaryGroup(String group, boolean b) {
        this.fileBuilder.setValue(group + "." + this.uuid, b);
        this.fileBuilder.save();
    }
    public boolean getPrimaryGroup(String group) {
        return this.fileBuilder.getBoolean(group + "." + this.uuid);
    }
    public void setPrimaryGroupUser(String group) {
        this.fileBuilder.setValue(this.uuid, group);
        this.fileBuilder.save();
    }
    public String getPrimaryGroupUser() {
        return this.fileBuilder.getString(this.uuid);
    }
}
