package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.entity.CourseControlPointInfo;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;

public class MysqlJournalDao implements JournalDao {

    private static final String READ_COURSE_POINTS = "SELECT idcourse_controlpoint, course_controlpoint.idcontrol_point, idcourse, date, name, description FROM course_controlpoint INNER JOIN control_point ON course_controlpoint.idcontrol_point=control_point.idcontrol_point WHERE idcourse=?";
    private static final String READ_MARKS = "SELECT idstudent, idcourse_controlpoint, value FROM mark WHERE idstudent=? AND idcourse_controlpoint IN (SELECT idcourse_controlpoint FROM course_controlpoint WHERE idcourse=?)";
    private static final String CREATE_COURSE_CP = "INSERT INTO course_controlpoint (idcourse, idcontrol_point, date) VALUES (?,?,?)";
    private static final String DELETE_COURSE_CP = "DELETE FROM course_controlpoint WHERE idcourse_controlpoint=?";
    private static final String UPDATE_DATE_COUSE_CP = "UPDATE course_controlpoint SET date=? WHERE idcourse_controlpoint=?";
    private static final String UPDATE_MARK = "INSERT INTO mark (idstudent, idcourse_controlpoint, value) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE value=?";

    /**
     * idControlPoint idCourse idCCP Date CName CDescr
     * 
     * @throws SQLException
     */
    @Override
    public ArrayList<CourseControlPointInfo> getControlPoints(Connection conn,
	    int idCourse) throws SQLException {
	ArrayList<CourseControlPointInfo> result = new ArrayList<CourseControlPointInfo>();

	try (PreparedStatement pstm = conn.prepareStatement(READ_COURSE_POINTS)) {
	    pstm.setInt(1, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    CourseControlPointInfo currentCCPInfo;
	    while (rs.next()) {
		currentCCPInfo = new CourseControlPointInfo();
		currentCCPInfo.setIdCourseControlPoint(rs.getInt(1));
		currentCCPInfo.setIdControlPoint(rs.getInt(2));
		currentCCPInfo.setIdCourse(rs.getInt(3));
		currentCCPInfo.setDate(rs.getDate(4));
		currentCCPInfo.setCourseName(rs.getString(5));
		currentCCPInfo.setCourseDescription(rs.getString(6));
		result.add(currentCCPInfo);
	    }
	} catch (SQLException ex) {
	    // Logger
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<Mark> getMarks(Connection conn, int idStudent, int idCourse)
	    throws SQLException {
	ArrayList<Mark> result = new ArrayList<Mark>();

	try (PreparedStatement pstm = conn.prepareStatement(READ_MARKS)) {
	    pstm.setInt(1, idStudent);
	    pstm.setInt(2, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    Mark currentMark;
	    while (rs.next()) {
		currentMark = new Mark();
		currentMark.setStudentId(rs.getInt(1));
		currentMark.setCourseControlpointId(rs.getInt(2));
		currentMark.setValue(rs.getInt(3));
		result.add(currentMark);
	    }
	} catch (SQLException ex) {
	    // Logger
	    throw ex;
	}
	return result;
    }

    @Override
    public int createCourseControlPoint(Connection conn,
	    CourseControlPoint point) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(CREATE_COURSE_CP)) {
	    pstm.setInt(1, point.getIdCourse());
	    pstm.setInt(2, point.getIdControlPoint());
	    pstm.setDate(3, point.getDate());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logger
	    throw ex;
	}
	return point.getIdCourse();
    }

    @Override
    public int deleteCourseControlPoint(Connection conn,
	    int idCourseControlPoint) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(DELETE_COURSE_CP)) {
	    pstm.setInt(1, idCourseControlPoint);
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logger
	    throw ex;
	}
	return idCourseControlPoint;
    }

    /**
     * Set another date
     */
    @Override
    public int updateCourseControlPoint(Connection conn,
	    CourseControlPoint point) throws SQLException {
	try (PreparedStatement pstm = conn
		.prepareStatement(UPDATE_DATE_COUSE_CP)) {
	    pstm.setInt(1, point.getIdCourseControlPoint());
	    pstm.setDate(2, point.getDate());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logger
	    throw ex;
	}
	return point.getIdCourseControlPoint();
    }

    /**
     * INSERT ... ON DUPLICATE KEY UPDATE
     * 
     * idStudent idCCP value
     * 
     * @throws SQLException
     */
    @Override
    public int updateMark(Connection conn, Mark mark) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UPDATE_MARK)) {
	    pstm.setInt(1, mark.getStudentId());
	    pstm.setInt(2, mark.getCourseControlpointId());
	    pstm.setInt(3, mark.getValue());
	    pstm.setInt(4, mark.getValue());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logger
	    throw ex;
	}
	return mark.getCourseControlpointId();
    }

}
