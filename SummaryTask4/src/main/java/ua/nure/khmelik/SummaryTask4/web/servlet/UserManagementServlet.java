package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.data.StudentData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.service.UserService;

public class UserManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 4201954460980180457L;
    private static final Logger LOGGER = Logger.getLogger(UserManagementServlet.class);

    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        
        if(userService == null){
            throw new UnavailableException("Couldn`t get DAO");
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html");

	LOGGER.info("Entered usermanagement servlet");
	
	try{
	    ArrayList<TeacherData> teachers = userService.readTeachers();
	    ArrayList<StudentData> students = userService.readStudents();
	    request.setAttribute("teachers", teachers);	
	    request.setAttribute("students", students);
	    request.getRequestDispatcher("/users").forward(request, response);
	} catch (SQLException ex){
	    LOGGER.error("Exception during users authorization.");
	    response.sendRedirect("error");
	    return;
	}	
    }

}
