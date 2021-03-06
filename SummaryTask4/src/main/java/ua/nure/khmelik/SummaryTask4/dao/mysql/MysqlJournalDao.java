package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.entity.data.CourseControlPointData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;

public class MysqlJournalDao implements JournalDao {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlJournalDao.class);

    private static final String READ_COURSE_POINTS = "SELECT idcourse_controlpoint, course_controlpoint.idcontrol_point, idcourse, date, name, description FROM course_controlpoint INNER JOIN control_point ON course_controlpoint.idcontrol_point=control_point.idcontrol_point WHERE idcourse=?";
    private static final String READ_MARKS = "SELECT idstudent, idcourse_controlpoint, value FROM mark WHERE idstudent=? AND idcourse_controlpoint IN (SELECT idcourse_controlpoint FROM course_controlpoint WHERE idcourse=?)";
    private static final String CREATE_COURSE_CP = "INSERT INTO course_controlpoint (idcourse, idcontrol_point, date) VALUES (?,?,?)";
    private static final String DELETE_COURSE_CP = "DELETE FROM course_controlpoint WHERE idcourse_controlpoint=?";
    private static final String UPDATE_DATE_COUSE_CP = "UPDATE course_controlpoint SET date=? WHERE idcourse_controlpoint=?";
    private static final String UPDATE_MARK = "INSERT INTO mark (idstudent, idcourse_controlpoint, value) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE value=?";
    private static final String INSERT_MARKS = "INSERT INTO mark (idstudent, idcourse_controlpoint, value) VALUES (?, ?, ?)";
    private static final String READ_SRUDENTS_IDS = "SELECT idstudent FROM student_course WHERE idcourse=?";
    private static final String READ_CCP_IDS = "SELECT idcourse_controlpoint FROM course_controlpoint WHERE idcourse=?";

    /**
     * idControlPoint idCourse idCCP Date CName CDescr
     * 
     * @throws SQLException
     */
    @Override
    public ArrayList<CourseControlPointData> readControlPoints(Connection conn,
	    int idCourse) throws SQLException {
	ArrayList<CourseControlPointData> result = new ArrayList<CourseControlPointData>();

	try (PreparedStatement pstm = conn.prepareStatement(READ_COURSE_POINTS)) {
	    pstm.setInt(1, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    CourseControlPointData currentCCPInfo;
	    while (rs.next()) {
		currentCCPInfo = new CourseControlPointData();
		currentCCPInfo.setIdCourseControlPoint(rs.getInt(1));
		currentCCPInfo.setIdControlPoint(rs.getInt(2));
		currentCCPInfo.setIdCourse(rs.getInt(3));
		currentCCPInfo.setDate(rs.getDate(4));
		currentCCPInfo.setCourseName(rs.getString(5));
		currentCCPInfo.setCourseDescription(rs.getString(6));
		result.add(currentCCPInfo);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read controlpoint entities with idCourse"
		    + idCourse, ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<Mark> readMarks(Connection conn, int idStudent,
	    int idCourse) throws SQLException {
	ArrayList<Mark> result = new ArrayList<Mark>();

	try (PreparedStatement pstm = conn.prepareStatement(READ_MARKS)) {
	    pstm.setInt(1, idStudent);
	    pstm.setInt(2, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    Mark currentMark;
	    while (rs.next()) {
		currentMark = new Mark();
		currentMark.setIdStudent(rs.getInt(1));
		currentMark.setIdCourseControlpoint(rs.getInt(2));
		currentMark.setValue(rs.getInt(3));
		result.add(currentMark);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read mark entities with idStudent "
		    + idStudent + " course " + idCourse, ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public int createCourseControlPoint(Connection conn,
	    CourseControlPoint point) throws SQLException {
	int result = 0;
	try (PreparedStatement pstm = conn.prepareStatement(CREATE_COURSE_CP, Statement.RETURN_GENERATED_KEYS)) {
	    pstm.setInt(1, point.getIdCourse());
	    pstm.setInt(2, point.getIdControlPoint());
	    pstm.setDate(3, point.getDate());
	    pstm.executeUpdate();
	    
	    ResultSet rs = pstm.getGeneratedKeys();
	    while(rs.next()){
		result = rs.getInt(1);
	    }
	    
	} catch (SQLException ex) {
	    LOGGER.error("Cannot create a course controlpoint ", ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public int deleteCourseControlPoint(Connection conn,
	    int idCourseControlPoint) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(DELETE_COURSE_CP)) {
	    pstm.setInt(1, idCourseControlPoint);
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot delete course control point with id "
		    + idCourseControlPoint, ex);
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
	    LOGGER.error(
		    "Cannot update course control point with id "
			    + point.getIdCourseControlPoint(), ex);
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
     * 
     * @inheritDao
     */
    @Override
    public int updateMark(Connection conn, Mark mark) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UPDATE_MARK)) {
	    pstm.setInt(1, mark.getIdStudent());
	    pstm.setInt(2, mark.getIdCourseControlpoint());
	    pstm.setInt(3, mark.getValue());
	    pstm.setInt(4, mark.getValue());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot update a mark ", ex);
	    throw ex;
	}
	return mark.getIdCourseControlpoint();
    }

    @Override
    public int addMarksOnStudentAdd(Connection conn, int idStudent,
	    ArrayList<CourseControlPoint> points) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(INSERT_MARKS)) {
	    for (CourseControlPoint courseControlPoint : points) {
		pstm.setInt(1, idStudent);
		pstm.setInt(2, courseControlPoint.getIdCourseControlPoint());
		pstm.setInt(3, 0);
		pstm.addBatch();
	    }
	    pstm.executeBatch();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot add marks on student add. ", ex);
	    throw ex;
	}
	return idStudent;
    }

    @Override
    public int addMarksOnCourseControlPointAdd(Connection conn,
	    int idCourseControlPoint, ArrayList<StudentCourse> students)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(INSERT_MARKS)) {
	    for (StudentCourse student : students) {
		pstm.setInt(1, student.getIdStudent());
		pstm.setInt(2, idCourseControlPoint);
		pstm.setInt(3, 0);
		pstm.addBatch();
	    }
	    pstm.executeBatch();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot add marks on course control point add. ", ex);
	    throw ex;
	}
	return idCourseControlPoint;
    }

    @Override
    public ArrayList<StudentCourse> getCourseStudents(Connection conn,
	    int idCourse) throws SQLException {
	ArrayList<StudentCourse> result = new ArrayList<StudentCourse>();
	try (PreparedStatement pstm = conn.prepareStatement(READ_SRUDENTS_IDS)) {
	    pstm.setInt(1, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    while (rs.next()) {
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setIdStudent(rs.getInt(1));
		result.add(studentCourse);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot get course students. ", ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<CourseControlPoint> getCourseControlPoints(
	    Connection conn, int idCourse) throws SQLException {
	ArrayList<CourseControlPoint> result = new ArrayList<CourseControlPoint>();
	try (PreparedStatement pstm = conn.prepareStatement(READ_CCP_IDS)){
	    pstm.setInt(1, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    while(rs.next()){
		CourseControlPoint courseControlPoint = new CourseControlPoint();
		courseControlPoint.setIdCourseControlPoint(rs.getInt(1));
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot get course control points. ", ex);
	    throw ex;
	}
	return result;
    }

}
