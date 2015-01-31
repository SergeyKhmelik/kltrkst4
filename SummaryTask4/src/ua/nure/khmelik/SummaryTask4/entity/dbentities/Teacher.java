package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class Teacher extends User {

    private static final long serialVersionUID = -3462317387359622778L;

    private String specialization;
    private int experience;

    public Teacher() {
    }

    public Teacher(User user) {
	this.setId(user.getId());
	this.setLogin(user.getLogin());
	this.setPassword(user.getPassword());
	this.setName(user.getName());
	this.setPatronymic(user.getPatronymic());
	this.setSirname(user.getSirname());
	this.setEmail(user.getEmail());
    }

    public String getSpecialization() {
	return specialization;
    }

    public void setSpecialization(String specialization) {
	this.specialization = specialization;
    }

    public int getExperience() {
	return experience;
    }

    public void setExperience(int expirience) {
	this.experience = expirience;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	Teacher teacher = (Teacher) o;

	if (experience != teacher.experience)
	    return false;
	if (getId() != teacher.getId())
	    return false;
	if (specialization != null ? !specialization
		.equals(teacher.specialization)
		: teacher.specialization != null)
	    return false;

	return true;
    }

    @Override
    public int hashCode() {
	int result = getId();
	result = 31 * result
		+ (specialization != null ? specialization.hashCode() : 0);
	result = 31 * result + experience;
	return result;
    }
}
