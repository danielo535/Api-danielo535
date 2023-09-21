package pl.danielo535.api_danielo535;

import org.bukkit.plugin.java.JavaPlugin;
import pl.danielo535.api_danielo535.listener.PlayerRegionListener;

import java.util.Arrays;

import static pl.danielo535.api_danielo535.utils.ColorUtils.*;

public final class Api_danielo535 extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerRegionListener(), this);
        colorText("&#FF9900Messages");
        colorList(Arrays.asList("&#FF9900Messages1","&#FF9900Messages2"));
    }
}
