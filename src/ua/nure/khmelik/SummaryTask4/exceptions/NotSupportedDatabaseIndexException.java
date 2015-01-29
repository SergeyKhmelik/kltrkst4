package ua.nure.khmelik.SummaryTask4.exceptions;

public class NotSupportedDatabaseIndexException extends RuntimeException {

    private static final long serialVersionUID = 7550978185536276544L;
    
    private int factoryNumber;
   
    public NotSupportedDatabaseIndexException(int factoryNumber) {
	super();
	this.factoryNumber = factoryNumber;
    }

    @Override
    public String getMessage() {
	return "There is not existing dao factory with index " + factoryNumber;
    }
    
}
