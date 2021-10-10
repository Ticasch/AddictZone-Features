package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;

import java.util.ArrayList;

public class SocialMediaManager {

    private FileBuilder fileBuilder = new FileBuilder("plugins/AddictZone-Features", "SocialMedia.yml");

    public SocialMediaManager() {
    }
    public String getName(String name) {
        return this.fileBuilder.getString(name + ".Name");
    }
    public String getLink(String name) {
        return this.fileBuilder.getString(name + ".Link");
    }
    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>(this.fileBuilder.getKeys(false));
        return list;
    }
}
