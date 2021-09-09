package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;

public class TpaToggleManager {
    private String uuid;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "toggledtpa.yml");

    public TpaToggleManager(String uuid) {
        this.uuid = uuid;
    }

    public void setToggledTpa(boolean toggled) {
        this.fileBuilder.setValue(this.uuid, Boolean.valueOf(toggled));
        this.fileBuilder.save();
    }

    public boolean getToggledTpa() {
        return this.fileBuilder.getBoolean(this.uuid);
    }
}