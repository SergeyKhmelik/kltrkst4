package ua.nure.khmelik.SummaryTask4.entity;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseControlPoint;


public class CourseControlPointInfo extends CourseControlPoint {

	private String courseName;
	private String courseDescription;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

}
