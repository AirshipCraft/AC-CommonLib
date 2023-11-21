package tk.airshipcraft.commonlib.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a rectangular area in a Minecraft world with Y-level boundaries.
 * The area is defined by a center {@link Location}, a size along the X-axis (xSize),
 * and a size along the Z-axis (zSize). This class can be used to check if specific
 * locations or chunks fall within the area.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-01
 */
public class RectangleArea extends AbstractYLimitedArea {

    private Location center;
    private double xSize;
    private double zSize;

    /**
     * Constructs a new {@code RectangleArea} with specified Y boundaries, center, and size.
     *
     * @param lowerYBound The lower Y boundary of the area.
     * @param upperYBound The upper Y boundary of the area.
     * @param center      The center location of the rectangular area.
     * @param xSize       Half of the size of the rectangle along the X-axis.
     * @param zSize       Half of the size of the rectangle along the Z-axis.
     */
    public RectangleArea(double lowerYBound, double upperYBound, Location center, double xSize, double zSize) {
        super(lowerYBound, upperYBound);
        this.center = center;
        this.xSize = xSize;
        this.zSize = zSize;
    }

    /**
     * Retrieves a collection of chunks that are within the rectangular area.
     *
     * @return A collection of {@link Chunk} objects within the area.
     */
    @Override
    public Collection<Chunk> getChunks() {
        Collection<Chunk> chunks = new HashSet<>();
        // Iterate through the area's extent along the X and Z axes, adding each chunk to the collection.
        for (double x = center.getX() - xSize; x <= center.getX() + xSize; x += 16) {
            for (double z = center.getZ() - zSize; z <= center.getZ() + zSize; z += 16) {
                chunks.add(new Location(center.getWorld(), x, center.getY(), z).getChunk());
            }
            // Ensure the last chunk in the Z-axis is included.
            chunks.add(new Location(center.getWorld(), x, center.getY(), center.getZ() + zSize).getChunk());
        }
        return chunks;
    }

    /**
     * Checks if a location is within the rectangular area.
     *
     * @param loc The location to check.
     * @return True if the location is within the area, false otherwise.
     */
    @Override
    public boolean isInArea(Location loc) {
        double x = loc.getX();
        double z = loc.getZ();
        return loc.getWorld().equals(getWorld())
                && x >= center.getX() - xSize && x <= center.getX() + xSize
                && z >= center.getZ() - zSize && z <= center.getZ() + zSize
                && super.isInArea(loc);
    }

    /**
     * Gets the center location of the rectangular area.
     *
     * @return The center {@link Location} of the area.
     */
    @Override
    public Location getCenter() {
        return center;
    }

    /**
     * Gets the world where the rectangular area is located.
     *
     * @return The {@link World} object representing the world of the area.
     */
    @Override
    public World getWorld() {
        return center.getWorld();
    }

    /**
     * Gets the size of the rectangular area along the X-axis.
     *
     * @return Half the size of the area along the X-axis.
     */
    public double getXSize() {
        return xSize;
    }

    /**
     * Gets the size of the rectangular area along the Z-axis.
     *
     * @return Half the size of the area along the Z-axis.
     */
    public double getZSize() {
        return zSize;
    }

    /**
     * Retrieves a collection of pseudo chunks that are within the rectangular area.
     * Pseudo chunks are represented by {@link PseudoChunk} and do not contain actual chunk data,
     * but represent chunk locations within the area.
     *
     * @return A collection of {@link PseudoChunk} objects within the area.
     */
    @Override
    public Collection<PseudoChunk> getPseudoChunks() {
        Collection<PseudoChunk> chunks = new HashSet<>();
        // Iterate through the area's extent along the X and Z axes, adding each pseudo chunk to the collection.
        for (int x = (int) (center.getX() - xSize); x <= center.getX() + xSize; x += 16) {
            for (int z = (int) (center.getZ() - zSize); z <= center.getZ() + zSize; z += 16) {
                chunks.add(new PseudoChunk(center.getWorld(), x >> 4, z >> 4));
            }
            // Ensure the last pseudo chunk in the Z-axis is included.
            chunks.add(new PseudoChunk(center.getWorld(), x >> 4, ((int) (center.getZ() + zSize)) >> 4));
        }
        return chunks;
    }
}
