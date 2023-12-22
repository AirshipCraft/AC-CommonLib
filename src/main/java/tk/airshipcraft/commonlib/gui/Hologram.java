package tk.airshipcraft.commonlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import tk.airshipcraft.commonlib.gui.events.HologramClickEvent;
import tk.airshipcraft.commonlib.utils.classes.SubclassFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hologram created using an {@link ArmorStand} in Minecraft.
 * Holograms are used to display text to players in the game world.
 * This class allows for the creation, manipulation, and interaction of holograms.
 *
 * <p>Subclasses should implement the {@code addClickAction} method to define behavior when a hologram is interacted with.</p>
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public abstract class Hologram implements Listener {

    public static final List<Hologram> hologramInstances = new ArrayList<>();
    private static final List<ArmorStand> armorStands = new ArrayList<>();
    private static SubclassFinder subclassFinder;
    private final Location location;
    private final String[] lines;

    /**
     * Creates a new Hologram at the given location with the given lines.
     *
     * @param location The location of the Hologram.
     * @param lines    the lines to add to the Hologram.
     */
    public Hologram(Location location, String... lines) {
        this.location = location.clone().add(0, (lines.length - 1) * 0.25, 0);
        this.lines = lines;
        subclassFinder = new SubclassFinder(this.getClass());
        for (int i = 0; i < lines.length; i++) {
            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, -i * 0.25, 0), EntityType.ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
            armorStand.setSmall(true);
            armorStand.setCustomName(lines[i]);
            armorStand.setCustomNameVisible(true);
            armorStand.setMarker(true);
            armorStand.setInvisible(true);
            armorStands.add(armorStand);
            hologramInstances.add(this);
        }
    }

    /**
     * Checks if a given armor stand is associated with a Hologram
     *
     * @param armorStand the desired armor stand to check
     * @return a boolean; if true, it means the given armor stand is associated with a Hologram, otherwise it is not.
     */
    public static boolean isHologram(ArmorStand armorStand) {
        return Hologram.armorStands.contains(armorStand);
    }

    /**
     * Retrieves the {@link Hologram} instance corresponding to the given {@link ArmorStand}.
     *
     * @param armorStand The ArmorStand associated with a Hologram.
     * @return The Hologram instance, or null if none found.
     */
    public static Hologram fromArmorStand(ArmorStand armorStand) {
        for (Hologram hologram : hologramInstances) {
            if (armorStands.contains(armorStand)) {
                return hologram;
            }
        }
        return null;
    }

    /**
     * Calls the {@code addClickAction} method for all hologram subclasses.
     *
     * @param hologram The hologram that was clicked.
     */
    public static void callClickAction(Hologram hologram) {
        List<Class<?>> subclasses = subclassFinder.getSubclasses();
        for (Class<?> subclass : subclasses) {
            try {
                Hologram instance = (Hologram) subclass.getDeclaredConstructor().newInstance();
                instance.addClickAction(hologram);
            } catch (Exception e) {
                // Handle any exceptions thrown when instantiating subclasses or calling addClickAction
                e.printStackTrace();
            }
        }
    }

    /**
     * Teleports the Holograms or the armor stands that are an instance of Hologram
     *
     * @param location The desired location to teleport the Hologram to.
     */
    public void teleport(Location location) {
        for (int i = 0; i < armorStands.size(); i++) {
            ArmorStand armorStand = armorStands.get(i);
            armorStand.teleport(location.clone().add(0, -i * 0.25, 0));
        }
        this.location.setWorld(location.getWorld());
        this.location.setX(location.getX());
        this.location.setY(location.getY());
        this.location.setZ(location.getZ());
    }

    /**
     * @return all the armor stands that are an instance of Hologram
     */
    public List<ArmorStand> getArmorStands() {
        return armorStands;
    }

    /**
     * Sets an item in a specified slot for the armor stand associated with this hologram.
     *
     * @param item The item to set.
     * @param slot The equipment slot to place the item in.
     */
    public void setSlot(ItemStack item, EquipmentSlot slot) {
        for (ArmorStand armorStand : armorStands) {
            armorStand.setItem(slot, item);
        }
    }

    /**
     * shows an invisible Hologram
     */
    public void show() {
        for (ArmorStand armorStand : armorStands) {
            armorStand.setInvisible(true);
        }
    }

    /**
     * hides a visible Hologram
     */
    public void hide() {
        for (ArmorStand armorStand : armorStands) {
            armorStand.setInvisible(false);
        }
    }

    /**
     * removes the Hologram.
     */
    public void remove() {
        for (ArmorStand armorStand : armorStands) {
            armorStand.remove();
        }
    }

    /**
     * An abstract method that subclasses must implement to define the behavior when the hologram is clicked.
     *
     * @param hologram The hologram that was clicked.
     */
    protected abstract void addClickAction(Hologram hologram);

    /**
     * Handles click events on the hologram and triggers the appropriate actions.
     *
     * @param event1 The PlayerInteractAtEntityEvent.
     * @param event2 The EntityDamageByEntityEvent.
     */
    @EventHandler
    public void onHologramClick(PlayerInteractAtEntityEvent event1, EntityDamageByEntityEvent event2) {
        if (event1.getRightClicked() instanceof ArmorStand armorStand && event2 instanceof ArmorStand armorStand1) {
            event2.setCancelled(true);
            if (Hologram.isHologram(armorStand) && Hologram.isHologram(armorStand1)) {
                Hologram hologram = Hologram.fromArmorStand(armorStand);
                if (hologram != null && armorStand == armorStand1) {
                    Bukkit.getServer().getPluginManager().callEvent(new HologramClickEvent(hologram, event1.getPlayer()));
                }
            }
        }
    }
}
