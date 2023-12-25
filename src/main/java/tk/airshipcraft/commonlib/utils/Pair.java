package tk.airshipcraft.commonlib.utils;

import java.util.Objects;

/**
 * A utility class representing a pair of objects.
 *
 * @param <L> the type of the left element
 * @param <R> the type of the right element
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
     * @param left  the left element
     * @param right the right element
     * @throws NullPointerException if either left or right is null
     */
    public Pair(L left, R right) {
        if (left == null || right == null) {
            throw new NullPointerException("Both left and right elements must be non-null");
        }

        this.left = left;
        this.right = right;
    }

    /**
     * Creates a new Pair with the specified values.
     * This method is a shorthand for {@code Pair.of(left, right)}.
     * @return new Pair<L, R>(left, right)
     * @throws NullPointerException if either left or right is null
     * @param <L> the type of the left element
     * @param <R> the type of the right element
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    /**
     * Returns the left element of the pair.
     *
     * @return the left element
     */
    public L getLeft() {
        return left;
    }

    /**
     * Returns the right element of the pair.
     *
     * @return the right element
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
