package ua.nure.khmelik.SummaryTask4.web.listener;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.dao.DaoFactory;
import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.dao.UserDao;
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

    public static final Logger LOGGER = Logger
	    .getLogger(AppContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
	ServletContext servletContext = servletContextEvent.getServletContext();

	initLog4J(servletContext);

	try {
	    // DAO
	    AuthorizationDao authorizationDao = DaoFactory.getDaoFactory(
		    DaoFactory.MYSQL).getAuthorizationDao();
	    CourseDao courseDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getCourseDao();
	    JournalDao journalDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getJournalDao();
	    PermissionDao permissionDao = DaoFactory.getDaoFactory(
		    DaoFactory.MYSQL).getPermissionDao();
	    UserDao userDao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
		    .getUserDao();

	    TransactionManager transactionManager = new TransactionManager();
	    
	    // Services
	    AuthorizationService authorizationService = new MysqlAuthorizationService(
		    transactionManager, authorizationDao);
	    CourseService courseService = new MysqlCourseService(
		    transactionManager, courseDao, userDao);
	    JournalService journalService = new MysqlJournalService(
		    transactionManager, journalDao, userDao);
	    PermissionService permissionService = new MysqlPermissionService(
		    transactionManager, permissionDao);
	    UserService userService = new MysqlUserService(transactionManager,
		    userDao, permissionDao);

	    // Put services into appContext
	    servletContext.setAttribute("authorizationService",
		    authorizationService);
	    servletContext.setAttribute("courseService", courseService);
	    servletContext.setAttribute("journalService", journalService);
	    servletContext.setAttribute("permissionService", permissionService);
	    servletContext.setAttribute("userService", userService);
	    LOGGER.info("App context initialized");
	} catch (NamingException e) {
	    LOGGER.error("Cannot get connection from the pool.", e);
	}
    }

    private void initLog4J(ServletContext servletContext) {
	log("Log4J initialization started");
	try {
	    PropertyConfigurator.configure(servletContext
		    .getRealPath("WEB-INF/log4j.properties"));
	} catch (Exception ex) {
	    LOGGER.error("Cannot configure Log4j", ex);
	}
	log("Log4J initialization finished");
	LOGGER.debug("Log4j has been initialized");
    }

    private void log(String msg) {
	System.out.println("[AppContextListener] " + msg);
    }

}
