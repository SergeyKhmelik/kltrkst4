package ua.nure.khmelik.SummaryTask4.service;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public interface AuthorizationService {

    User getUserByLoginPassword(String login, String password);

    ArrayList<Permission> getPermissionsByRole(int idRole);

}