package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.dao.JournalDao;
import ua.nure.khmelik.SummaryTask4.entity.Journal;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.JournalService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlJournalService implements JournalService {

    private TransactionManager transactionManager;
    private JournalDao journalDao;

    public MysqlJournalService(TransactionManager transactionManager,
	    JournalDao journalDao) {
	super();
	this.transactionManager = transactionManager;
	this.journalDao = journalDao;
    }

    @Override
    public Journal getJournal(int idCourse) {
	// TODO Auto-generated method stub
	return null;
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
	    e.printStackTrace();
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
	    e.printStackTrace();
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
	    e.printStackTrace();
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
	    e.printStackTrace();
	}
	return result;
    }

}
