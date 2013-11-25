package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ToUser database table.
 * 
 */
@Entity
@NamedQuery(name="ToUser.findAll", query="SELECT t FROM ToUser t")
public class ToUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="id")
	private User user;

	public ToUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}