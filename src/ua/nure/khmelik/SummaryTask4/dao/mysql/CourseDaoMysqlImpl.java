package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;

public class CourseDaoMysqlImpl implements CourseDao {

    private static final String FIND_COURSE_THEMES = "SELECT * FROM course_theme";
    private static final String FIND_INCOMING_COURSES = "SELECT * FROM course WHERE start>?";
    private static final String FIND_INCOMING_COURSES_WITH_TID = "SELECT * FROM course WHERE start>? AND idteacher=?";
    private static final String FIND_STUDENT_PASSED_COURSES = "SELECT * FROM course WHERE end<? AND idcourse IN (SELECT idcourse FROM student_course WHERE idstudent=?)";
    private static final String FIND_TEACHER_CURRENT_COURSES = "SELECT * FROM course WHERE idteacher=? AND start<? AND end>?";
    private static final String CREATE_COURSE = "INSERT INTO course (name, start, end, idtheme) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_COURSE = "UPDATE course SET name=?, start=?, end=? WHERE idcourse=?";
    private static final String DELETE_COURSE = "DELETE FROM course WHERE idcourse=?";
    private static final String ADD_STUDENT = "INSERT INTO student_course (idstudent, idcourse) VALUES (?, ?)";

    public ArrayList<Course> getIncomingCourses(Connection conn)
	    throws SQLException {
	ArrayList<Course> result = new ArrayList<Course>();
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_INCOMING_COURSES)) {
	    java.util.Date now = new java.util.Date();
	    pstm.setDate(1, new java.sql.Date(now.getTime()));
	    ResultSet rs = pstm.executeQuery();
	    while (rs.next()) {
		Course currentCourse = new Course();
		currentCourse.setId(rs.getInt(1));
		currentCourse.setName(rs.getString(2));
		currentCourse.setStart(rs.getDate(3));
		currentCourse.setEnd(rs.getDate(4));
		result.add(currentCourse);
	    }
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return result;
    }

    public ArrayList<Course> getIncomingCourses(Connection conn, int teacherId)
	    throws SQLException {
	ArrayList<Course> result = new ArrayList<Course>();

	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_INCOMING_COURSES_WITH_TID)) {
	    java.util.Date now = new java.util.Date();
	    pstm.setDate(1, new java.sql.Date(now.getTime()));
	    pstm.setInt(2, teacherId);
	    ResultSet rs = pstm.executeQuery();

	    while (rs.next()) {
		Course currentCourse = new Course();
		currentCourse.setId(rs.getInt(1));
		currentCourse.setName(rs.getString(2));
		currentCourse.setStart(rs.getDate(3));
		currentCourse.setEnd(rs.getDate(4));
		result.add(currentCourse);
	    }
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return result;
    }

    public ArrayList<Course> getPassedCourses(Connection conn, int studentId)
	    throws SQLException {
	ArrayList<Course> result = new ArrayList<Course>();
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_STUDENT_PASSED_COURSES)) {
	    java.util.Date now = new java.util.Date();
	    pstm.setDate(1, new java.sql.Date(now.getTime()));
	    pstm.setInt(2, studentId);
	    ResultSet rs = pstm.executeQuery();

	    while (rs.next()) {
		Course currentCourse = new Course();
		currentCourse.setId(rs.getInt(1));
		currentCourse.setName(rs.getString(2));
		currentCourse.setStart(rs.getDate(3));
		currentCourse.setEnd(rs.getDate(4));
		result.add(currentCourse);
	    }
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return result;
    }

    public ArrayList<Course> getCurrentCourses(Connection conn, int teacherId)
	    throws SQLException {
	ArrayList<Course> result = new ArrayList<Course>();
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_TEACHER_CURRENT_COURSES)) {
	    java.util.Date now = new java.util.Date();
	    pstm.setInt(1, teacherId);
	    pstm.setDate(2, new java.sql.Date(now.getTime()));
	    pstm.setDate(3, new java.sql.Date(now.getTime()));

	    ResultSet rs = pstm.executeQuery();

	    while (rs.next()) {
		Course currentCourse = new Course();
		currentCourse.setId(rs.getInt(1));
		currentCourse.setName(rs.getString(2));
		currentCourse.setStart(rs.getDate(3));
		currentCourse.setEnd(rs.getDate(4));
		result.add(currentCourse);
	    }
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<CourseTheme> getCourseThemes(Connection conn)
	    throws SQLException {
	ArrayList<CourseTheme> result = new ArrayList<CourseTheme>();
	try (Statement stm = conn.createStatement()) {
	    ResultSet rs = stm.executeQuery(FIND_COURSE_THEMES);
	    while (rs.next()) {
		CourseTheme currentTheme = new CourseTheme();
		currentTheme.setId(rs.getInt(1));
		currentTheme.setName(rs.getString(2));
		currentTheme.setDescription(rs.getString(3));
		result.add(currentTheme);
	    }
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return result;
    }

    public int createCourse(Connection conn, Course course) throws SQLException {
	int idCourse = 0;
	try (PreparedStatement pstm = conn.prepareStatement(CREATE_COURSE,
		Statement.RETURN_GENERATED_KEYS)) {
	    pstm.setString(1, course.getName());
	    pstm.setDate(2, course.getStart());
	    pstm.setDate(3, course.getEnd());
	    pstm.setInt(4, course.getIdTheme());
	    pstm.executeUpdate();

	    ResultSet rs = pstm.getGeneratedKeys();
	    if (rs.next()) {
		idCourse = rs.getInt(1);
	    }
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return idCourse;
    }

    public int updateCourse(Connection conn, Course course) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UPDATE_COURSE)) {
	    pstm.setString(1, course.getName());
	    pstm.setDate(2, course.getStart());
	    pstm.setDate(3, course.getEnd());
	    pstm.setInt(4, course.getId());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return course.getId();
    }

    public int deleteCourse(Connection conn, int courseId) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(DELETE_COURSE)) {
	    pstm.setInt(1, courseId);
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return courseId;
    }

    @Override
    public int addStudent(Connection conn, StudentCourse stc)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(ADD_STUDENT)) {
	    pstm.setInt(1, stc.getIdStudent());
	    pstm.setInt(2, stc.getIdCourse());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return stc.getIdStudent();
    }

}
