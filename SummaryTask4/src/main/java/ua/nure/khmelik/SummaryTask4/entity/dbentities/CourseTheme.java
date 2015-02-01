package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class CourseTheme extends Entity{
	
	private static final long serialVersionUID = -7997174588437069920L;

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

		CourseTheme that = (CourseTheme) o;

		if (getId() != that.getId())
			return false;
		if (description != null ? !description.equals(that.description)
				: that.description != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
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
}
