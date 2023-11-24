package tk.airshipcraft.commonlib.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Collection;

/**
 * <p>Represents a world-limited area with vertical bounds that extend infinitely along the X and Z axes.
 * This class provides functionality to work with and determine locations within these vertical boundaries
 * within a specific world context.</p>
 *
 * <p>This class is particularly useful for operations that are concerned with vertical sections of the world,
 * such as checking if entities are within a certain Y-level range or applying effects to those regions.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-01
 */
public class GlobalYLimitedArea extends AbstractYLimitedArea {

    private World world;

    /**
     * Constructs a new {@code GlobalYLimitedArea} with specified Y boundaries in a given world.
     *
     * @param lowerYBound The lower Y boundary of the area.
     * @param upperYBound The upper Y boundary of the area.
     * @param world       The world in which this area is defined.
     */
    public GlobalYLimitedArea(double lowerYBound, double upperYBound, World world) {
        super(lowerYBound, upperYBound);
        this.world = world;
    }

    /**
     * This method returns null because the area is not confined to specific chunks,
     * thus spanning an infinite amount of chunks in the X and Z dimensions.
     *
     * @return Null, indicating an infinite number of chunks in the area.
     */
    @Override
    public Collection<Chunk> getChunks() {
        return null;
    }

    /**
     * Gets the center of the global Y-limited area. Since this area spans the entire world,
     * the center is conceptually at the origin of the world coordinates.
     *
     * @return The center {@link Location} at the origin of the world coordinates.
     */
    @Override
    public Location getCenter() {
        return new Location(world, 0, (getLowerYBound() + getUpperYBound()) / 2, 0);
    }

    /**
     * Retrieves the world where this Y-limited area exists.
     *
     * @return The {@link World} instance associated with this area.
     */
    @Override
    public World getWorld() {
        return world;
    }

    /**
     * Checks if a location is within the vertical (Y) boundaries of this global area.
     *
     * @param loc The location to check.
     * @return True if the location's Y-level falls within the boundaries, false otherwise.
     */
    @Override
    public boolean isInArea(Location loc) {
        return super.isInArea(loc) && loc.getWorld().equals(world);
    }

    /**
     * This method returns null because the area spans an infinite number of pseudo chunks.
     * Pseudo chunks are a representation of chunk locations without loading the actual chunks.
     *
     * @return Null, indicating an infinite number of pseudo chunks in the area.
     */
    @Override
    public Collection<PseudoChunk> getPseudoChunks() {
        return null;
    }
}
