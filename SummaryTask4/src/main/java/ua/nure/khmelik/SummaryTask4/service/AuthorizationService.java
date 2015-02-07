package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.entity.data.UserData;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;

public interface AuthorizationService {

    UserData getUser(String login, String password) throws SQLException, NoSuchRoleException, NoSuchUserException;

}