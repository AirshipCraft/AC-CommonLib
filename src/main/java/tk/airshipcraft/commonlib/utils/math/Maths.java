package tk.airshipcraft.commonlib.utils.math;

/**
 * A utility class offering a collection of mathematical functions commonly used in game development.
 * It includes methods for clamping values, normalizing and linearly interpolating between ranges,
 * and specialized rounding. These utilities are helpful for various gameplay mechanics, such as
 * controlling player movement, managing game object properties, and handling game state transitions.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-04-03
 */
public class Maths {

    /**
     * Limits a value between and including two values.
     *
     * @param value The value to clamp.
     * @param min   The minimum value.
     * @param max   The maximum value.
     * @return The clamped value.
     */
    public static int clamp(final int value, final int min, final int max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * Limits a value between and including two values.
     *
     * @param value The value to clamp.
     * @param min   The minimum value.
     * @param max   The maximum value.
     * @return The clamped value.
     */
    public static long clamp(final long value, final long min, final long max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * Limits a value between and including two values.
     *
     * @param value The value to clamp.
     * @param min   The minimum value.
     * @param max   The maximum value.
     * @return The clamped value.
     */
    public static float clamp(final float value, final float min, final float max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * Limits a value between and including two values.
     *
     * @param value The value to clamp.
     * @param min   The minimum value.
     * @param max   The maximum value.
     * @return The clamped value.
     */
    public static double clamp(final double value, final double min, final double max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * Normalizes a value within a minimum and maximum range to a 0-1 range.
     * The normalization process translates the range of possible input values into a standard
     * 0 to 1 scale, where 0 represents the minimum and 1 represents the maximum.
     *
     * @param value The value to normalize.
     * @param min   The lower limit of the original range.
     * @param max   The upper limit of the original range.
     * @return The normalized value, proportionally adjusted between 0 and 1.
     */
    public static int norm(final int value, final int min, final int max) {
        return (value - min) / (max - min);
    }

    /**
     * Normalizes a value within a minimum and maximum range to a 0-1 range.
     * The normalization process translates the range of possible input values into a standard
     * 0 to 1 scale, where 0 represents the minimum and 1 represents the maximum.
     *
     * @param value The value to normalize.
     * @param min   The lower limit of the original range.
     * @param max   The upper limit of the original range.
     * @return The normalized value, proportionally adjusted between 0 and 1.
     */
    public static long norm(final long value, final long min, final long max) {
        return (value - min) / (max - min);
    }

    /**
     * Normalizes a value within a minimum and maximum range to a 0-1 range.
     * The normalization process translates the range of possible input values into a standard
     * 0 to 1 scale, where 0 represents the minimum and 1 represents the maximum.
     *
     * @param value The value to normalize.
     * @param min   The lower limit of the original range.
     * @param max   The upper limit of the original range.
     * @return The normalized value, proportionally adjusted between 0 and 1.
     */
    public static float norm(final float value, final float min, final float max) {
        return (value - min) / (max - min);
    }

    /**
     * Normalizes a value within a minimum and maximum range to a 0-1 range.
     * The normalization process translates the range of possible input values into a standard
     * 0 to 1 scale, where 0 represents the minimum and 1 represents the maximum.
     *
     * @param value The value to normalize.
     * @param min   The lower limit of the original range.
     * @param max   The upper limit of the original range.
     * @return The normalized value, proportionally adjusted between 0 and 1.
     */
    public static double norm(final double value, final double min, final double max) {
        return (value - min) / (max - min);
    }

    /**
     * Applies a normalized value (previously obtained through normalization) to a new range.
     * This process is known as linear interpolation or 'lerping'. It's useful for translating
     * a normalized value back into a range with different limits, often for animations, transitions,
     * or other gradual changes over time in game properties.
     *
     * @param norm The normalized value, typically between 0 and 1.
     * @param min  The lower limit of the new range.
     * @param max  The upper limit of the new range.
     * @return The linearly interpolated value within the new range.
     */
    public static int lerp(final int norm, final int min, final int max) {
        return (max - min) * norm + min;
    }

    /**
     * Applies a normalized value (previously obtained through normalization) to a new range.
     * This process is known as linear interpolation or 'lerping'. It's useful for translating
     * a normalized value back into a range with different limits, often for animations, transitions,
     * or other gradual changes over time in game properties.
     *
     * @param norm The normalized value, typically between 0 and 1.
     * @param min  The lower limit of the new range.
     * @param max  The upper limit of the new range.
     * @return The linearly interpolated value within the new range.
     */
    public static long lerp(final long norm, final long min, final long max) {
        return (max - min) * norm + min;
    }

    /**
     * Applies a normalized value (previously obtained through normalization) to a new range.
     * This process is known as linear interpolation or 'lerping'. It's useful for translating
     * a normalized value back into a range with different limits, often for animations, transitions,
     * or other gradual changes over time in game properties.
     *
     * @param norm The normalized value, typically between 0 and 1.
     * @param min  The lower limit of the new range.
     * @param max  The upper limit of the new range.
     * @return The linearly interpolated value within the new range.
     */
    public static float lerp(final float norm, final float min, final float max) {
        return (max - min) * norm + min;
    }

    /**
     * Applies a normalized value (previously obtained through normalization) to a new range.
     * This process is known as linear interpolation or 'lerping'. It's useful for translating
     * a normalized value back into a range with different limits, often for animations, transitions,
     * or other gradual changes over time in game properties.
     *
     * @param norm The normalized value, typically between 0 and 1.
     * @param min  The lower limit of the new range.
     * @param max  The upper limit of the new range.
     * @return The linearly interpolated value within the new range.
     */
    public static double lerp(final double norm, final double min, final double max) {
        return (max - min) * norm + min;
    }

    /**
     * Rounds a floating-point number <i>away</i> from zero to the nearest integer.
     * This method of rounding is commonly used when a consistent behavior is needed for both positive
     * and negative numbers, ensuring that they are always rounded to a larger absolute value.
     *
     * <ul>
     *     <li>1.5 → 2</li>
     *     <li>-1.5 → -2</li>
     * </ul>
     *
     * @param value The floating-point value to round.
     * @return The rounded value, always away from zero.
     */
    public static double roundOut(final double value) {
        return value < 0 ? Math.floor(value) : Math.ceil(value);
    }

    /**
     * Rounds a floating-point number <i>towards</i> zero to the nearest integer.
     * This approach to rounding is useful when needing to truncate a decimal number to its integer component,
     * disregarding the fractional part, which can be necessary for certain types of game logic that rely on discrete values.
     *
     * <ul>
     *     <li>1.5 → 1</li>
     *     <li>-1.5 → -1</li>
     * </ul>
     *
     * @param value The floating-point value to round.
     * @return The rounded value, always towards zero.
     */
    public static double roundIn(final double value) {
        return value < 0 ? Math.ceil(value) : Math.floor(value);
    }
}
