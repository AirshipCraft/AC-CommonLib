package tk.airshipcraft.commonlib.utils.search.impl;

import org.jetbrains.annotations.NotNull;
import tk.airshipcraft.commonlib.utils.search.StringDistance;

import java.util.Arrays;

/**
 * Highly optimized Jaro-Winkler similarity algorithm implementation.
 * <p></p>
 * Main difference between this and most other implementations is that
 * this counts all the metrics used to calculate the total similarity
 * measurement, in the same loop.
 * <p></p>
 * Additionally, we use byte arrays directly here instead of strings.
 * I also removed the common character checking, because it slows the
 * algorithm down drastically.
 * <p></p>
 * Originally from:
 *
 * @author Lars Marius Garshol
 * @link <a href="https://github.com/larsga/Duke/blob/master/duke-core/src/main/java/no/priv/garshol/duke/comparators/JaroWinkler.java">Lars Marius Garshol</a>
 */
public final class DukeJaroWinklerAlgorithm implements StringDistance {
    /**
     * Calculates the Jaro-Winkler similarity between two strings.
     * <p></p>
     * This implementation uses the same algorithm as in the original
     * <a href="http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance"> Wikipedia article</a>.Jaro-Winkler distance</a> implementation.
     *
     * @param x the first string
     * @param y the second string
     * @return the Jaro-Winkler similarity between the two strings
     */
    @Override
    public double calculate(final byte @NotNull [] x, final byte @NotNull [] y) {
        if (Arrays.equals(x, y)) {
            return 0;
        }

        final byte[] min;
        final byte[] max;

        if (x.length > y.length) {
            min = y;
            max = x;
        } else {
            min = x;
            max = y;
        }

        final int minLength = min.length;
        final int maxLength = max.length;

        final int maxDistance = maxLength / 2;

        int commonCharacters = 0;
        int transpositions = 0;
        int previousPosition = -1;
        for (int minIndex = 0; minIndex < minLength; ++minIndex) {
            final byte character = min[minIndex];

            for (int maxIndex = Math.max(0, minIndex - maxDistance);
                 maxIndex < Math.min(maxLength, minIndex + maxDistance);
                 ++maxIndex) {
                if (character == max[maxIndex]) {
                    ++commonCharacters;

                    if (previousPosition != -1 && maxIndex < previousPosition) {
                        ++transpositions;
                    }

                    previousPosition = maxIndex;
                    break;
                }
            }
        }

        if (commonCharacters == 0) {
            return 0;
        }

        double score = ((commonCharacters / (double) minLength) +
                (commonCharacters / (double) maxLength) +
                ((commonCharacters - transpositions) / (double) commonCharacters)) / 3D;
        final int last = Math.min(4, minLength);

        int prefix = 0;
        while (prefix < last && min[prefix] == max[prefix]) {
            ++prefix;
        }

        score = score + ((prefix * (1 - score)) / 10);
        return score;
    }
}