package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.constants.Constants;
import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlAuthorizationService implements AuthorizationService {

    private TransactionManager transactionManager;
    private AuthorizationDao authorizationDao;

    public MysqlAuthorizationService(TransactionManager transactionManager,
	    AuthorizationDao authdao) throws NamingException {
	this.transactionManager = transactionManager;
	this.authorizationDao = authdao;
    }

    @Override
    public User getUser(final String login, final String password)
	    throws SQLException, NoSuchUserException {

	User user = (User) transactionManager.doTransaction(new Operation<User>() {

	    public User execute(Connection conn) throws SQLException {
		User result = authorizationDao.getUser(conn, login, password);
		// No such user in db
		if (result != null) {
		    if (result.getIdRole() == Constants.ROLE_ADMIN) {
			result = authorizationDao.readAdmin(conn, result);
		    } else if (result.getIdRole() == Constants.ROLE_STUDENT) {
			result = authorizationDao.readStudent(conn, result);
		    } else if (result.getIdRole() == Constants.ROLE_TEACHER) {
			result = authorizationDao.readTeacher(conn, result);
		    }
		}
		return result;
	    }
	});

	if(user == null){
	    throw new NoSuchUserException();
	}
	return user;
    }

}
