package ua.nure.khmelik.SummaryTask4.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class TransactionManager {

    private static final Logger LOGGER = Logger
	    .getLogger(TransactionManager.class);

    private DataSource ds;

    public TransactionManager() throws NamingException {
	InitialContext initContext = new InitialContext();
	ds = (DataSource) initContext.lookup("java:comp/env/jdbc/courses");
    }

    public <T> T doTransaction(Operation<T> operation) throws SQLException {
	T result = null;
	Connection connection = null;
	try {
	    connection = ds.getConnection();
	    LOGGER.trace("Connection is taken from the pool.");
	    if (connection == null) {
		throw new SQLException();
	    }
	    result = operation.execute(connection);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		if (connection != null) {
		    LOGGER.error("Connection rollback caused by SQLException.",
			    e);
		    connection.rollback();
		}
	    } catch (SQLException e1) {
		LOGGER.error("Cannot rollback the connection.", e1);
		throw e1;
	    }
	    throw e;
	} finally {
	    try {
		if (connection != null) {
		    LOGGER.trace("Connection returned to the pool.");
		    connection.close();
		}
	    } catch (SQLException e) {
		LOGGER.error("Cannot close the connection.", e);
		throw e;
	    }
	}
	return result;
    }

}
