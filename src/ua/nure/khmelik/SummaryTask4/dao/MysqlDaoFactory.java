package ua.nure.khmelik.SummaryTask4.dao;

import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlAuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlCourseDao;
import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlJournalDao;
import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlPermissionDao;
import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlUserDao;

public class MysqlDaoFactory extends DaoFactory {

    @Override
    public CourseDao getCourseDao() {
	return new MysqlCourseDao();
    }

    @Override
    public UserDao getUserDao() {
	return new MysqlUserDao();
    }

    @Override
    public JournalDao getJournalDao() {
	return new MysqlJournalDao();
    }

    @Override
    public AuthorizationDao getAuthorizationDao() {
	return new MysqlAuthorizationDao();
    }

    @Override
    public PermissionDao getPermissionDao() {
	return new MysqlPermissionDao();
    }

}
