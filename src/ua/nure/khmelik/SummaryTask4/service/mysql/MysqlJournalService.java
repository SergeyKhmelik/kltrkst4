package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.dao.UserDao;
import ua.nure.khmelik.SummaryTask4.entity.Journal;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.JournalService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlJournalService implements JournalService {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlJournalService.class);
    
    private TransactionManager transactionManager;
    private JournalDao journalDao;
    private UserDao userDao;

    public MysqlJournalService(TransactionManager transactionManager,
	    JournalDao journalDao, UserDao userDao) {
	this.transactionManager = transactionManager;
	this.journalDao = journalDao;
	this.userDao = userDao;
    }

    @Override
    public Journal getJournal(final int idCourse) {
	Journal journal = null;

	try {
	    journal = transactionManager
		    .doTransaction(new Operation<Journal>() {

			@Override
			public Journal execute(Connection conn)
				throws SQLException, NoSuchRoleException,
				NoSuchUserException {

			    Journal result = new Journal();

			    // Read all ccp
			    result.setCoursePoints(journalDao
				    .readControlPoints(conn, idCourse));

			    // Read all students
			    ArrayList<Student> students = userDao.readStudents(conn, idCourse);

			    // Read marks by student id
			    HashMap<Student, ArrayList<Mark>> marks = new HashMap<Student, ArrayList<Mark>>();
			    for (Student student : students) {
				marks.put(
					student,
					journalDao.readMarks(conn,
						student.getId(), idCourse));
			    }
			    result.setMarks(marks);
			    return result;
			}

		    });
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    LOGGER.error("Something caused custom exceptions in non authorization service.");
	}

	return journal;
    }

    @Override
    public int addCourseControlPoint(final CourseControlPoint point) {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return journalDao.createCourseControlPoint(conn, point);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    LOGGER.error("Something caused custom exceptions in non authorization service.");
	}
	return result;
    }

    @Override
    public int deleteCourseControlPoint(final int idPoint) {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return journalDao.deleteCourseControlPoint(conn, idPoint);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    LOGGER.error("Something caused custom exceptions in non authorization service.");
	}
	return result;
    }

    @Override
    public int setAnotherDate(final CourseControlPoint point) {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return journalDao.updateCourseControlPoint(conn, point);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    LOGGER.error("Something caused custom exceptions in non authorization service.");
	}
	return result;
    }

    @Override
    public int updateMark(final Mark mark) {
	int result = 0;
	try {
	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return journalDao.updateMark(conn, mark);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    LOGGER.error("Something caused custom exceptions in non authorization service.");
	}
	return result;
    }

}
