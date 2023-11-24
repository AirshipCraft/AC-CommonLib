package tk.airshipcraft.commonlib.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents an elliptical area in a world, defined by a center point, and the radii along the X and Z axes.
 * The area is bounded vertically by specified Y levels.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-01
 */
public class EllipseArea extends AbstractYLimitedArea {

    private Location center;
    private double xSize;
    private double zSize;

    /**
     * Constructs a new {@code EllipseArea} with specified Y boundaries, center, and radii.
     *
     * @param lowerYBound The lower Y boundary of the area.
     * @param upperYBound The upper Y boundary of the area.
     * @param center      The center location of the ellipse.
     * @param xSize       The radius of the ellipse along the X-axis.
     * @param zSize       The radius of the ellipse along the Z-axis.
     */
    public EllipseArea(double lowerYBound, double upperYBound, Location center, double xSize, double zSize) {
        super(lowerYBound, upperYBound);
        this.center = center;
        this.xSize = xSize;
        this.zSize = zSize;
    }

    /**
     * Retrieves a collection of chunks that fall within or intersect the boundaries of the elliptical area.
     *
     * @return A collection of {@link Chunk} instances representing the chunks within or intersecting the area.
     */
    @Override
    public Collection<Chunk> getChunks() {
        // Iterate through the potential area's extent along the X and Z axes, adding each chunk to the collection.
        // Checks each corner of the chunk to determine if any part of it falls within the ellipse.
        Collection<Chunk> chunks = new HashSet<>();
        for (double x = center.getX() - xSize; x <= center.getX() + xSize; x += 16) {
            for (double z = center.getZ() - zSize; z <= center.getZ() + zSize; z += 16) {
                Chunk c = new Location(center.getWorld(), x, center.getY(), z).getChunk();
                // if one of the corners is in the area the chunk is inside
                if (isInArea(new Location(c.getWorld(), c.getX() * 16, 0, (c.getZ() * 16) + 15))
                        || isInArea(new Location(c.getWorld(), c.getX() * 16, 0, c.getZ() * 16))
                        || isInArea(new Location(c.getWorld(), (c.getX() * 16) + 15, 0, c.getZ() * 16))
                        || isInArea(new Location(c.getWorld(), (c.getX() * 16) + 15, 0, (c.getZ() * 16) + 15))) {
                    chunks.add(c);
                }
            }
        }
        return chunks;
    }

    /**
     * Gets the center of the elliptical area.
     *
     * @return The center {@link Location} of the ellipse.
     */
    @Override
    public Location getCenter() {
        return center;
    }

    /**
     * Retrieves the world where this elliptical area exists.
     *
     * @return The {@link World} instance associated with this area.
     */
    @Override
    public World getWorld() {
        return center.getWorld();
    }

    /**
     * Checks if a location is within the boundaries of the elliptical area.
     *
     * @param loc The location to check.
     * @return True if the location falls within the ellipse, false otherwise.
     */
    @Override
    public boolean isInArea(Location loc) {
        double xDist = center.getX() - loc.getX();
        double zDist = center.getZ() - loc.getZ();
        return super.isInArea(loc) && ((xDist * xDist) / (xSize * xSize)) + ((zDist * zDist) / (zSize * zSize)) <= 1;
    }

    /**
     * Gets the radius of the ellipse along the X-axis.
     *
     * @return The radius of the ellipse along the X-axis.
     */
    public double getXSize() {
        return xSize;
    }

    /**
     * Gets the radius of the ellipse along the Z-axis.
     *
     * @return The radius of the ellipse along the Z-axis.
     */
    public double getZSize() {
        return zSize;
    }

    /**
     * Retrieves a collection of pseudo chunk coordinates that fall within or intersect the boundaries of the elliptical area.
     * This method provides a lightweight alternative to {@link #getChunks()} by avoiding actual chunk loading.
     *
     * @return A collection of {@link PseudoChunk} instances representing the chunk coordinates within or intersecting the area.
     */
    @Override
    public Collection<PseudoChunk> getPseudoChunks() {
        Collection<PseudoChunk> chunks = new HashSet<>();
        for (int x = (int) (center.getX() - xSize); x <= center.getX() + xSize; x += 16) {
            for (int z = (int) (center.getZ() - zSize); z <= center.getZ() + zSize; z += 16) {
                PseudoChunk c = new PseudoChunk(center.getWorld(), x / 16, z / 16);
                // if one of the corners is in the area the chunk is inside
                if (isInArea(new Location(c.getWorld(), c.getX() * 16, 0, (c.getZ() * 16) + 15))
                        || isInArea(new Location(c.getWorld(), c.getX() * 16, 0, c.getZ() * 16))
                        || isInArea(new Location(c.getWorld(), (c.getX() * 16) + 15, 0, c.getZ() * 16))
                        || isInArea(new Location(c.getWorld(), (c.getX() * 16) + 15, 0, (c.getZ() * 16) + 15))) {
                    chunks.add(c);
                }
            }
        }
        return chunks;
    }
}
