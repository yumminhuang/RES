package edu.neu.pattern;

import android.os.Parcel;
import android.os.Parcelable;

public class Apartment implements Parcelable {

    public Apartment() {
    }

    private int id;

    private String address;

    private int area;

    private String number;

    private int owner;

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

    public int getArea() {
        return this.area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + id + "\n")
                .append("Address:" + address + "\n")
                .append("Area:" + area + "\n")
                .append("number:" + number + "\n")
                .append("owner:" + owner + "\n");
        return sb.toString();
    }

    private Apartment(Parcel in) {
        id = in.readInt();
        address = in.readString();
        area = in.readInt();
        number = in.readString();
        owner = in.readInt();

    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeString(address);
        out.writeInt(area);
        out.writeString(number);
        out.writeInt(owner);
    }

    public static final Parcelable.Creator<Apartment> CREATOR
            = new Parcelable.Creator<Apartment>() {

        public Apartment createFromParcel(Parcel in) {
            return new Apartment(in);
        }

        public Apartment[] newArray(int size) {
            return new Apartment[size];
        }
    };
}