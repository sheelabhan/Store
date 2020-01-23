package com.sheela.mobilestore.model;

public class Contacts {
    private String Title;
    private String Content;
    private int imageId;

    public Contacts(String title, String content, int imageId) {
        Title = title;
        Content = content;
        this.imageId = imageId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
