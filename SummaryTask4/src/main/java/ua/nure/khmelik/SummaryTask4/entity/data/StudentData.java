package ua.nure.khmelik.SummaryTask4.entity.data;

public class StudentData extends UserData {

    private boolean blocked;
    private String college;

    public boolean isBlocked() {
	return blocked;
    }

    public void setBlocked(boolean blocked) {
	this.blocked = blocked;
    }

    public String getCollege() {
	return college;
    }

    public void setCollege(String college) {
	this.college = college;
    }

    @Override
    public String toString() {
	return super.toString() + "StudentData [blocked=" + blocked
		+ ", college=" + college + "]";
    }

}
