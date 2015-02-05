package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public class MysqlUserDao implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlUserDao.class);

    private static final String FIND_ALL_STUDENTS = "SELECT student.iduser, name, patronymic, sirname, login, password, email, college, isBlocked, idrole FROM user INNER JOIN student ON user.iduser=student.iduser";
    private static final String FIND_ALL_STUDENTS_BY_COURSE = "SELECT student.iduser, name, patronymic, sirname, login, password, email, college, isBlocked, idrole FROM user INNER JOIN student ON user.iduser=student.iduser WHERE student.iduser IN (select idstudent FROM student_course WHERE idcourse=?)";
    private static final String FIND_ALL_TEACHERS = "SELECT teacher.iduser, name, patronymic, sirname, login, password, email, experience, specialization, idrole FROM user INNER JOIN teacher ON user.iduser=teacher.iduser";
    private static final String CREATE_USER = "INSERT INTO user (login, password, idrole, name, sirname, patronymic, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_STUDENT = "INSERT INTO student (iduser, isBlocked, college) VALUES (?, ?, ?)";
    private static final String CREATE_TEACHER = "INSERT INTO teacher (iduser, specialization, experience) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET login=?, password=?, name=?, sirname=?, patronymic=?, email=? WHERE iduser=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE iduser=?";
    private static final String BLOCK_STUDENT = "UPDATE student SET isBlocked=1 WHERE iduser=?";
    private static final String UNBLOCK_STUDENT = "UPDATE student SET isBlocked=0 WHERE iduser=?";
    private static final String FIND_TEACHER = "SELECT teacher.iduser, name, patronymic, sirname, login, password, email, experience, specialization, idrole FROM user INNER JOIN teacher ON user.iduser=teacher.iduser WHERE user.iduser=?";
    private static final String FIND_STUDENT = "SELECT student.iduser, name, patronymic, sirname, login, password, email, college, isBlocked, idrole FROM user INNER JOIN student ON user.iduser=student.iduser WHERE user.iduser=?";
    private static final String UPDATE_STUDENT = "UPDATE student SET college=? WHERE iduser=?";
    private static final String UPDATE_TEACHER = "UPDATE teacher SET experience=?, specialization=? WHERE iduser=?";

    @Override
    public ArrayList<Student> readStudents(Connection conn) throws SQLException {
	ArrayList<Student> result = new ArrayList<Student>();
	try (Statement stm = conn.createStatement()) {
	    ResultSet rs = stm.executeQuery(FIND_ALL_STUDENTS);
	    while (rs.next()) {
		Student currentStudent = new Student();
		currentStudent.setId(rs.getInt(1));
		currentStudent.setName(rs.getString(2));
		currentStudent.setPatronymic(rs.getString(3));
		currentStudent.setSirname(rs.getString(4));
		currentStudent.setLogin(rs.getString(5));
		currentStudent.setPassword(rs.getString(6));
		currentStudent.setEmail(rs.getString(7));
		currentStudent.setCollege(rs.getString(8));
		currentStudent.setBlocked(!(rs.getInt(9) == 0));
		currentStudent.setIdRole(rs.getInt(10));
		result.add(currentStudent);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read all students ", ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<Student> readStudents(Connection conn, int idCourse)
	    throws SQLException {
	ArrayList<Student> result = new ArrayList<Student>();
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_ALL_STUDENTS_BY_COURSE)) {
	    pstm.setInt(1, idCourse);
	    ResultSet rs = pstm.executeQuery();
	    while (rs.next()) {
		Student currentStudent = new Student();
		currentStudent.setId(rs.getInt(1));
		currentStudent.setName(rs.getString(2));
		currentStudent.setPatronymic(rs.getString(3));
		currentStudent.setSirname(rs.getString(4));
		currentStudent.setLogin(rs.getString(5));
		currentStudent.setPassword(rs.getString(6));
		currentStudent.setEmail(rs.getString(7));
		currentStudent.setCollege(rs.getString(8));
		currentStudent.setBlocked(!(rs.getInt(9) == 0));
		currentStudent.setIdRole(rs.getInt(10));
		result.add(currentStudent);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read all students for course " + idCourse, ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<Teacher> readTeachers(Connection conn) throws SQLException {
	ArrayList<Teacher> result = new ArrayList<Teacher>();
	try (Statement stm = conn.createStatement()) {
	    ResultSet rs = stm.executeQuery(FIND_ALL_TEACHERS);
	    while (rs.next()) {
		Teacher currentTeacher = new Teacher();
		currentTeacher.setId(rs.getInt(1));
		currentTeacher.setName(rs.getString(2));
		currentTeacher.setPatronymic(rs.getString(3));
		currentTeacher.setSirname(rs.getString(4));
		currentTeacher.setLogin(rs.getString(5));
		currentTeacher.setPassword(rs.getString(6));
		currentTeacher.setEmail(rs.getString(7));
		currentTeacher.setExperience(rs.getInt(8));
		currentTeacher.setSpecialization(rs.getString(9));
		currentTeacher.setIdRole(rs.getInt(10));
		result.add(currentTeacher);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read all teachers ", ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public int createStudent(Connection conn, Student student)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(CREATE_STUDENT)) {
	    pstm.setInt(1, student.getId());
	    pstm.setInt(2, 0);
	    pstm.setString(3, student.getCollege());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot create student ", ex);
	    throw ex;
	}
	return student.getId();
    }

    @Override
    public int createTeacher(Connection conn, Teacher teacher)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(CREATE_TEACHER)) {
	    pstm.setInt(1, teacher.getId());
	    pstm.setString(2, teacher.getSpecialization());
	    pstm.setInt(3, teacher.getExperience());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot create teacher ", ex);
	    throw ex;
	}
	return teacher.getId();
    }

    @Override
    public int createUser(Connection conn, User user) throws SQLException {
	int autogeneratedId = 0;
	try (PreparedStatement pstm = conn.prepareStatement(CREATE_USER,
		Statement.RETURN_GENERATED_KEYS)) {
	    pstm.setString(1, user.getLogin());
	    pstm.setString(2, user.getPassword());
	    pstm.setInt(3, user.getIdRole());
	    pstm.setString(4, user.getName());
	    pstm.setString(5, user.getSirname());
	    pstm.setString(6, user.getPatronymic());
	    pstm.setString(7, user.getEmail());
	    pstm.executeUpdate();

	    ResultSet rs = pstm.getGeneratedKeys();
	    if (rs.next()) {
		autogeneratedId = rs.getInt(1);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot create user ", ex);
	    throw ex;
	}
	user.setId(autogeneratedId);
	return autogeneratedId;
    }

    @Override
    public int updateUser(Connection conn, User user) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UPDATE_USER)) {
	    pstm.setString(1, user.getLogin());
	    pstm.setString(2, user.getPassword());
	    pstm.setString(3, user.getName());
	    pstm.setString(4, user.getSirname());
	    pstm.setString(5, user.getPatronymic());
	    pstm.setString(6, user.getEmail());
	    pstm.setInt(7, user.getId());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot update user " + user.getId(), ex);
	    throw ex;
	}
	return user.getId();
    }

    @Override
    public int deleteUser(Connection conn, int idUser) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(DELETE_USER)) {
	    pstm.setInt(1, idUser);
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot update user " + idUser, ex);
	    throw ex;
	}
	return idUser;
    }

    @Override
    public int blockStudent(Connection conn, int idStudent) throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(BLOCK_STUDENT)) {
	    pstm.setInt(1, idStudent);
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot block student " + idStudent, ex);
	    throw ex;
	}
	return idStudent;
    }

    @Override
    public int unblockStudent(Connection conn, int idStudent)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UNBLOCK_STUDENT)) {
	    pstm.setInt(1, idStudent);
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot unblock student " + idStudent, ex);
	    throw ex;
	}
	return idStudent;
    }

    @Override
    public Teacher readTeacher(Connection conn, int idTeacher)
	    throws SQLException {
	Teacher result = new Teacher();
	try (PreparedStatement pstm = conn.prepareStatement(FIND_TEACHER)) {
	    pstm.setInt(1, idTeacher);
	    ResultSet rs = pstm.executeQuery(FIND_ALL_TEACHERS);
	    if (rs.next()) {
		result.setId(rs.getInt(1));
		result.setName(rs.getString(2));
		result.setPatronymic(rs.getString(3));
		result.setSirname(rs.getString(4));
		result.setLogin(rs.getString(5));
		result.setPassword(rs.getString(6));
		result.setEmail(rs.getString(7));
		result.setExperience(rs.getInt(8));
		result.setSpecialization(rs.getString(9));
		result.setIdRole(rs.getInt(10));
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read teacher ", ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public Student readStudent(Connection conn, int idStudent)
	    throws SQLException {
	Student result = new Student();
	try (PreparedStatement pstm = conn.prepareStatement(FIND_STUDENT)) {
	    pstm.setInt(1, idStudent);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Student();
		result.setId(rs.getInt(1));
		result.setName(rs.getString(2));
		result.setPatronymic(rs.getString(3));
		result.setSirname(rs.getString(4));
		result.setLogin(rs.getString(5));
		result.setPassword(rs.getString(6));
		result.setEmail(rs.getString(7));
		result.setCollege(rs.getString(8));
		result.setBlocked(!(rs.getInt(9) == 0));
		result.setIdRole(rs.getInt(10));
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read student " + idStudent, ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public int updateTeacher(Connection conn, Teacher teacher)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UPDATE_TEACHER)) {
	    pstm.setInt(1, teacher.getExperience());
	    ;
	    pstm.setString(2, teacher.getSpecialization());
	    pstm.setInt(3, teacher.getId());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot update user " + teacher.getId(), ex);
	    throw ex;
	}
	return teacher.getId();
    }

    @Override
    public int updateStudent(Connection conn, Student student)
	    throws SQLException {
	try (PreparedStatement pstm = conn.prepareStatement(UPDATE_STUDENT)) {
	    pstm.setString(1, student.getCollege());
	    pstm.setInt(2, student.getId());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot update user " + student.getId(), ex);
	    throw ex;
	}
	return student.getId();
    }

}
