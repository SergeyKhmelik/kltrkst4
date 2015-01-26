package ua.nure.khmelik.SummaryTask4.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.dao.DaoFactory;
import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.dao.mysql.ConnectionManager;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public class UserManagementLogic {

    public ArrayList<Teacher> readTeachers() throws ClassNotFoundException,
	    SQLException, NamingException {
	ArrayList<Teacher> result = null;
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    result = dao.readTeachers(conn);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
	return result;
    }

    public ArrayList<Student> readStudents() throws ClassNotFoundException,
	    SQLException, NamingException {
	ArrayList<Student> result = null;
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    result = dao.readStudents(conn);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
	return result;
    }

    public void addStudent(Student st) throws ClassNotFoundException,
	    SQLException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.createStudent(conn, st);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void addTeacher(Teacher te) throws ClassNotFoundException,
	    SQLException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.createTeacher(conn, te);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void updateUser(User u) throws ClassNotFoundException, SQLException,
	    NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.updateUser(conn, u);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void deleteUser(int userId) throws SQLException,
	    ClassNotFoundException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.deleteUser(conn, userId);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void blockStudent(boolean toBlock, int studentId)
	    throws ClassNotFoundException, SQLException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    if (toBlock) {
		dao.blockStudent(conn, studentId);
	    } else {
		dao.unblockStudent(conn, studentId);
	    }
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

}
