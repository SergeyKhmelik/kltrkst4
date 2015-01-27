package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.CourseDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.service.CourseService;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlCourseService implements CourseService {

    private TransactionManager transactionManager;
    private CourseDao courseDao;
    
    public MysqlCourseService(TransactionManager transactionManager,
	    CourseDao courseDao) {
	super();
	this.transactionManager = transactionManager;
	this.courseDao = courseDao;
    }

    @Override
    public ArrayList<Course> getIncomingCourses() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<Course> getIncomingCourses(int idTeacher) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<Course> getPassedCourses(int idStudent) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<Course> getCurrentCourses(int idTeacher) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int createCourse(Course course) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int updateCourse(Course course) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int deleteCourse(int idCourse) {
	// TODO Auto-generated method stub
	return 0;
    }

}
