package edu.neu.pattern;

import java.util.Date;

public class Schedule{

	private int id;

	private String content;

	private int schedulefrom;

	private Date scheduletime;

	private int scheduleto;

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
