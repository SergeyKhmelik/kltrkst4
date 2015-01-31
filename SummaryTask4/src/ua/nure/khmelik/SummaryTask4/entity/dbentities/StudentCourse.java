package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class StudentCourse{

	private int idStudent;
	private int idCourse;

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StudentCourse that = (StudentCourse) o;

		if (idCourse != that.idCourse)
			return false;
		if (idStudent != that.idStudent)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idStudent;
		result = 31 * result + idCourse;
		return result;
	}
}
