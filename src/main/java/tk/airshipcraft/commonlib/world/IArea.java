package tk.airshipcraft.commonlib.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Collection;

/**
 * Defines the contract for area-related operations within the world.
 * Implementations of this interface should provide mechanisms for determining
 * area boundaries, locating chunks within these boundaries, and managing world-related properties.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-03-30
 */
public interface IArea {

    /**
     * Determines whether a specific location falls within the boundaries of this area.
     *
     * @param loc The location to check.
     * @return True if the location is within the boundaries of the area, false otherwise.
     */
    boolean isInArea(Location loc);

    /**
     * Retrieves a collection of all chunks that are within or intersect the boundaries of this area.
     * For areas of infinite size, this method may return null.
     *
     * @return A collection of {@link Chunk} instances representing the chunks within this area,
     * or null if the area has an infinite extent.
     */
    Collection<Chunk> getChunks();

    /**
     * Retrieves a collection of all pseudo chunk coordinates that are within or intersect the boundaries of this area.
     * This method provides a lightweight alternative to {@link #getChunks()} by avoiding actual chunk loading.
     * For areas of infinite size, this method may return null.
     *
     * @return A collection of {@link PseudoChunk} instances representing the chunk coordinates within this area,
     * or null if the area has an infinite extent.
     */
    Collection<PseudoChunk> getPseudoChunks();

    /**
     * Provides the central point of this area, which can be used as an anchor for various operations.
     *
     * @return The central {@link Location} of the area.
     */
    Location getCenter();

    /**
     * Retrieves the world where this area exists.
     *
     * @return The {@link World} instance associated with this area.
     */
    World getWorld();
}
