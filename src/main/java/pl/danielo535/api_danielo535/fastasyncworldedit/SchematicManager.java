package pl.danielo535.api_danielo535.fastasyncworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.bukkit.Bukkit.getLogger;

public class SchematicManager {

    private final Clipboard clipboard;

    public SchematicManager(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    public Clipboard getClipboard(File file) {
        return clipboard;
    }

    public static void pasteSchematic(String schemName,org.bukkit.Location location,File directory) {
        File schematic = new File(directory + schemName + ".schem");

        Optional<SchematicManager> optionalSchematic = SchematicManager.load(schematic);

        if (optionalSchematic.isPresent()) {
            SchematicManager schematicManager = optionalSchematic.get();
            schematicManager.paste(location);
        } else {
            getLogger().warning("Failed to load the Schematic.");
        }
    }

    public void paste(org.bukkit.Location target) {
        World world = BukkitAdapter.adapt(target.getWorld());
        Location location = BukkitAdapter.adapt(target);

        EditSession session = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);

        Operation operation = new ClipboardHolder(clipboard).createPaste(session)
                .to(location.toVector().toBlockPoint()).ignoreAirBlocks(true).build();

        try {
            Operations.complete(operation);

            session.flushSession();
        } catch (WorldEditException exception) {
            exception.printStackTrace();
        }
    }

    public static Optional<SchematicManager> load(File file) {
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        if (format == null) {
            return Optional.empty();
        }

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            return Optional.of(new SchematicManager(reader.read()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }
}

