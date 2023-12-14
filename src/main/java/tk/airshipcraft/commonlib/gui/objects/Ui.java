package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a custom User Interface (UI) in Minecraft, facilitating the creation and management of custom inventory interfaces.
 * This class allows for the design and interaction of inventory-based GUIs such as chests or hoppers, with custom layouts and functionalities.
 * It supports adding items as buttons and managing these interfaces within the Minecraft environment.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class Ui {

    private static final Map<UUID, Ui> uis = new HashMap<>(); // A map to keep track of all UI instances by their ID.

    private String inventoryName;
    private Inventory inventory;
    private UUID inventoryId;
    private InventoryHolder owner;
    private InventoryType type;
    private int rows;

    /**
     * Constructs a new Ui instance with specified parameters.
     * This constructor sets up a custom inventory interface, which can be a chest, hopper, etc., based on the provided type and rows.
     *
     * @param inventoryName The name of the inventory, displayed at the top of the UI.
     * @param owner         The InventoryHolder associated with this UI, can be null.
     * @param type          The type of inventory (e.g., CHEST, HOPPER) to create.
     * @param rows          The number of rows in the inventory if it's a chest type.
     */
    public Ui(String inventoryName, InventoryHolder owner, InventoryType type, int rows) {
        this.inventoryName = inventoryName;
        this.type = type;
        this.rows = rows;
        this.inventory = Bukkit.createInventory(owner, rows * 9, inventoryName);
        this.owner = owner;
        this.inventoryId = UUID.randomUUID();
        uis.put(inventoryId, this); // Track the UI instance.
    }

    /**
     * Checks if a given inventory is a custom UI created by this class.
     * This static method can be used to verify if an inventory is part of the custom UI system.
     *
     * @param inventory The Inventory to check.
     * @return True if the inventory is a custom UI created by this class, false otherwise.
     */
    public static boolean isUi(Inventory inventory) {
        if (inventory.getHolder() instanceof Ui) {
            Ui ui = (Ui) inventory.getHolder();
            return uis.containsKey(ui.getInventoryId());
        }
        return false;
    }

    /**
     * Adds an item as a button to the UI at the specified slot.
     * This method places an ItemStack in the inventory at a given slot, which can represent a button or an interactive element.
     *
     * @param item The ItemStack to be added as a button.
     * @param slot The inventory slot where the item will be placed.
     */
    public void addButton(ItemStack item, int slot) {
        this.inventory.setItem(slot, item);
    }

    /**
     * Retrieves the name of the UI's inventory.
     *
     * @return The name of the inventory.
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * Gets the unique identifier of this UI instance.
     * This UUID can be used to distinguish and track individual UIs.
     *
     * @return The UUID of the inventory.
     */
    public UUID getInventoryId() {
        return inventoryId;
    }

    /**
     * Retrieves the InventoryHolder associated with this UI.
     * The InventoryHolder represents the entity or object that holds this UI.
     *
     * @return The InventoryHolder of this UI, which may be null.
     */
    public InventoryHolder getOwner() {
        return owner;
    }

    /**
     * Obtains the Inventory object representing this UI.
     * This inventory is the actual container in which items and buttons are placed.
     *
     * @return The Inventory instance representing this UI.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the type of the UI's inventory.
     * The type determines the layout and behavior of the inventory (e.g., chest, hopper).
     *
     * @return The InventoryType of this UI.
     */
    public InventoryType getType() {
        return type;
    }

    /**
     * Retrieves the number of rows in the UI's inventory.
     * This is relevant for types like chests that have a variable number of rows.
     *
     * @return The number of rows in the inventory.
     */
    public int getRows() {
        return rows;
    }
}
