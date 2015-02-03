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
import ua.nure.khmelik.SummaryTask4.service.UserService;

public class UserRegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger
	    .getLogger(UserRegistrationServlet.class);
    private static final String STUDENT = null;
    private static final String TEACHER = null;
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
	HttpSession session = request.getSession();
	try {
	    if (role == STUDENT) {
		StudentData studentData = new StudentData();
		getUserFromRequest(request, studentData);
		studentData.setCollege(request.getParameter("college"));
		studentData.setBlocked(true);
		if (!validateStudent(studentData, session)) {
		    // TODO error validating popup
		    return;
		}
		userService.addStudent(studentData);
	    } else if (role == TEACHER) {
		TeacherData teacherData = new TeacherData();
		getUserFromRequest(request, teacherData);
		teacherData.setExperience(Integer.parseInt(request
			.getParameter("experience")));
		teacherData.setSirname(request.getParameter("specialization"));
		if (!validateTeacher(teacherData, session)) {
		    // TODO error validating popup
		    return;
		}
		userService.addTeacher(teacherData);
	    }
	    response.sendRedirect("/userManagement");

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

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
	result = result && validateName(userData.getPatronymic(), session);
	result = result && validateName(userData.getSirname(), session);
	result = result && validateName(userData.getLogin(), session);
	result = result && validateEmail(userData.getEmail(), session);
	result = result && validatePassword(userData.getPassword(), session);
	return result;
    }

    private boolean validateName(String userName, HttpSession session) {
	return false;
    }

    private boolean validateEmail(String userEmail, HttpSession session) {
	return false;
    }

    private boolean validatePassword(String userPassword, HttpSession session) {
	return false;
    }

    private boolean validateCollege(String college, HttpSession session) {
	// TODO Auto-generated method stub
	return false;
    }

    private boolean validateSpecialization(String specialization,
	    HttpSession session) {
	// TODO Auto-generated method stub
	return false;
    }

    private boolean validateExperience(int experience, HttpSession session) {
	// TODO Auto-generated method stub
	return false;
    }

}
