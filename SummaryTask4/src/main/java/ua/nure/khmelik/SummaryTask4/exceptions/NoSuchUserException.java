package ua.nure.khmelik.SummaryTask4.exceptions;

public class NoSuchUserException extends Exception {

    private static final long serialVersionUID = -6791530473282059272L;
    
    private static final String MESSAGE = "Wrong user name or password.";
    
    @Override
    public String getMessage() {
	return MESSAGE;
    }
    
}
