package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Landord database table.
 * 
 */
@Entity
@NamedQuery(name="Landord.findAll", query="SELECT l FROM Landord l")
public class Landord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int landlordid;

	private int hasApartment;

	public Landord() {
	}

	public int getLandlordid() {
		return this.landlordid;
	}

	public void setLandlordid(int landlordid) {
		this.landlordid = landlordid;
	}

	public int getHasApartment() {
		return this.hasApartment;
	}

	public void setHasApartment(int hasApartment) {
		this.hasApartment = hasApartment;
	}

}