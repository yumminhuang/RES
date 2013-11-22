package edu.neu.pattern;

public class Apartment {
	
	private int id;

	private String address;

	private double area;

	private String number;

	private int owner;

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

	public double getArea() {
		return this.area;
	}

	public void setArea(double area) {
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

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + id + "\n")
		.append("Address:" + address + "\n")
		.append("Area:" + area + "\n")
		.append("number:" + number + "\n")
		.append("owner:" + owner + "\n");
		return sb.toString();
	}
}