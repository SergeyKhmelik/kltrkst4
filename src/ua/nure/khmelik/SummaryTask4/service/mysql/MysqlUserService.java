package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

    private TransactionManager transactionManager;
    private UserDao userDao;
    
    public MysqlUserService(TransactionManager transactionManager,
	    UserDao userDao) {
	super();
	this.transactionManager = transactionManager;
	this.userDao = userDao;
    }

    @Override
    public ArrayList<Teacher> readTeachers() {
	ArrayList<Teacher> result = null;
	try {
	    result = transactionManager
		    .doTransaction(new Operation<ArrayList<Teacher>>() {

			@Override
			public ArrayList<Teacher> execute(Connection conn)
				throws SQLException {
			    return userDao.readTeachers(conn);
			}
		    });

	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public ArrayList<Student> readStudents() {
	ArrayList<Student> result = null;
	try {
	    result = transactionManager
		    .doTransaction(new Operation<ArrayList<Student>>() {

			@Override
			public ArrayList<Student> execute(Connection conn)
				throws SQLException {
			    return userDao.readStudents(conn);
			}
		    });

	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int addStudent(final Student student) {
	int result = 0;
	try{
	   result = transactionManager.doTransaction(new Operation<Integer>(){
		
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    userDao.createUser(conn, student);
		    return userDao.createStudent(conn, student);
		}
		
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int addTeacher(final Teacher teacher) {
	int result = 0;
	try{
	   result = transactionManager.doTransaction(new Operation<Integer>(){
		
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    userDao.createUser(conn, teacher);
		    return userDao.createTeacher(conn, teacher);
		}
		
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int updateUser(final User user) {
	int result = 0;
	try{
	   result = transactionManager.doTransaction(new Operation<Integer>(){
		
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return userDao.updateUser(conn, user);
		}
		
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int deleteUser(final int idUser) {
	int result = 0;
	try{
	   result = transactionManager.doTransaction(new Operation<Integer>(){
		
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return userDao.deleteUser(conn, idUser);
		}
		
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int blockStudent(final int idStudent, final boolean block) {
	int result = 0;
	try{
	   result = transactionManager.doTransaction(new Operation<Integer>(){
		
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    if(block){
			return userDao.blockStudent(conn, idStudent);
		    } else {
			return userDao.unblockStudent(conn, idStudent);
		    }
		}
		
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

}
