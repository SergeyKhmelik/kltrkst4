package ua.nure.khmelik.SummaryTask4.entity.dbentities;

public class User extends Entity {

    private static final long serialVersionUID = 545796908293501004L;

    private String login;
    private String password;
    private String name;
    private String sirname;
    private String patronymic;
    private String email;
    private int idRole;

    public User() {}
    
    public User(int id, String login, String password, String name, String sirname,
	    String patronymic, String email, int idRole) {
	this.setId(id);
	this.login = login;
	this.password = password;
	this.name = name;
	this.sirname = sirname;
	this.patronymic = patronymic;
	this.email = email;
	this.idRole = idRole;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSirname() {
	return sirname;
    }

    public void setSirname(String sirname) {
	this.sirname = sirname;
    }

    public String getPatronymic() {
	return patronymic;
    }

    public void setPatronymic(String patronymic) {
	this.patronymic = patronymic;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public int getIdRole() {
	return idRole;
    }

    public void setIdRole(int idRole) {
	this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	User user = (User) o;

	if (this.getId() != user.getId())
	    return false;
	if (email != null ? !email.equals(user.email) : user.email != null)
	    return false;
	if (login != null ? !login.equals(user.login) : user.login != null)
	    return false;
	if (name != null ? !name.equals(user.name) : user.name != null)
	    return false;
	if (password != null ? !password.equals(user.password)
		: user.password != null)
	    return false;
	if (patronymic != null ? !patronymic.equals(user.patronymic)
		: user.patronymic != null)
	    return false;
	if (sirname != null ? !sirname.equals(user.sirname)
		: user.sirname != null)
	    return false;
	if (idRole != user.idRole)
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	int result = getId();
	result = 31 * result + (login != null ? login.hashCode() : 0);
	result = 31 * result + (password != null ? password.hashCode() : 0);
	result = 31 * result + (name != null ? name.hashCode() : 0);
	result = 31 * result + (sirname != null ? sirname.hashCode() : 0);
	result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
	result = 31 * result + (email != null ? email.hashCode() : 0);
	result = 31 * result + idRole;
	return result;
    }

    @Override
    public String toString() {
	return "User [login=" + login + ", password=" + password + ", name="
		+ name + ", sirname=" + sirname + ", patronymic=" + patronymic
		+ ", email=" + email + ", roleId=" + idRole + "]";
    }
    
    

}
