package com.sheela.mobilestore.model;

public class Cartcrud {
    private String _id;
    private String image;
    private String username;
    private String product_name;
    private String cost;

    public Cartcrud() {
    }

    public Cartcrud(String _id, String image, String username, String product_name, String cost) {
        this._id = _id;
        this.image = image;
        this.username = username;
        this.product_name = product_name;
        this.cost = cost;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
