package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.khmelik.SummaryTask4.entity.data.StudentData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;

public class UpdateDataServlet extends HttpServlet {

    private static final long serialVersionUID = 3750742488559984799L;

    private static final String USER_UPDATE = "user";
    private static final Object STUDENT_ROLE = "student";
    private static final Object TEACHER_ROLE = "teacher";

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");

	String roleName = request.getParameter("role");
	Role role = new Role();
	role.setName(roleName);
	if (USER_UPDATE.equals(request.getParameter("object"))) {
	    request.getRequestDispatcher("/updateForm").forward(request,
		    response);
	    if (STUDENT_ROLE.equals(role)) {
		StudentData studentData = new StudentData();
		setRequestDataInUserData(request, studentData);
		studentData.setCollege(request.getParameter("college"));
		studentData.setRole(role);
		request.setAttribute("user", studentData);
	    } else if (TEACHER_ROLE.equals(role)) {
		TeacherData teacherData = new TeacherData();
		setRequestDataInUserData(request, teacherData);
		teacherData.setSpecialization(request
			.getParameter("specialization"));
		teacherData.setExperience(Integer.parseInt(request
			.getParameter(request.getParameter("experience"))));
		teacherData.setRole(role);
		request.setAttribute("user", teacherData);
	    }
	}
	request.getRequestDispatcher("/updateForm").forward(request,
		    response);
	
    }

    private void setRequestDataInUserData(HttpServletRequest request,
	    UserData userData) {
	userData.setIdUser(Integer.parseInt(request.getParameter("id")));
	userData.setName(request.getParameter("name"));
	userData.setSirname(request.getParameter("sirname"));
	userData.setPatronymic(request.getParameter("patronymic"));
	userData.setLogin(request.getParameter("login"));
	userData.setPassword(request.getParameter("password"));
	userData.setEmail(request.getParameter("email"));
    }

}
