package tk.airshipcraft.commonlib.db.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a player on the Minecraft server.
 * It holds attributes specific to the player's interaction within the server.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class User {

    private UUID id;
    private String username;
    private LocalDateTime joinDate;
    private LocalDateTime lastJoin;
    private LocalDateTime lastSeen;
    private long totalPlaytime;
    private int totalDeaths;
    private int totalKills;
    private int totalBlocksBroken;
    private int fishCaught;

    /**
     * Default constructor.
     * @see #User(UUID, String, LocalDateTime, LocalDateTime, LocalDateTime, long, int, int, int, int)
     */
    public User() {}

    /**
     * Constructs a new User with specified details.
     *
     * @param id                The unique identifier for the user.
     * @param username          The username of the user.
     * @param joinDate          The date when the user first joined the server.
     * @param lastJoin          The last time the user joined the server.
     * @param lastSeen          The last time the user was seen on the server.
     * @param totalPlaytime     The total playtime of the user in milliseconds.
     * @param totalDeaths       The total number of times the user has died.
     * @param totalKills        The total number of kills by the user.
     * @param totalBlocksBroken The total number of blocks broken by the user.
     * @param fishCaught        The total number of fish caught by the user.
     */
    public User(UUID id, String username, LocalDateTime joinDate, LocalDateTime lastJoin,
                LocalDateTime lastSeen, long totalPlaytime, int totalDeaths, int totalKills,
                int totalBlocksBroken, int fishCaught) {
        this.id = id;
        this.username = username;
        this.joinDate = joinDate;
        this.lastJoin = lastJoin;
        this.lastSeen = lastSeen;
        this.totalPlaytime = totalPlaytime;
        this.totalDeaths = totalDeaths;
        this.totalKills = totalKills;
        this.totalBlocksBroken = totalBlocksBroken;
        this.fishCaught = fishCaught;
    }

    /**
     * Returns the user's unique identifier.
     *
     * @return The UUID of the user.
     * @see #setId(UUID)
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param id The UUID to be set for the user.
     * @see #getId()
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the user's username.
     *
     * @return The username of the user.
     * @see #setUsername(String)
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The username to be set for the user.
     * @see #getUsername()
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the date when the user joined the server.
     *
     * @return The join date of the user.
     * @see #setJoinDate(LocalDateTime)
     */
    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    /**
     * Sets the date when the user joined the server.
     *
     * @param joinDate The join date to be set for the user.
     * @see #getJoinDate()
     */
    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Returns the last time the user joined the server.
     *
     * @return LocalDateTime
     * @see #setLastJoin(LocalDateTime)
     */
    public LocalDateTime getLastJoin() {
        return lastJoin;
    }

    /**
     * Sets the last time the user joined the server.
     *
     * @param lastJoin
     * @see #getLastJoin()
     */
    public void setLastJoin(LocalDateTime lastJoin) {
        this.lastJoin = lastJoin;
    }

    /**
     * Returns the last time the user was seen on the server.
     *
     * @return LocalDateTime
     * @see #setLastSeen(LocalDateTime)
     */
    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    /**
     * Sets the last time the user was seen on the server.
     *
     * @param lastSeen
     * @see #getLastSeen()
     *
     */
    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     * Returns the total playtime of the user in milliseconds.
     *
     * @return playtime in milliseconds. (long)
     * @see #setTotalPlaytime(long)
     */
    public long getTotalPlaytime() {
        return totalPlaytime;
    }

    /**
     * Sets the total playtime of the user in milliseconds.
     *
     * @param totalPlaytime playtime in milliseconds. (long)
     * @see #getTotalPlaytime()
     */
    public void setTotalPlaytime(long totalPlaytime) {
        this.totalPlaytime = totalPlaytime;
    }

    /**
     * Returns the total number of deaths by the user.
     *
     * @return total number of deaths (int)
     * @see #setTotalDeaths(int)
     */
    public int getTotalDeaths() {
        return totalDeaths;
    }

    /**
     * Sets the total number of deaths by the user.
     *
     * @param totalDeaths total number of deaths (int)
     * @see #getTotalDeaths()
     */
    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    /**
     * Returns the total number of kills by the user.
     *
     * @return totalKills total number of kills
     * @see #setTotalKills(int)
     */
    public int getTotalKills() {
        return totalKills;
    }

    /**
     * Sets the total number of kills by the user.
     *
     * @param totalKills total number of kills
     * @see #getTotalKills()
     */
    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    /**
     * Returns the total number of blocks broken by the user.
     *
     * @return total number of blocks broken
     * @see #setTotalBlocksBroken(int)
     */
    public int getTotalBlocksBroken() {
        return totalBlocksBroken;
    }

    /**
     * Sets the total number of blocks broken by the user.
     *
     * @param totalBlocksBroken total number of blocks broken (int)
     * @see #getTotalBlocksBroken()
     */
    public void setTotalBlocksBroken(int totalBlocksBroken) {
        this.totalBlocksBroken = totalBlocksBroken;
    }

    /**
     * Returns the total number of fish caught by the user.
     *
     * @return fish caught
     * @see #setFishCaught(int)
     */
    public int getFishCaught() {
        return fishCaught;
    }

    /**
     * Sets the total number of fish caught by the user.
     *
     * @param fishCaught fish caught (int)
     * @see #getFishCaught()
     */
    public void setFishCaught(int fishCaught) {
        this.fishCaught = fishCaught;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", joinDate=" + joinDate +
                ", lastJoin=" + lastJoin +
                ", lastSeen=" + lastSeen +
                ", totalPlaytime=" + totalPlaytime +
                ", totalDeaths=" + totalDeaths +
                ", totalKills=" + totalKills +
                ", totalBlocksBroken=" + totalBlocksBroken +
                ", fishCaught=" + fishCaught +
                '}';
    }
}
