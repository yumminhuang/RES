package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the Reply database table.
 * 
 */
@Entity
@NamedQuery(name="Reply.findAll", query="SELECT r FROM Reply r")
public class Reply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Lob
	private String content;

	private String image1;

	private String image2;

	private int post;

	private int postby;

	@Temporal(TemporalType.DATE)
	private Date replytime;

	private int userId;

	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="topicId")
	private Topic topic1;

	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="topicId")
	private Topic topic2;

	public Reply() {
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

	public String getImage1() {
		return this.image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Topic getTopic1() {
		return this.topic1;
	}

	public void setTopic1(Topic topic1) {
		this.topic1 = topic1;
	}

	public Topic getTopic2() {
		return this.topic2;
	}

	public void setTopic2(Topic topic2) {
		this.topic2 = topic2;
	}

}