package ua.nure.khmelik.SummaryTask4.entity.dbentities;

import java.sql.Date;

public class CourseControlPoint{

	private int idCourseControlPoint;
	private int idCourse;
	private int idControlPoint;
	private Date date;
	
	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public int getIdControlPoint() {
		return idControlPoint;
	}

	public void setIdControlPoint(int idControlPoint) {
		this.idControlPoint = idControlPoint;
	}

	public int getIdCourseControlPoint() {
		return idCourseControlPoint;
	}

	public void setIdCourseControlPoint(int idCourseControlpoint) {
		this.idCourseControlPoint = idCourseControlpoint;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CourseControlPoint that = (CourseControlPoint) o;

		if (idCourseControlPoint != that.idCourseControlPoint)
			return false;
		if (date != null ? !date.equals(that.date) : that.date != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idCourseControlPoint;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}
	
}