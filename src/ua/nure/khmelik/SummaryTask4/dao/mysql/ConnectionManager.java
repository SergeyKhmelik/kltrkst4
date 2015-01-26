package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.nure.khmelik.SummaryTask4.constants.Constants;

public class ConnectionManager {

    private static InitialContext initcont;

    private static InitialContext getInitContext() throws NamingException {
	if (initcont == null) {
	    initcont = new InitialContext();
	}
	return initcont;
    }

    public static Connection getConnection() throws NamingException,
	    SQLException, ClassNotFoundException {

	/*
	 * tbContext context = new InitialContext(); Context envcontext =
	 * (Context) context.lookup("java:/comp/env"); DataSource ds =
	 * (DataSource) envcontext.lookup("jdbc/CoursesDB"); return
	 * ds.getConnection();
	 */

	Class.forName(Constants.MYSQL_DRIVER);

	return DriverManager.getConnection(Constants.MYSQL_DB_PATH,
		Constants.MYSQL_USER, Constants.MYSQL_PASSWORD);
    }

}
