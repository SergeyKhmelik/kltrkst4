package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.constants.Constants;
import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlAuthorizationService implements AuthorizationService {

    private TransactionManager transactionManager;
    private AuthorizationDao authDao;

    public MysqlAuthorizationService(TransactionManager transactionManager, AuthorizationDao authdao)
	    throws NamingException {
	super();
	this.transactionManager = transactionManager;
	this.authDao = authdao;
    }

    @Override
    public User getUserByLoginPassword(final String login, final String password) {
	User result = (User) transactionManager.doTransaction(new Operation<User>() {
	    public User execute(Connection conn) throws SQLException,
		    NoSuchRoleException, NoSuchUserException {
		User result = null;
		int[] userInfo = authDao.getUserIdRoleId(conn, login, password);

		//No such user in db
		if (userInfo[0] == 0) {
		    throw new NoSuchUserException();
		}

		// Creating different objects depending on idRole(userInfo[1])
		if (userInfo[1] == Constants.ROLE_ADMIN) {
		    result = authDao.readAdmin(conn, userInfo[0]);
		} else if (userInfo[1] == Constants.ROLE_STUDENT) {
		    result = authDao.readStudent(conn, userInfo[0]);
		} else if (userInfo[1] == Constants.ROLE_TEACHER) {
		    result = authDao.readTeacher(conn, userInfo[0]);
		} else {
		    throw new NoSuchRoleException(userInfo[1]);
		}
		result.setLogin(login);
		result.setPassword(password);
		result.setRoleId(userInfo[1]);

		return result;
	    };
	});
	return result;
    }

}
