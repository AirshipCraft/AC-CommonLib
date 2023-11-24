package tk.airshipcraft.commonlib.utils.search;

import org.jetbrains.annotations.NotNull;

/**
 * An interface for string distance algorithms that calculate the difference between two byte arrays.
 * Implementations of this interface can compute distances or similarities between sequences of bytes,
 * typically representing text data. This measure can be used in search algorithms, spell checking,
 * text analysis, and other areas where quantifying the divergence between strings is required.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-06-23
 */
public interface StringDistance {

    /**
     * Calculates the distance or similarity between two sequences of bytes. The interpretation of the
     * returned value depends on the specific algorithm implemented. For some algorithms, a lower value
     * indicates greater similarity, while for others, a higher value may represent a closer match.
     *
     * @param x The first byte array to be compared.
     * @param y The second byte array to be compared.
     * @return A double value representing the calculated distance or similarity between the two arrays.
     */
    double calculate(final byte @NotNull [] x, final byte @NotNull [] y);
}
