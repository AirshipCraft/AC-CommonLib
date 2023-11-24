package tk.airshipcraft.commonlib.utils.math;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Provides functionality to perform raycasts in Minecraft, which is a process of simulating "rays" to detect
 * blocks or entities along a line. This can be used for features like shooting mechanics, line of sight calculations,
 * and interaction with the game world at a distance.
 *
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-04-11
 */
public class RayCast {

    /**
     * Casts a ray from the player's eye location in the given direction, returning the first block or entity encountered.
     * This method can be used to determine what a player is looking at or could interact with at a distance.
     *
     * @param player      The player from whose perspective the ray is cast.
     * @param direction   The direction vector along which to cast the ray.
     * @param maxDistance The maximum distance the ray should travel.
     * @return The first non-air block or entity the ray intersects, or null if it hits nothing within the max distance.
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
     * Performs a raycast to detect the first non-air block from the player's eye location in a specified direction.
     * Useful for detecting which block a player is looking at from a distance.
     *
     * @param player      The player performing the raycast.
     * @param direction   The normalized direction vector for the raycast.
     * @param maxDistance The maximum range of the raycast.
     * @return The first solid block encountered by the ray, or null if only air is found within the range.
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
     * Raycasts between two blocks, returning a sequence of blocks that lie between them on a straight line.
     * This is useful for determining the path of a projectile or line of sight between two fixed points.
     *
     * @param startBlock The block from which to start the raycast.
     * @param endBlock   The block where the raycast should end.
     * @return An array of blocks intersected by the ray, starting with the startBlock and ending with the endBlock.
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
