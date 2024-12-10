package com.petrol.petrol_project.users;

public class DoctorPage {
    private String text;
    private String designation;
    private Class<?> activity;
    private int imageResourceId;


    // Constructor
    public DoctorPage(String text,String designation, Class<?> activity, int imageResourceId) {
        this.designation = designation;
        this.text = text;
        this.activity = activity;
        this.imageResourceId = imageResourceId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
