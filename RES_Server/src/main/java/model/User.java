package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String address;

	private String email;

	private String name;

	private int telphone;

	private String type;

	//bi-directional many-to-one association to Apartment
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Apartment> apartments;

	//bi-directional many-to-one association to Topic
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Topic> topics;

	//bi-directional one-to-one association to FromUser
	@OneToOne
	@JoinColumn(name="id")
	private FromUser fromUser;

	//bi-directional one-to-one association to ToUser
	@OneToOne(mappedBy="user")
	private ToUser toUser;

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

	public int getTelphone() {
		return this.telphone;
	}

	public void setTelphone(int telphone) {
		this.telphone = telphone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Apartment> getApartments() {
		return this.apartments;
	}

	public void setApartments(Set<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Apartment addApartment(Apartment apartment) {
		getApartments().add(apartment);
		apartment.setUser(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setUser(null);

		return apartment;
	}

	public Set<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setUser(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setUser(null);

		return topic;
	}

	public FromUser getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(FromUser fromUser) {
		this.fromUser = fromUser;
	}

	public ToUser getToUser() {
		return this.toUser;
	}

	public void setToUser(ToUser toUser) {
		this.toUser = toUser;
	}

}