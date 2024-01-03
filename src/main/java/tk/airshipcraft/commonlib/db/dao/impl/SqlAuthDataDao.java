package tk.airshipcraft.commonlib.db.dao.impl;

import tk.airshipcraft.commonlib.db.SqlConnectionManager;
import tk.airshipcraft.commonlib.db.dao.AuthDataDao;

public class SqlAuthDataDao implements AuthDataDao {

    private SqlConnectionManager connectionManager;

    public SqlAuthDataDao(SqlConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
