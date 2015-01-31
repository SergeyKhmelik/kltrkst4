package ua.nure.khmelik.SummaryTask4.service;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;

public interface PermissionService {

    ArrayList<Permission> getPermissions();
    
    ArrayList<Permission> getPermissions(int idRole);
    
    int addPermissionToRole(int idPermission, int idRole);

    int removePermissionFromRole(int idPermission, int idRole);
    
}
