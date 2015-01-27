package ua.nure.khmelik.SummaryTask4.exceptions;

public class NoSupportedDatabase extends Exception {

    private int factoryNumber;
   
    public NoSupportedDatabase(int factoryNumber) {
	super();
	this.factoryNumber = factoryNumber;
    }

    @Override
    public String getMessage() {
	return "There is not existing dao factory with index " + factoryNumber;
    }
    
}
