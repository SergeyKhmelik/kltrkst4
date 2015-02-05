package ua.nure.khmelik.SummaryTask4.exceptions;

public class NoSuchRoleException extends Exception {

    private static final long serialVersionUID = -8414968190664759165L;

    private String wrongRoleName;
    private int wrongRoleId;
    
    public NoSuchRoleException(String roleName) {
	this.wrongRoleName = roleName;
    }
  
    public NoSuchRoleException(int wrongRoleId) {
	this.wrongRoleId = wrongRoleId;
    }

    @Override
    public String getMessage() {
	if(wrongRoleName!=null){	    
	    return "There is no role with name " + wrongRoleName;
	}
	return "There is no role with id " + wrongRoleId;
    }
    
}
