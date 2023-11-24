package tk.airshipcraft.commonlib.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Provides utility methods for working with time values in Java.
 * This includes methods for getting the current time in various formats
 * and converting between different units of time.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-02
 */
public final class Time {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Time() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Retrieves the current Unix time in milliseconds.
     *
     * @return The current Unix time in milliseconds since the epoch.
     */
    public static long nowMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Retrieves the current Unix time in seconds.
     *
     * @return The current Unix time in seconds since the epoch.
     */
    public static long nowSeconds() {
        return nowMillis() / 1000L;
    }

    /**
     * Retrieves the current time as an {@link Instant}.
     *
     * @return The current time as an {@link Instant}.
     */
    public static Instant now() {
        return Instant.ofEpochMilli(System.currentTimeMillis());
    }

    /**
     * Calculates the absolute duration between the current time and another specified instant.
     *
     * @param other The {@link Instant} to compare with the current time.
     * @return The absolute {@link Duration} between now and the provided instant.
     */
    public static Duration diffToNow(Instant other) {
        return Duration.between(now(), other).abs();
    }

    /**
     * Creates a {@link Duration} based on a specified {@link TimeUnit} and amount.
     *
     * @param unit   The {@link TimeUnit} representing the unit of the duration.
     * @param amount The amount of the specified unit.
     * @return A {@link Duration} representing the specified amount of the specified time unit.
     * @throws NullPointerException if the specified unit is null.
     * @throws AssertionError       if the time unit is unknown or unsupported.
     */
    public static Duration duration(TimeUnit unit, long amount) {
        Objects.requireNonNull(unit, "unit");
        switch (unit) {
            case NANOSECONDS:
                return Duration.ofNanos(amount);
            case MICROSECONDS:
                return Duration.ofNanos(TimeUnit.MICROSECONDS.toNanos(amount));
            case MILLISECONDS:
                return Duration.ofMillis(amount);
            case SECONDS:
                return Duration.ofSeconds(amount);
            case MINUTES:
                return Duration.ofMinutes(amount);
            case HOURS:
                return Duration.ofHours(amount);
            case DAYS:
                return Duration.ofDays(amount);
            default:
                throw new AssertionError("unknown time unit: " + unit);

        }
    }
}
