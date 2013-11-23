package edu.neu.pattern;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {

    private int id;

    private String address;

    private String email;

    private String name;

    private String phone;

    private String type;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String telephone) {
        this.phone = telephone;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + id + "\n")
                .append("name:" + name + "\n")
                .append("address:" + address + "\n")
                .append("tel:" + phone + "\n")
                .append("email:" + email + "\n")
                .append("type:" + type + "\n");
        return sb.toString();
    }

    private User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        email = in.readString();
        phone = in.readString();
        type = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(address);
        out.writeString(email);
        out.writeString(name);
        out.writeString(phone);
        out.writeString(type);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
