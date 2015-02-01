package ua.nure.khmelik.SummaryTask4.entity;

import java.util.ArrayList;
import java.util.HashMap;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Mark;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Student;

/**
 * Class journal aggregates all marks and control points info of one course.
 * 
 * @author Sergey Khmelik
 *
 */
public class Journal {

    ArrayList<CourseControlPointInfo> coursePoints;
    HashMap<Student, ArrayList<Mark>> marks;

    public ArrayList<CourseControlPointInfo> getCoursePoints() {
	if(coursePoints == null){
	    coursePoints = new ArrayList<CourseControlPointInfo>();
	}
	return coursePoints;
    }

    public void setCoursePoints(ArrayList<CourseControlPointInfo> coursePoints) {
	this.coursePoints = coursePoints;
    }

    public HashMap<Student, ArrayList<Mark>> getMarks() {
	if(marks == null){
	    marks = new HashMap<Student, ArrayList<Mark>>();
	}
	return marks;
    }

    public void setMarks(HashMap<Student, ArrayList<Mark>> marks) {
	this.marks = marks;
    }

}