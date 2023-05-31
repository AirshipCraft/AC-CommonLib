package tk.airshipcraft.commonlib.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import tk.airshipcraft.commonlib.Events.HologramClickEvent;

import java.util.ArrayList;
import java.util.List;

import static tk.airshipcraft.commonlib.utils.ACRPlugin.getSubclassesOf;

/**

 A utility class for creating and managing holograms in Minecraft using ArmorStands.
 */
public abstract class Hologram implements Listener {

    private static final List<ArmorStand> armorStands = new ArrayList<>();
    private final Location location;
    public static final List<Hologram> hologramInstances = new ArrayList<>();
    private final String[] lines;
/**

 Creates a new Hologram at the given location with the given lines.
 @param location The location of the Hologram.
 @param lines the lines to add to the Hologram.
*/
 public Hologram(Location location, String... lines) {
        this.location = location.clone().add(0, (lines.length - 1) * 0.25, 0);
        this.lines = lines;
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
     * Teleports the Holograms or the armor stands that are an instance of Hologram
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
     *
     * @return all the armor stands that are an instance of Hologram
     */
    public List<ArmorStand> getArmorStands() {
        return armorStands;
    }

    /**
     * Sets the given slot to a given item
     * @param item The desired item to put in a slot
     * @param slot The desired slot to put the item in
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
     * Checks if a given armor stand is associated with a Hologram
     * @param armorStand the desired armor stand to check
     * @return a boolean; if true, it means the given armor stand is associated with a Hologram, otherwise it is not.
     */
    public static boolean isHologram(ArmorStand armorStand) {
        return Hologram.armorStands.contains(armorStand);
    }

    /**
     * Returns an instance of a Hologram if an armor stand is associated with a Hologram
     * @param armorStand the desired armor stand to return an instance of a Hologram from
     * @return an instance of a hologram
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
     * An abstract method used to add functions to a hologram when they are clicked on
     * @param hologram
     */
    protected abstract void addClickAction(Hologram hologram);

    /**
     * calls the addClickAction abstract methods for all the subclasses
     * @param hologram the hologram with the click action
     */
    public static void callClickAction(Hologram hologram) {
        List<Class<? extends Hologram>> subclasses = getSubclassesOf(Hologram.class);
        for (Class<? extends Hologram> subclass : subclasses) {
            try {
                Hologram instance = subclass.getDeclaredConstructor().newInstance();
                instance.addClickAction(hologram);
            } catch (Exception e) {
                // Handle any exceptions thrown when instantiating subclasses or calling addClickAction
                e.printStackTrace();
            }
        }
    }

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

