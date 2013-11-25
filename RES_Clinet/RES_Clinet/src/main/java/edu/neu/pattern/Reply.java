package edu.neu.pattern;

import android.os.Parcel;
import android.os.Parcelable;

public class Reply implements Parcelable {

    private int id;

    private String content;

    private String image1;

    private String image2;

    private String replytime;

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

    public String getReplytime() {
        return this.replytime;
    }

    public void setReplytime(String replytime) {
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

    public Reply() {
    }

    private Reply(Parcel in) {
        id = in.readInt();
        topicId = in.readInt();
        userId = in.readInt();
        content = in.readString();
        replytime = in.readString();
        image1 = in.readString();
        image2 = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeInt(userId);
        out.writeInt(topicId);
        out.writeString(content);
        out.writeString(replytime);
        out.writeString(image1);
        out.writeString(image2);
    }

    public static final Parcelable.Creator<Reply> CREATOR
            = new Parcelable.Creator<Reply>() {

        public Reply createFromParcel(Parcel in) {
            return new Reply(in);
        }

        public Reply[] newArray(int size) {
            return new Reply[size];
        }
    };

}
