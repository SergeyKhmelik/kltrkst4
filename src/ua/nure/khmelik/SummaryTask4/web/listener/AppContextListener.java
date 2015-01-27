package ua.nure.khmelik.SummaryTask4.web.listener;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.dao.DaoFactory;
import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSupportedDatabase;
import ua.nure.khmelik.SummaryTask4.service.AuthorizationService;
import ua.nure.khmelik.SummaryTask4.service.CourseService;
import ua.nure.khmelik.SummaryTask4.service.JournalService;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;
import ua.nure.khmelik.SummaryTask4.service.UserService;
import ua.nure.khmelik.SummaryTask4.service.mysql.MysqlAuthorizationService;
import ua.nure.khmelik.SummaryTask4.service.mysql.MysqlCourseService;
import ua.nure.khmelik.SummaryTask4.service.mysql.MysqlJournalService;
import ua.nure.khmelik.SummaryTask4.service.mysql.MysqlPermissionService;
import ua.nure.khmelik.SummaryTask4.service.mysql.MysqlUserService;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
	ServletContext context = servletContextEvent.getServletContext();
	
	try {
	    //DAO
	    AuthorizationDao authorizationDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getAuthorizationDao();
	    CourseDao courseDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getCourseDao();
	    JournalDao journalDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getJournalDao();
	    PermissionDao permissionDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getPermissionDao();
	    UserDao userDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getUserDao();
	    
	    TransactionManager transactionManager = new TransactionManager();
	    
	    //Services
	    AuthorizationService authorizationService = new MysqlAuthorizationService(transactionManager, authorizationDao);
	    CourseService courseService = new MysqlCourseService(transactionManager, courseDao);
	    JournalService journalService = new MysqlJournalService(transactionManager, journalDao);
	    PermissionService permissionService = new MysqlPermissionService(transactionManager, permissionDao);
	    UserService userService = new MysqlUserService(transactionManager, userDao);
	    
	    //Put services into appContext
	    context.setAttribute("authorizationService", authorizationService);
	    context.setAttribute("courseService", courseService);
	    context.setAttribute("journalService", journalService);
	    context.setAttribute("permissionService", permissionService);
	    context.setAttribute("userService", userService);
	} catch (NoSupportedDatabase e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NamingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
