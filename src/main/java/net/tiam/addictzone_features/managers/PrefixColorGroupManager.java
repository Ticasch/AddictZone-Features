package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.util.FileBuilder;

public class PrefixColorGroupManager {
    private String groupName;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "groupPrefixes.yml");

    public PrefixColorGroupManager(String groupName) {
        this.groupName = groupName;
    }

    public void setTabGroupColor(String weight) {
        this.fileBuilder.setValue(this.groupName, String.valueOf(weight));
        this.fileBuilder.save();
    }

    public String getTabGroupColor() {
        if (this.fileBuilder.getString(this.groupName) == null)
            return "";
        return this.fileBuilder.getString(this.groupName);
    }
}