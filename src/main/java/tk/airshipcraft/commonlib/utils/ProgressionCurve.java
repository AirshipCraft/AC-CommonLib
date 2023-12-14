package tk.airshipcraft.commonlib.utils;


/**
 * The {@code ProgressionCurve} class provides a calculation model to determine progression
 * requirements within a game. It is designed to balance the progression of different players,
 * ensuring fair competition by slowing down the advancement of more active players and
 * allowing less active players to catch up.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-16
 */
public class ProgressionCurve {

    /**
     * Calculates the required number of rank-ups for a player to earn their next token.
     * This method applies a catch-up mechanic, where the difficulty of earning additional tokens
     * increases as players progress, but does so at a rate that allows less active players
     * to remain competitive.
     *
     * @param tokensEarned  The total number of tokens the player has already earned.
     * @param catchUpFactor A coefficient dictating the progression curve's slope; a higher value
     *                      results in a gentler slope, offering a more lenient catch-up mechanism.
     * @return The number of rank-ups the player must achieve to obtain the next token. This value
     * is always a whole number, as partial rank-ups are not considered in the game's design.
     */
    public static int calculateRankUpsForNextToken(int tokensEarned, double catchUpFactor) {
        // Apply the progression formula as per the design
        double rankUpsRequired = 1 + (double) tokensEarned / catchUpFactor;

        // Use ceiling to always round up to the nearest whole number
        return (int) Math.ceil(rankUpsRequired);
    }
}
