package pattern;

import org.joda.time.LocalDateTime;

public class Message {

	private int id;

	private String content;

	private int messagefrom;

	private LocalDateTime messagetime;

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

	public LocalDateTime getMessagetime() {
		return this.messagetime;
	}

	public void setMessagetime(LocalDateTime messagetime) {
		this.messagetime = messagetime;
	}

	public int getMessageto() {
		return this.messageto;
	}

	public void setMessageto(int messageto) {
		this.messageto = messageto;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + id + "\n")
		.append("content:" + content + "\n")
		.append("From:" + messagefrom + "\n")
		.append("To:" + messageto + "\n")
		.append("Time:" + messagetime + "\n");
		return sb.toString();
	}

}