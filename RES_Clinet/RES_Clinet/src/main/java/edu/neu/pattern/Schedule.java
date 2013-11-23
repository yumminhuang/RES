package edu.neu.pattern;

import android.os.Parcel;
import android.os.Parcelable;

public class Schedule implements Parcelable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String content;

    private int schedulefrom;

    private String scheduletime;

    private int scheduleto;

    public Schedule() {
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

    public int getSchedulefrom() {
        return this.schedulefrom;
    }

    public void setSchedulefrom(int schedulefrom) {
        this.schedulefrom = schedulefrom;
    }

    public String getScheduletime() {
        return this.scheduletime;
    }

    public void setScheduletime(String scheduletime) {
        this.scheduletime = scheduletime;
    }

    public int getScheduleto() {
        return this.scheduleto;
    }

    public void setScheduleto(int scheduleto) {
        this.scheduleto = scheduleto;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + id + "\n")
                .append("content:" + content + "\n")
                .append("From:" + schedulefrom + "\n")
                .append("To:" + scheduleto + "\n")
                .append("Time:" + scheduletime + "\n");
        return sb.toString();
    }

    private Schedule(Parcel in) {
        id = in.readInt();
        content = in.readString();
        schedulefrom = in.readInt();
        scheduleto = in.readInt();
        scheduletime = in.readString();

    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(content);
        out.writeInt(schedulefrom);
        out.writeInt(scheduleto);
        out.writeString(scheduletime);
    }

    public static final Parcelable.Creator<Schedule> CREATOR
            = new Parcelable.Creator<Schedule>() {

        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

}