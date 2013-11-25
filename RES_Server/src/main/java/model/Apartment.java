package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Apartment database table.
 * 
 */
@Entity
@NamedQuery(name="Apartment.findAll", query="SELECT a FROM Apartment a")
public class Apartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private int area;

	private String number;

	private int owner;

	public Apartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getArea() {
		return this.area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getOwner() {
		return this.owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

}