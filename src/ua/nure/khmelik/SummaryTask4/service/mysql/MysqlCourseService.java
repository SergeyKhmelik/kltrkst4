package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.service.CourseService;

public class MysqlCourseService implements CourseService {

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
