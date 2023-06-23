package tk.airshipcraft.commonlib.utils.search;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import tk.airshipcraft.commonlib.utils.search.impl.DukeJaroWinklerAlgorithm;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Search algorithms.
 */
public enum SearchAlgorithm implements StringDistance {

//    NORMALIZED_LEVENSHTEIN(new NormalizedLevenshteinAlgorithm()),
    DUKE_JARO_WINKLER(new DukeJaroWinklerAlgorithm());

    /**
     * All search algorithms.
     * @see SearchAlgorithm
     */
    @Unmodifiable
    public static final Map<String, SearchAlgorithm> NAMES = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(Enum::name, constant -> constant));

    private final StringDistance distance;

    SearchAlgorithm(@NotNull final StringDistance distance) {
        this.distance = distance;
    }

    @Override
    public double calculate(final byte @NotNull [] x, final byte @NotNull [] y) {
        return -1 * distance.calculate(x, y);
    }
}
