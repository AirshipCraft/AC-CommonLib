package tk.airshipcraft.commonlib.utils.search;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enumerates available string distance algorithms which can be used to compare similarity or dissimilarity
 * between two strings. Each algorithm follows the {@link StringDistance} interface to provide a standardized
 * approach for calculation.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-06-23
 */
public enum SearchAlgorithm implements StringDistance {

    NORMALIZED_LEVENSHTEIN(new NormalizedLevenshteinAlgorithm()),
    DUKE_JARO_WINKLER(new DukeJaroWinklerAlgorithm());

    /**
     * A map of search algorithm names to their corresponding {@link SearchAlgorithm} enum constants.
     * This allows for easy lookup and usage of search algorithms by their name.
     */
    @Unmodifiable
    public static final Map<String, SearchAlgorithm> NAMES = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(Enum::name, constant -> constant));

    private final StringDistance distance;

    /**
     * Constructs a {@link SearchAlgorithm} instance with a specific string distance calculation strategy.
     *
     * @param distance The string distance algorithm to be used for this enum constant.
     */
    SearchAlgorithm(@NotNull final StringDistance distance) {
        this.distance = distance;
    }

    /**
     * Calculates the string distance between two sequences of bytes using the associated algorithm.
     * The result is negated to transform a distance measure into a similarity score where a higher value
     * means greater similarity.
     *
     * @param x The first byte array.
     * @param y The second byte array.
     * @return The negated string distance, interpreted as a similarity score.
     */
    @Override
    public double calculate(final byte @NotNull [] x, final byte @NotNull [] y) {
        return -1 * distance.calculate(x, y);
    }
}
