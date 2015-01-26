package ua.nure.khmelik.SummaryTask4.entity.dbentities;

import java.io.Serializable;

public abstract class Entity implements Serializable{

	private static final long serialVersionUID = -414447302509675806L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
