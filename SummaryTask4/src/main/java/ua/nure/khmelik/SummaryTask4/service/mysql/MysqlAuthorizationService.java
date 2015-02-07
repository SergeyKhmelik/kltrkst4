package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.data.AdminData;
import ua.nure.khmelik.SummaryTask4.entity.data.StudentData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlAuthorizationService implements AuthorizationService {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlAuthorizationService.class);

    private static final String ADMIN_ROLE = "admin";
    private static final String TEACHER_ROLE = "teacher";
    private static final String STUDENT_ROLE = "student";

    private TransactionManager transactionManager;
    private AuthorizationDao authorizationDao;
    private PermissionDao permissionDao;

    public MysqlAuthorizationService(TransactionManager transactionManager,
	    AuthorizationDao authorizationDao, PermissionDao permissionDao) {
	this.transactionManager = transactionManager;
	this.authorizationDao = authorizationDao;
	this.permissionDao = permissionDao;
    }

    @Override
    public UserData getUser(final String login, final String password)
	    throws SQLException, NoSuchUserException, NoSuchRoleException {

	UserData user = (UserData) transactionManager
		.doTransaction(new Operation<UserData>() {

		    public UserData execute(Connection conn)
			    throws SQLException {
			UserData result = null;
			User user = authorizationDao.getUser(conn, login,
				password);
			if (user != null && user.getIdRole() != 0) {
			    Role role = permissionDao.readRole(conn,
				    user.getIdRole());

			    if (ADMIN_ROLE.equals(role.getName())) {
				result = new AdminData(user);
				result.setRole(role);
			    } else if (TEACHER_ROLE.equals(role.getName())) {
				result = new TeacherData(user);
				result.setRole(role);
			    } else if (STUDENT_ROLE.equals(role.getName())) {
				result = new StudentData(user);
				result.setRole(role);
			    }
			}
			return result;
		    }
		});

	if (user == null) {
	    throw new NoSuchUserException();
	}

	if (user.getRole() == null) {
	    throw new NoSuchRoleException();
	}
	return user;
    }

}
