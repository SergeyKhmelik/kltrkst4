package ua.nure.khmelik.SummaryTask4.service;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface AuthorizationService {

    User getUserByLoginPassword(String login, String password);

}