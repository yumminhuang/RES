package pattern;

import java.util.Date;

public class Message {

	private int id;

	private String content;

	private int messagefrom;

	private Date messagetime;

	private int messageto;

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