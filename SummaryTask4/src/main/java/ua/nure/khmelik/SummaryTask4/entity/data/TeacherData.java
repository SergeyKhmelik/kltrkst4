package ua.nure.khmelik.SummaryTask4.entity.data;

public class TeacherData extends UserData {

    private String specialization;
    private int experience;

    public String getSpecialization() {
	return specialization;
    }

    public void setSpecialization(String specialization) {
	this.specialization = specialization;
    }

    public int getExperience() {
	return experience;
    }

    public void setExperience(int experience) {
	this.experience = experience;
    }

}
