package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Topic database table.
 * 
 */
@Entity
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String content;

	private String image1;

	private String image2;

	private String title;

	//bi-directional many-to-one association to Reply
	@OneToMany(mappedBy="topic", fetch=FetchType.EAGER)
	private Set<Reply> replies;

	public Topic() {
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Reply addReply(Reply reply) {
		getReplies().add(reply);
		reply.setTopic(this);

		return reply;
	}

	public Reply removeReply(Reply reply) {
		getReplies().remove(reply);
		reply.setTopic(null);

		return reply;
	}

}