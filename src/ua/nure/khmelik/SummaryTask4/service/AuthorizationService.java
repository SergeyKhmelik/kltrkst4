package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;

public interface AuthorizationService {

    User getUser(String login, String password) throws SQLException, NoSuchRoleException, NoSuchUserException;

}