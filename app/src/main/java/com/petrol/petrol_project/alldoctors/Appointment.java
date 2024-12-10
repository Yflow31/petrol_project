package com.petrol.petrol_project.alldoctors;

public class Appointment {
    String doctorname;
    String designation;
    String addressDetail;
    String DescriptionDetail;
    String Requestid;
    String Date;
    String time;

    public Appointment() {
    }

    public Appointment(String doctorname, String designation, String addressDetail, String descriptionDetail, String requestid, String date, String time) {
        this.doctorname = doctorname;
        this.designation = designation;
        this.addressDetail = addressDetail;
        this.DescriptionDetail = descriptionDetail;
        this.Requestid = requestid;
        this.Date = date;
        this.time = time;

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequestid() {
        return Requestid;
    }

    public void setRequestid(String requestid) {
        Requestid = requestid;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDescriptionDetail() {
        return DescriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        DescriptionDetail = descriptionDetail;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
