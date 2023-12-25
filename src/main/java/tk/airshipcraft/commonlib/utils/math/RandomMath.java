package tk.airshipcraft.commonlib.utils.math;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for generating random numbers within specified ranges.
 *
 * @author eerieXanthic, notzune
 * @version 1.1.0
 * @since 2023-10-11
 */
public class RandomMath {

    // Static class should not be instantiable
    private RandomMath() {
        throw new UnsupportedOperationException("RandomMath is a utility class and cannot be instantiated");
    }

    /**
     * Generates a random double within the specified range [min, max).
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return a random double within the specified range
     * @throws IllegalArgumentException if min >= max
     */
    public static double nextDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    /**
     * Generates a random integer within the specified range [min, max].
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return a random integer within the specified range
     * @throws IllegalArgumentException if min > max
     */
    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be less than or equal to max");
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Generates a random long within the specified range [min, max].
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return a random long within the specified range
     * @throws IllegalArgumentException if min > max
     */
    public static long nextLong(long min, long max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be less than or equal to max");
        }
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    /**
     * Generates a random float within the specified range [min, max).
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return a random float within the specified range
     * @throws IllegalArgumentException if min >= max
     */
    public static float nextFloat(float min, float max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }
        return ThreadLocalRandom.current().nextFloat() * (max - min) + min;
    }

    /**
     * Generates a random double from a Gaussian (normal) distribution with the given mean and standard deviation.
     *
     * @param mean         the mean of the distribution
     * @param stdDeviation the standard deviation of the distribution
     * @return a random double from the Gaussian distribution
     */
    public static double nextGaussian(double mean, double stdDeviation) {
        return mean + stdDeviation * ThreadLocalRandom.current().nextGaussian();
    }

    /**
     * Generates a random boolean value.
     *
     * @return a random boolean value
     */
    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
