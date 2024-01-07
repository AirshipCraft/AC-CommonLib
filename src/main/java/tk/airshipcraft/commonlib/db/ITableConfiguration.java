package tk.airshipcraft.commonlib.db;

import java.util.List;

/**
 * The ITableConfiguration interface specifies the methods that must be implemented
 * to define the SQL table creation and migration commands for a plugin. It allows
 * for dynamic table management and supports database schema evolution over time.
 *
 * <p>Implementations of this interface should provide SQL statements required to
 * create the initial tables and to migrate them to newer versions as the plugin evolves.</p>
 *
 * <p>Use cases include:</p>
 * <ul>
 *     <li>Initializing fresh installations with the necessary database tables.</li>
 *     <li>Providing migration paths for existing installations when the database schema changes.</li>
 *     <li>Allowing for a systematic approach to managing database versions and updates.</li>
 * </ul>
 *
 * <p>Works in conjunction with {@link TableManager} which calls these methods
 * to actually apply the SQL commands to the database.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @see TableManager
 * @since 2023-12-27
 */
public interface ITableConfiguration {

    /**
     * Provides a list of SQL commands for creating the necessary tables
     * for a plugin if they do not exist. This method is typically called
     * during the plugin startup to ensure that all required tables are present.
     *
     * @return a List of SQL command strings for table creation.
     */
    List<String> getTableCreationCommands();

    /**
     * Provides a list of SQL commands for migrating the existing tables
     * to a new version. This method supports upgrading the database schema
     * without losing data.
     *
     * <p>The migrations should be incremental and idempotent, ensuring they can
     * be run multiple times without causing errors or duplications.</p>
     *
     * @param currentVersion the current version of the database schema.
     * @return a List of SQL command strings to migrate the database to the latest version.
     */
    List<String> getMigrationCommands(int currentVersion);

    /**
     * Returns the latest version number of the database schema as defined by
     * the plugin. This version number is used in conjunction with migration
     * commands to determine if a migration is necessary.
     *
     * @return the latest schema version number.
     */
    int getLatestVersion();
}
