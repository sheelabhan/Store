package com.sheela.mobilestore.model;

import java.io.Serializable;

public class Usercrud implements Serializable {
    private String firstName;
    private String lastName;
    //    private String email;
    private String username;
    private String phone;
    private String image;

    public Usercrud(String firstName, String lastName, String username, String phone, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
        this.image = image;
    }

    public Usercrud() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

