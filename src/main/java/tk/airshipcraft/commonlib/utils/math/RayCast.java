package tk.airshipcraft.commonlib.utils.math;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Provides functionality to perform ray casts in Minecraft, which is a process of simulating "rays" to detect
 * blocks or entities along a line. This can be used for features like shooting mechanics, line of sight calculations,
 * and interaction with the game world at a distance.
 *
 * @author Locutusque, notzune, eerieXanthic
 * @version 1.0.0
 * @since 2023-04-11
 */
public class RayCast {

    // Private constructor to prevent instantiation
    private RayCast() {
        throw new UnsupportedOperationException("RayCast is a utility class and cannot be instantiated");
    }

    /**
     * Validates that the specified distance is a non-negative value.
     * If the distance is negative, an {@link IllegalArgumentException} is thrown.
     *
     * @param distance the distance to validate
     * @throws IllegalArgumentException if the distance is negative
     */
    private static void validateDistance(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative, but was " + distance);
        }
    }

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

    /**
     * Performs a raycast from the specified living entity to the nearest solid block within
     * a specified distance.
     *
     * @param from     the living entity from which the raycast originates
     * @param distance the maximum distance to perform the raycast (in blocks)
     * @return an Optional containing the location of the nearest solid block, or an empty Optional if no solid block is found
     * @throws IllegalArgumentException if distance is negative
     */
    public static Optional<Location> raycastWithinRadius(LivingEntity from, int distance) {
        validateDistance(distance);

        BlockIterator blockIterator = new BlockIterator(from.getEyeLocation(), 0, distance);
        while (blockIterator.hasNext()) {
            Location location = blockIterator.next().getLocation();
            if (location.getBlock().getType().isSolid()) {
                return Optional.of(location);
            }
        }
        return Optional.empty();
    }

    /**
     * Performs a raycast from the specified living entity and returns all blocks hit by the ray within
     * the specified distance.
     *
     * @param from     the living entity from which the raycast originates
     * @param distance the maximum distance to perform the raycast (in blocks)
     * @return a list of blocks hit by the ray
     * @throws IllegalArgumentException if distance is negative
     */
    public static List<Block> raycastAllBlocks(LivingEntity from, int distance) {
        validateDistance(distance);

        List<Block> hitBlocks = new ArrayList<>();
        BlockIterator blockIterator = new BlockIterator(from.getEyeLocation(), 0, distance);
        while (blockIterator.hasNext()) {
            hitBlocks.add(blockIterator.next());
        }
        return hitBlocks;
    }

    /**
     * Performs a raycast from the specified living entity and returns the nearest block that satisfies
     * the specified predicate within the specified distance.
     *
     * @param from      the living entity from which the raycast originates
     * @param distance  the maximum distance to perform the raycast (in blocks)
     * @param predicate the predicate to filter blocks
     * @return an Optional containing the block that satisfies the predicate, or an empty Optional if no such block is found
     * @throws IllegalArgumentException if distance is negative
     */
    public static Optional<Block> raycastWithFilter(LivingEntity from, int distance, Predicate<Block> predicate) {
        validateDistance(distance);

        BlockIterator blockIterator = new BlockIterator(from.getEyeLocation(), 0, distance);
        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();
            if (predicate.test(block)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }
}
