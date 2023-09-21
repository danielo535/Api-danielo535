package pl.danielo535.api_danielo535.listener;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.danielo535.api_danielo535.event.PlayerJoinRegionEvent;
import pl.danielo535.api_danielo535.event.PlayerQuitRegionEvent;

public class PlayerRegionListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();

        ApplicableRegionSet fromRegions = regionContainer.createQuery().getApplicableRegions(BukkitAdapter.adapt(event.getFrom()));
        ApplicableRegionSet toRegions = regionContainer.createQuery().getApplicableRegions(BukkitAdapter.adapt(event.getTo()));

        for (ProtectedRegion region : toRegions) {
            if (!fromRegions.getRegions().contains(region)) {
                PlayerJoinRegionEvent joinEvent = new PlayerJoinRegionEvent(player, region.getId());
                Bukkit.getServer().getPluginManager().callEvent(joinEvent);
            }
        }

        for (ProtectedRegion region : fromRegions) {
            if (!toRegions.getRegions().contains(region)) {
                PlayerQuitRegionEvent quitEvent = new PlayerQuitRegionEvent(player, region.getId());
                Bukkit.getServer().getPluginManager().callEvent(quitEvent);
            }
        }
    }
}