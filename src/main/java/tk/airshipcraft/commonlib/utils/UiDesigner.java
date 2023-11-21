package tk.airshipcraft.commonlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.gui.objects.Ui;

import java.util.List;

/**
 * A utility class for designing user interfaces (UIs) in Minecraft.
 * Provides methods to create custom inventory GUIs, set items in inventory slots,
 * fill empty slots with items, and manage inventory borders and titles.
 * This class is intended to be extended to add specific click actions for different UIs.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-09
 */
public abstract class UiDesigner {

    private static SubclassFinder subclassFinder;
    private static final CommonLib commonLib = CommonLib.getInstance();

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
     * Calls the addClickAction method for all subclasses of the UiDesigner class.
     * This method is useful for triggering UI-related actions when a player clicks on a specific inventory slot.
     *
     * @param inventory The inventory where the click action occurred.
     * @param slot      The slot number that was clicked.
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
     * Abstract method to define actions when a player clicks on a specific slot in the UI.
     * Subclasses should override this method to implement custom click behavior.
     *
     * @param inventory The inventory where the click occurred.
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
     * Sets the border of the given inventory to the specified item stack.
     *
     * @param inventory the inventory to modify
     * @param item      the item stack to set as the border
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
     * Fills all empty non-border slots in the given inventory with the specified item stack.
     *
     * @param inventory the inventory to fill
     * @param item      the item stack to fill the non-border slots with
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
     * Sets the title of the given inventory to the specified title.
     * If the inventory holder is a player, the player's inventory will be closed
     * and a new inventory with the new title will be created and opened for the player.
     * Otherwise, an IllegalArgumentException is thrown because the GUI must be owned by a player.
     *
     * @param inventory the inventory to modify
     * @param title     the new title for the inventory
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
