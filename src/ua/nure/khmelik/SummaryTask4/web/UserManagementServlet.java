package ua.nure.khmelik.SummaryTask4.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Teacher;
import ua.nure.khmelik.SummaryTask4.service.implementation.UserManagementLogic;

public class UserManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 4201954460980180457L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	resp.setContentType("text/html");
	HttpSession session = req.getSession();

	ArrayList<Teacher> teachers;
	ArrayList<Student> students;
	try {
	    teachers = new UserManagementLogic().readTeachers();
	    students = new UserManagementLogic().readStudents();
	    session.setAttribute("teachers", teachers);
	    session.setAttribute("students", students);
	    req.getRequestDispatcher(
		    "WEB-INF/view/jsp/admin/UserManagement.jsp").forward(req,
		    resp);
	} catch (ClassNotFoundException | SQLException | NamingException e) {
	    req.setAttribute("error", e);
	    req.getRequestDispatcher("WEB-INF/view/jsp/login_error.jsp")
		    .forward(req, resp);
	    System.out.println(e.getMessage());
	    System.out.println(e.getLocalizedMessage());
	    e.printStackTrace();
	}
    }

}
