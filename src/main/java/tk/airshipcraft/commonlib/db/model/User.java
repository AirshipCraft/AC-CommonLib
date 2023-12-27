package tk.airshipcraft.commonlib.db.model;

import java.util.UUID;

/**
 * Represents a player on the Minecraft server.
 * It holds attributes specific to the player's interaction within the server.
 */
public class User {

    private UUID id;
    private String username;
    private long lastLogin;
    private double balance;
    private boolean isActive;
    private String rank;

    // Constructors
    public User() {
    }

    public User(UUID id, String username, long lastLogin, double balance, boolean isActive, String rank) {
        this.id = id;
        this.username = username;
        this.lastLogin = lastLogin;
        this.balance = balance;
        this.isActive = isActive;
        this.rank = rank;
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

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // toString, equals, and hashCode methods

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastLogin=" + lastLogin +
                ", balance=" + balance +
                ", isActive=" + isActive +
                ", rank='" + rank + '\'' +
                '}';
    }

    // Override equals and hashCode if you plan to use instances in collections or compare them.
    // ...

    // Additional methods specific to Minecraft player management can be added as needed.
}
