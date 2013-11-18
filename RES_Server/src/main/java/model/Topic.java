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
	@OneToMany(mappedBy="topic1", fetch=FetchType.EAGER)
	private Set<Reply> replies1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="postby")
	private User user;

	//bi-directional many-to-one association to Reply
	@OneToMany(mappedBy="topic2", fetch=FetchType.EAGER)
	private Set<Reply> replies2;

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

	public Set<Reply> getReplies1() {
		return this.replies1;
	}

	public void setReplies1(Set<Reply> replies1) {
		this.replies1 = replies1;
	}

	public Reply addReplies1(Reply replies1) {
		getReplies1().add(replies1);
		replies1.setTopic1(this);

		return replies1;
	}

	public Reply removeReplies1(Reply replies1) {
		getReplies1().remove(replies1);
		replies1.setTopic1(null);

		return replies1;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Reply> getReplies2() {
		return this.replies2;
	}

	public void setReplies2(Set<Reply> replies2) {
		this.replies2 = replies2;
	}

	public Reply addReplies2(Reply replies2) {
		getReplies2().add(replies2);
		replies2.setTopic2(this);

		return replies2;
	}

	public Reply removeReplies2(Reply replies2) {
		getReplies2().remove(replies2);
		replies2.setTopic2(null);

		return replies2;
	}

}