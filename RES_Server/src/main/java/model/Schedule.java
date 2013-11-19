package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the Schedule database table.
 * 
 */
@Entity
@NamedQuery(name="Schedule.findAll", query="SELECT s FROM Schedule s")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	private Date scheduletime;

	//bi-directional many-to-one association to FromUser
	@ManyToOne
	@JoinColumn(name="schedulefrom")
	private FromUser fromUser;

	//bi-directional many-to-one association to ToUser
	@ManyToOne
	@JoinColumn(name="scheduleto")
	private ToUser toUser;

	public Schedule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getScheduletime() {
		return this.scheduletime;
	}

	public void setScheduletime(Date scheduletime) {
		this.scheduletime = scheduletime;
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