package tk.airshipcraft.commonlib.particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

/**
 * Utility class for creating and playing particle effects in Minecraft.
 * This class encapsulates the properties of a particle effect and provides a method to display it.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-03-30
 */
public class ParticleEffect {

    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;
    private final float speed;
    private final int particleCount;
    private Particle particle;

    /**
     * Constructs a new ParticleEffect with the specified properties.
     *
     * @param particle      The type of particle for this effect.
     * @param offsetX       The amount to be randomly offset by in the X axis.
     * @param offsetY       The amount to be randomly offset by in the Y axis.
     * @param offsetZ       The amount to be randomly offset by in the Z axis.
     * @param speed         The speed of the particles.
     * @param particleCount The number of particles to display.
     */
    public ParticleEffect(Particle particle, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        this.particle = particle;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
        this.particleCount = particleCount;
    }

    /**
     * @return the type of particle used in this effect
     */
    public Particle getParticle() {
        return particle;
    }

    /**
     * Sets a new particle type for this effect.
     *
     * @param particle The new particle type.
     */
    public void setParticle(Particle particle) {
        this.particle = particle;
    }

    /**
     * @return the amount to be randomly offset by in the X axis
     */
    public float getOffsetX() {
        return offsetX;
    }

    /**
     * @return the amount to be randomly offset by in the Y axis
     */
    public float getOffsetY() {
        return offsetY;
    }

    /**
     * @return the amount to be randomly offset by in the Z axis
     */
    public float getOffsetZ() {
        return offsetZ;
    }

    /**
     * @return the speed of the particles
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @return the amount of particle to display.
     */
    public int getParticleCount() {
        return particleCount;
    }

    /**
     * Displays the particle effect at the specified location.
     * The particles will be spawned in the world of the given location.
     *
     * @param location the location at which to play the particle effect.
     * @throws IllegalArgumentException if the location's world is null.
     */
    public void playEffect(Location location) {
        World world = location.getWorld();
        if (world == null) {
            throw new IllegalArgumentException("Location's world cannot be null when playing a particle effect.");
        }
        world.spawnParticle(particle, location, particleCount, offsetX, offsetY, offsetZ, speed, null);
    }

    /**
     * Checks if two ParticleEffect objects are equal based on their properties.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ParticleEffect that = (ParticleEffect) obj;
        return Float.compare(that.offsetX, offsetX) == 0 &&
                Float.compare(that.offsetY, offsetY) == 0 &&
                Float.compare(that.offsetZ, offsetZ) == 0 &&
                Float.compare(that.speed, speed) == 0 &&
                particleCount == that.particleCount &&
                particle == that.particle;
    }

    /**
     * Creates a copy of this ParticleEffect.
     *
     * @return a new ParticleEffect instance with the same properties.
     */
    public ParticleEffect copy() {
        return new ParticleEffect(particle, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    @Override
    public String toString() {
        return String.format("ParticleEffect{type=%s, offsetX=%.3f, offsetY=%.3f, offsetZ=%.3f, speed=%.3f, particleCount=%d}",
                particle, offsetX, offsetY, offsetZ, speed, particleCount);
    }
}
