package tk.airshipcraft.commonlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * A class to build and manage Graphical User Interfaces (GUIs) in Minecraft.
 * This builder class facilitates the creation of custom inventory interfaces by providing
 * a fluent interface for adding items and setting properties. It encapsulates the complexity
 * of managing inventory slots and item placement, streamlining the process of GUI creation.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class GuiBuilder {

    private final Inventory inventory;

    /**
     * Constructs a new GUI with the specified size and title.
     * The size must be a multiple of 9 as it corresponds to the number of slots in a single inventory row.
     *
     * @param size  The number of slots in the inventory (must be a multiple of 9).
     * @param title The title displayed at the top of the inventory GUI.
     */
    public GuiBuilder(int size, String title) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    /**
     * Adds an item to the GUI at the specified slot. This method is designed for chaining,
     * allowing multiple calls to be linked in a single line of code for convenient GUI setup.
     *
     * @param slot The slot index where the item should be placed.
     * @param item The ItemStack to be placed at the specified slot.
     * @return The current instance of GuiBuilder, for chaining method calls.
     */
    public GuiBuilder setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    /**
     * Finalizes the GUI construction and retrieves the built Inventory instance.
     * This method should be called once all desired items and properties have been set.
     *
     * @return The completed Inventory object representing the GUI.
     */
    public Inventory build() {
        return inventory;
    }
}
