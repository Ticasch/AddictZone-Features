package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;

public class TablistWeightManager {
    private String groupName;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "tabweights.yml");

    public TablistWeightManager(String groupName) {
        this.groupName = groupName;
    }

    public void setTabWeight(int weight) {
        this.fileBuilder.setValue(this.groupName, Integer.valueOf(weight));
        this.fileBuilder.save();
    }

    public int getWeight() {
        if (this.fileBuilder.getInt(this.groupName) == null)
            return 1000;
        return this.fileBuilder.getInt(this.groupName).intValue();
    }
}