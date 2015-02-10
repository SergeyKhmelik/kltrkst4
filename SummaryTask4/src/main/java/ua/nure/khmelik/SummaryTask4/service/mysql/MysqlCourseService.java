package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;
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
    private UserDao userDao;

    public MysqlCourseService(TransactionManager transactionManager,
	    CourseDao courseDao, UserDao userDao) {
	this.transactionManager = transactionManager;
	this.courseDao = courseDao;
	this.userDao = userDao;
    }

    @Override
    public ArrayList<CourseData> getIncomingCourses() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readIncomingCourses(conn);
			for(Course course: courses){
			    CourseData courseData = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }
    
    @Override
    public ArrayList<CourseData> getPassedCourses() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readPassedCourses(conn);
			for(Course course: courses){
			    CourseData courseData = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }
    
    @Override
    public ArrayList<CourseData> getPassedStudentsCourses(final int idStudent) throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readPassedStudentsCourses(conn, idStudent);
			for(Course course: courses){
			    CourseData courseData  = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }

    @Override
    public ArrayList<CourseData> getPassedTeachersCourses(final int idTeacher)
	    throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readPassedStudentsCourses(conn, idTeacher);
			for(Course course: courses){
			    CourseData courseData  = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }
    
    @Override
    public ArrayList<CourseData> getCurrentCourses() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readCurrentCourses(conn);
			for(Course course: courses){
			    CourseData courseData = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }
    
    @Override
    public ArrayList<CourseData> getCurrentTeachersCourses(final int idTeacher) throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readCurrentTeachersCourses(conn, idTeacher);
			for(Course course: courses){
			    CourseData courseData  = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }

    @Override
    public ArrayList<CourseData> getCurrentStudentsCourses(final int idStudent) throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<CourseData>>() {

		    @Override
		    public ArrayList<CourseData> execute(Connection conn)
			    throws SQLException {
			ArrayList<CourseData> result = new ArrayList<CourseData>();
			ArrayList<Course> courses = courseDao.readCurrentStudentsCourses(conn, idStudent);
			for(Course course: courses){
			    CourseData courseData  = convertCourseBeanToData(course);
			    courseData.setTheme(courseDao.readTheme(conn, course.getIdTheme()));
			    courseData.setTeacher(userDao.readTeacher(conn, course.getIdTeacher()));
			    result.add(courseData);
			}
			return result;
		    }
		});
    }
    
    @Override
    public int createCourse(final CourseData courseData) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		
		return courseDao.createCourse(conn, convertCourseDataToBean(courseData));
	    }
	}).intValue();
    }

    @Override
    public int updateCourse(final CourseData courseData) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return courseDao.updateCourse(conn, convertCourseDataToBean(courseData));
	    }
	}).intValue();
    }

    @Override
    public int deleteCourse(final int idCourse) throws SQLException {
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
	final StudentCourse studentCourse  = new StudentCourse();
	studentCourse.setIdCourse(idCourse);
	studentCourse.setIdStudent(idStudent);
	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		int result = courseDao.addStudent(conn, studentCourse);
		ArrayList<CourseControlPoint> points = journalDao.getCourseControlPoints(conn,
				studentCourse.getIdCourse());
		journalDao.addMarksOnStudentAdd(conn,
			studentCourse.getIdStudent(), points);
		return result;
	    }
	}).intValue();
    }
    
    private Course convertCourseDataToBean(CourseData courseData){
	Course course = new Course();
	course.setId(courseData.getIdCourse());
	course.setName(courseData.getName());
	course.setStart(courseData.getStart());
	course.setEnd(courseData.getEnd());
	course.setIdTheme(courseData.getTeacher().getId());
	course.setIdTheme(courseData.getTheme().getId());
	return course;
    }
    
    private CourseData convertCourseBeanToData(Course course){
	 CourseData courseData = new CourseData();
	    courseData.setIdCourse(course.getId());
	    courseData.setName(course.getName());
	    courseData.setStart(course.getStart());
	    courseData.setEnd(course.getEnd());
	    return courseData;
    }
    
}
