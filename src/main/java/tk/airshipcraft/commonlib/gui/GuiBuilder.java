package tk.airshipcraft.commonlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * A class to build and manage GUIs (Graphical User Interfaces) in Minecraft.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class GuiBuilder {

    private final Inventory inventory;

    /**
     * Creates a new GUI with the specified size and title.
     *
     * @param size  The number of slots in the inventory (must be a multiple of 9).
     * @param title The title of the inventory.
     */
    public GuiBuilder(int size, String title) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    /**
     * Adds an item to the GUI at the specified slot.
     *
     * @param slot The slot to place the item in.
     * @param item The item to add.
     * @return The current instance of GuiBuilder, for chaining methods.
     */
    public GuiBuilder setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    /**
     * Retrieves the built inventory (GUI).
     *
     * @return The inventory representing this GUI.
     */
    public Inventory build() {
        return inventory;
    }
}
