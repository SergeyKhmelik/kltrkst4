package ua.nure.khmelik.SummaryTask4;

import java.sql.SQLException;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.dao.mysql.AuthorizationDaoMysqlImpl;
import ua.nure.khmelik.SummaryTask4.dao.mysql.PermissionDaoMySqlImpl;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.service.implementation.AuthorizationLogic;
import ua.nure.khmelik.SummaryTask4.service.mysql.AuthorizationServiceMysqlImpl;

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
		    .getPermissionsByRole(user.getRoleId()));
	} catch (SQLException | NamingException | ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	User user;
	try {
	    user = new AuthorizationServiceMysqlImpl(
	    	new AuthorizationDaoMysqlImpl(),
	    	new PermissionDaoMySqlImpl())
	    	.getUserByLoginPassword("koloturka", "cepera");
	    System.out.println(user);
	} catch (NamingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}