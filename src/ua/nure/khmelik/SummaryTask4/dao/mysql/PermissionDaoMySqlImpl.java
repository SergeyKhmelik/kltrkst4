package ua.nure.khmelik.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.dao.PermissionDao;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.RolePermission;

public class PermissionDaoMySqlImpl implements PermissionDao {

    private static final String FIND_PERMISSIONS = "";
    private static final String ADD_PERMISSION_TO_ROLE = "";
    private static final String REMOVE_PERMISSION_FROM_ROLE = "";

    @Override
    public ArrayList<Permission> getPermissions(Connection conn)
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
	    //Logging
	    throw ex;
	}
	return result;
    }

    @Override
    public int createRolePermission(Connection conn, RolePermission rp)
	    throws SQLException {
	try (PreparedStatement pstm = conn
		.prepareStatement(ADD_PERMISSION_TO_ROLE)) {
	    pstm.setInt(1, rp.getIdPermition());
	    pstm.setInt(2, rp.getIdRole());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return rp.getIdPermition();
    }

    @Override
    public int removeRolePermission(Connection conn, RolePermission rp)
	    throws SQLException {
	try (PreparedStatement pstm = conn
		.prepareStatement(REMOVE_PERMISSION_FROM_ROLE)) {
	    pstm.setInt(1, rp.getIdPermition());
	    pstm.setInt(2, rp.getIdRole());
	    pstm.executeUpdate();
	} catch (SQLException ex) {
	    // Logging
	    throw ex;
	}
	return rp.getIdPermition();
    }

}
