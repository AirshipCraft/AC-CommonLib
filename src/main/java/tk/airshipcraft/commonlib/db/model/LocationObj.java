package tk.airshipcraft.commonlib.db.model;

import java.util.UUID;

/**
 * <p>Represents a location in the world.</p>
 * <p>Locations are stored in the database and are used to store the location of
 * a block or entity.</p>
 * <p>Locations are identified by a unique identifier, which is generated when
 * the location is created. This identifier is used to identify the location
 * when it is stored in the database.</p>
 * <p>Locations are stored in the database in the following format:</p>
 * <pre>
 *     CREATE TABLE locations (
 *     id UUID PRIMARY KEY,
 *     world_name VARCHAR(255) NOT NULL,
 *     x DOUBLE PRECISION NOT NULL,
 *     y DOUBLE PRECISION NOT NULL,
 *     z DOUBLE PRECISION NOT NULL,
 *     pitch FLOAT,
 *     yaw FLOAT
 * );
 * </pre>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-06
 */
public class LocationObj {

    private UUID id; // Unique identifier for the location record
    private String worldName; // Name of the world
    private double x; // X-coordinate
    private double y; // Y-coordinate
    private double z; // Z-coordinate
    private float pitch; // Pitch (optional)
    private float yaw; // Yaw (optional)

    /**
     * Default constructor.
     *
     * @see LocationObj#LocationObj(UUID, String, double, double, double)
     * @see LocationObj#LocationObj(UUID, String, double, double, double, float, float)
     */
    public LocationObj() {
    }

    /**
     * Constructs a new LocationObj with no pitch or yaw.
     *
     * @param id The unique identifier for the location.
     * @param worldName The name of the world.
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     * @param z The Z-coordinate.
     */
    public LocationObj(UUID id, String worldName, double x, double y, double z) {
        this.id = id;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a new LocationObj with specified details.
     *
     * @param id The unique identifier for the location.
     * @param worldName The name of the world.
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     * @param z The Z-coordinate.
     * @param pitch The pitch.
     * @param yaw The yaw.
     */
    public LocationObj(UUID id, String worldName, double x, double y, double z, float pitch, float yaw) {
        this.id = id;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Gets the unique identifier for the location.
     *
     * @return The unique identifier for the location.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the location.
     *
     * @param id The unique identifier for the location.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the name of the world.
     *
     * @return The name of the world.
     */
    public String getWorldName() {
        return worldName;
    }

    /**
     * Sets the name of the world.
     *
     * @param worldName The name of the world.
     */
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    /**
     * Gets the X-coordinate.
     *
     * @return The X-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the X-coordinate.
     *
     * @param x The X-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the Y-coordinate.
     *
     * @return The Y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate.
     *
     * @param y The Y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the Z-coordinate.
     *
     * @return The Z-coordinate.
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the Z-coordinate.
     *
     * @param z The Z-coordinate.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Gets the pitch.
     *
     * @return The pitch.
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch.
     *
     * @param pitch The pitch.
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets the yaw.
     *
     * @return The yaw.
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Sets the yaw.
     *
     * @param yaw The yaw.
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * Gets the hash code for this object.
     *
     * @return The hash code for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        temp = Float.floatToIntBits(pitch);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        temp = Float.floatToIntBits(yaw);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        result = prime * result + ((worldName == null) ? 0 : worldName.hashCode());

        return result;
    }

    /**
     * Checks if this object is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocationObj) {
            LocationObj other = (LocationObj) obj;

            if (id != null && other.id != null) {
                return id.equals(other.id);
            }

            return worldName.equals(other.worldName) && x == other.x && y == other.y && z == other.z && pitch == other.pitch && yaw == other.yaw;
        }
        return false;
    }

    /**
     * Gets a string representation of this object.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return "LocationObj{" +
                "id=" + id +
                ", worldName=" + worldName +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                "}";
    }
}
