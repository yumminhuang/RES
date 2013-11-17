package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PostTopic database table.
 * 
 */
@Entity
@NamedQuery(name="PostTopic.findAll", query="SELECT p FROM PostTopic p")
public class PostTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int post;

	private int postby;

	@Temporal(TemporalType.DATE)
	private Date topictime;

	public PostTopic() {
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

	public Date getTopictime() {
		return this.topictime;
	}

	public void setTopictime(Date topictime) {
		this.topictime = topictime;
	}

}