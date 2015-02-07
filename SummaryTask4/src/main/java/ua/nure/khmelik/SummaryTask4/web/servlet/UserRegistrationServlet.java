package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.data.StudentData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.service.UserService;

public class UserRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 2000495712214199248L;
    private static final Logger LOGGER = Logger
	    .getLogger(UserRegistrationServlet.class);

    private static final String SPECIALIZATION_PATTERN = "^[\\p{L}{3,}...]+$";
    private static final String COLLEGE_PATTERN = "[\\p{L}{3,}...]+";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{4,20}$";
    private static final String LOGIN_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String NAME_PATTERN = "^[\\p{L}{2,}...]+$";
    private static final String STUDENT_ROLE = "student";
    private static final String TEACHER_ROLE = "teacher";
    private static final String INSERT_COMMAND = "insert";
    private static final String UPDATE_COMMAND = "update";

    private UserService userService;

    @Override
    public void init() throws ServletException {
	userService = (UserService) getServletContext().getAttribute(
		"userService");
	if (userService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get user service.");
	}
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	
	String role = request.getParameter("role");
	String command = request.getParameter("command");
	
	LOGGER.debug("Update/insert servlet started with command " + command);
	
	HttpSession session = request.getSession();
	try {
	    if (INSERT_COMMAND.equals(command)) { // IF INSERT
		if (STUDENT_ROLE.equals(role)) { // INSERT STUDENT
		    StudentData studentData = getStudentFromRequest(request);
		    if (validateStudent(studentData, session)
			    && validateUserOnDuplicateInsert(studentData,
				    session)) {
			userService.addStudent(studentData);
		    }
		} else if (TEACHER_ROLE.equals(role)) { // INSERT TEACHER
		    TeacherData teacherData = getTeacherFromRequest(request);
		    if (validateTeacher(teacherData, session)
			    && validateUserOnDuplicateInsert(teacherData,
				    session)) {
			userService.addTeacher(teacherData);
		    }
		}
	    } else if (UPDATE_COMMAND.equals(command)) { // IF UPDATE
		if (STUDENT_ROLE.equals(role)) { // UPDATE STUDENT
		    StudentData studentData = getStudentFromRequest(request);
		    if (validateStudent(studentData, session)) {
			userService.updateStudent(studentData);
		    }
		} else if (TEACHER_ROLE.equals(role)) { // UPDATE TEACHER
		    TeacherData teacherData = getTeacherFromRequest(request);
		    if (validateTeacher(teacherData, session)) {
			userService.updateTeacher(teacherData);
		    }
		}
	    }
	    response.sendRedirect("userManagement");
	} catch (SQLException e) {
	    LOGGER.error("SQLException during users authorization.");
	    response.sendRedirect("error");
	} catch (NoSuchRoleException e) {
	    LOGGER.error("No such role exception during users authorization.");
	    response.sendRedirect("error");
	}
    }

    private StudentData getStudentFromRequest(HttpServletRequest request)
	    throws SQLException, NoSuchRoleException {
	StudentData studentData = new StudentData();
	studentData.setRole(userService.readRole(STUDENT_ROLE));
	getUserFromRequest(request, studentData);
	studentData.setCollege(request.getParameter("college"));
	studentData.setBlocked(true);
	return studentData;
    }

    private TeacherData getTeacherFromRequest(HttpServletRequest request)
	    throws SQLException, NoSuchRoleException {
	TeacherData teacherData = new TeacherData();
	teacherData.setRole(userService.readRole(TEACHER_ROLE));
	getUserFromRequest(request, teacherData);
	teacherData.setExperience(Integer.parseInt(request
		.getParameter("experience")));
	teacherData.setSirname(request.getParameter("specialization"));
	return teacherData;
    }

    private void getUserFromRequest(HttpServletRequest request,
	    UserData userData) {
	userData.setLogin(request.getParameter("login"));
	userData.setPassword(request.getParameter("password"));
	userData.setName(request.getParameter("name"));
	userData.setPatronymic(request.getParameter("patronymic"));
	userData.setSirname(request.getParameter("sirname"));
	userData.setEmail(request.getParameter("email"));
    }

    private boolean validateStudent(StudentData studentData, HttpSession session) {
	boolean result = true;
	result = result && validateUser(studentData, session);
	result = result && validateCollege(studentData.getCollege(), session);
	return result;
    }

    private boolean validateTeacher(TeacherData teacherData, HttpSession session) {
	boolean result = true;
	result = result && validateUser(teacherData, session);
	result = result
		&& validateExperience(teacherData.getExperience(), session);
	result = result
		&& validateSpecialization(teacherData.getSpecialization(),
			session);
	return result;
    }

    private boolean validateUser(UserData userData, HttpSession session) {
	boolean result = true;
	result = result && validateName(userData.getName(), session);
	LOGGER.error("NAME VALIDATION!"
		+ validateName(userData.getName(), session));
	result = result && validateName(userData.getPatronymic(), session);
	LOGGER.error("Patron VALIDATION!"
		+ validateName(userData.getPatronymic(), session));
	result = result && validateName(userData.getSirname(), session);
	LOGGER.error("sirname VALIDATION!"
		+ validateName(userData.getSirname(), session));
	result = result && validateLogin(userData.getLogin(), session);
	LOGGER.error("login VALIDATION!"
		+ validateLogin(userData.getLogin(), session));
	result = result && validateEmail(userData.getEmail(), session);
	LOGGER.error("email VALIDATION!"
		+ validateEmail(userData.getEmail(), session));
	result = result && validatePassword(userData.getPassword(), session);
	LOGGER.error("password VALIDATION!"
		+ validatePassword(userData.getPassword(), session));
	return result;
    }

    private boolean validateName(String userName, HttpSession session) {
	if (!userName.matches(NAME_PATTERN)) {
	    session.setAttribute("nameValidationError",
		    "User name/patronymic/sirname should contain 2 or more symbols.");
	    return false;
	}
	return true;
    }

    private boolean validateEmail(String userEmail, HttpSession session) {
	if (!userEmail.matches(EMAIL_PATTERN)) {
	    session.setAttribute("emailValidationError",
		    "Email should contain 2 or more symbols.");
	    return false;
	}
	return true;
    }

    private boolean validateLogin(String userLogin, HttpSession session) {
	if (!userLogin.matches(LOGIN_PATTERN)) {
	    session.setAttribute("loginValidationError",
		    "User login should be 3-15 symbols without  or more symbols.");
	    return false;
	}
	return true;
    }

    private boolean validatePassword(String userPassword, HttpSession session) {
	if (!userPassword.matches(PASSWORD_PATTERN)) {
	    session.setAttribute(
		    "passwordValidationError",
		    "Password should contain only letters or numbers."
			    + " It shoul be more than 3 and less then 20 symbols long.");
	    return false;
	}
	return true;
    }

    private boolean validateCollege(String college, HttpSession session) {
	if (!college.matches(COLLEGE_PATTERN)) {
	    session.setAttribute("collegeValidationError",
		    "College name should contain 2 or more symbols.");
	    return false;
	}
	return true;
    }

    private boolean validateSpecialization(String specialization,
	    HttpSession session) {
	if (!specialization.matches(SPECIALIZATION_PATTERN)) {
	    session.setAttribute("nameValidationError",
		    "Teacher`s specialization should contain 2 or more symbols.");
	    return false;
	}
	return true;
    }

    private boolean validateExperience(int experience, HttpSession session) {
	if (experience < 0) {
	    session.setAttribute("experienceValidationError",
		    "Experience should be >= 0.");
	    return false;
	}
	return true;
    }

    private boolean validateUserOnDuplicateInsert(UserData userData,
	    HttpSession session) throws SQLException {
	boolean result = true;
	if (!userService.validateUserLoginOnDuplicate(userData.getLogin())) {
	    session.setAttribute("loginDuplicateInsert",
		    "This login is already in use.");
	    result = false;
	}
	if (!userService.validateUserEmailOnDuplicate(userData.getEmail())) {
	    session.setAttribute("emailDuplicateInsert",
		    "This email is already in use.");
	    result = false;
	}
	return result;
    }

}
