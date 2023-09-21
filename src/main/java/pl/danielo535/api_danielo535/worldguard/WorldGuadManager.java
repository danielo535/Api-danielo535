package pl.danielo535.api_danielo535.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The WorldGuadManager class contains methods for managing information about regions in WorldGuard.
 * It allows retrieving a list of regions, checking object membership in regions, and performing operations on lists of regions.
 */
public class WorldGuadManager {
    /**
     * Retrieves a list of region IDs to which a given object belongs.
     *
     * @param obj The object for which a list of regions should be retrieved.
     * @return A list of region IDs that the object belongs to.
     */
    public List<String> getObjectRegions(Object obj) {
        RegionContainer container = com.sk89q.worldguard.WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        if (obj instanceof Player) {
            ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(((Player) obj).getLocation()));
            return set.getRegions().stream().map(ProtectedRegion::getId).collect(Collectors.toList());
        } else if (obj instanceof Entity) {
            ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(((Entity) obj).getLocation()));
            return set.getRegions().stream().map(ProtectedRegion::getId).collect(Collectors.toList());
        } else if (obj instanceof Block) {
            ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(((Block) obj).getLocation()));
            return set.getRegions().stream().map(ProtectedRegion::getId).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    /**
     * Retrieves a list of region IDs to which a given player belongs.
     *
     * @param player The player for whom a list of regions should be retrieved.
     * @return A list of region IDs that the player belongs to.
     */
    public List<String> getPlayerRegions(Player player) {
        return getObjectRegions(player);
    }

    /**
     * Retrieves a list of region IDs to which a given Entity object belongs.
     *
     * @param entity The Entity object for which a list of regions should be retrieved.
     * @return A list of region IDs that the Entity object belongs to.
     */
    public List<String> getEntityRegions(Entity entity) {
        return getObjectRegions(entity);
    }

    /**
     * Retrieves a list of region IDs to which a given block belongs.
     *
     * @param block The block for which a list of regions should be retrieved.
     * @return A list of region IDs that the block belongs to.
     */
    public List<String> getBlockRegions(Block block) {
        return getObjectRegions(block);
    }

    /**
     * Checks if a player is in a specific region.
     *
     * @param player The player to check for region membership.
     * @param region The ID of the region to check.
     * @return True if the player is in the specified region; otherwise, false.
     */
    public boolean isPlayerInRegion(Player player, String region) {
        List<String> regions = getPlayerRegions(player);
        return regions.contains(region);
    }

    /**
     * Checks if an Entity object is in a specific region.
     *
     * @param entity The Entity object to check for region membership.
     * @param region The ID of the region to check.
     * @return True if the Entity object is in the specified region; otherwise, false.
     */
    public boolean isEntityInRegion(Entity entity, String region) {
        List<String> regions = getEntityRegions(entity);
        return regions.contains(region);
    }

    /**
     * Checks if a block is in a specific region.
     *
     * @param block  The block to check for region membership.
     * @param region The ID of the region to check.
     * @return True if the block is in the specified region; otherwise, false.
     */
    public boolean isBlockInRegion(Block block, String region) {
        List<String> regions = getBlockRegions(block);
        return regions.contains(region);
    }

    /**
     * Checks if a player is in at least one of the regions from the list.
     *
     * @param player  The player to check for region membership.
     * @param regions The list of region IDs to check.
     * @return True if the player is in at least one of the regions from the list; otherwise, false.
     */
    public boolean isPlayerInRegionList(Player player, List<String> regions) {
        List<String> playerRegions = getPlayerRegions(player);
        return playerRegions.stream().anyMatch(regions::contains);
    }

    /**
     * Checks if an Entity object is in at least one of the regions from the list.
     *
     * @param entity  The Entity object to check for region membership.
     * @param regions The list of region IDs to check.
     * @return True if the Entity object is in at least one of the regions from the list; otherwise, false.
     */
    public boolean isEntityInRegionList(Entity entity, List<String> regions) {
        List<String> entityRegions = getEntityRegions(entity);
        return entityRegions.stream().anyMatch(regions::contains);
    }

    /**
     * Checks if a block is in at least one of the regions from the list.
     *
     * @param block   The block to check for region membership.
     * @param regions The list of region IDs to check.
     * @return True if the block is in at least one of the regions from the list; otherwise, false.
     */
    public boolean isBlockInRegionList(Block block, List<String> regions) {
        List<String> blockRegions = getBlockRegions(block);
        return blockRegions.stream().anyMatch(regions::contains);
    }
}
