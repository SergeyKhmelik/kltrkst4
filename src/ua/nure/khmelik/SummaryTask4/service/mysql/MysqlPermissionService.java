package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlPermissionService implements PermissionService {

    private TransactionManager transactionManager;
    private PermissionDao permissionDao;

    public MysqlPermissionService(TransactionManager transactionManager,
	    PermissionDao permissionDao) {
	super();
	this.transactionManager = transactionManager;
	this.permissionDao = permissionDao;
    }

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
