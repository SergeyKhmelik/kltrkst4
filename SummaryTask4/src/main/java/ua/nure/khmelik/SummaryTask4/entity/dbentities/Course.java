package ua.nure.khmelik.SummaryTask4.entity.dbentities;

import java.sql.Date;

public class Course extends Entity {

	private static final long serialVersionUID = -5240287589018438965L;

	private String name;
	private Date start;
	private Date end;
	private int idTeacher;
	private int idTheme;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}
	
	public int getIdTheme() {
	    return idTheme;
	}

	public void setIdTheme(int idTheme) {
	    this.idTheme = idTheme;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Course course = (Course) o;

		if (getId() != course.getId())
			return false;
		if (end != null ? !end.equals(course.end) : course.end != null)
			return false;
		if (name != null ? !name.equals(course.name) : course.name != null)
			return false;
		if (start != null ? !start.equals(course.start) : course.start != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (start != null ? start.hashCode() : 0);
		result = 31 * result + (end != null ? end.hashCode() : 0);
		return result;
	}

}
