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

    public void addStudent(Student student) throws ClassNotFoundException,
	    SQLException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.createStudent(conn, student);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void addTeacher(Teacher teacher) throws ClassNotFoundException,
	    SQLException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.createTeacher(conn, teacher);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void updateUser(User uuser) throws ClassNotFoundException, SQLException,
	    NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.updateUser(conn, uuser);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void deleteUser(int idUser) throws SQLException,
	    ClassNotFoundException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    dao.deleteUser(conn, idUser);
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

    public void blockStudent(boolean block, int idStudent)
	    throws ClassNotFoundException, SQLException, NamingException {
	try (Connection conn = ConnectionManager.getConnection()) {
	    conn.setAutoCommit(false);
	    UserDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();
	    if (block) {
		dao.blockStudent(conn, idStudent);
	    } else {
		dao.unblockStudent(conn, idStudent);
	    }
	    conn.commit();
	    conn.setAutoCommit(true);
	}
    }

}
