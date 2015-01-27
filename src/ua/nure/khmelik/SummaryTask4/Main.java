package ua.nure.khmelik.SummaryTask4;

import java.sql.SQLException;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlAuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.mysql.MysqlPermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.service.implementation.AuthorizationLogic;
import ua.nure.khmelik.SummaryTask4.service.mysql.MysqlAuthorizationService;

public class Main {

    public static void main(String[] args) {
	/*
	 * Teacher teacher = new Teacher(); teacher.setId(1L);
	 * teacher.setLogin("федор123"); teacher.setPassword("дядяфедя123");
	 * teacher.setEmail("fedorivanovitch@mail.ru");
	 * teacher.setName("Федор"); teacher.setSirname("Федоров");
	 * teacher.setPatronymic("Федорович"); teacher.setExpirience(13);
	 * teacher.setSpecialization("Био-инженерия");
	 */
	try {
	    User user = new AuthorizationLogic().getUserByLoginPassword(
		    "koloturka", "cepera");
	    System.out.println(user);
	    System.out.println(new AuthorizationLogic()
		    .getPermissionsByRole(user.getIdRole()));
	} catch (SQLException | NamingException | ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	User user;
	try {
	    user = new MysqlAuthorizationService(
	    	new MysqlAuthorizationDao(),
	    	new MysqlPermissionDao())
	    	.getUser("koloturka", "cepera");
	    System.out.println(user);
	} catch (NamingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}