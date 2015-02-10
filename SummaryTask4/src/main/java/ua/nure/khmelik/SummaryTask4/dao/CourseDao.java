package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;

public interface CourseDao {

    ArrayList<Course> readIncomingCourses(Connection conn) throws SQLException;

    ArrayList<Course> readPassedCourses(Connection conn) throws SQLException;

    ArrayList<Course> readPassedStudentsCourses(Connection conn, int idStudent)
	    throws SQLException;

    ArrayList<Course> readPassedTeachersCourses(Connection conn, int idTeacher)
	    throws SQLException;

    ArrayList<Course> readCurrentCourses(Connection conn) throws SQLException;

    ArrayList<Course> readCurrentStudentsCourses(Connection conn, int idStudent)
	    throws SQLException;

    ArrayList<Course> readCurrentTeachersCourses(Connection conn, int idTeacher)
	    throws SQLException;

    ArrayList<CourseTheme> readCourseThemes(Connection conn)
	    throws SQLException;

    int createCourse(Connection conn, Course course) throws SQLException;

    int updateCourse(Connection conn, Course course) throws SQLException;

    int deleteCourse(Connection conn, int idCourse) throws SQLException;

    int addStudent(Connection conn, StudentCourse studentCourse)
	    throws SQLException;

    CourseTheme readTheme(Connection conn, int idTheme) throws SQLException;

}
