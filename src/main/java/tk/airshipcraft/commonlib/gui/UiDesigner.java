package tk.airshipcraft.commonlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.gui.objects.Ui;
import tk.airshipcraft.commonlib.utils.SubclassFinder;

import java.util.List;

/**
 * A utility class designed for crafting and handling Graphical User Interfaces (GUIs) within Minecraft.
 * It provides a suite of methods to create and configure custom inventory GUIs, such as setting items in slots,
 * filling empty slots with placeholder items, managing inventory borders, and defining titles.
 * Extend this class to implement specific click actions and other interactive features for different GUIs.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-09
 */
public abstract class UiDesigner {

    private static SubclassFinder subclassFinder;
    private static final CommonLib commonLib = CommonLib.getInstance();

    /**
     * Initializes a new SubclassFinder for UiDesigner subclasses.
     * The SubclassFinder utility helps identify all subclasses of UiDesigner for click action processing.
     */
    public UiDesigner() {
        subclassFinder = new SubclassFinder(this.getClass());
    }

    /**
     * Creates a custom GUI (Graphical User Interface) inventory with a specific title and type.
     * This method simplifies the process of creating custom inventory interfaces.
     *
     * @param title The title of the inventory GUI.
     * @param type  The type of the inventory (e.g., CHEST, HOPPER).
     * @param rows  The number of rows in the inventory (chest type).
     * @return A new Ui instance representing the custom GUI.
     */
    public static Ui createGUI(String title, InventoryType type, int rows) {
        return new Ui(title, null, type, rows);
    }

    /**
     * Invokes the addClickAction method for all UiDesigner subclasses.
     * This allows the execution of click actions defined in UiDesigner subclasses when a player interacts with an inventory slot.
     *
     * @param inventory The inventory where the interaction occurred.
     * @param slot      The slot number that was interacted with.
     */
    public static void callClickAction(Inventory inventory, int slot) {
        // Get all subclasses of UiDesigner
        List<Class<?>> subclasses = subclassFinder.getSubclasses();

        // Iterate over all subclasses and call addClickAction on each instance
        for (Class<?> subclass : subclasses) {
            try {
                UiDesigner instance = (UiDesigner) subclass.getDeclaredConstructor().newInstance();
                instance.addClickAction(inventory, slot);
            } catch (Exception e) {
                // Handle exceptions
                commonLib.logDebug("Error in executing click action for " + subclass.getName() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Clears all items from the given inventory.
     *
     * @param inventory The inventory to clear.
     */
    public void clearInventory(Inventory inventory) {
        inventory.clear();
    }

    /**
     * Opens the specified inventory for the given player.
     * This method is useful for showing a custom UI to a player.
     *
     * @param player    The player to show the inventory to.
     * @param inventory The inventory to be shown.
     */
    public void openInventoryForPlayer(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }

    /**
     * Creates an item stack with specified material, amount, and custom name.
     * This method simplifies the process of creating customized items for UIs.
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     * @param name     The custom name of the item.
     * @return The created ItemStack.
     */
    public static ItemStack createItemStack(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Abstract method to add a click action for a GUI.
     * Subclasses of UiDesigner should override this method to provide specific behavior when a slot in the UI is clicked.
     *
     * @param inventory The inventory in which the click occurred.
     * @param slot      The slot number that was clicked.
     */
    public abstract void addClickAction(Inventory inventory, int slot);

    /**
     * Set a specific slot in an inventory with a given item stack.
     *
     * @param inventory the inventory to modify
     * @param slot      the slot to modify
     * @param item      the item stack to set in the slot
     */
    public void setSlot(Inventory inventory, int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    /**
     * Fills all empty slots in the given inventory with the specified item stack.
     *
     * @param inventory the inventory to fill
     * @param item      the item stack to fill the inventory with
     */
    public void fillSlots(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }

    /**
     * Sets the border of the inventory with a specified item.
     * This method can be used to visually separate the content area from the edges in the GUI.
     *
     * @param inventory The inventory to modify.
     * @param item      The ItemStack to use as a border.
     */
    public void setBorder(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 0; i < size; i++) {
            if (i < 9 || i >= size - 9 || i % 9 == 0 || (i + 1) % 9 == 0) {
                inventory.setItem(i, item);
            }
        }
    }

    /**
     * Fills all empty slots that are not part of the border in the inventory with a specified item.
     * This can be used to create a background effect or indicate unusable slots.
     *
     * @param inventory The inventory to fill.
     * @param item      The ItemStack to use as filler for non-border slots.
     */
    public void setFiller(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 0; i < size; i++) {
            if (i >= 9 && i < size - 9 && (i + 1) % 9 != 0 && i % 9 != 0 && inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }

    /**
     * Changes the title of the inventory.
     * This method will close the existing inventory for the player, if necessary, and open a new one with the new title.
     * Note that changing the title is not possible without reopening the inventory.
     *
     * @param inventory The inventory whose title is to be changed.
     * @param title     The new title for the inventory.
     */
    public void setTitle(Inventory inventory, String title) {
        if (inventory.getHolder() instanceof Player) {
            Player player = (Player) inventory.getHolder();
            player.closeInventory();
            inventory = Bukkit.createInventory(player, inventory.getSize(), title);
            player.openInventory(inventory);
        } else {
            throw new IllegalArgumentException("GUI holder must be a player");
        }
    }
}
