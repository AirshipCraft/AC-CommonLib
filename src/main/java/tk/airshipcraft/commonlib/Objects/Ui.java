package tk.airshipcraft.commonlib.Objects;


import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class Ui implements Listener {

    private static String inventoryName;
    private Inventory inventory;
    private UUID inventoryId;
    private InventoryType type;
    private InventoryHolder owner;
    private int rows;

    /**
     * @param inventoryName sets the name of the inventory
     * @param owner         who owns the inventory
     * @param type          the type of inventory to open. This is preferred to be a chest.
     */
    public Ui(String inventoryName, @Nullable InventoryHolder owner, InventoryType type) {
        this.inventory = Bukkit.createInventory(owner, type, inventoryName);
        this.type = type;
        Component component = Component.text(inventoryName);
        this.inventory.getType().defaultTitle().append(component);
        this.owner = owner;
        this.rows = this.inventory.getSize() / 9;
        this.inventoryName = inventoryName;
        this.inventoryId = UUID.randomUUID();
    }

    /**
     * @param inventoryName the name of the inventory you want to check
     * @return a boolean if the inventoryName is equal to a Ui name
     */
    public static Boolean isUi(String inventoryName) {
        return Objects.equals(Ui.inventoryName, inventoryName);
    }

    /**
     * @param inventory that you want to check
     * @return a boolean if the inventory is an instance of Ui
     */
    public static Boolean isUi(Inventory inventory) {
        return inventory instanceof Ui;
    }

    /**
     * @return the Ui name
     */
    public String getName() {
        return inventoryName;
    }

    /**
     * @return the Ui's UUID
     */
    public UUID getId() {
        return this.inventoryId;
    }

    /**
     * @param item The desired item to add in the GUI
     * @param slot The slot of the inventory to add the item
     */
    public void addButton(ItemStack item, int slot) {
        this.inventory.setItem(slot, item);
    }

    /**
     * @param items The desired items to add in the GUI
     */
    public void addButton(ItemStack... items) {
        this.inventory.addItem(items);
    }

    /**
     * Sets the name to the current UI
     *
     * @param name the name to set the UI to.
     * @return the new inventory, which contains the old items
     */
    public Inventory setName(String name) {
        Inventory oldInventory = this.inventory;
        ItemStack[] items = oldInventory.getContents();
        Inventory newInventory = Bukkit.createInventory(this.owner, this.type, name);
        newInventory.setContents(items);
        return newInventory;
    }


}
