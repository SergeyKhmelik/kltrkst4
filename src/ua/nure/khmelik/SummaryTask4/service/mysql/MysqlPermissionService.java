package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;

public class MysqlPermissionService implements PermissionService {

    @Override
    public ArrayList<Permission> getPermissions() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<Permission> getPermissions(int idRole) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int addPermissionToRole(int idPermission, int idRole) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int removePermissionFromRole(int idPermission, int idRole) {
	// TODO Auto-generated method stub
	return 0;
    }

}
