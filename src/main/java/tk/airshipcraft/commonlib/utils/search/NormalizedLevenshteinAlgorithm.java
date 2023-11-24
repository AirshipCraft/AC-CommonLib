package tk.airshipcraft.commonlib.utils.search;

import org.jetbrains.annotations.NotNull;
import tk.airshipcraft.commonlib.utils.search.StringDistance;

import java.util.Arrays;

/**
 * Implements the normalized Levenshtein distance algorithm, which calculates a similarity score
 * based on the minimum number of single-character edits required to change one byte array into the other.
 * The normalization is performed by dividing the Levenshtein distance by the length of the longest array,
 * producing a score between 0 and 1, where 0 indicates identical arrays and 1 indicates maximum dissimilarity.
 *
 * @version 1.0.0
 * @author notzune
 * @since 2023-06-23
 */
public final class NormalizedLevenshteinAlgorithm implements StringDistance {

    /**
     * Calculates the normalized Levenshtein distance between two byte arrays.
     * This distance reflects the similarity of the arrays, normalized to a value between 0 and 1.
     *
     * @param x The first byte array.
     * @param y The second byte array.
     * @return The normalized Levenshtein distance, a value between 0 and 1.
     */
    @Override
    public double calculate(final byte @NotNull [] x, final byte @NotNull [] y) {
        if (Arrays.equals(x, y)) {
            return 0;
        }

        final int maxLength = Math.max(x.length, y.length);

        if (maxLength == 0) {
            return 0;
        }

        return distance(x, y) / maxLength;
    }

    /**
     * Calculates the raw Levenshtein distance between two byte arrays.
     * The distance is the minimum number of single-byte edits (insertions, deletions, or substitutions)
     * required to change one array into the other.
     *
     * @param x The first byte array.
     * @param y The second byte array.
     * @return The Levenshtein distance as a double.
     */
    private double distance(final byte @NotNull [] x, final byte @NotNull [] y) {
        if (x.length == 0) {
            return y.length;
        }

        if (y.length == 0) {
            return x.length;
        }

        int[] distances0 = new int[y.length + 1];
        int[] distances1 = new int[y.length + 1];
        int[] temp;

        for (int i = 0; i < distances0.length; ++i) {
            distances0[i] = i;
        }

        for (int i = 0; i < x.length; ++i) {
            distances1[0] = i + 1;

            int minDistance1 = distances1[0];

            for (int j = 0; j < y.length; ++j) {
                int cost = 1;

                if (x[i] == y[i]) {
                    cost = 0;
                }

                distances1[j + 1] = Math.min(
                        distances1[j] + 1,
                        Math.min(distances0[j + 1] + 1, distances0[j] + cost)
                );

                minDistance1 = Math.min(minDistance1, distances1[j + 1]);
            }

            if (minDistance1 >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            temp = distances0;
            distances0 = distances1;
            distances1 = temp;
        }

        return distances0[y.length];
    }
}
