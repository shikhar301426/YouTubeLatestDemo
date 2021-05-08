package com.example.mayank.youtubelatestdemo;
import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {

    private int id;
    private String name;
    private String dateOfBirth;
    private String salary;
    private String status;

    public Employee() {
        super();
    }

    private Employee(Parcel in) {
        super();
        this.id = in.readInt();
        this.name = in.readString();
        this.dateOfBirth = in.readString();
        this.salary = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServerName() {
        return name;
    }

    public void setServerName(String name) {
        this.name = name;
    }

    public String getIPAddr() {
        return dateOfBirth;
    }

    public void setIPAddr(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPortNum() {
        return salary;
    }

    public String getServerStatus() {
        return status;
    }

    public void setServerStatus(String status) {
        this.status = status;
    }

    public void setPortNum(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", dateOfBirth="
                + dateOfBirth + ", salary=" + salary + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getServerName());
        parcel.writeString(getIPAddr());
        parcel.writeString("PORT");
        parcel.writeString(getPortNum());
        parcel.writeString(getServerStatus());
    }

    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

}