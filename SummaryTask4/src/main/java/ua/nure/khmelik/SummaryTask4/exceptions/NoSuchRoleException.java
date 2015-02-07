package ua.nure.khmelik.SummaryTask4.exceptions;

public class NoSuchRoleException extends Exception {

    private static final long serialVersionUID = -8414968190664759165L;

    @Override
    public String getMessage() {
	return "There is no role with that name or id.";
    }
    
}
