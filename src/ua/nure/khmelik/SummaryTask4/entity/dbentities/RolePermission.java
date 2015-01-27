package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class RolePermission {
	
	private int idRole;
	private int idPermition;

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idrole) {
		this.idRole = idrole;
	}

	public int getIdPermition() {
		return idPermition;
	}

	public void setIdPermition(int idpermition) {
		this.idPermition = idpermition;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RolePermission that = (RolePermission) o;

		if (idPermition != that.idPermition)
			return false;
		if (idRole != that.idRole)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idRole;
		result = 31 * result + idPermition;
		return result;
	}
}
