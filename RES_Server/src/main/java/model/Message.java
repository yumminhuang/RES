package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	private Date messagetime;

	//bi-directional many-to-one association to FromUser
	@ManyToOne
	@JoinColumn(name="messagefrom")
	private FromUser fromUser;

	//bi-directional many-to-one association to ToUser
	@ManyToOne
	@JoinColumn(name="messageto")
	private ToUser toUser;

	public Message() {
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

	public Date getMessagetime() {
		return this.messagetime;
	}

	public void setMessagetime(Date messagetime) {
		this.messagetime = messagetime;
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