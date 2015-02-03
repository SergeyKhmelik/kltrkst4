package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.entity.data.StudentData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.service.UserService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlUserService implements UserService {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlUserService.class);

    private TransactionManager transactionManager;
    private UserDao userDao;
    private PermissionDao permissionDao;

    public MysqlUserService(TransactionManager transactionManager,
	    UserDao userDao, PermissionDao permissionDao) {
	this.transactionManager = transactionManager;
	this.userDao = userDao;
	this.permissionDao = permissionDao;
    }

    @Override
    public ArrayList<TeacherData> readTeachers() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<TeacherData>>() {

		    @Override
		    public ArrayList<TeacherData> execute(Connection conn)
			    throws SQLException {
			ArrayList<TeacherData> teacherDatas = new ArrayList<TeacherData>();
			ArrayList<Teacher> teachers = userDao
				.readTeachers(conn);
			System.out.println("TEACHERI SUK" + teachers);
			Role role = null;
			for (Teacher teacher : teachers) {
			    TeacherData teacherData = convertTeacherBeanToData(teacher);
			    if (role == null) {
				role = permissionDao.readRole(conn,
					teacher.getIdRole());
			    }
			    teacherData.setRole(role);
			    teacherDatas.add(teacherData);
			}
			return teacherDatas;
		    }
		});
    }

    @Override
    public ArrayList<StudentData> readStudents() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<StudentData>>() {

		    @Override
		    public ArrayList<StudentData> execute(Connection conn)
			    throws SQLException {
			ArrayList<StudentData> studentDatas = new ArrayList<StudentData>();
			ArrayList<Student> students = userDao
				.readStudents(conn);
			Role role = null;
			for (Student student : students) {
			    StudentData studentData = convertStudentBeanToData(student);
			    if (role == null) {
				role = permissionDao.readRole(conn,
					student.getIdRole());
			    }
			    studentData.setRole(role);
			    studentDatas.add(studentData);
			}
			return studentDatas;
		    }
		});
    }

    @Override
    public int addStudent(final StudentData studentData) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		Student student = convertStudentDataToBean(studentData);
		userDao.createUser(conn, student);
		return userDao.createStudent(conn, student);
	    }
	}).intValue();
    }

    @Override
    public int addTeacher(final TeacherData teacherData) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		Teacher teacher = convertTeacherDataToBean(teacherData);
		userDao.createUser(conn, teacher);
		return userDao.createTeacher(conn, teacher);
	    }

	}).intValue();
    }

    @Override
    public int deleteUser(final int idUser) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return userDao.deleteUser(conn, idUser);
	    }

	}).intValue();
    }

    @Override
    public int blockStudent(final int idStudent, final boolean block)
	    throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		if (block) {
		    return userDao.blockStudent(conn, idStudent);
		} else {
		    return userDao.unblockStudent(conn, idStudent);
		}
	    }

	}).intValue();
    }

    @Override
    public int updateStudent(final StudentData studentData) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		Student student = convertStudentDataToBean(studentData);
		userDao.updateUser(conn, student);
		userDao.updateStudent(conn, student);
		return student.getId();
	    }
	}).intValue();
    }

    @Override
    public int updateTeacher(final TeacherData teacherData) throws SQLException {
	return transactionManager.doTransaction(new Operation<Integer>() {

	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		Teacher teacher = convertTeacherDataToBean(teacherData);
		userDao.updateUser(conn, teacher);
		userDao.updateTeacher(conn, teacher);
		return teacher.getId();
	    }
	}).intValue();
    }

    private TeacherData convertTeacherBeanToData(Teacher teacherBean) {
	TeacherData teacherData = new TeacherData();
	setUserInfoFromBeanToData(teacherBean, teacherData);
	teacherData.setExperience(teacherBean.getExperience());
	teacherData.setSpecialization(teacherBean.getSpecialization());
	return teacherData;
    }

    private StudentData convertStudentBeanToData(Student studentBean) {
	StudentData studentData = new StudentData();
	setUserInfoFromBeanToData(studentBean, studentData);
	studentData.setCollege(studentBean.getCollege());
	studentData.setBlocked(studentBean.isBlocked());
	return studentData;
    }

    private Student convertStudentDataToBean(StudentData studentData) {
	Student student = new Student();
	setUserInfoFromDataToBean(studentData, student);
	student.setCollege(studentData.getCollege());
	student.setBlocked(studentData.isBlocked());
	student.setIdRole(studentData.getRole().getId());
	return student;
    }

    private Teacher convertTeacherDataToBean(TeacherData teacherData) {
	Teacher teacher = new Teacher();
	setUserInfoFromDataToBean(teacherData, teacher);
	teacher.setExperience(teacherData.getExperience());
	teacher.setSpecialization(teacherData.getSpecialization());
	teacher.setIdRole(teacherData.getRole().getId());
	return teacher;
    }

    private void setUserInfoFromBeanToData(User userBean, UserData userData) {
	userData.setIdUser(userBean.getId());
	userData.setName(userBean.getName());
	userData.setPatronymic(userBean.getPatronymic());
	userData.setSirname(userBean.getSirname());
	userData.setEmail(userBean.getEmail());
	userData.setLogin(userBean.getLogin());
	userData.setPassword(userBean.getPassword());
    }

    private void setUserInfoFromDataToBean(UserData userData, User userBean) {
	userBean.setId(userData.getIdUser());
	userBean.setName(userData.getName());
	userBean.setPatronymic(userData.getPatronymic());
	userBean.setSirname(userData.getSirname());
	userBean.setEmail(userData.getEmail());
	userBean.setLogin(userData.getLogin());
	userBean.setPassword(userData.getPassword());
    }
}
