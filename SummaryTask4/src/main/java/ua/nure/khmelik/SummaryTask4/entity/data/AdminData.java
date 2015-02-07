package ua.nure.khmelik.SummaryTask4.entity.data;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.User;

public class AdminData extends UserData{
    
    private int phone;

    public AdminData(User user) {
	super(user);
    }
        
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
