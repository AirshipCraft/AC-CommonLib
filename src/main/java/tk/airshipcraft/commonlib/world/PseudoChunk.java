package tk.airshipcraft.commonlib.world;

import org.bukkit.Chunk;
import org.bukkit.World;

/**
 * Represents a chunk-like structure that holds the coordinates for a chunk in a world without storing the actual chunk data.
 * This is useful for representing chunk locations without the overhead of the actual chunk data in memory.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-03-30
 */
public class PseudoChunk {

    private World world;
    private int x;
    private int z;

    /**
     * Constructs a new {@code PseudoChunk} with the given world and chunk coordinates.
     *
     * @param world The world in which this pseudo chunk is located.
     * @param x     The x-coordinate of the chunk.
     * @param z     The z-coordinate of the chunk.
     */
    public PseudoChunk(World world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
    }

    /**
     * Gets the x-coordinate of this pseudo chunk.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the z-coordinate of this pseudo chunk.
     *
     * @return The z-coordinate.
     */
    public int getZ() {
        return z;
    }

    /**
     * Gets the world where this pseudo chunk is located.
     *
     * @return The world.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the actual x-coordinate of the chunk's origin in the world (block coordinate).
     *
     * @return The actual x-coordinate of the chunk in block coordinates.
     */
    public int getActualX() {
        return x * 16;
    }

    /**
     * Gets the actual z-coordinate of the chunk's origin in the world (block coordinate).
     *
     * @return The actual z-coordinate of the chunk in block coordinates.
     */
    public int getActualZ() {
        return z * 16;
    }

    /**
     * Retrieves the actual {@link Chunk} object that this pseudo chunk represents from the world.
     *
     * @return The corresponding {@link Chunk} object.
     */
    public Chunk getActualChunk() {
        return world.getChunkAt(x, z);
    }
}
