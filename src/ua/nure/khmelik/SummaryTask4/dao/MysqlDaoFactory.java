package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.mysql.AuthorizationDaoMysqlImpl;
import ua.nure.khmelik.SummaryTask4.dao.mysql.CourseDaoMysqlImpl;
import ua.nure.khmelik.SummaryTask4.dao.mysql.JournalDaoMysqlImpl;
import ua.nure.khmelik.SummaryTask4.dao.mysql.PermissionDaoMySqlImpl;
import ua.nure.khmelik.SummaryTask4.dao.mysql.UserDaoMysqlImpl;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;

public class MysqlDaoFactory extends DaoFactory {

    @Override
    public CourseDao getCourseDao() {
	return new CourseDaoMysqlImpl();
    }

    @Override
    public UserDao getUserDao() {
	return new UserDaoMysqlImpl();
    }

    @Override
    public JournalDao getJournalDao() {
	return new JournalDaoMysqlImpl();
    }

    @Override
    public AuthorizationDao getAuthorizationDao() {
	return new AuthorizationDaoMysqlImpl();
    }

    @Override
    public PermissionDao getPermissionDao() {
	return new PermissionDaoMySqlImpl();
    }

}
