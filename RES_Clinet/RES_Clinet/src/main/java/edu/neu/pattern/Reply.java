package edu.neu.pattern;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

public class Reply  implements Serializable {

    private int id;

    private String content;

    private String image1;

    private String image2;

    private LocalDateTime replytime;

    private int topicId;

    private int userId;

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

    public LocalDateTime getReplytime() {
        return this.replytime;
    }

    public void setReplytime(LocalDateTime replytime) {
        this.replytime = replytime;
    }

    public int getTopicId() {
        return this.topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + id + "\n")
                .append("content:" + content + "\n")
                .append("Topic:" + topicId + "\n")
                .append("User:" + userId + "\n")
                .append("Time:" + replytime + "\n");
        return sb.toString();
    }

}