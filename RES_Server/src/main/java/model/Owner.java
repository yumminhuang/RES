package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Owner database table.
 * 
 */
@Entity
@NamedQuery(name="Owner.findAll", query="SELECT o FROM Owner o")
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int apartment;

	private int person;

	public Owner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApartment() {
		return this.apartment;
	}

	public void setApartment(int apartment) {
		this.apartment = apartment;
	}

	public int getPerson() {
		return this.person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

}