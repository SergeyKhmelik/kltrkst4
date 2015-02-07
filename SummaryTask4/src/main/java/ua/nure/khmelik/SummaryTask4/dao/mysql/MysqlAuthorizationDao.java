package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Admin;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public class MysqlAuthorizationDao implements AuthorizationDao {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlAuthorizationDao.class);

    private static final String FIND_USER_BY_LOGIN_PASSWORD = "SELECT iduser, name, patronymic, sirname, email, idrole FROM user WHERE login=? AND password=?";
    private static final String FIND_STUDENT_BY_USERID = "SELECT * FROM student WHERE student.iduser=?";
    private static final String FIND_TEACHER_BY_USERID = "SELECT * FROM teacher WHERE teacher.iduser=?";
    private static final String FIND_ADMIN_BY_USERID = "SELECT * FROM admin WHERE admin.iduser=?";

    @Override
    public User getUser(Connection conn, String login, String password)
	    throws SQLException {
	User result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_USER_BY_LOGIN_PASSWORD)) {
	    pstm.setString(1, login);
	    pstm.setString(2, password);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new User();
		result.setId(rs.getInt(1));
		result.setName(rs.getString(2));
		result.setPatronymic(rs.getString(3));
		result.setSirname(rs.getString(4));
		result.setEmail(rs.getString(5));
		result.setIdRole(rs.getInt(6));
		result.setLogin(login);
		result.setPassword(password);
	    }
	} catch (SQLException e) {
	    LOGGER.error("Cannot read user info ", e);
	    throw e;
	}
	return result;
    }

    @Override
    public Student readStudent(Connection conn, User user)
	    throws SQLException {
	Student result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_STUDENT_BY_USERID)) {
	    pstm.setInt(1, user.getId());
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Student();
		result.setId(user.getId());
		result.setBlocked(!(rs.getInt(2) == 0));
		result.setCollege(rs.getString(3));
	    }
	} catch (SQLException e) {
	    LOGGER.error("Cannot read student entity ", e);
	    throw e;
	}
	return result;
    }

    @Override
    public Teacher readTeacher(Connection conn, User user)
	    throws SQLException {
	Teacher result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_TEACHER_BY_USERID)) {
	    pstm.setInt(1, user.getId());
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Teacher();
		result.setId(user.getId());
		result.setSpecialization(rs.getString(2));
		result.setExperience(rs.getInt(3));
	    }
	} catch (SQLException e) {
	    LOGGER.error("Cannot read teacher entity ", e);
	    throw e;
	}
	return result;
    }

    @Override
    public Admin readAdmin(Connection conn, User user) throws SQLException {
	Admin result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_ADMIN_BY_USERID)) {
	    pstm.setInt(1, user.getId());
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Admin();
		result.setId(user.getId());
		result.setPhone(rs.getInt(2));
	    }
	} catch (SQLException e) {
	    LOGGER.error("Cannot read admin entity ", e);
	    throw e;
	}
	return result;
    }

}
