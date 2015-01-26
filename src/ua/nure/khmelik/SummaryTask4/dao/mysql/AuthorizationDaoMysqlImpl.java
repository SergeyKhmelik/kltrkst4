package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Admin;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;

public class AuthorizationDaoMysqlImpl implements AuthorizationDao {

    private static final String FIND_USERINFO_BY_LOGIN_PASSWORD = "SELECT iduser, idrole FROM user WHERE login=? AND password=?";
    private static final String FIND_STUDENT_BY_USERID = "SELECT name, patronymic, sirname, email, college, isBlocked FROM user INNER JOIN student ON user.iduser = student.iduser WHERE user.iduser=?";
    private static final String FIND_TEACHER_BY_USERID = "SELECT name, patronymic, sirname, email, specialization, experience FROM user INNER JOIN teacher ON user.iduser = teacher.iduser WHERE user.iduser=?";
    private static final String FIND_ADMIN_BY_USERID = "SELECT name, patronymic, sirname, email, phone FROM user INNER JOIN admin ON user.iduser = admin.iduser WHERE user.iduser=?";
    private static final String FIND_PERMITIONS_BY_ROLEID = "SELECT * FROM permission WHERE idpermission IN (SELECT idpermission FROM role_permission WHERE idrole=?)";

    @Override
    public int[] getUserIdRoleId(Connection conn, String login, String password)
	    throws SQLException {
	int roleId, userId;
	int[] result = new int[2];

	// Search for userID and roleID
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_USERINFO_BY_LOGIN_PASSWORD)) {
	    pstm.setString(1, login);
	    pstm.setString(2, password);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		userId = rs.getInt(1);
		roleId = rs.getInt(2);
	    } else {
		return new int[] { 0, 0 };
	    }
	    result[0] = userId;
	    result[1] = roleId;
	} catch (SQLException e) {
	    // Logger
	    throw e;
	}
	return result;
    }

    @Override
    public Student readStudentById(Connection conn, int idStudent)
	    throws SQLException {
	Student result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_STUDENT_BY_USERID)) {
	    pstm.setInt(1, idStudent);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Student();
		result.setId(idStudent);
		result.setName(rs.getString(1));
		result.setPatronymic(rs.getString(2));
		result.setSirname(rs.getString(3));
		result.setEmail(rs.getString(4));
		result.setCollege(rs.getString(5));
		result.setBlocked(!(rs.getInt(6) == 0));
	    }
	} catch (SQLException e) {
	    // Logger
	    throw e;
	}
	return result;
    }

    @Override
    public Teacher readTeacherById(Connection conn, int idTeacher)
	    throws SQLException {
	Teacher result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_TEACHER_BY_USERID)) {
	    pstm.setInt(1, idTeacher);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Teacher();
		result.setId(idTeacher);
		result.setName(rs.getString(1));
		result.setPatronymic(rs.getString(2));
		result.setSirname(rs.getString(3));
		result.setEmail(rs.getString(4));
		result.setSpecialization(rs.getString(5));
		result.setExperience(rs.getInt(6));
	    }
	} catch (SQLException e) {
	    // Logger
	    throw e;
	}
	return result;
    }

    @Override
    public Admin readAdminById(Connection conn, int idAdmin)
	    throws SQLException {
	Admin result = null;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_ADMIN_BY_USERID)) {
	    pstm.setInt(1, idAdmin);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Admin();
		result.setId(idAdmin);
		result.setName(rs.getString(1));
		result.setPatronymic(rs.getString(2));
		result.setSirname(rs.getString(3));
		result.setEmail(rs.getString(4));
		result.setPhone(rs.getInt(5));
	    }
	} catch (SQLException e) {
	    // Logger
	    throw e;
	}
	return result;
    }

    @Override
    public ArrayList<Permission> getPermitionsByRole(Connection conn, int roleId)
	    throws SQLException {
	ArrayList<Permission> result = new ArrayList<Permission>();
	Permission currentPermission;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_PERMITIONS_BY_ROLEID)) {
	    pstm.setInt(1, roleId);

	    ResultSet rs = pstm.executeQuery();
	    while (rs.next()) {
		currentPermission = new Permission();
		currentPermission.setId(rs.getInt(1));
		currentPermission.setName(rs.getString(2));
		currentPermission.setDescription(rs.getString(3));
		result.add(currentPermission);
	    }
	} catch (SQLException e) {
	    // Logger
	    throw e;
	}
	return result;
    }

}
