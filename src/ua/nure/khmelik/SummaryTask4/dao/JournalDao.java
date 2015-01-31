package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.CourseControlPointInfo;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;

public interface JournalDao {

    ArrayList<CourseControlPointInfo> readControlPoints(Connection conn,
	    int idCourse) throws SQLException;

    ArrayList<Mark> readMarks(Connection conn, int idStudent, int idCourse)
	    throws SQLException;

    int createCourseControlPoint(Connection conn, CourseControlPoint point)
	    throws SQLException;

    int deleteCourseControlPoint(Connection conn, int idCourseControlPoint)
	    throws SQLException;

    int updateCourseControlPoint(Connection conn, CourseControlPoint point)
	    throws SQLException;

    int updateMark(Connection conn, Mark mark) throws SQLException;

    int addMarksOnStudentAdd(Connection conn, int idStudent,
	    ArrayList<CourseControlPoint> points) throws SQLException;

    int addMarksOnCourseControlPointAdd(Connection conn,
	    int idCourseControlPoint, ArrayList<StudentCourse> students)
	    throws SQLException;

    ArrayList<StudentCourse> getCourseStudents(Connection conn, int idCourse)
	    throws SQLException;

    ArrayList<CourseControlPoint> getCourseControlPoints(Connection conn,
	    int idCourse) throws SQLException;

}
