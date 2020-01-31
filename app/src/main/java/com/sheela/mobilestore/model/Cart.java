package com.sheela.mobilestore.model;

public class Cart {
    private String image;
    private String username;
    private String product_name;
    private String cost;

    public Cart(String username, String product_name, String cost,String image) {
        this.username = username;
        this.product_name = product_name;
        this.cost = cost;
        this.image=image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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
