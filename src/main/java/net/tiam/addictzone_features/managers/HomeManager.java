package net.tiam.addictzone_features.managers;

import net.tiam.addictzone_features.utilities.FileBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class HomeManager {
    private String uuid;
    private String home;
    private int homecount;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "homes.yml");

    public HomeManager(String uuid, String home) {
        this.uuid = uuid;
        this.home = home;
    }

    public void setHome(@NotNull Location position) {
        this.fileBuilder.setValue(this.uuid + "." + this.home, position);
        this.fileBuilder.save();
    }

    public @NotNull Location getHome() {
        if (this.fileBuilder.getString(this.uuid + "." + this.home) == null)
            return null;
        return this.fileBuilder.getLocation(this.uuid + "." + this.home);
    }

    public void setCount(int count) {
        this.fileBuilder.setValue(this.uuid + ".homes", count);
        this.fileBuilder.save();
    }

    public int getCount() {
        if (this.fileBuilder.getString(this.uuid + ".homes") == null) {
            return 0;
        }
        return this.fileBuilder.getInt(this.uuid + ".homes");
    }
}
