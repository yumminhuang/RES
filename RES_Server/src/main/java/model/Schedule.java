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
	private int id;

	@Lob
	private String content;

	private int schedulefrom;

	@Temporal(TemporalType.DATE)
	private Date scheduletime;

	private int scheduleto;

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

	public int getSchedulefrom() {
		return this.schedulefrom;
	}

	public void setSchedulefrom(int schedulefrom) {
		this.schedulefrom = schedulefrom;
	}

	public Date getScheduletime() {
		return this.scheduletime;
	}

	public void setScheduletime(Date scheduletime) {
		this.scheduletime = scheduletime;
	}

	public int getScheduleto() {
		return this.scheduleto;
	}

	public void setScheduleto(int scheduleto) {
		this.scheduleto = scheduleto;
	}

}