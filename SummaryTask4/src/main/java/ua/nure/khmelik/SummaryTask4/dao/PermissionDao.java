package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;

public interface PermissionDao {

    ArrayList<Permission> readPermissions(Connection conn) throws SQLException;

    ArrayList<Permission> readPermissions(Connection conn, int idRole)
	    throws SQLException;

    int createRolePermission(Connection conn, RolePermission rolePermission)
	    throws SQLException;

    int deleteRolePermission(Connection conn, RolePermission rolePermission)
	    throws SQLException;

    Role readRole(Connection conn, String roleName) throws SQLException;

    Role readRole(Connection conn, int idRole) throws SQLException;
    
}
