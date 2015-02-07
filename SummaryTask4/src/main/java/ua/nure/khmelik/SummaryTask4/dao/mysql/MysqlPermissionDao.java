package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Role;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;

public class MysqlPermissionDao implements PermissionDao {

    private final static Logger LOGGER = Logger
	    .getLogger(MysqlPermissionDao.class);

    private static final String FIND_PERMISSIONS = "SELECT * FROM permission";
    private static final String FIND_PERMITIONS_BY_ROLEID = "SELECT * FROM permission WHERE idpermission IN (SELECT idpermission FROM role_permission WHERE idrole=?)";
    private static final String ADD_PERMISSION_TO_ROLE = "INSERT INTO role_permission VALUES (?, ?)";
    private static final String REMOVE_PERMISSION_FROM_ROLE = "DELETE FROM role_permission WHERE idrole=? AND idpermission=?";
    private static final String FIND_ROLE_BY_NAME = "SELECT * FROM role WHERE name=?";
    private static final String FIND_ROLE_BY_ID = "SELECT * FROM role WHERE idrole=?";
    
    @Override
    public ArrayList<Permission> readPermissions(Connection conn)
	    throws SQLException {
	ArrayList<Permission> result = new ArrayList<Permission>();
	try (Statement stm = conn.createStatement()) {
	    ResultSet rs = stm.executeQuery(FIND_PERMISSIONS);
	    Permission currentPermission;
	    while (rs.next()) {
		currentPermission = new Permission();
		currentPermission.setId(rs.getInt(1));
		currentPermission.setName(rs.getString(2));
		currentPermission.setDescription(rs.getString(3));
		result.add(currentPermission);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read permission entities ", ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public ArrayList<Permission> readPermissions(Connection conn, int idRole)
	    throws SQLException {
	ArrayList<Permission> result = new ArrayList<Permission>();
	Permission currentPermission;
	try (PreparedStatement pstm = conn
		.prepareStatement(FIND_PERMITIONS_BY_ROLEID)) {
	    pstm.setInt(1, idRole);

	    ResultSet rs = pstm.executeQuery();
	    while (rs.next()) {
		currentPermission = new Permission();
		currentPermission.setId(rs.getInt(1));
		currentPermission.setName(rs.getString(2));
		currentPermission.setDescription(rs.getString(3));
		result.add(currentPermission);
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read permission entities where idRole="
		    + idRole, ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public int createRolePermission(Connection conn,
	    RolePermission rolePermission) throws SQLException {
	try (PreparedStatement pstm = conn
		.prepareStatement(ADD_PERMISSION_TO_ROLE)) {
	    pstm.setInt(1, rolePermission.getIdPermission());
	    pstm.setInt(2, rolePermission.getIdRole());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot create a role_permission ", ex);
	    throw ex;
	}
	return rolePermission.getIdPermission();
    }

    @Override
    public int deleteRolePermission(Connection conn,
	    RolePermission rolePermission) throws SQLException {
	try (PreparedStatement pstm = conn
		.prepareStatement(REMOVE_PERMISSION_FROM_ROLE)) {
	    pstm.setInt(1, rolePermission.getIdPermission());
	    pstm.setInt(2, rolePermission.getIdRole());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    LOGGER.error("Cannot delete role_permission with idRole="
		    + rolePermission.getIdRole() + " and idPermission="
		    + rolePermission.getIdPermission(), ex);
	    throw ex;
	}
	return rolePermission.getIdPermission();
    }

@Override
    public Role readRole(Connection conn, String roleName) throws SQLException {
	Role result = null;
	try (PreparedStatement pstm = conn.prepareStatement(FIND_ROLE_BY_NAME)) {
	    pstm.setString(1, roleName);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Role();
		result.setId(rs.getInt(1));
		result.setName(rs.getString(2));
		result.setDescription(rs.getString(3));
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read role with name " + roleName, ex);
	    throw ex;
	}
	return result;
    }

    @Override
    public Role readRole(Connection conn, int idRole) throws SQLException {
	Role result = null;
	try (PreparedStatement pstm = conn.prepareStatement(FIND_ROLE_BY_ID)) {
	    pstm.setInt(1, idRole);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
		result = new Role();
		result.setId(rs.getInt(1));
		result.setName(rs.getString(2));
		result.setDescription(rs.getString(3));
	    }
	} catch (SQLException ex) {
	    LOGGER.error("Cannot read role with id " + idRole, ex);
	    throw ex;
	}
	return result;
    }

}
