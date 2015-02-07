package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class Role extends Entity{

	private static final long serialVersionUID = -714215175378868456L;

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Role role = (Role) o;

		if (getId() != role.getId())
			return false;
		if (description != null ? !description.equals(role.description)
				: role.description != null)
			return false;
		if (name != null ? !name.equals(role.name) : role.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result
				+ (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
	    return "Role [name=" + name + ", description=" + description + "]";
	}
}

