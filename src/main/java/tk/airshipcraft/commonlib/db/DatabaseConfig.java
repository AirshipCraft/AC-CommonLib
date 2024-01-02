package tk.airshipcraft.commonlib.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Manages database configuration settings, providing methods to load from
 * external sources and to access individual configuration properties.
 * This class facilitates the setup of database connections by allowing
 * dynamic configuration management.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class DatabaseConfig {

    private final Properties properties;

    /**
     * Initializes a new DatabaseConfig object with empty properties.
     */
    public DatabaseConfig() {
        this.properties = new Properties();
    }

    /**
     * Loads database configuration properties from a specified file.
     * This method reads a properties file from the given file path and loads
     * the configuration settings into the properties object.
     *
     * @param filePath The path to the configuration file.
     * @throws IOException If an error occurs during reading the file.
     */
    public void loadFromFile(String filePath) throws IOException {
        try (FileInputStream input = new FileInputStream(filePath)) {
            this.properties.load(input);
        }
    }

    /**
     * Gets the value of a configuration property.
     *
     * @param key The property key.
     * @return The property value or {@code null} if the property is not found.
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    /**
     * Sets the value of a configuration property.
     *
     * @param key   The property key.
     * @param value The property value.
     */
    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    // Add setters and getters for individual properties here

    /**
     * Gets the JDBC URL from the properties.
     *
     * @return The JDBC URL.
     */
    public String getJdbcUrl() {
        return getProperty("jdbcUrl");
    }

    /**
     * Sets the JDBC URL in the properties.
     *
     * @param jdbcUrl The JDBC URL to set.
     */
    public void setJdbcUrl(String jdbcUrl) {
        setProperty("jdbcUrl", jdbcUrl);
    }

    /**
     * Gets the database username from the properties.
     *
     * @return The database username.
     */
    public String getUsername() {
        return getProperty("username");
    }

    /**
     * Sets the database username in the properties.
     *
     * @param username The database username to set.
     */
    public void setUsername(String username) {
        setProperty("username", username);
    }

    /**
     * Gets the database password from the properties.
     *
     * @return The database password.
     */
    public String getPassword() {
        return getProperty("password");
    }

    /**
     * Sets the database password in the properties.
     *
     * @param password The database password to set.
     */
    public void setPassword(String password) {
        setProperty("password", password);
    }

    // ... additional setters and getters for other properties ...

    // Example for Redis Configuration

    /**
     * Gets the Redis server URI from the properties.
     *
     * @return The Redis server URI.
     */
    public String getRedisUri() {
        return getProperty("redisUri");
    }

    /**
     * Sets the Redis server URI in the properties.
     *
     * @param redisUri The Redis server URI to set.
     */
    public void setRedisUri(String redisUri) {
        setProperty("redisUri", redisUri);
    }

    // ... and so on for each property you need to manage
}
