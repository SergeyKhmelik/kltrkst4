package ua.nure.khmelik.SummaryTask4.entity.data;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public abstract class UserData {

    private int idUser;
    private String name;
    private String patronymic;
    private String sirname;
    private String email;
    private String login;
    private String password;
    private Role role;

    public UserData() {
    }
    
    public UserData(User user) {
	this.idUser = user.getId();
	this.name = user.getName();
	this.patronymic = user.getPatronymic();
	this.sirname = user.getSirname();
	this.email = user.getEmail();
	this.login = user.getLogin();
	this.password = user.getPassword();
    }

    public int getIdUser() {
	return idUser;
    }

    public void setIdUser(int idUser) {
	this.idUser = idUser;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPatronymic() {
	return patronymic;
    }

    public void setPatronymic(String patronymic) {
	this.patronymic = patronymic;
    }

    public String getSirname() {
	return sirname;
    }

    public void setSirname(String sirname) {
	this.sirname = sirname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
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

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public void setRole(RoleData role) {
	this.role = new Role();
	this.role.setId(role.getIdRole());
	this.role.setName(role.getName());
	this.role.setDescription(role.getDescription());
    }

    @Override
    public String toString() {
	return "UserData [idUser=" + idUser + ", name=" + name
		+ ", patronymic=" + patronymic + ", sirname=" + sirname
		+ ", email=" + email + ", login=" + login + ", password="
		+ password + ", role=" + role + "]";
    }

}
