package ua.nure.khmelik.SummaryTask4.web.servlet;

public enum RoleName {
    
    ADMIN, TEACHER, STUDENT;
    
    public String getName(){
	return name().toLowerCase();
    }
    
}
