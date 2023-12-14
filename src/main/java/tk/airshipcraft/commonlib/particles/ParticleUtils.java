package tk.airshipcraft.commonlib.particles;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

/**
 * Utility class for spawning particles.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-10-28
 */
public class ParticleUtils {

    /**
     * Spawns bullet particles at the specified location.
     *
     * @param location   The location to spawn the bullet particles.
     * @param incendiary Whether the bullet is incendiary.
     */
    public static void spawnBulletParticles(Location location, boolean incendiary) {
        World world = location.getWorld();
        if (world != null) {
            Snowball snowball = (Snowball) world.spawnEntity(location, EntityType.SNOWBALL);
            if (incendiary) {
                // This will cause the snowball to produce fire particles
                snowball.setFireTicks(160);  // 8 seconds of fire
            }
        }
    }

    /**
     * Spawns rocket particles at the specified location.
     *
     * @param location The location to spawn the rocket particles.
     */
    public static void spawnRocketParticles(Location location) {
        World world = location.getWorld();
        if (world != null) {
            Firework firework = (Firework) world.spawnEntity(location, EntityType.FIREWORK);
            FireworkMeta meta = firework.getFireworkMeta();
            // Customize the firework meta to your liking
            meta.setPower(1);
            meta.addEffect(
                    FireworkEffect.builder()
                            .withColor(Color.RED)
                            .withFade(Color.ORANGE)
                            .with(FireworkEffect.Type.BALL)
                            .build()
            );
            firework.setFireworkMeta(meta);
            // The firework will explode immediately, creating a rocket-like effect
            firework.detonate();
        }
    }

    /**
     * Spawns energy beam particles along a line between two points.
     *
     * @param start The starting point of the beam.
     * @param end   The ending point of the beam.
     */
    public static void spawnEnergyBeamParticles(Location start, Location end) {
        World world = start.getWorld();
        if (world != null && start.getWorld().equals(end.getWorld())) {
            Vector direction = end.toVector().subtract(start.toVector()).normalize();
            double distance = start.distance(end);
            for (double d = 0; d <= distance; d += 0.5) {  // Adjust 0.5 to change the spacing between particles
                Location point = start.clone().add(direction.clone().multiply(d));
                world.spawnParticle(Particle.END_ROD, point, 0);  // END_ROD particle looks like a small beam of light
            }
        }
    }

    /**
     * Plays a sound effect at the specified location.
     *
     * @param location The location to play the sound effect.
     */
    public static void playFireworkSound(Location location) {
        World world = location.getWorld();
        if (world != null) {
            world.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.0f);
        }
    }
}
