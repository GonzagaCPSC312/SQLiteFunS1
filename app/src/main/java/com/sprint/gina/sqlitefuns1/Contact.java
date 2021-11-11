package com.sprint.gina.sqlitefuns1;

public class Contact {
    // table row id
    private long id;
    private String name;
    private String phoneNumber;
    // to use as a contact's photo
    private int imageResourceId;

    public Contact() {
        id = -1; // determined later by database table insertion order
        name = "Spike the Bulldog";
        phoneNumber = "509-509-5095";
        imageResourceId = -1; // to be filled in later
    }

    public Contact(String name, String phoneNumber) {
        this();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact(long id, String name, String phoneNumber, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageResourceId = imageResourceId;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}