package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String email;

	private String name;

	private String phone;

	private String type;

	//bi-directional one-to-one association to ToUser
	@OneToOne(mappedBy="user")
	private ToUser toUser;

	//bi-directional one-to-one association to FromUser
	@OneToOne(mappedBy="user")
	private FromUser fromUser;

	public User() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ToUser getToUser() {
		return this.toUser;
	}

	public void setToUser(ToUser toUser) {
		this.toUser = toUser;
	}

	public FromUser getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(FromUser fromUser) {
		this.fromUser = fromUser;
	}

}