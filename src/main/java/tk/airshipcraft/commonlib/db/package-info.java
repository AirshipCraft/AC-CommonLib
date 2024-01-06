/**
 * Provides the classes necessary for database operations within the AirshipCraft plugin.
 * This includes model classes for various data entities, data access objects (DAOs) for CRUD operations,
 * and utility classes for managing database connections and configurations.
 *
 * <h2>DAO Interfaces:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.AuthDataDao}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.UserDao}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.WarningDao}</li>
 * </ul>
 *
 * <h2>DAO Implementations:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.impl.SqlAuthDataDao}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.impl.SqlUserDao}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.dao.impl.SqlWarningDao}</li>
 * </ul>
 *
 * <h2>Model Classes:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.model.AuthData}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.model.User}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.model.Warning}</li>
 * </ul>
 *
 * <h2>Utility Classes:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.DatabaseConfig}</li>
 *   <li>{@link tk.airshipcraft.commonlib.db.SqlConnectionManager}</li>
 * </ul>
 *
 * <h2>Core Interfaces:</h2>
 * <ul>
 *   <li>{@link tk.airshipcraft.commonlib.db.GenericDao}</li>
 * </ul>
 *
 * <p>These classes are designed to provide a layer of abstraction between the plugin's operations and the underlying
 * SQL database, allowing for a more modular and maintainable codebase. The use of connection pooling and DAO patterns
 * ensures efficient and scalable database access.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-06
 */
package tk.airshipcraft.commonlib.db;
