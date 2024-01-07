package tk.airshipcraft.commonlib.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Provides utility methods for null-safe operations, inspired by JavaScript's nullish coalescing and optional chaining.
 * This class helps to reduce boilerplate code associated with null checks and default value assignments.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Nullish_coalescing_operator"> Nullish Coalescing Operator</a>
 */
public final class NullCoalescing {

    /**
     * Returns the first non-null value from the provided items.
     *
     * @param <T>   The type of the values being checked.
     * @param items The array of values to check for non-nullity.
     * @return The first non-null value, or null if all are null.
     */
    @SafeVarargs
    public static <T> T coalesce(T... items) {
        for (T item : items) {
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    /**
     * Executes a function and returns its value if not null; otherwise, returns null.
     * This is similar to JavaScript's optional chaining.
     *
     * @param <T>       The type of the value expected from the function.
     * @param statement The function to execute.
     * @return The value from the function if not null, or null if the function or its result is null.
     */
    public static <T> T chain(NullChecker<T> statement) {
        return chain(statement, null);
    }

    /**
     * Executes a function and returns its value if not null; otherwise, returns a fallback value.
     *
     * @param <T>       The type of the value expected from the function.
     * @param statement The function to execute.
     * @param fallback  The fallback value to return if the function or its result is null.
     * @return The value from the function if not null, or the fallback value.
     */
    public static <T> T chain(NullChecker<T> statement, T fallback) {
        if (statement == null) {
            return fallback;
        }
        try {
            return statement.get();
        } catch (Exception ignored) {
            return fallback;
        }
    }

    /**
     * Executes a consumer if the provided value is not null.
     *
     * @param <T>     The type of the value.
     * @param value   The value to check.
     * @param handler The consumer to execute if the value is not null.
     */
    public static <T> void exists(T value, Consumer<T> handler) {
        if (value != null && handler != null) {
            handler.accept(value);
        }
    }

    /**
     * If the provided value is null, uses the supplier to generate a new value.
     *
     * @param <T>     The type of the value.
     * @param value   The value to check.
     * @param handler The supplier to use if the value is null.
     * @return The original value if not null; otherwise, the value from the supplier.
     */
    public static <T> T notExists(T value, Supplier<T> handler) {
        if (value == null && handler != null) {
            value = handler.get();
        }
        return value;
    }

    /**
     * Attempts to cast an object to a specified type and returns null if the cast is not possible.
     *
     * @param <T>   The target type for the cast.
     * @param clazz The class object of the target type.
     * @param value The object to cast.
     * @return The cast object if successful, or null if the cast is not possible.
     */
    @SuppressWarnings("unchecked")
    public static <T> T castOrNull(Class<T> clazz, Object value) {
        if (clazz == null || value == null) {
            return null;
        }
        if (clazz.isAssignableFrom(value.getClass())) {
            return (T) value;
        }
        return null;
    }

    /**
     * Compares two objects for equality, handling nulls safely.
     *
     * @param former The first object to compare.
     * @param latter The second object to compare.
     * @return True if both objects are equal; false otherwise.
     */
    public static boolean equals(Object former, Object latter) {
        if (former == latter) {
            return true;
        }
        if (former != null && former.equals(latter)) {
            return true;
        }
        if (latter != null && latter.equals(former)) {
            return true;
        }
        return false;
    }

    /**
     * Compares two objects for equality, where nulls are considered unequal.
     *
     * @param former The first object to compare.
     * @param latter The second object to compare.
     * @return True if both objects are non-null and equal; false otherwise.
     */
    public static boolean equalsNotNull(Object former, Object latter) {
        if (former == null || latter == null) {
            return false;
        }
        if (former.equals(latter)) {
            return true;
        }
        if (latter.equals(former)) {
            return true;
        }
        return false;
    }

    /**
     * Functional interface for null-check operations.
     *
     * @param <T> The type of the value returned by the operation.
     */
    @FunctionalInterface
    public interface NullChecker<T> {

        /**
         * Executes the operation and returns its result.
         *
         * @return The result of the operation.
         * @throws Exception If an error occurs during the operation.
         */
        T get() throws Exception;
    }
}
