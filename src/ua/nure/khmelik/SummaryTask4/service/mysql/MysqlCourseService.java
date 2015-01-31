package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;
import ua.nure.khmelik.SummaryTask4.service.CourseService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlCourseService implements CourseService {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlCourseService.class);

    private TransactionManager transactionManager;
    private CourseDao courseDao;
    private JournalDao journalDao;

    public MysqlCourseService(TransactionManager transactionManager,
	    CourseDao courseDao) {
	this.transactionManager = transactionManager;
	this.courseDao = courseDao;
    }

    @Override
    public ArrayList<Course> getIncomingCourses() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Course>>() {

		    @Override
		    public ArrayList<Course> execute(Connection conn)
			    throws SQLException {
			return courseDao.readIncomingCourses(conn);
		    }
		});
    }

    @Override
    public ArrayList<Course> getIncomingCourses(final int idTeacher) {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Course>>() {

		    @Override
		    public ArrayList<Course> execute(Connection conn)
			    throws SQLException {
			return courseDao.readIncomingCourses(conn, idTeacher);
		    }
		});
    }

    @Override
    public ArrayList<Course> getPassedCourses(final int idStudent) {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Course>>() {

		    @Override
		    public ArrayList<Course> execute(Connection conn)
			    throws SQLException {
			return courseDao.readPassedCourses(conn, idStudent);
		    }
		});
    }

    @Override
    public ArrayList<Course> getCurrentCourses(final int idTeacher) {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Course>>() {

		    @Override
		    public ArrayList<Course> execute(Connection conn)
			    throws SQLException {
			return courseDao.readCurrentCourses(conn, idTeacher);
		    }
		});
    }

    @Override
    public int createCourse(final Course course) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return courseDao.createCourse(conn, course);
	    }
	}).intValue();
    }

    @Override
    public int updateCourse(final Course course) {
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return courseDao.updateCourse(conn, course);
	    }
	}).intValue();
    }

    @Override
    public int deleteCourse(final int idCourse) {
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return courseDao.deleteCourse(conn, idCourse);
	    }
	}).intValue();
    }

    @Override
    public ArrayList<CourseTheme> getCourseThemes() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseTheme>>() {

		    @Override
		    public ArrayList<CourseTheme> execute(Connection conn)
			    throws SQLException {
			return courseDao.readCourseThemes(conn);
		    }
		});
    }

    @Override
    public int addStudent(int idCourse, int idStudent) throws SQLException {
	final StudentCourse studentCourse;
	studentCourse = new StudentCourse();
	studentCourse.setIdCourse(idCourse);
	studentCourse.setIdStudent(idStudent);
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		int result = courseDao.addStudent(conn, studentCourse);
		ArrayList<CourseControlPoint> points = journalDao
			.getCourseControlPoints(conn,
				studentCourse.getIdCourse());
		journalDao.addMarksOnStudentAdd(conn,
			studentCourse.getIdStudent(), points);
		return result;
	    }
	}).intValue();
    }

}
