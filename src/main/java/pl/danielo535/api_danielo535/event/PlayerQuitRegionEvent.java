package pl.danielo535.api_danielo535.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerQuitRegionEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String regionName;

    public PlayerQuitRegionEvent(Player player, String regionName) {
        this.player = player;
        this.regionName = regionName;
    }

    public Player getPlayer() {
        return player;
    }

    public String getRegionName() {
        return regionName;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

