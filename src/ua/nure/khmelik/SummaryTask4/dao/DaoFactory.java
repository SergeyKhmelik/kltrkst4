package ua.nure.khmelik.SummaryTask4.dao;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.exceptions.NotSupportedDatabaseIndexException;

public abstract class DaoFactory {

    /** List of DAO types supported by the factory */
    public static final int MYSQL = 1;
    public static final int DERBY = 2;

    public abstract CourseDao getCourseDao();

    public abstract UserDao getUserDao();

    public abstract JournalDao getJournalDao();

    public abstract AuthorizationDao getAuthorizationDao();
    
    public abstract PermissionDao getPermissionDao();

    public static DaoFactory getDaoFactory(int factoryNum) throws NotSupportedDatabaseIndexException {
	switch (factoryNum) {
	case MYSQL:
	    return new MysqlDaoFactory();
	case DERBY:
	    return new DerbyDaoFactory();
	default:
	    throw new NotSupportedDatabaseIndexException(factoryNum);
	}
    }

}
