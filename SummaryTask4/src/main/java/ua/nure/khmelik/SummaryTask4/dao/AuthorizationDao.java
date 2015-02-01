package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Admin;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface AuthorizationDao {

    User getUser(Connection conn, String login, String password)
	    throws SQLException;

    Student readStudent(Connection conn, User user) throws SQLException;

    Teacher readTeacher(Connection conn, User user) throws SQLException;

    Admin readAdmin(Connection conn, User user) throws SQLException;

}
