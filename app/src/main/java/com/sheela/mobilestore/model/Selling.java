package com.sheela.mobilestore.model;

public class Selling {
    public String name;
    public String location;
    public String  cost;
    public String image;

    public Selling(String name, String location, String cost, String image) {
        this.name = name;
        this.location = location;
        this.cost = cost;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
