package pl.danielo535.api_danielo535;

import org.bukkit.plugin.java.JavaPlugin;
import pl.danielo535.api_danielo535.listener.PlayerRegionListener;

public final class Api_danielo535 extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerRegionListener(), this);
    }
}
