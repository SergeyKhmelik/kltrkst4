package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.constants.Constants;
import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.service.Operation;
import ua.nure.khmelik.SummaryTask4.service.TransactionManager;

public class AuthorizationServiceMysqlImpl implements AuthorizationService {

    private TransactionManager tm;
    private AuthorizationDao authDao;
    private PermissionDao permDao;

    public AuthorizationServiceMysqlImpl(AuthorizationDao authdao,
	    PermissionDao permdao) throws NamingException {
	super();
	this.tm = new TransactionManager();
	this.authDao = authdao;
	this.permDao = permdao;
    }

    @Override
    public User getUserByLoginPassword(final String login, final String password) {
	User result = (User) tm.doTransaction(new Operation<User>() {
	    public User execute(java.sql.Connection conn) throws SQLException {
		User result = null;
		int[] userInfo = authDao.getUserIdRoleId(conn, login, password);

		// В зависимости от роли, создать соответствующую сущность
		if (userInfo != null && userInfo[0] != 0) {
			switch (userInfo[1]) {
			case Constants.ROLE_ADMIN:
				result = authDao.readAdminById(conn, userInfo[0]);
				break;
			case Constants.ROLE_STUDENT:
				result = authDao.readStudentById(conn, userInfo[0]);
				break;
			case Constants.ROLE_TEACHER:
				result = authDao.readTeacherById(conn, userInfo[0]);
				break;
			default:
				// Logging no role exception
				break;
			}
		} else {
			// Logging here (No such user)
			return null;
		}
		if (result != null) {
			result.setLogin(login);
			result.setPassword(password);
			result.setRoleId(userInfo[1]);
		}
		return result;
	    };
	});
	return result;
    }

    @Override
    public ArrayList<Permission> getPermissionsByRole(int idRole) {
	// TODO Auto-generated method stub
	return null;
    }

}
