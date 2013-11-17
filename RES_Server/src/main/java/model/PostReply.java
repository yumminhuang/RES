package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PostReply database table.
 * 
 */
@Entity
@NamedQuery(name="PostReply.findAll", query="SELECT p FROM PostReply p")
public class PostReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int post;

	private int postby;

	@Temporal(TemporalType.DATE)
	private Date replytime;

	public PostReply() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPost() {
		return this.post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public int getPostby() {
		return this.postby;
	}

	public void setPostby(int postby) {
		this.postby = postby;
	}

	public Date getReplytime() {
		return this.replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

}