package tk.airshipcraft.commonlib.utils.search;

import org.jetbrains.annotations.NotNull;
import tk.airshipcraft.commonlib.utils.search.StringDistance;

import java.util.Arrays;

/**
 * <p>This class provides a highly optimized implementation of the Jaro-Winkler similarity algorithm,
 * which is a measure of similarity between two byte arrays (and by extension, two strings). The
 * main optimizations over standard implementations include calculating all metrics within a single
 * loop and using byte arrays instead of Strings for performance gains.</p>
 *
 * <p>Unlike some implementations, it doesn't perform a common character check because it tends to
 * slow down the algorithm without significant accuracy benefit for the intended use-cases.</p>
 *
 * <p>This implementation is adapted from Lars Marius Garshol's version, and it is designed to be
 * used in high-performance scenarios where text similarity needs to be computed rapidly and at scale.</p>
 *
 * @see <a href="https://github.com/larsga/Duke/blob/master/duke-core/src/main/java/no/priv/garshol/duke/comparators/JaroWinkler.java">
 * Lars Marius Garshol's original implementation</a>
 *
 * @version 1.0.0
 * @since 2023-04-11
 */
public final class DukeJaroWinklerAlgorithm implements StringDistance {
    /**
     * <p>Computes the Jaro-Winkler similarity score between two byte arrays. The score is symmetrical
     * and gives a value between 0 and 1, where 1 means an exact match and 0 means no similarity.</p>
     *
     * <p>The algorithm considers the number of matching characters and transpositions, adjusting for
     * common prefixes up to a maximum of 4 characters.</p>
     *
     * @param x The first byte array to compare.
     * @param y The second byte array to compare.
     * @return A double value representing the similarity score between the two byte arrays.
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