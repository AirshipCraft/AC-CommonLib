package tk.airshipcraft.commonlib.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>The TableManager class manages the creation and updating of database tables
 * for various plugins using the CommonLib library. It works with implementations
 * of the {@link ITableConfiguration} interface to execute SQL commands necessary
 * for setting up and migrating tables.</p>
 *
 * <p>Use cases include:</p>
 * <ul>
 *     <li>Setting up initial database tables for plugins on startup.</li>
 *     <li>Managing database migrations when plugins are updated with new schema requirements.</li>
 * </ul>
 *
 * <p>This class ensures that all plugins extending CommonLib have a centralized
 * way to manage their data schemas without needing to implement boilerplate code
 * for database interactions.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class TableManager {

    private final List<ITableConfiguration> tableConfigurations = new ArrayList<>();
    private final SqlConnectionManager connectionManager;

    /**
     * Creates a new TableManager instance with a specified connection manager.
     *
     * @param connectionManager The SqlConnectionManager responsible for providing database connections.
     */
    public TableManager(SqlConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Registers a plugin's table configuration with the TableManager.
     * This allows the TableManager to execute the SQL commands provided by the plugin
     * for table creation and migration.
     *
     * @param config An implementation of the ITableConfiguration interface.
     */
    public void registerPluginTableConfiguration(ITableConfiguration config) {
        tableConfigurations.add(config);
    }

    /**
     * Sets up the database tables for all registered plugins. It calls the
     * {@link ITableConfiguration#getTableCreationCommands()} method for each
     * registered configuration and executes the returned SQL commands to create
     * the necessary tables.
     */
    public void setupPluginTables() {
        for (ITableConfiguration config : tableConfigurations) {
            try (Connection connection = connectionManager.getConnection();
                 Statement statement = connection.createStatement()) {
                for (String sql : config.getTableCreationCommands()) {
                    statement.execute(sql);
                }
            } catch (SQLException e) {
                // Log and handle exception
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks the current version of the database tables and performs migrations to the latest version.
     *
     * @param connection The database connection to use.
     * @param statement  The statement to use for executing migration commands.
     * @param config     The table configuration that provides migration commands.
     * @throws SQLException If a database access error occurs or migration commands fail to execute.
     */
    private void performMigrations(Connection connection, Statement statement, ITableConfiguration config) throws SQLException {
        int currentVersion = getCurrentDatabaseVersion(connection); // Implement this method to get the current DB version
        int latestVersion = config.getLatestVersion();

        if (currentVersion < latestVersion) {
            List<String> migrationCommands = config.getMigrationCommands(currentVersion);
            for (String migrationCommand : migrationCommands) {
                statement.execute(migrationCommand);
            }
            updateDatabaseVersion(connection, latestVersion); // Implement this method to update the DB version
        }
    }

    /**
     * Retrieves the current version of the database schema.
     *
     * @param connection The database connection to use.
     * @return The current version of the database schema.
     * @throws SQLException If a database access error occurs.
     */
    private int getCurrentDatabaseVersion(Connection connection) throws SQLException {
        // Implement the logic to retrieve the current database version
        // This might involve querying a special table that tracks schema versions
        return 0; // Placeholder return value
    }

    /**
     * Updates the version of the database schema to the specified version.
     *
     * @param connection    The database connection to use.
     * @param latestVersion The latest version number to set.
     * @throws SQLException If a database access error occurs.
     */
    private void updateDatabaseVersion(Connection connection, int latestVersion) throws SQLException {
        // Implement the logic to update the database version
        // This might involve updating a special table that tracks schema versions
    }
}
