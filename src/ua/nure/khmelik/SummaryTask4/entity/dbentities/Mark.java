package ua.nure.khmelik.SummaryTask4.entity.dbentities;

import java.io.Serializable;

public class Mark implements Serializable{

	private static final long serialVersionUID = -4399100703782619629L;

	private int studentId;
	private int courseControlpointId;
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseControlpointId() {
		return courseControlpointId;
	}

	public void setCourseControlpointId(int courseCodePointId) {
		this.courseControlpointId = courseCodePointId;
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
		int result = studentId;
		result = 31 * result + courseControlpointId;
		result = 31 * result + value;
		return result;
	}

}
