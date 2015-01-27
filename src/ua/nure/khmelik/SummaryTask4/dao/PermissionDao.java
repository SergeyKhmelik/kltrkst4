package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;

public interface PermissionDao {

    ArrayList<Permission> getPermissions(Connection conn) throws SQLException;
    
    int createRolePermission(Connection conn, RolePermission rolePermission) throws SQLException;
    
    int removeRolePermission(Connection conn, RolePermission rolePermission) throws SQLException;

    ArrayList<Permission> getPermitionsByRole(Connection conn, int idRole)
	    throws SQLException;
    
}
