package tk.airshipcraft.commonlib.db.model;

import java.util.UUID;

/**
 * Represents the authentication data for a player, storing information related to the linkage of Minecraft accounts to Discord accounts.
 * This class is used to verify if a player has successfully linked their accounts.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-05
 */
public class AuthData {

    private UUID id;
    private String IGN;
    private String username;
    private UUID token;
    private boolean verified;

    /**
     * Constructs an AuthData object with the specified details.
     *
     * @param uuid     The UUID of the player.
     * @param IGN      The in-game name of the player.
     * @param username The username associated with the Discord account.
     * @param token    The UUID token used for authentication.
     * @param verified A boolean indicating if the player's account is verified.
     */
    public AuthData(UUID uuid, String IGN, String username, UUID token, boolean verified) {
        this.id = uuid;
        this.IGN = IGN;
        this.username = username;
        this.token = token;
        this.verified = verified;
    }

    /**
     * Returns the UUID of the player.
     *
     * @return A UUID representing the player's identity.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the UUID of the player.
     *
     * @param id The UUID to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the in-game name of the player.
     *
     * @return A String representing the player's in-game name.
     */
    public String getIGN() {
        return IGN;
    }

    /**
     * Sets the in-game name of the player.
     *
     * @param IGN The in-game name to set.
     */
    public void setIGN(String IGN) {
        this.IGN = IGN;
    }

    /**
     * Returns the username associated with the player's Discord account.
     *
     * @return A String representing the Discord username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the player's Discord account.
     *
     * @param username The Discord username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the authentication token associated with the player.
     *
     * @return A UUID token used for authentication purposes.
     */
    public UUID getToken() {
        return token;
    }

    /**
     * Sets the authentication token for the player.
     *
     * @param token The UUID token to set.
     */
    public void setToken(UUID token) {
        this.token = token;
    }

    /**
     * Checks if the player's account is verified.
     *
     * @return A boolean indicating the verification status.
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Sets the verification status of the player's account.
     *
     * @param verified The verification status to set.
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
