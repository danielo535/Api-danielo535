package pl.danielo535.api_danielo535.decentholograms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

import java.util.List;

public class DecentHologramsManager {

    public static void createHologram(Location location, String hologramName,List<String> lines,Boolean saveFile) {
        DHAPI.createHologram(hologramName, location,saveFile,lines);
    }

    public static void updateHologram(String hologramName, List<String> lines) {
        Hologram hologram = DHAPI.getHologram(hologramName);
        if (hologram == null) return;
        DHAPI.setHologramLines(hologram, lines);
    }
    public static void removeHologram(String hologram) {
        DHAPI.removeHologram(hologram);
    }
}
