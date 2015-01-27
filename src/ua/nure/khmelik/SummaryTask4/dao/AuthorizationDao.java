package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Admin;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;

public interface AuthorizationDao {

    int[] getUserIdRoleId(Connection conn, String login, String password)
	    throws SQLException;

    Student readStudent(Connection conn, int idStudent) throws SQLException;

    Teacher readTeacher(Connection conn, int idTeacher) throws SQLException;

    Admin readAdmin(Connection conn, int idAdmin) throws SQLException;

}
