package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.CourseService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlCourseService implements CourseService {

    private TransactionManager transactionManager;
    private CourseDao courseDao;

    public MysqlCourseService(TransactionManager transactionManager,
	    CourseDao courseDao) {
	super();
	this.transactionManager = transactionManager;
	this.courseDao = courseDao;
    }

    @Override
    public ArrayList<Course> getIncomingCourses() throws SQLException {
	ArrayList<Course> result = null;
	try {
	    result = transactionManager
		    .doTransaction(new Operation<ArrayList<Course>>() {

			@Override
			public ArrayList<Course> execute(Connection conn)
				throws SQLException {
			    return courseDao.getIncomingCourses(conn);
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
    public ArrayList<Course> getIncomingCourses(final int idTeacher) {
	ArrayList<Course> result = null;
	try {
	    result = transactionManager
		    .doTransaction(new Operation<ArrayList<Course>>() {

			@Override
			public ArrayList<Course> execute(Connection conn)
				throws SQLException {
			    return courseDao
				    .getIncomingCourses(conn, idTeacher);
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
    public ArrayList<Course> getPassedCourses(final int idStudent) {
	ArrayList<Course> result = null;
	try {
	    result = transactionManager
		    .doTransaction(new Operation<ArrayList<Course>>() {

			@Override
			public ArrayList<Course> execute(Connection conn)
				throws SQLException {
			    return courseDao.getPassedCourses(conn, idStudent);
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
    public ArrayList<Course> getCurrentCourses(final int idTeacher) {
	ArrayList<Course> result = null;
	try {
	    result = transactionManager
		    .doTransaction(new Operation<ArrayList<Course>>() {

			@Override
			public ArrayList<Course> execute(Connection conn)
				throws SQLException {
			    return courseDao.getCurrentCourses(conn, idTeacher);
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
    public int createCourse(final Course course) throws SQLException {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return courseDao.createCourse(conn, course);
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
    public int updateCourse(final Course course) {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return courseDao.updateCourse(conn, course);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int deleteCourse(final int idCourse) {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return courseDao.deleteCourse(conn, idCourse);
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
    public int addStudent(int idCourse, int idStudent) throws SQLException {
	int result = 0;
	final StudentCourse studentCourse;
	try {
	    studentCourse = new StudentCourse();
	    studentCourse.setIdCourse(idCourse);
	    studentCourse.setIdStudent(idStudent);
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return courseDao.addStudent(conn, studentCourse);
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
