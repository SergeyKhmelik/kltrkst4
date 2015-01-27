package ua.nure.khmelik.SummaryTask4.exceptions;

public class NoSuchRoleException extends Exception {

    private static final long serialVersionUID = -8414968190664759165L;

    private int wrongRoleId;
    
    public NoSuchRoleException(int idRole) {
	this.wrongRoleId = idRole;
    }
    
    @Override
    public String getMessage() {
        return "Missing role id " + wrongRoleId;
    }
    
}
