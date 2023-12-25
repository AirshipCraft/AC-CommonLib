package tk.airshipcraft.commonlib.utils;

import java.util.Objects;

/**
 * A utility class representing a pair of objects.
 *
 * @param <L> The type of the left element.
 * @param <R> The type of the right element.
 * @author eerieXanthic, notzune
 * @version 1.0.0
 * @since 2023-10-11
 */
public class Pair<L, R> {

    private final L left;
    private final R right;

    /**
     * Constructs a new Pair with the specified values.
     *
     * @param left  The left element, must not be null.
     * @param right The right element, must not be null.
     * @throws NullPointerException if either left or right is null.
     */
    public Pair(L left, R right) {
        this.left = Objects.requireNonNull(left, "Left element must not be null");
        this.right = Objects.requireNonNull(right, "Right element must not be null");
    }

    /**
     * Creates a new Pair with the specified values.
     * This method is a shorthand for creating a new Pair instance.
     *
     * @param <L>   The type of the left element.
     * @param <R>   The type of the right element.
     * @param left  The left element, must not be null.
     * @param right The right element, must not be null.
     * @return A new {@code Pair<L, R>} instance.
     * @throws NullPointerException if either left or right is null.
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    /**
     * Returns the left element of the pair.
     *
     * @return The left element.
     */
    public L getLeft() {
        return left;
    }

    /**
     * Returns the right element of the pair.
     *
     * @return The right element.
     */
    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
