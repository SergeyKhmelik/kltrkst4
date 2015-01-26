package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Admin;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;

public interface AuthorizationDao {

    int[] getUserIdRoleId(Connection conn, String login, String password)
	    throws SQLException;

    ArrayList<Permission> getPermitionsByRole(Connection conn, int roleId)
	    throws SQLException;

    Student readStudentById(Connection conn, int idStudent) throws SQLException;

    Teacher readTeacherById(Connection conn, int idTeacher) throws SQLException;

    Admin readAdminById(Connection conn, int idAdmin) throws SQLException;

}
