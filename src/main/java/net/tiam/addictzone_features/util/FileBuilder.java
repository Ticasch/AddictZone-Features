package net.tiam.addictzone_features.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileBuilder {
    private File f;

    private YamlConfiguration c;

    public FileBuilder(String FilePath, String Filename) {
        this.f = new File(FilePath, Filename);
        this.c = YamlConfiguration.loadConfiguration(this.f);
    }

    public FileBuilder(String FilePath) {
        this.f = new File(FilePath);
        this.c = YamlConfiguration.loadConfiguration(this.f);
    }

    public FileBuilder setValue(String ValuePath, Object Value) {
        this.c.set(ValuePath, Value);
        return this;
    }

    public boolean exist() {
        return this.f.exists();
    }

    public Object getObject(String ValuePth) {
        return this.c.get(ValuePth);
    }

    public Integer getInt(String ValuePth) {
        return Integer.valueOf(this.c.getInt(ValuePth));
    }

    public String getString(String ValuePth) {
        return this.c.getString(ValuePth);
    }

    public boolean getBoolean(String ValuePth) {
        return this.c.getBoolean(ValuePth);
    }

    public long getLong(String ValuePath) {
        return this.c.getLong(ValuePath);
    }

    public List<String> getStringList(String ValuePath) {
        return this.c.getStringList(ValuePath);
    }

    public Set<String> getKeys(boolean deep) {
        return this.c.getKeys(deep);
    }

    public double getDouble(String ValuePath) {
        return this.c.getDouble(ValuePath);
    }

    public ConfigurationSection getsection(String Section) {
        return this.c.getConfigurationSection(Section);
    }

    public List<?> getList(String ValuePath) {
        return this.c.getList(ValuePath);
    }

    public FileBuilder save() {
        try {
            this.c.save(this.f);
        } catch (IOException iOException) {}
        return this;
    }

    public void deleteFile() {
        this.f.delete();
    }
}
