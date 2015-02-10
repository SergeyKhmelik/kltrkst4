package ua.nure.khmelik.SummaryTask4.entity.data;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Permission;

public class RoleData {

    private int idRole;
    private String name;
    private String description;
    private ArrayList<Permission> permissions;
    private ArrayList<Permission> missingPermissions;

    public int getIdRole() {
	return idRole;
    }

    public void setIdRole(int idRole) {
	this.idRole = idRole;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public ArrayList<Permission> getPermissions() {
	return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
	this.permissions = permissions;
    }

    public ArrayList<Permission> getMissingPermissions() {
	return missingPermissions;
    }

    public void setMissingPermissions(ArrayList<Permission> missingPermission) {
	this.missingPermissions = missingPermission;
    }

}
