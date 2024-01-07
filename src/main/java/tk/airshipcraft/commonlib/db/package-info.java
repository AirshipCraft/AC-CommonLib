/**
 * Provides classes for database operations within the AirshipCraft plugin framework.
 * It includes models for entity representation, DAOs for data manipulation, and utilities
 * for managing database connections and table schemas.
 *
 * <h2>DAO Interfaces:</h2>
 * <p>Define contracts for CRUD operations on specific models:</p>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.AuthDataDao} - Access and modify authentication data.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.UserDao} - Retrieve and manage user information.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.WarningDao} - Handle warnings issued to users.</li>
 * </ul>
 *
 * <h2>DAO Implementations:</h2>
 * <p>Provide SQL-based implementations of the DAO interfaces:</p>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.impl.SqlAuthDataDao} - Implements AuthDataDao with SQL operations.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.impl.SqlUserDao} - Implements UserDao for SQL databases.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.impl.SqlWarningDao} - Manages Warning records in an SQL context.</li>
 * </ul>
 *
 * <h2>Model Classes:</h2>
 * <p>Represent entities within the database:</p>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.model.AuthData} - Stores authentication-related information.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.model.User} - Represents a user in the system.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.model.Warning} - Tracks warnings assigned to users.</li>
 * </ul>
 *
 * <h2>Utility Classes:</h2>
 * <p>Facilitate database configuration and connection pooling:</p>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.DatabaseConfig} - Manages database settings for connection setup.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.SqlConnectionManager} - Handles pooled connections for efficient database access.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.ITableConfiguration} - Interface for defining table schemas and migrations.</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.TableManager} - Registers and initializes plugin-specific tables in the database.</li>
 * </ul>
 *
 * <h2>Core Interfaces:</h2>
 * <p>Define a generic template for DAOs:</p>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.GenericDao} - Generic interface for CRUD operations applicable to all models.</li>
 * </ul>
 *
 * <h2>Example Usage:</h2>
 * <pre>{@code
 * // Initialize DatabaseConfig with connection details
 * DatabaseConfig dbConfig = new DatabaseConfig();
 * dbConfig.loadFromFile("db.properties");
 *
 * // Set up connection pooling with HikariCP
 * SqlConnectionManager connectionManager = new SqlConnectionManager(dbConfig);
 *
 * // Register table configurations for plugin-specific tables
 * TableManager tableManager = new TableManager(connectionManager);
 * tableManager.registerPluginTableConfiguration(new PluginTableConfig());
 * tableManager.setupPluginTables();
 *
 * // Use DAO to interact with the database
 * UserDao userDao = new SqlUserDao(connectionManager);
 * User user = userDao.findById(userId).orElseThrow();
 * user.setUsername("newUsername");
 * userDao.update(user);
 * }</pre>
 *
 * <p>The above usage demonstrates initializing the database connection, registering table configurations,
 * setting up plugin-specific tables, and performing CRUD operations using a DAO. This encapsulates data access
 * within well-defined interfaces and implementations, promoting clean, maintainable, and scalable code.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-06
 */
package tk.airshipcraft.commonlib.db;
