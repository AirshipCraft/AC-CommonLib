package tk.airshipcraft.commonlib.utils;

/**
 * This class represents a progression curve for a game where players earn tokens and rank up.
 * The curve is designed to slow down more active players, allowing newer or less active
 * players a better chance to catch up.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-16
 */
public class ProgressionCurve {

    /**
     * Calculates the number of rank-ups required for the next token.
     *
     * @param tokensEarned  The total number of tokens already earned by the player.
     * @param catchUpFactor The catch-up factor that controls the rate of growth in difficulty.
     * @return The number of rank-ups required to earn the next token, rounded up to the nearest whole number.
     */
    public static int calculateRankUpsForNextToken(int tokensEarned, double catchUpFactor) {
        // Apply the progression formula as per the design
        double rankUpsRequired = 1 + (double) tokensEarned / catchUpFactor;

        // Use ceiling to always round up to the nearest whole number
        return (int) Math.ceil(rankUpsRequired);
    }
}
