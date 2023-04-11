package tk.airshipcraft.commonlib.utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private final List<ArmorStand> armorStands = new ArrayList<>();
    private final Location location;
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

    public void setSlot(ItemStack item, EquipmentSlot slot) {
        for (ArmorStand armorStand : armorStands){
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
}
