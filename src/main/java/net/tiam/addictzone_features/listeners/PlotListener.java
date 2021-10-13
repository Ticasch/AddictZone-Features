package net.tiam.addictzone_features.listeners;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.plotsquared.core.events.PlayerLeavePlotEvent;
import com.plotsquared.core.plot.Plot;
import net.tiam.addictzone_features.MainClass;
import net.tiam.addictzone_features.managers.PerkManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlotListener {
    String prefix = MainClass.Prefix;
    public PlotListener(PlotAPI api) {
        api.registerListener(this);
    }
    @Subscribe
    public void onEnter(PlayerEnterPlotEvent e) {
        Player p = Bukkit.getPlayer(e.getPlotPlayer().getUUID());
        PerkManager manager = new PerkManager(p.getUniqueId().toString());
        if (p.hasPermission("AddictZone.Perk.Plot-Fly.ByPass"))
            return;
        Plot plot = e.getPlot();
        if (plot.getMembers().contains(p.getUniqueId()) || plot.getOwners().contains(p.getUniqueId()) || plot.getTrusted().contains(p.getUniqueId())) {
            if (manager.getPerkStatus(PerkManager.Perk.Plot_Fly)) {
                p.sendMessage(prefix + "Plot-Fly aktiviert.");
                p.setAllowFlight(true);
                p.setFlying(true);
            }
        }
    }
    @Subscribe
    public void onLeave(PlayerLeavePlotEvent e) {
        Player p = Bukkit.getPlayer(e.getPlotPlayer().getUUID());
        PerkManager manager = new PerkManager(p.getUniqueId().toString());
        if (p.hasPermission("AddictZone.Perk.Plot-Fly.ByPass"))
            return;
        Plot plot = e.getPlot();
        if (plot.getMembers().contains(p.getUniqueId()) || plot.getOwners().contains(p.getUniqueId()) || plot.getTrusted().contains(p.getUniqueId())) {
            if (manager.getPerkStatus(PerkManager.Perk.Plot_Fly)) {
                p.sendMessage(prefix + "Plot-Fly deaktiviert.");
                p.setAllowFlight(false);
            }
        }
    }
}
