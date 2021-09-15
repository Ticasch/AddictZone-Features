package net.tiam.addictzone_features.managers;

import net.kyori.adventure.text.BlockNBTComponent;
import net.tiam.addictzone_features.utilities.FileBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class WarpManager{
    private String warp;

    private final FileBuilder fileBuilder = new FileBuilder("plugins/Citybuild", "warps.yml");

    public WarpManager(String warp) {
        this.warp = warp;
    }

    public void setWarp(@NotNull Location position) {
        this.fileBuilder.setValue(this.warp, position);
        this.fileBuilder.save();
    }

    public @NotNull Location getWarp() {
        if (this.fileBuilder.getString(this.warp) == null)
            return null;
        return this.fileBuilder.getLocation(this.warp);
    }
}
