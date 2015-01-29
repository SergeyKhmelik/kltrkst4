package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.UserService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlUserService implements UserService {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlUserService.class);

    private TransactionManager transactionManager;
    private UserDao userDao;

    public MysqlUserService(TransactionManager transactionManager,
	    UserDao userDao) {
	this.transactionManager = transactionManager;
	this.userDao = userDao;
    }

    @Override
    public ArrayList<Teacher> readTeachers() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Teacher>>() {

		    @Override
		    public ArrayList<Teacher> execute(Connection conn)
			    throws SQLException {
			return userDao.readTeachers(conn);
		    }
		});
    }

    @Override
    public ArrayList<Student> readStudents() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Student>>() {

		    @Override
		    public ArrayList<Student> execute(Connection conn)
			    throws SQLException {
			return userDao.readStudents(conn);
		    }
		});
    }

    @Override
    public int addStudent(final Student student) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		userDao.createUser(conn, student);
		return userDao.createStudent(conn, student);
	    }

	}).intValue();
    }

    @Override
    public int addTeacher(final Teacher teacher) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		userDao.createUser(conn, teacher);
		return userDao.createTeacher(conn, teacher);
	    }

	}).intValue();
    }

    @Override
    public int updateUser(final User user) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return userDao.updateUser(conn, user);
	    }

	}).intValue();
    }

    @Override
    public int deleteUser(final int idUser) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return userDao.deleteUser(conn, idUser);
	    }

	}).intValue();
    }

    @Override
    public int blockStudent(final int idStudent, final boolean block)
	    throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		if (block) {
		    return userDao.blockStudent(conn, idStudent);
		} else {
		    return userDao.unblockStudent(conn, idStudent);
		}
	    }

	}).intValue();
    }

}
