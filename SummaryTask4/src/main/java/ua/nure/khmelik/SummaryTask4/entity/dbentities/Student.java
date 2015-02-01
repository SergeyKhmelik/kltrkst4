package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class Student extends User {

    private static final long serialVersionUID = -3854749700675642172L;

    private boolean blocked;
    private String college;

    public Student() {
    }

    public Student(User user) {
	this.setId(user.getId());
	this.setLogin(user.getLogin());
	this.setPassword(user.getPassword());
	this.setName(user.getName());
	this.setPatronymic(user.getPatronymic());
	this.setSirname(user.getSirname());
	this.setEmail(user.getEmail());
    }

    public String getCollege() {
	return college;
    }

    public void setCollege(String college) {
	this.college = college;
    }

    public boolean isBlocked() {
	return blocked;
    }

    public void setBlocked(boolean blocked) {
	this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	Student student = (Student) o;

	if (getId() != student.getId())
	    return false;
	if (blocked != student.blocked)
	    return false;
	if (college != null ? !college.equals(student.college)
		: student.college != null)
	    return false;

	return true;
    }

    @Override
    public int hashCode() {
	int result = getId();
	result = 31 * result + Boolean.toString(blocked).hashCode();
	result = 31 * result + (college != null ? college.hashCode() : 0);
	return result;
    }
}
