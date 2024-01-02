package tk.airshipcraft.commonlib.db.dao;


import tk.airshipcraft.commonlib.db.GenericDao;
import tk.airshipcraft.commonlib.db.model.User;

import java.util.UUID;

/**
 * Interface for data access operations on users.
 */
public interface UserDao extends GenericDao<User, UUID> {
    // Additional user-specific methods can be defined here
    User findByUsername(String username);
}
