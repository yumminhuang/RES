package pattern;

import org.joda.time.LocalDateTime;

public class Schedule{

	private int id;

	private String content;

	private int schedulefrom;

	private LocalDateTime scheduletime;

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

	public LocalDateTime getScheduletime() {
		return this.scheduletime;
	}

	public void setScheduletime(LocalDateTime scheduletime) {
		this.scheduletime = scheduletime;
	}

	public int getScheduleto() {
		return this.scheduleto;
	}

	public void setScheduleto(int scheduleto) {
		this.scheduleto = scheduleto;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("id:" + id + "\n")
		.append("content:" + content + "\n")
		.append("From:" + schedulefrom + "\n")
		.append("To:" + scheduleto + "\n")
		.append("Time:" + scheduletime + "\n");
		return sb.toString();
	}

}