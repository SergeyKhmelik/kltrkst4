package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.service.CourseService;

public class CourseManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 5128155184892996667L;

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
	courseService = (CourseService) getServletContext().getAttribute(
		"courseService");

	if (courseService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get DAO");
	}
    }

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");

	String course = request.getParameter("course");
	String theme = request.getParameter("theme");
	LOGGER.info("Entered courses servlet" );
	
	ArrayList<CourseData> passedCourseDatas;
	ArrayList<CourseData> currentCourseDatas;
	ArrayList<CourseData> incomingCourseDatas;
	
	if (course == null || theme == null) {
	    try {
		passedCourseDatas = courseService.getPassedCourses();
		currentCourseDatas = courseService.getCurrentCourses();
		incomingCourseDatas = courseService.getIncomingCourses();
	    } catch (SQLException e) {
		response.sendRedirect("error");
		LOGGER.error("Exception during course management servlet.");
		return;
	    }
	} else {
	    String type = request.getParameter("type");
	    int idTheme = Integer.parseInt(theme);
	    try {
		passedCourseDatas = courseService.getPassedCourses();
		currentCourseDatas = courseService.getCurrentCourses();
		incomingCourseDatas = courseService.getIncomingCourses();
	    } catch (SQLException e) {
		response.sendRedirect("error");
		LOGGER.error("Exception during course management servlet.");
		return;
	    }
	}
	
	HashMap<CourseTheme, ArrayList<CourseData>> passedCourses = getHasmMapByThemes(passedCourseDatas);
	HashMap<CourseTheme, ArrayList<CourseData>> currentCourses = getHasmMapByThemes(currentCourseDatas);
	HashMap<CourseTheme, ArrayList<CourseData>> incomingCourses = getHasmMapByThemes(incomingCourseDatas);
	
	request.setAttribute("incomingCourses", passedCourses);
	request.setAttribute("currentCourses", currentCourses);
	request.setAttribute("passedCourses", incomingCourses);
		
	request.getRequestDispatcher("/courses").forward(request, response);
	
    }

    private HashMap<CourseTheme, ArrayList<CourseData>> getHasmMapByThemes(
	    ArrayList<CourseData> passedCourseDatas) {
	HashMap<CourseTheme, ArrayList<CourseData>> result = new HashMap<CourseTheme, ArrayList<CourseData>>();

	for (CourseData courseData : passedCourseDatas) {
	    if (result.containsKey(courseData.getTheme())) {
		result.get(courseData.getTheme()).add(courseData);
	    } else {
		ArrayList<CourseData> courseDatasByTheme = new ArrayList<CourseData>();
		courseDatasByTheme.add(courseData);
		result.put(courseData.getTheme(), courseDatasByTheme);
	    }
	}
	return result;
    }

}
