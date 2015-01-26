package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class Admin extends User {

	private static final long serialVersionUID = -3936901415577208092L;

	private int phone;

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Admin admin = (Admin) o;

		if (getId() != admin.getId())
			return false;
		if (phone != admin.phone)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + phone;
		return result;
	}

	@Override
	public String toString() {
	    return super.toString() + " Admin [phone=" + phone + "]";
	}
	
	
}
