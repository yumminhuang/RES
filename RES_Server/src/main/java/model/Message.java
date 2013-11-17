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

	private int messagefrom;

	@Temporal(TemporalType.DATE)
	private Date messagetime;

	private int messageto;

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

	public int getMessagefrom() {
		return this.messagefrom;
	}

	public void setMessagefrom(int messagefrom) {
		this.messagefrom = messagefrom;
	}

	public Date getMessagetime() {
		return this.messagetime;
	}

	public void setMessagetime(Date messagetime) {
		this.messagetime = messagetime;
	}

	public int getMessageto() {
		return this.messageto;
	}

	public void setMessageto(int messageto) {
		this.messageto = messageto;
	}

}