package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
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
	ArrayList<Permission> permissions = null;
	try {
	    permissions = transactionManager
		    .doTransaction(new Operation<ArrayList<Permission>>() {

			@Override
			public ArrayList<Permission> execute(Connection conn)
				throws SQLException {
			    return permissionDao.getPermissions(conn);
			}
		    });

	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return permissions;
    }

    @Override
    public ArrayList<Permission> getPermissions(final int idRole) {
	ArrayList<Permission> permissions = null;
	try {
	    permissions = transactionManager
		    .doTransaction(new Operation<ArrayList<Permission>>() {

			@Override
			public ArrayList<Permission> execute(Connection conn)
				throws SQLException {
			    return permissionDao.getPermissions(conn, idRole);
			}
		    });

	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return permissions;
    }

    @Override
    public int addPermissionToRole(int idPermission, int idRole) {
	int result = 0;
	final RolePermission rolePermission;
	try {
	    rolePermission = new RolePermission();
	    rolePermission.setIdRole(idRole);
	    rolePermission.setIdPermission(idPermission);

	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return permissionDao.createRolePermission(conn,
			    rolePermission);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public int removePermissionFromRole(int idPermission, int idRole) {
	int result = 0;
	final RolePermission rolePermission;
	try {
	    rolePermission = new RolePermission();
	    rolePermission.setIdRole(idRole);
	    rolePermission.setIdPermission(idPermission);

	    result = transactionManager.doTransaction(new Operation<Integer>() {
		@Override
		public Integer execute(Connection conn) throws SQLException,
			NoSuchRoleException, NoSuchUserException {
		    return permissionDao.deleteRolePermission(conn,
			    rolePermission);
		}
	    }).intValue();
	} catch (NoSuchRoleException | NoSuchUserException e) {
	    // THERE IS NO SITUATION, WHEN THESE EXCEPTIONS CAN BE FORCED IN
	    // THIS METHOD...
	    e.printStackTrace();
	}
	return result;
    }

}
