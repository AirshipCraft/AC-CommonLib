package tk.airshipcraft.commonlib.utils.math;

import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for vector mathematical operations.
 * Provides methods for vector rotation, randomization, scaling, and more.
 *
 * @author eerieXanthic, notzune
 * @version 1.1.0
 * @since 2023-10-11
 */
public class VectorMath {

    /**
     * Rotates a vector counterclockwise around a specified axis by a specified angle.
     *
     * @param vec   The vector to rotate. Must not be null.
     * @param axis  The axis of rotation. Must not be null.
     * @param theta The angle of rotation in radians.
     * @return The rotated vector.
     * @throws IllegalArgumentException if vec or axis is null.
     */
    public static Vector rotateVectorCC(Vector vec, Vector axis, double theta) {
        validateNonNull(vec, "Vector to rotate must not be null");
        validateNonNull(axis, "Axis of rotation must not be null");

        double x, y, z;
        double u, v, w;
        x = vec.getX();
        y = vec.getY();
        z = vec.getZ();
        u = axis.getX();
        v = axis.getY();
        w = axis.getZ();
        double v1 = u * x + v * y + w * z;
        double xPrime = u * v1 * (1d - Math.cos(theta))
                + x * Math.cos(theta)
                + (-w * y + v * z) * Math.sin(theta);
        double yPrime = v * v1 * (1d - Math.cos(theta))
                + y * Math.cos(theta)
                + (w * x - u * z) * Math.sin(theta);
        double zPrime = w * v1 * (1d - Math.cos(theta))
                + z * Math.cos(theta)
                + (-v * x + u * y) * Math.sin(theta);

        return new Vector(xPrime, yPrime, zPrime);
    }

    /**
     * Adds a random amount to each component of a vector within a specified range.
     *
     * @param v           The vector to randomize. Must not be null.
     * @param instability The maximum amount of randomization.
     * @return The randomized vector.
     * @throws IllegalArgumentException if v is null.
     */
    public static Vector instabilizeVector(Vector v, double instability) {
        validateNonNull(v, "Vector to randomize must not be null");

        return new Vector(
                v.getX() + randomDouble(-instability, instability),
                v.getY() + randomDouble(-instability, instability),
                v.getZ() + randomDouble(-instability, instability));
    }

    /**
     * Scales a vector by a specified factor.
     *
     * @param v      The vector to scale.
     * @param factor The scaling factor.
     * @return The scaled vector.
     */
    public static Vector scaleVector(Vector v, double factor) {
        return v.clone().multiply(factor);
    }

    /**
     * Normalizes a vector to have a length of 1 while maintaining its direction.
     *
     * @param v The vector to normalize.
     * @return The normalized vector.
     */
    public static Vector normalizeVector(Vector v) {
        return v.clone().normalize();
    }

    /**
     * Generates a random double value within a specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random double value within the specified range.
     */
    private static double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * Validates that the specified object is non-null.
     *
     * @param obj     The object to validate.
     * @param message The exception message to use if the object is null.
     * @throws IllegalArgumentException if obj is null.
     */
    private static void validateNonNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
