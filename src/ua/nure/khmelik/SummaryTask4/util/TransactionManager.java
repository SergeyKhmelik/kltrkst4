package ua.nure.khmelik.SummaryTask4.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;

public class TransactionManager {

    private DataSource ds;

    public TransactionManager() throws NamingException {
	InitialContext initContext = new InitialContext();
	ds = (DataSource) initContext.lookup("java:comp/env/jdbc/courses");
    }

    public <T> T doTransaction(Operation<T> operation) {
	T result = null;
	Connection connection = null;
	try {
	    connection = ds.getConnection();
	    result = operation.execute(connection);
	    connection.commit();
	} catch (SQLException | NoSuchRoleException | NoSuchUserException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		// logger.error(e1);
	    }
	} finally {
	    try {
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return result;
    }

}
