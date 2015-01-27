package ua.nure.khmelik.SummaryTask4.entity.dbentities;

import java.io.Serializable;

public class Mark implements Serializable{

	private static final long serialVersionUID = -4399100703782619629L;

	private int idStudent;
	private int idCourseControlpoint;
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int studentId) {
		this.idStudent = studentId;
	}

	public int getIdCourseControlpoint() {
		return idCourseControlpoint;
	}

	public void setIdCourseControlpoint(int idCourseCodePoint) {
		this.idCourseControlpoint = idCourseCodePoint;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Mark mark = (Mark) o;

		if (value != mark.value)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idStudent;
		result = 31 * result + idCourseControlpoint;
		result = 31 * result + value;
		return result;
	}

}
