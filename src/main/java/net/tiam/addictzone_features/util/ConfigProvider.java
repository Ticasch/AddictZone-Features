package net.tiam.addictzone_features.util;

public class ConfigProvider {
    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "config.yml");



    public void getMoneyMaxValue(String path) {
       fileBuilder.getDouble(path);
    }

    public void getBankMaxValue(String path) {
        fileBuilder.getDouble(path);
    }

    public void getMinPayedValue(String path) {
        fileBuilder.getDouble(path);
    }

    public void getPrefix (String path) {
        fileBuilder.getString(path);
    }

}
