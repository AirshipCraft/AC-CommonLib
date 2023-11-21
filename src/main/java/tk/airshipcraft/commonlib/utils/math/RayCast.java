package tk.airshipcraft.commonlib.utils.math;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Utility class for performing raycasts in Minecraft.
 *
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-04-11
 */
public class RayCast {

    /**
     * Performs a raycast from the player's location to the first non-air block or entity in the specified direction.
     *
     * @param player      the player whose location to start the raycast from
     * @param direction   the direction to cast the ray in
     * @param maxDistance the maximum distance to cast the ray
     * @return the first non-air block or entity that the ray hits, or null if nothing is hit within the specified distance
     */
    public static Object raycast(Player player, Vector direction, double maxDistance) {
        Location location = player.getEyeLocation();
        double distance = 0;

        while (distance <= maxDistance) {
            location = location.add(direction);
            Block block = location.getBlock();
            Entity entity = null;
            List<Entity> entities = player.getWorld().getEntities();
            for (Entity e : entities) {
                if (e.getLocation().distance(location) < 1 && e != player) {
                    entity = e;
                    break;
                }
            }

            if (!block.getType().isAir()) {
                return block;
            } else if (entity != null) {
                return entity;
            }

            distance += 1;
        }

        return null;
    }

    /**
     * Performs a raycast from the player's location to the first non-air block in the specified direction.
     *
     * @param player      the player whose location to start the raycast from
     * @param direction   the direction to cast the ray in
     * @param maxDistance the maximum distance to cast the ray
     * @return the first non-air block that the ray hits, or null if no block is hit within the specified distance
     */
    public static Block raycastToBlock(Player player, Vector direction, double maxDistance) {
        Location location = player.getEyeLocation();
        double distance = 0;

        while (distance <= maxDistance) {
            location = location.add(direction);
            Block block = location.getBlock();

            if (!block.getType().isAir()) {
                return block;
            }

            distance += 1;
        }

        return null;
    }

    /**
     * Performs a raycast from the first block to the second block in a straight line.
     *
     * @param startBlock the starting block
     * @param endBlock   the ending block
     * @return an array of blocks that the ray hits between the start and end blocks, including the start and end blocks themselves
     */
    public static Block[] raycastBetweenBlocks(Block startBlock, Block endBlock) {
        Location start = startBlock.getLocation().add(0.5, 0.5, 0.5);
        Location end = endBlock.getLocation().add(0.5, 0.5, 0.5);
        Vector direction = end.subtract(start).toVector().normalize();
        double distance = start.distance(end);
        int steps = (int) Math.ceil(distance);

        Block[] blocks = new Block[steps];
        Location currentLocation = start.clone();

        for (int i = 0; i < steps; i++) {
            blocks[i] = currentLocation.getBlock();
            currentLocation.add(direction);
        }

        return blocks;
    }
}
