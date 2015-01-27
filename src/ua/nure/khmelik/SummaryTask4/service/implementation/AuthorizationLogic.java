package ua.nure.khmelik.SummaryTask4.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import ua.nure.khmelik.SummaryTask4.constants.Constants;
import ua.nure.khmelik.SummaryTask4.dao.AuthorizationDao;
import ua.nure.khmelik.SummaryTask4.dao.DaoFactory;
import ua.nure.khmelik.SummaryTask4.dao.mysql.ConnectionManager;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public class AuthorizationLogic {

	public User getUserByLoginPassword(String login, String password)
			throws SQLException, NamingException, ClassNotFoundException {

		User result = null;

		try (Connection conn = ConnectionManager.getConnection()) {
			conn.setAutoCommit(false);

			// Получение пользовательских айди (и айди роли)
			AuthorizationDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getAuthorizationDao();
			int[] userInfo = dao.getUserIdRoleId(conn, login, password);

			// В зависимости от роли, создать соответствующую сущность
			if (userInfo != null && userInfo[0] != 0) {
				switch (userInfo[1]) {
				case Constants.ROLE_ADMIN:
					result = dao.readAdmin(conn, userInfo[0]);
					break;
				case Constants.ROLE_STUDENT:
					result = dao.readStudent(conn, userInfo[0]);
					break;
				case Constants.ROLE_TEACHER:
					result = dao.readTeacher(conn, userInfo[0]);
					break;
				default:
					// Logging no role exception
					break;
				}
			} else {
				// Logging here (No such user)
				return null;
			}
			if (result != null) {
				result.setLogin(login);
				result.setPassword(password);
				result.setRoleId(userInfo[1]);
			}
			conn.commit();
			conn.setAutoCommit(true);
		}
		return result;
	}

	public ArrayList<Permission> getPermissionsByRole(int idRole)
			throws SQLException, ClassNotFoundException, NamingException {
		ArrayList<Permission> result = null;
		try (Connection conn = ConnectionManager.getConnection()) {
			conn.setAutoCommit(false);
			AuthorizationDao dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getAuthorizationDao();
			result = dao.getPermitionsByRole(conn, idRole);
			conn.commit();
			conn.setAutoCommit(true);
		}
		return result;
	}

}