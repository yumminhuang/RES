package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Topic database table.
 * 
 */
@Entity
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String content;

	private String image1;

	private String image2;

	private String title;

	private int uid;

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

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}