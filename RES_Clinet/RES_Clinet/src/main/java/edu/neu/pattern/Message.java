package edu.neu.pattern;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {


    private int id;

    private String content;

    private int messagefrom;

    private String messagetime;

    private int messageto;

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

    public int getMessagefrom() {
        return this.messagefrom;
    }

    public void setMessagefrom(int messagefrom) {
        this.messagefrom = messagefrom;
    }

    public String getMessagetime() {
        return this.messagetime;
    }

    public void setMessagetime(String messagetime) {
        this.messagetime = messagetime;
    }

    public int getMessageto() {
        return this.messageto;
    }

    public void setMessageto(int messageto) {
        this.messageto = messageto;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + id + "\n")
                .append("content:" + content + "\n")
                .append("From:" + messagefrom + "\n")
                .append("To:" + messageto + "\n")
                .append("Time:" + messagetime + "\n");
        return sb.toString();
    }

    public Message() {
    }

    private Message(Parcel in) {
        id = in.readInt();
        content = in.readString();
        messagefrom = in.readInt();
        messageto = in.readInt();
        messagetime = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(content);
        out.writeInt(messagefrom);
        out.writeInt(messageto);
        out.writeString(messagetime);
    }

    public static final Parcelable.Creator<Message> CREATOR
            = new Parcelable.Creator<Message>() {

        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

}
