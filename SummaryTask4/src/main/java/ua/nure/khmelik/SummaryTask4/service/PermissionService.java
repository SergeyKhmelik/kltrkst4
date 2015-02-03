package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;

public interface PermissionService {

    ArrayList<Permission> getPermissions() throws SQLException;
    
    ArrayList<Permission> getPermissions(int idRole) throws SQLException;
    
    int addPermissionToRole(int idPermission, int idRole) throws SQLException;

    int removePermissionFromRole(int idPermission, int idRole) throws SQLException;
    
}
