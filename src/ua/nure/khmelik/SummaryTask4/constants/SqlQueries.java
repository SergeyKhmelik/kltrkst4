package ua.nure.khmelik.SummaryTask4.constants;

public final class SqlQueries {

	
	/**
	 * Authorization constants
	 */
	public static final String FIND_USERINFO_BY_LOGIN_PASSWORD = "SELECT iduser, idrole FROM user WHERE login=? AND password=?";
	public static final String FIND_STUDENT_BY_USERID = "SELECT name, patronymic, sirname, email, college, isBlocked FROM user INNER JOIN student ON user.iduser = student.iduser WHERE user.iduser=?";
	public static final String FIND_TEACHER_BY_USERID = "SELECT name, patronymic, sirname, email, specialization, experience FROM user INNER JOIN teacher ON user.iduser = teacher.iduser WHERE user.iduser=?";
	public static final String FIND_ADMIN_BY_USERID = "SELECT name, patronymic, sirname, email, phone FROM user INNER JOIN admin ON user.iduser = admin.iduser WHERE user.iduser=?";
	public static final String FIND_PERMITIONS_BY_ROLEID = "SELECT * FROM permission WHERE idpermission IN (SELECT idpermission FROM role_permission WHERE idrole=?)";
	
	/**
	 * UserManagement constants
	 */
	public static final String FIND_ALL_STUDENTS = "SELECT student.iduser, name, patronymic, sirname, login, password, email, college, isBlocked FROM user INNER JOIN student ON user.iduser=student.iduser";
	public static final String FIND_ALL_TEACHERS = "SELECT teacher.iduser, name, patronymic, sirname, login, password, email, experience, specialization FROM user INNER JOIN teacher ON user.iduser=teacher.iduser";
	public static final String CREATE_USER = "INSERT INTO user (login, password, idrole, name, sirname, patronymic, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String CREATE_STUDENT = "INSERT INTO student (iduser, isBlocked, college) VALUES (?, ?, ?)";
	public static final String CREATE_TEACHER = "INSERT INTO teacher (iduser, specialization, experience) VALUES (?, ?, ?)";
	public static final String UPDATE_USER = "UPDATE user SET login=?, password=?, name=?, sirname=?, patronymic=?, email=? WHERE iduser=?";
	public static final String DELETE_USER = "DELETE FROM user WHERE iduser=?";
	public static final String BLOCK_STUDENT = "UPDATE student SET isBlocked=1 WHERE iduser=?";
	public static final String UNBLOCK_STUDENT = "UPDATE student SET isBlocked=0 WHERE iduser=?";
	
	
	/**
	 * JournalManagement constants
	 */
	public static final String READ_COURSE_POINTS = "";
	public static final String CREATE_COURSE_CP = "";
	public static final String DELETE_COURSE_CP = "";
	public static final String UPDATE_DATE_COUSE_CP = "";
	public static final String UPDATE_MARK = "";
	
	
	/**
	 * CourseManagement constants
	 */
	
	
	
}
