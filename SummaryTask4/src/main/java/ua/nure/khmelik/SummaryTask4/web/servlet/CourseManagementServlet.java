package ua.nure.khmelik.SummaryTask4.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;
import ua.nure.khmelik.SummaryTask4.entity.data.TeacherData;
import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.entity.data.comparators.CourseNameComparator;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.service.CourseService;
import ua.nure.khmelik.SummaryTask4.service.UserService;

public class CourseManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 5128155184892996667L;

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final String PASSED = "passed";
    private static final String CURRENT = "current";
    private static final String INCOMING = "incoming";
    private static final String NAME_SORT = "name";
    private static final String THEME_SORT = "theme";
    private static final String START_SORT = "start";
    private static final String END_SORT = "end";
    private static final String TEACHER_SORT = "teacher";
    private static final String ASC_ORDER = "asc";

    private CourseService courseService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
	courseService = (CourseService) getServletContext().getAttribute(
		"courseService");
	userService = (UserService) getServletContext().getAttribute("userService");
	if (courseService == null) {
	    LOGGER.error("Could not get services from appcontext");
	    throw new UnavailableException("Couldn`t get DAO");
	}
    }

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");

	String time = request.getParameter("time");
	String sort = request.getParameter("sort");
	String order = request.getParameter("order");

	UserData user = (UserData) request.getSession().getAttribute("user");

	// COURSES
	ArrayList<CourseData> courses = new ArrayList<CourseData>();
	ArrayList<TeacherData> teachers = new ArrayList<TeacherData>();
	ArrayList<CourseTheme> themes = new ArrayList<CourseTheme>();
	try {
	    //Get courses depending on a time period
	    if (PASSED.equals(time)) {
		courses = getPassedCourses(user);
	    } else if (CURRENT.equals(time)) {
		courses = getCurrentCourses(user);
	    } else if (INCOMING.equals(time)) {
		courses = getIncomingCourses(user);
	    }

	    // Sort courses
	    if (NAME_SORT.equals(sort)) {
		if (ASC_ORDER.equals(order)) {
		    Collections.sort(courses, new CourseNameComparator());
		} else {
		    Collections.sort(courses, Collections
			    .reverseOrder(new CourseNameComparator()));
		}
	    } else if (THEME_SORT.equals(sort)) {
		if (ASC_ORDER.equals(order)) {
		    Collections.sort(courses, new CourseNameComparator());
		} else {
		    Collections.sort(courses, Collections
			    .reverseOrder(new CourseNameComparator()));
		}
	    } else if (START_SORT.equals(sort)) {
		if (ASC_ORDER.equals(order)) {
		    Collections.sort(courses, new CourseNameComparator());
		} else {
		    Collections.sort(courses, Collections
			    .reverseOrder(new CourseNameComparator()));
		}
	    } else if (END_SORT.equals(sort)) {
		if (ASC_ORDER.equals(order)) {
		    Collections.sort(courses, new CourseNameComparator());
		} else {
		    Collections.sort(courses, Collections
			    .reverseOrder(new CourseNameComparator()));
		}
	    } else if (TEACHER_SORT.equals(sort)) {
		if (ASC_ORDER.equals(order)) {
		    Collections.sort(courses, new CourseNameComparator());
		} else {
		    Collections.sort(courses, Collections
			    .reverseOrder(new CourseNameComparator()));
		}
	    }
	    
	    // get all teachers for update
	    teachers = userService.readTeachers();

	    // get all themes for update
	    themes = courseService.getCourseThemes();

	    
	    request.setAttribute("courses", courses);
	    request.setAttribute("teachers", teachers);
	    request.setAttribute("themes", themes);
	    
	    request.getRequestDispatcher("coursesjsp").forward(request, response);
	} catch (SQLException e) {
	    LOGGER.error("SQLException while adding permission.");
	    response.sendRedirect("error");
	    return;
	}

    }

    private ArrayList<CourseData> getIncomingCourses(UserData user)
	    throws SQLException {
	ArrayList<CourseData> result = new ArrayList<CourseData>();
	result = courseService.getIncomingCourses();
	return result;
    }

    private ArrayList<CourseData> getCurrentCourses(UserData user)
	    throws SQLException {
	ArrayList<CourseData> result = new ArrayList<CourseData>();
	String userRoleName = user.getRole().getName();
	if (RoleName.ADMIN.equals(userRoleName)) {
	    result = courseService.getCurrentCourses();
	} else if (RoleName.TEACHER.equals(userRoleName)) {
	    result = courseService.getCurrentTeachersCourses(user.getIdUser());
	} else if (RoleName.STUDENT.equals(userRoleName)) {
	    result = courseService.getCurrentStudentsCourses(user.getIdUser());
	}
	return result;
    }

    private ArrayList<CourseData> getPassedCourses(UserData user)
	    throws SQLException {
	ArrayList<CourseData> result = new ArrayList<CourseData>();
	String userRoleName = user.getRole().getName();
	if (RoleName.ADMIN.getName().equals(userRoleName)) {
	    result = courseService.getPassedCourses();
	} else if (RoleName.STUDENT.getName().equals(userRoleName)) {
	    result = courseService.getPassedStudentsCourses(user.getIdUser());
	} else if (RoleName.TEACHER.getName().equals(userRoleName)) {
	    result = courseService.getPassedTeachersCourses(user.getIdUser());
	}
	return result;
    }

}
