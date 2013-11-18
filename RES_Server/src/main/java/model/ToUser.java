package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


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

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="toUser", fetch=FetchType.EAGER)
	private Set<Message> messages;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="toUser", fetch=FetchType.EAGER)
	private Set<Schedule> schedules;

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

	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setToUser(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setToUser(null);

		return message;
	}

	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Schedule addSchedule(Schedule schedule) {
		getSchedules().add(schedule);
		schedule.setToUser(this);

		return schedule;
	}

	public Schedule removeSchedule(Schedule schedule) {
		getSchedules().remove(schedule);
		schedule.setToUser(null);

		return schedule;
	}

}