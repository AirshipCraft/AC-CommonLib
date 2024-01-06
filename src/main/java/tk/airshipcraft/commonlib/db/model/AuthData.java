package tk.airshipcraft.commonlib.db.model;

import java.util.UUID;

/**
 * <p>Represents the authentication data for a player, storing information related to the linkage of Minecraft accounts to Discord accounts.
 * This class is used to verify if a player has successfully linked their accounts.</p>
 *
 * <p>Authentication data is stored in a database table with the following schema: </p>
 * <pre>
 *     CREATE TABLE IF NOT EXISTS auth_data (
 *     id UUID PRIMARY KEY,
 *     IGN VARCHAR(255) NOT NULL,
 *     username VARCHAR(255) NOT NULL,
 *     token UUID NOT NULL,
 *     verified BOOLEAN NOT NULL
 *     );
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_IGN_index ON auth_data (IGN);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_username_index ON auth_data (username);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_token_index ON auth_data (token);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_verified_index ON auth_data (verified);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_IGN_username_index ON auth_data (IGN, username);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_IGN_token_index ON auth_data (IGN, token);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_IGN_verified_index ON auth_data (IGN, verified);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_username_token_index ON auth_data (username, token);
 *     CREATE UNIQUE INDEX IF NOT EXISTS auth_data_username_verified_index ON auth_data (username, verified);
 * </pre>
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

    /**
     * Returns a String representation of the AuthData object.
     *
     * @return A String representation of the AuthData object.
     */
    @Override
    public String toString() {
        return "AuthData{" +
                "id=" + id +
                ", IGN='" + IGN + '\'' +
                ", username='" + username + '\'' +
                ", token=" + token +
                ", verified=" + verified +
                '}';
    }

    /**
     * Checks if the specified object is equal to this AuthData object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuthData other) {
            return this.id.equals(other.id) && this.IGN.equals(other.IGN) && this.username.equals(other.username) && this.token.equals(other.token) && this.verified == other.verified;
        }
        return false;
    }

    /**
     * Returns the hash code of the AuthData object.
     *
     * @return An integer representing the hash code.
     */
    @Override
    public int hashCode() {
        return this.id.hashCode() + this.IGN.hashCode() + this.username.hashCode() + this.token.hashCode() + Boolean.hashCode(this.verified);
    }
}
