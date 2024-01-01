package tk.airshipcraft.commonlib.db.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a player on the Minecraft server.
 * It holds attributes specific to the player's interaction within the server.
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

    // Constructors
    public User() {
    }

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

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDateTime getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(LocalDateTime lastJoin) {
        this.lastJoin = lastJoin;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public long getTotalPlaytime() {
        return totalPlaytime;
    }

    public void setTotalPlaytime(long totalPlaytime) {
        this.totalPlaytime = totalPlaytime;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public int getTotalBlocksBroken() {
        return totalBlocksBroken;
    }

    public void setTotalBlocksBroken(int totalBlocksBroken) {
        this.totalBlocksBroken = totalBlocksBroken;
    }

    public int getFishCaught() {
        return fishCaught;
    }

    public void setFishCaught(int fishCaught) {
        this.fishCaught = fishCaught;
    }

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
