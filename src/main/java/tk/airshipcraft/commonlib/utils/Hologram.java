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

import static tk.airshipcraft.commonlib.CommonLib.getSubclassesOf;

public abstract class Hologram implements Listener {

    private static final List<ArmorStand> armorStands = new ArrayList<>();
    private final Location location;
    private static final List<Hologram> hologramInstances = new ArrayList<>();
    private final String[] lines;

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

    public List<ArmorStand> getArmorStands() {
        return armorStands;
    }

    public void setSlot(ItemStack item, EquipmentSlot slot) {
        for (ArmorStand armorStand : armorStands) {
            armorStand.setItem(slot, item);
        }
    }

    public void show(Player player) {
        for (ArmorStand armorStand : armorStands) {
            player.spawnParticle(org.bukkit.Particle.valueOf("ENTITY_EFFECT"), armorStand.getLocation().add(new Vector(0, 0.075, 0)), 1, 0, 0, 0, 0);
        }
    }

    public void hide(Player player) {
        for (ArmorStand armorStand : armorStands) {
            player.spawnParticle(org.bukkit.Particle.valueOf("ENTITY_EFFECT"), armorStand.getLocation().add(new Vector(0, 0.075, 0)), 1, 0, 0, 0, 0);
        }
    }

    public void remove() {
        for (ArmorStand armorStand : armorStands) {
            armorStand.remove();
        }
    }

    public static boolean isHologram(ArmorStand armorStand) {
        return Hologram.armorStands.contains(armorStand);
    }

    public static Hologram fromArmorStand(ArmorStand armorStand) {
        for (Hologram hologram : hologramInstances) {
            if (armorStands.contains(armorStand)) {
                return hologram;
            }
        }
        return null;
    }

    public abstract void addClickAction(Hologram hologram);

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
                if (hologram != null) {
                    Bukkit.getServer().getPluginManager().callEvent(new HologramClickEvent(hologram, event1.getPlayer()));
                    for (Hologram subclassHologram : hologramInstances) {
                        callClickAction(fromArmorStand(armorStand));
                    }
                }
            }
        }
    }
}

