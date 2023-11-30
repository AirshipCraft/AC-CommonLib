package tk.airshipcraft.commonlib.utils;

import org.bukkit.Material;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility class for categorizing different types of Minecraft blocks.
 * Allows for easy identification of block types such as logs, stones, planks, etc.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-28
 */
public class BlockUtils {

    private static final Map<Material, BlockType> blockTypeMap = new HashMap<>();
    private static final Map<BlockType, Set<Material>> typeToMaterialsMap = new HashMap<>();

    // Static initializer to populate the block type mappings
    static {
        // Initialize the mapping
        for (Material material : Material.values()) {
            if (isUnstrippedLogMaterial(material)) {
                addMapping(material, BlockType.LOG_UNSTRIPPED);
            }
            if (isStrippedLogMaterial(material)) {
                addMapping(material, BlockType.LOG_STRIPPED);
            }
            if (isLogMaterial(material)) {
                addMapping(material, BlockType.LOG);
            }
            if (isStoneMaterial(material)) {
                addMapping(material, BlockType.STONE);
            }
            if (isPlankMaterial(material)) {
                addMapping(material, BlockType.PLANK);
            }
        }
    }

    /**
     * Adds a mapping between a material and a block type.
     *
     * @param material The material to be mapped.
     * @param type The block type to associate with the material.
     */
    private static void addMapping(Material material, BlockType type) {
        blockTypeMap.put(material, type);

        typeToMaterialsMap.putIfAbsent(type, new HashSet<>());
        typeToMaterialsMap.get(type).add(material);
    }

    /**
     * Checks if a material is an unstripped log type.
     *
     * @param material The material to check.
     * @return true if the material is an unstripped log, false otherwise.
     */
    private static boolean isUnstrippedLogMaterial(Material material) {
        return material == Material.ACACIA_LOG ||
                material == Material.OAK_LOG ||
                material == Material.DARK_OAK_LOG ||
                material == Material.BIRCH_LOG ||
                material == Material.JUNGLE_LOG ||
                material == Material.SPRUCE_LOG ||
                material == Material.MANGROVE_LOG ||
                material == Material.CRIMSON_HYPHAE ||
                material == Material.CRIMSON_STEM;
    }

    /**
     * Checks if a material is an stripped log type.
     *
     * @param material The material to check.
     * @return true if the material is an stripped log, false otherwise.
     */
    private static boolean isStrippedLogMaterial(Material material) {
        return material == Material.STRIPPED_ACACIA_LOG ||
                material == Material.STRIPPED_OAK_LOG ||
                material == Material.STRIPPED_DARK_OAK_LOG ||
                material == Material.STRIPPED_BIRCH_LOG ||
                material == Material.STRIPPED_JUNGLE_LOG ||
                material == Material.STRIPPED_SPRUCE_LOG ||
                material == Material.STRIPPED_MANGROVE_LOG ||
                material == Material.STRIPPED_CRIMSON_HYPHAE ||
                material == Material.STRIPPED_CRIMSON_STEM;
    }

    /**
     * Checks if a material is a log type (agnostic if it is stripped or unstripped).
     *
     * @param material The material to check.
     * @return true if the material is a log, false otherwise.
     */
    private static boolean isLogMaterial(Material material) {
        return material == Material.ACACIA_LOG || material == Material.STRIPPED_ACACIA_LOG ||
                material == Material.OAK_LOG || material == Material.STRIPPED_OAK_LOG ||
                material == Material.DARK_OAK_LOG || material == Material.STRIPPED_DARK_OAK_LOG ||
                material == Material.BIRCH_LOG || material == Material.STRIPPED_BIRCH_LOG ||
                material == Material.JUNGLE_LOG || material == Material.STRIPPED_JUNGLE_LOG ||
                material == Material.SPRUCE_LOG || material == Material.STRIPPED_SPRUCE_LOG ||
                material == Material.MANGROVE_LOG || material == Material.STRIPPED_MANGROVE_LOG ||
                material == Material.CRIMSON_STEM || material == Material.STRIPPED_CRIMSON_STEM ||
                material == Material.WARPED_STEM || material == Material.STRIPPED_WARPED_STEM;
    }

    private static boolean isStoneMaterial(Material material) {
        return material == Material.STONE ||
                material == Material.GRANITE ||
                material == Material.DIORITE ||
                material == Material.ANDESITE;
    }

    private static boolean isPlankMaterial(Material material) {
        return material == Material.ACACIA_PLANKS ||
                material == Material.OAK_PLANKS ||
                material == Material.DARK_OAK_PLANKS ||
                material == Material.BIRCH_PLANKS ||
                material == Material.JUNGLE_PLANKS ||
                material == Material.SPRUCE_PLANKS ||
                material == Material.MANGROVE_PLANKS;
    }

    /**
     * Retrieves the block type of a given material.
     *
     * @param material The material whose block type is to be determined.
     * @return The block type of the material, or null if the material does not have a mapped block type.
     */
    public static BlockType getBlockType(Material material) {
        return blockTypeMap.get(material);
    }


    /**
     * Retrieves all materials associated with a given block type.
     *
     * @param type The block type for which materials are to be retrieved.
     * @return A set of materials associated with the specified block type.
     */
    public static Set<Material> getMaterialsFromType(BlockType type) {
        return typeToMaterialsMap.getOrDefault(type, new HashSet<>());
    }

    // Added boolean check methods
    /**
     * Checks if a material is a log (including both stripped and unstripped variants).
     *
     * @param material The material to check.
     * @return true if the material is any type of log, false otherwise.
     */
    public static boolean isLog(Material material) {
        return getBlockType(material) == BlockType.LOG;
    }

    /**
     * Checks if a material is an unstripped log.
     *
     * @param material The material to check.
     * @return true if the material is any type of unstripped log, false otherwise.
     */
    public static boolean isLogUnstripped(Material material) {
        return getBlockType(material) == BlockType.LOG_UNSTRIPPED;
    }

    /**
     * Checks if a material is an stripped log.
     *
     * @param material The material to check.
     * @return true if the material is any type of stripped log, false otherwise.
     */
    public static boolean isLogStripped(Material material) {
        return getBlockType(material) == BlockType.LOG_STRIPPED;
    }

    /**
     * Checks if a material is a stone.
     *
     * @param material The material to check.
     * @return true if the material is any type of stone, false otherwise.
     */
    public static boolean isStone(Material material) {
        return getBlockType(material) == BlockType.STONE;
    }

    /**
     * Checks if a material is a plank.
     *
     * @param material The material to check.
     * @return true if the material is any type of plank, false otherwise.
     */
    public static boolean isPlank(Material material) {
        return getBlockType(material) == BlockType.PLANK;
    }

    /**
     * Enum representing different types of Minecraft blocks grouped by category.
     */
    public enum BlockType {
        LOG, LOG_STRIPPED, LOG_UNSTRIPPED,
        WOOD, PLANK,
        STONE
    }
}
