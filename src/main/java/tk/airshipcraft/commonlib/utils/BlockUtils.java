package tk.airshipcraft.commonlib.utils;

import org.bukkit.Material;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlockUtils {

    private static final Map<Material, BlockType> blockTypeMap = new HashMap<>();
    private static final Map<BlockType, Set<Material>> typeToMaterialsMap = new HashMap<>();

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

    private static void addMapping(Material material, BlockType type) {
        blockTypeMap.put(material, type);

        typeToMaterialsMap.putIfAbsent(type, new HashSet<>());
        typeToMaterialsMap.get(type).add(material);
    }

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

    public static BlockType getBlockType(Material material) {
        return blockTypeMap.get(material);
    }

    public static Set<Material> getMaterialsFromType(BlockType type) {
        return typeToMaterialsMap.getOrDefault(type, new HashSet<>());
    }

    // Added boolean check methods
    public static boolean isLog(Material material) {
        return getBlockType(material) == BlockType.LOG;
    }

    public static boolean isStone(Material material) {
        return getBlockType(material) == BlockType.STONE;
    }

    public static boolean isPlank(Material material) {
        return getBlockType(material) == BlockType.PLANK;
    }

    public static boolean isLogUnstripped(Material material) {
        return getBlockType(material) == BlockType.LOG_UNSTRIPPED;
    }

    public static boolean isLogStripped(Material material) {
        return getBlockType(material) == BlockType.LOG_STRIPPED;
    }

    public enum BlockType {
        LOG, LOG_STRIPPED, LOG_UNSTRIPPED,
        WOOD, PLANK,
        STONE
    }
}
