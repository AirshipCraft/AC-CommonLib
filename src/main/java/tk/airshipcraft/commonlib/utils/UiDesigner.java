package tk.airshipcraft.commonlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.Objects.Ui;

import java.util.List;

public abstract class UiDesigner {
    
    private static SubclassFinder subclassFinder;
    private static CommonLib commonLib = CommonLib.getInstance();

    public UiDesigner() {
        this.subclassFinder = new SubclassFinder(this.getClass());
    }

    /**
     * Create a GUI inventory with a specific title and inventory type.
     *
     * @param title the title of the inventory
     * @param type  The GUI host inventory type
     * @return the created Ui
     */
    public static Ui createGUI(String title, InventoryType type) {
        return new Ui(title, null, type);
    }

    /**
     * Calls the addClickAction method for all subclasses of the UiDesigner class with the provided inventory and slot parameters.
     *
     * @param inventory the inventory to pass to the addClickAction method of each subclass
     * @param slot      the slot to pass to the addClickAction method of each subclass
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
                // Handle any exceptions thrown when instantiating subclasses or calling addClickAction
                commonLib.logDebug("No click actions found for " + subclass);
            }
        }
    }

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

    /**
     * Add an action to be executed when a player clicks on a specific slot in the inventory.
     *
     * @param inventory the inventory to modify
     * @param slot      the slot to add the action to
     */
    public abstract void addClickAction(Inventory inventory, int slot);
}


