package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.CourseControlPointInfo;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;

public interface JournalDao {

    ArrayList<CourseControlPointInfo> getControlPoints(Connection conn,
	    int idCourse) throws SQLException;

    ArrayList<Mark> getMarks(Connection conn, int idCourse, int idStudent)
	    throws SQLException;

    int createCourseControlPoint(Connection conn, CourseControlPoint point)
	    throws SQLException;

    int deleteCourseControlPoint(Connection conn, int courseControlPointId)
	    throws SQLException;

    int updateCourseControlPoint(Connection conn, CourseControlPoint point)
	    throws SQLException;

    int updateMark(Connection conn, Mark m) throws SQLException;

}
