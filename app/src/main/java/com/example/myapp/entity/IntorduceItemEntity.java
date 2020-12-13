package com.example.myapp.entity;

public class IntorduceItemEntity{
    private int imageId;
    private String desc;
    private String title;
    private int index;

    public IntorduceItemEntity(int imageId,String title,String desc, int index) {
        this.imageId = imageId;
        this.desc = desc;
        this.title = title;
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
