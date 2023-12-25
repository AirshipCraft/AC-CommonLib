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
                addMapping(material, BlockType.UNSTRIPPED_LOG);
            }
            if (isStrippedLogMaterial(material)) {
                addMapping(material, BlockType.STRIPPED_LOG);
            }
            if (isLogMaterial(material)) {
                addMapping(material, BlockType.LOG);
            }
            if (isPlankMaterial(material)) {
                addMapping(material, BlockType.PLANK);
            }
            if (isWoodMaterial(material)) {
                addMapping(material, BlockType.WOOD);
            }
            if (isLeavesMaterial(material)) {
                addMapping(material, BlockType.LEAVES);
            }
            if (isStoneMaterial(material)) {
                addMapping(material, BlockType.STONE);
            }
            if (isCandleMaterial(material)) {
                addMapping(material, BlockType.CANDLE);
            }
            if (isSignMaterial(material)) {
                addMapping(material, BlockType.SIGN);
            }
            if (isWallSignMaterial(material)) {
                addMapping(material, BlockType.WALL_SIGN);
            }
        }
    }

    /**
     * Adds a mapping between a material and a block type.
     *
     * @param material The material to be mapped.
     * @param type     The block type to associate with the material.
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
     * Checks if a material is a log type agnostic if it is stripped or unstripped.
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

    /**
     * Checks if a material is a type of plank.
     *
     * @param material The material to check.
     * @return true if the material is a plank, false otherwise.
     */
    private static boolean isPlankMaterial(Material material) {
        return material == Material.ACACIA_PLANKS ||
                material == Material.OAK_PLANKS ||
                material == Material.DARK_OAK_PLANKS ||
                material == Material.BIRCH_PLANKS ||
                material == Material.JUNGLE_PLANKS ||
                material == Material.SPRUCE_PLANKS ||
                material == Material.MANGROVE_PLANKS ||
                material == Material.CRIMSON_PLANKS ||
                material == Material.WARPED_PLANKS;
    }

    /**
     * Checks if a material is a type of wood. ("Log" texture on all six sides)
     *
     * @param material The material to check.
     * @return true if the material is wood, false otherwise.
     */
    private static boolean isWoodMaterial(Material material) {
        return material == Material.ACACIA_WOOD || material == Material.STRIPPED_ACACIA_WOOD ||
                material == Material.OAK_WOOD || material == Material.STRIPPED_OAK_WOOD ||
                material == Material.DARK_OAK_WOOD || material == Material.STRIPPED_DARK_OAK_WOOD ||
                material == Material.BIRCH_WOOD || material == Material.STRIPPED_BIRCH_WOOD ||
                material == Material.JUNGLE_WOOD || material == Material.STRIPPED_JUNGLE_WOOD ||
                material == Material.SPRUCE_WOOD || material == Material.STRIPPED_SPRUCE_WOOD ||
                material == Material.MANGROVE_WOOD || material == Material.STRIPPED_MANGROVE_WOOD ||
                material == Material.CRIMSON_HYPHAE || material == Material.STRIPPED_CRIMSON_HYPHAE ||
                material == Material.WARPED_HYPHAE || material == Material.STRIPPED_WARPED_HYPHAE;
    }

    /**
     * Checks if a material is a leaf type.
     *
     * @param material The material to check.
     * @return true if the material is a leaf, false otherwise.
     */
    private static boolean isLeavesMaterial(Material material) {
        return material == Material.ACACIA_LEAVES ||
                material == Material.OAK_LEAVES ||
                material == Material.DARK_OAK_LEAVES ||
                material == Material.BIRCH_LEAVES ||
                material == Material.JUNGLE_LEAVES ||
                material == Material.SPRUCE_LEAVES ||
                material == Material.MANGROVE_LEAVES ||
                material == Material.CRIMSON_FUNGUS ||  // i think this is the right material but tbh not sure
                material == Material.WARPED_FUNGUS;     // same for this
    }

    /**
     * Checks if a material is a type of stone.
     *
     * @param material The material to check.
     * @return true if the material is stone, false otherwise.
     */
    private static boolean isStoneMaterial(Material material) {
        return material == Material.STONE ||
                material == Material.GRANITE ||
                material == Material.DIORITE ||
                material == Material.ANDESITE;
    }

    /**
     * Checks if a material is a candle type.
     *
     * @param material The material to check.
     * @return true if the material is a candle, false otherwise.
     */
    private static boolean isCandleMaterial(Material material) {
        return material == Material.CANDLE ||
                material == Material.BLACK_CANDLE ||
                material == Material.WHITE_CANDLE ||
                material == Material.GRAY_CANDLE ||
                material == Material.LIGHT_GRAY_CANDLE ||
                material == Material.ORANGE_CANDLE ||
                material == Material.MAGENTA_CANDLE ||
                material == Material.LIGHT_BLUE_CANDLE ||
                material == Material.YELLOW_CANDLE ||
                material == Material.LIME_CANDLE ||
                material == Material.PINK_CANDLE ||
                material == Material.PURPLE_CANDLE ||
                material == Material.BLUE_CANDLE ||
                material == Material.BROWN_CANDLE ||
                material == Material.GREEN_CANDLE ||
                material == Material.CYAN_CANDLE ||
                material == Material.RED_CANDLE;
    }

    /**
     * Checks if a material is a wall sign type.
     *
     * @param material The material to check.
     * @return true if the material is a wall sign, false otherwise.
     */
    private static boolean isWallSignMaterial(Material material) {
        return material == Material.ACACIA_WALL_SIGN ||
                material == Material.BIRCH_WALL_SIGN ||
                material == Material.DARK_OAK_WALL_SIGN ||
                material == Material.JUNGLE_WALL_SIGN ||
                material == Material.OAK_WALL_SIGN ||
                material == Material.SPRUCE_WALL_SIGN ||
                material == Material.WARPED_WALL_SIGN ||
                material == Material.CRIMSON_WALL_SIGN;
    }

    /**
     * Checks if a material is a sign type agnostic if it is a wall sign or not.
     *
     * @param material The material to check.
     * @return true if the material is a sign, false otherwise.
     */
    private static boolean isSignMaterial(Material material) {
        return material == Material.ACACIA_SIGN || material == Material.ACACIA_WALL_SIGN ||
                material == Material.BIRCH_SIGN || material == Material.BIRCH_WALL_SIGN ||
                material == Material.DARK_OAK_SIGN || material == Material.DARK_OAK_WALL_SIGN ||
                material == Material.JUNGLE_SIGN || material == Material.JUNGLE_WALL_SIGN ||
                material == Material.OAK_SIGN || material == Material.OAK_WALL_SIGN ||
                material == Material.SPRUCE_SIGN || material == Material.SPRUCE_WALL_SIGN ||
                material == Material.WARPED_SIGN || material == Material.WARPED_WALL_SIGN ||
                material == Material.CRIMSON_SIGN || material == Material.CRIMSON_WALL_SIGN;
    }

    // the reason for this method checking both is that I assume if you are looking for a freestanding sign then ANY sign would do

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
     * Checks if a material is a log agnostic of whether it is stripped or not.
     *
     * @param material The material to check.
     * @return true if the material is any type of log, false otherwise.
     */
    public static boolean isLog(Material material) {
        return getBlockType(material) == BlockType.LOG;
    }

    /**
     * Checks if a material is wood agnostic of whether it is stripped or not.
     *
     * @param material The material to check.
     * @return true if the material is any type of log, false otherwise.
     */
    public static boolean isWood(Material material) {
        return getBlockType(material) == BlockType.WOOD;
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
     * Checks if a material is a leaf.
     *
     * @param material The material to check.
     * @return true if the material is any type of leaf, false otherwise.
     */
    public static boolean isLeaf(Material material) {
        return getBlockType(material) == BlockType.LEAVES;
    }

    /**
     * Checks if a material is a stone type. Includes granite, diorite, and andesite.
     *
     * @param material The material to check.
     * @return true if the material is any type of stone, false otherwise.
     */
    public static boolean isStone(Material material) {
        return getBlockType(material) == BlockType.STONE;
    }

    /**
     * Checks if a material is a candle agnostic of its color.
     *
     * @param material The material to check.
     * @return true if the material is any type of candle, false otherwise.
     */
    public static boolean isCandle(Material material) {
        return getBlockType(material) == BlockType.CANDLE;
    }

    /**
     * Checks if a material is a wall sign.
     *
     * @param material The material to check.
     * @return true if the material is any type of wall sign, false otherwise.
     */
    public static boolean isWallSign(Material material) {
        return getBlockType(material) == BlockType.WALL_SIGN;
    }

    /**
     * Checks if a material is a sign agnostic if it is a wall sign or not.
     *
     * @param material The material to check.
     * @return true if the material is any type of sign, false otherwise.
     */
    public static boolean isSign(Material material) {
        return getBlockType(material) == BlockType.SIGN;
    }

    /**
     * Enum representing different types of Minecraft blocks grouped by category.
     */
    public enum BlockType {
        /**
         * Represents a log block type.
         */
        LOG,
        /**
         * Represents a stripped log block type.
         */
        STRIPPED_LOG,
        /**
         * Represents only unstripped log block variants.
         */
        UNSTRIPPED_LOG,
        /**
         * Represents a wood block (log or plank) type.
         */
        WOOD,
        /**
         * Represents a plank block type.
         */
        PLANK,
        /**
         * Represents a leaf block type.
         */
        LEAVES,
        /**
         * Represents a wall sign block type.
         */
        WALL_SIGN,
        /**
         * Represents any sign type (wall sign or freestanding sign)
         */
        SIGN,
        /**
         * Represents any kind of stone
         */
        STONE,
        /**
         * Represents a candle block type.
         */
        CANDLE
    }
}
