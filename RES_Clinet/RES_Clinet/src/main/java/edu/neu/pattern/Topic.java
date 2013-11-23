package edu.neu.pattern;


import android.os.Parcel;
import android.os.Parcelable;

public class Topic implements Parcelable {

    private int id;

    private String content;

    private String image1;

    private String image2;

    private int postby;

    private String title;

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

    public int getPostby() {
        return this.postby;
    }

    public void setPostby(int postby) {
        this.postby = postby;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Topic ID:" + id).append("\nTitle:" + title).
                append("\nContent:" + content).append("\nPostby:" + postby);
        return sb.toString();
    }

    private Topic(Parcel in) {
        id = in.readInt();
        content = in.readString();
        image1 = in.readString();
        image2 = in.readString();
        postby = in.readInt();
        title = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(content);
        out.writeString(image1);
        out.writeString(image2);
        out.writeInt(postby);
        out.writeString(title);
    }

    public static final Parcelable.Creator<Topic> CREATOR
            = new Parcelable.Creator<Topic>() {

        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

}
