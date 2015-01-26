package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class StudentCourse{

	private int idstudent;
	private int idcourse;

	public int getIdStudent() {
		return idstudent;
	}

	public void setIdStudent(int idstudent) {
		this.idstudent = idstudent;
	}

	public int getIdCourse() {
		return idcourse;
	}

	public void setIdCourse(int idcourse) {
		this.idcourse = idcourse;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StudentCourse that = (StudentCourse) o;

		if (idcourse != that.idcourse)
			return false;
		if (idstudent != that.idstudent)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idstudent;
		result = 31 * result + idcourse;
		return result;
	}
}
