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
 * Represents a custom User Interface (UI) in Minecraft.
 * This class allows for creating and managing custom inventory interfaces.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class Ui {

    private static final Map<UUID, Ui> uis = new HashMap<>(); // A map to track all UI instances by their ID.

    private String inventoryName;
    private Inventory inventory;
    private UUID inventoryId;
    private InventoryHolder owner;
    private InventoryType type;
    private int rows;

    /**
     * Constructs a new UI with the specified name, owner, and number of rows.
     * This UI is typically used for creating custom inventory interfaces such as chests.
     *
     * @param inventoryName The name of the inventory.
     * @param owner         The owner of the inventory, can be null.
     * @param type          The type of the inventory (e.g., CHEST, HOPPER).
     * @param rows          The number of rows in the inventory (chest type).
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
     * Adds a button (item) to the UI at the specified slot.
     *
     * @param item The ItemStack to add as a button.
     * @param slot The slot to place the item in.
     */
    public void addButton(ItemStack item, int slot) {
        this.inventory.setItem(slot, item);
    }

    /**
     * Gets the name of the UI.
     *
     * @return The inventory name.
     */
    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * Gets the unique identifier of the UI.
     *
     * @return The UUID of the inventory.
     */
    public UUID getInventoryId() {
        return inventoryId;
    }

    /**
     * Gets the InventoryHolder of the UI.
     *
     * @return The InventoryHolder, which can be null.
     */
    public InventoryHolder getOwner() {
        return owner;
    }

    /**
     * Gets the Inventory object representing this UI.
     *
     * @return The Inventory instance.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the type of the UI.
     *
     * @return The InventoryType instance.
     */
    public InventoryType getType() {
        return type;
    }

    /**
     * Gets the number of rows in the UI.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Checks if the provided inventory is a UI created by this plugin.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory is a custom UI, false otherwise.
     */
    public static boolean isUi(Inventory inventory) {
        if (inventory.getHolder() instanceof Ui) {
            Ui ui = (Ui) inventory.getHolder();
            return uis.containsKey(ui.getInventoryId());
        }
        return false;
    }
}
