package tk.airshipcraft.commonlib.db.model;

import java.util.Objects;
import java.util.UUID;

/**
 * <p>Represents a warning issued to a player or user. This class is designed to handle warnings for both Minecraft players
 * and Discord users. It stores the issuer's UUID, the recipient's Minecraft UUID (if applicable), the recipient's
 * Discord ID (if applicable), the reason for the warning, and a timestamp indicating when the warning was issued.</p>
 *
 * <p>Warnings are stored in a database table with the following schema: </p>
 * <pre>
 *     CREATE TABLE IF NOT EXISTS warnings (
 *     id UUID PRIMARY KEY,
 *     issuer UUID NOT NULL,
 *     minecraft_recipient UUID,
 *     discord_recipient VARCHAR(255),
 *     reason VARCHAR(255) NOT NULL,
 *     timestamp BIGINT NOT NULL
 *     );
 * </pre>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-06
 */
public class Warning {

    private UUID id; // Unique identifier for the warning
    private UUID issuer; // UUID of the person who issued the warning
    private UUID minecraftRecipient; // Nullable, UUID of the Minecraft player
    private String discordRecipient; // Nullable, Discord ID of the user
    private String reason; // Reason for the warning
    private long timestamp; // Timestamp when the warning was issued

    /**
     * Constructs a new Warning instance.
     *
     * @param id                The unique identifier for the warning.
     * @param issuer            The UUID of the issuer.
     * @param minecraftRecipient The UUID of the Minecraft player being warned. Can be null if the warning is for a Discord user.
     * @param discordRecipient  The Discord ID of the user being warned. Can be null if the warning is for a Minecraft player.
     * @param reason            The reason for issuing the warning.
     * @param timestamp         The timestamp when the warning was issued.
     */
    public Warning(UUID id, UUID issuer, UUID minecraftRecipient, String discordRecipient, String reason, long timestamp) {
        this.id = id;
        this.issuer = issuer;
        this.minecraftRecipient = minecraftRecipient;
        this.discordRecipient = discordRecipient;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    /**
     * Returns the unique identifier for the warning.
     *
     * @return A UUID representing the warning's identity.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the warning.
     *
     * @param id The UUID to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the UUID of the issuer.
     *
     * @return A UUID representing the issuer's identity.
     */
    public UUID getIssuer() {
        return issuer;
    }

    /**
     * Sets the UUID of the issuer.
     *
     * @param issuer The UUID to set.
     */
    public void setIssuer(UUID issuer) {
        this.issuer = issuer;
    }

    /**
     * Returns the UUID of the Minecraft player being warned.
     *
     * @return A UUID representing the player's identity.
     */
    public UUID getMinecraftRecipient() {
        return minecraftRecipient;
    }

    /**
     * Sets the UUID of the Minecraft player being warned.
     *
     * @param minecraftRecipient The UUID to set.
     */
    public void setMinecraftRecipient(UUID minecraftRecipient) {
        this.minecraftRecipient = minecraftRecipient;
    }

    /**
     * Returns the Discord ID of the user being warned.
     *
     * @return A String representing the user's Discord ID.
     */
    public String getDiscordRecipient() {
        return discordRecipient;
    }

    /**
     * Sets the Discord ID of the user being warned.
     *
     * @param discordRecipient The Discord ID to set.
     */
    public void setDiscordRecipient(String discordRecipient) {
        this.discordRecipient = discordRecipient;
    }

    /**
     * Returns the reason for issuing the warning.
     *
     * @return A String representing the reason for the warning.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason for issuing the warning.
     *
     * @param reason The reason to set.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Returns the timestamp when the warning was issued.
     *
     * @return A long representing the timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the warning was issued.
     *
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns a String representation of the warning.
     *
     * @return A String representing the warning.
     */
    @Override
    public String toString() {
        return "Warning{" +
                "id=" + id +
                ", issuer=" + issuer +
                ", minecraftRecipient=" + minecraftRecipient +
                ", discordRecipient='" + discordRecipient + '\'' +
                ", reason='" + reason + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * Compares this Warning to another Warning.
     *
     * @param obj The Warning to compare to.
     * @return True if the two Warnings are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Warning other)) return false;
        return id.equals(other.id) &&
                issuer.equals(other.issuer) &&
                (Objects.equals(minecraftRecipient, other.minecraftRecipient)) &&
                (Objects.equals(discordRecipient, other.discordRecipient)) &&
                reason.equals(other.reason) &&
                timestamp == other.timestamp;
    }

    /**
     * Returns a hash code for the Warning.
     *
     * @return A hash code for the Warning.
     */
    @Override
    public int hashCode() {
        return id.hashCode() + issuer.hashCode() +
                (minecraftRecipient != null ? minecraftRecipient.hashCode() : 0) +
                (discordRecipient != null ? discordRecipient.hashCode() : 0) +
                reason.hashCode() + (int) timestamp;
    }
}
