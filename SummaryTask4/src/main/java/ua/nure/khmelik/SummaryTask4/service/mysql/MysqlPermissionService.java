package ua.nure.khmelik.SummaryTask4.service.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.data.RoleData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;
import ua.nure.khmelik.SummaryTask4.service.PermissionService;
import ua.nure.khmelik.SummaryTask4.util.Operation;
import ua.nure.khmelik.SummaryTask4.util.TransactionManager;

public class MysqlPermissionService implements PermissionService {

    private static final Logger LOGGER = Logger
	    .getLogger(MysqlPermissionService.class);

    private TransactionManager transactionManager;
    private PermissionDao permissionDao;

    public MysqlPermissionService(TransactionManager transactionManager,
	    PermissionDao permissionDao) {
	this.transactionManager = transactionManager;
	this.permissionDao = permissionDao;
    }

    @Override
    public ArrayList<Permission> getPermissions() throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Permission>>() {

		    @Override
		    public ArrayList<Permission> execute(Connection conn)
			    throws SQLException {
			return permissionDao.readPermissions(conn);
		    }
		});
    }

    @Override
    public ArrayList<Permission> getPermissions(final int idRole) throws SQLException {
	return transactionManager
		.doTransaction(new Operation<ArrayList<Permission>>() {

		    @Override
		    public ArrayList<Permission> execute(Connection conn)
			    throws SQLException {
			return permissionDao.readPermissions(conn, idRole);
		    }
		});
    }

    @Override
    public int addPermissionToRole(int idPermission, int idRole) throws SQLException {
	final RolePermission rolePermission;
	rolePermission = new RolePermission();
	rolePermission.setIdRole(idRole);
	rolePermission.setIdPermission(idPermission);

	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return permissionDao.createRolePermission(conn, rolePermission);
	    }
	}).intValue();
    }

    @Override
    public int removePermissionFromRole(int idPermission, int idRole) throws SQLException {
	final RolePermission rolePermission;
	rolePermission = new RolePermission();
	rolePermission.setIdRole(idRole);
	rolePermission.setIdPermission(idPermission);

	return transactionManager.doTransaction(new Operation<Integer>() {
	    @Override
	    public Integer execute(Connection conn) throws SQLException {
		return permissionDao.deleteRolePermission(conn, rolePermission);
	    }
	}).intValue();
    }

    @Override
    public ArrayList<RoleData> getRoles() throws SQLException {
	ArrayList<RoleData> result = transactionManager.doTransaction(new Operation<ArrayList<RoleData>>(){

	    @Override
	    public ArrayList<RoleData> execute(Connection conn)
		    throws SQLException {
		ArrayList<RoleData> result = new ArrayList<RoleData>();
		
		ArrayList<Role> roles = permissionDao.readRoles(conn);
		for(Role role: roles){
		    RoleData roleData = new RoleData();
		    roleData.setIdRole(role.getId());
		    roleData.setName(role.getName());
		    roleData.setDescription(role.getDescription());
		    roleData.setPermissions(permissionDao.readPermissions(conn, role.getId()));
		    roleData.setMissingPermissions(permissionDao.readMissingPermissions(conn,role.getId()));
		    result.add(roleData);
		}
		return result;
	    }
	});
	return result;
    }

}
