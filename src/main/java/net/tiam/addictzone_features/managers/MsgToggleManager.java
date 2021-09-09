package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.util.FileBuilder;

import java.util.UUID;

public class MsgToggleManager {
    private String uuid;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "toggledmsg.yml");

    public MsgToggleManager(String uuid) {
        this.uuid = uuid;
    }

    public void setToggledMsg(boolean toggled) {
        this.fileBuilder.setValue(this.uuid, Boolean.valueOf(toggled));
        this.fileBuilder.save();
    }

    public boolean getToggledMsg() {
        return this.fileBuilder.getBoolean(this.uuid);
    }
}